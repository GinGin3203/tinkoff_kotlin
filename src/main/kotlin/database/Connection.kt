package database

import domain_entities.*
import java.lang.IllegalArgumentException
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Types

data class Connection(val db_url: String) {

    // Если вдруг кто-то еще будет компилить этот код:
    // для использования use с AutoCloseable нужно подключить либу в build.gradle,
    // см https://youtrack.jetbrains.com/issue/KT-41507

    fun initializeTables() {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            for (contentList in TableContents.tableData) {
                var stat = it.createStatement()
                stat.execute(ScriptsManager.generateTableCreationScript(contentList.first()))
                stat = it.createStatement()
                val changedRowsNum = stat.executeUpdate(ScriptsManager.generateInsertScript(contentList))

                if (changedRowsNum != contentList.size) {
                    println("SOMETHING WENT WRONG!!!!!!!!!!")
                    break
                }
            }
            println("TABLES CREATED")
            val rs = conn.metaData.getTables(null, null, null, null)
            while (rs.next()) {
                println(rs.getString("TABLE_NAME"))
            }
        }
    }

    private fun buildRawResponseFromResultSet(rs: ResultSet): List<Map<String, Any?>> {
        val retList = ArrayList<HashMap<String, Any?>>()
        val md = rs.metaData
        while (rs.next()) {
            val resMap = HashMap<String, Any?>()
            for (i in 1..md.columnCount) {
                val type: Int = md.getColumnType(i)
                if (type == Types.VARCHAR || type == Types.CHAR) {
                    resMap[md.getColumnName(i)] = rs.getString(i)
                } else {
                    resMap[md.getColumnName(i)] = rs.getInt(i)
                }
            }
            retList.add(resMap)
        }
        return retList
    }

    fun selectById(tableName: String, id: Int, condition: Char): List<Map<String, Any?>> {

        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.selectByIdScriptTemplate.format(tableName, condition)
            val ps = it.prepareStatement(sql)
            ps.setInt(1, id)
            val rs = ps.executeQuery()
            return buildRawResponseFromResultSet(rs)
        }
    }


    private enum class JoinType {
        INNER, LEFT
    }

    private enum class LeftTable {
        IDE, TEXTEDITOR
    }

    private fun convertJoinType(joinType: String) =
        try {
            val jt = JoinType.valueOf(joinType)
            jt
        } catch (e: IllegalArgumentException) {
            println("joinType argument is incorrect. Available values: INNER or LEFT")
            null
        }

    fun join(joinType: String, leftTable: String): List<Map<String, Any?>>? {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val lt = LeftTable.valueOf(leftTable)
            val jt = convertJoinType(joinType) ?: return null
            val sql = if (lt == LeftTable.IDE) ScriptsManager.joinIdeAndTextEditorScript.format(jt) else
                ScriptsManager.joinTextEditorAndIdeScript.format(jt)
            val rs = it.prepareStatement(sql).executeQuery()
            return buildRawResponseFromResultSet(rs)
        }
    }

    fun clean() {
        val conn = DriverManager.getConnection(db_url)

        conn.use {
            for (sql in ScriptsManager.dropTablesScripts) {
                it.prepareStatement(sql).execute()
            }
        }
        println("DATABASE CLEAN")
    }
}

