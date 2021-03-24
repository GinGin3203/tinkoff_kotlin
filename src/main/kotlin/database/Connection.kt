package database

import domain_entities.*
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Types

data class Connection(val db_url: String) {

    // Если вдруг кто-то еще будет компилить этот код:
    // для использования use с AutoCloseable нужно подключить либу в build.gradle,
    // см https://youtrack.jetbrains.com/issue/KT-41507

    data class Response(val className: AllClasses, val data: Map<String, Any?>)

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

    private fun buildResponseList(
        rs: ResultSet,
        className: AllClasses = AllClasses.valueOf(rs.metaData.getTableName(1))
    ): List<Response> {
        val retList = ArrayList<Response>()
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
            retList.add(Response(className, resMap))
        }
        return retList
    }

    fun mySelectById(tableName: TableClasses, id: Int, condition: Char): List<Response> {

        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.selectByIdScriptTemplate.format(tableName, condition)
            val ps = it.prepareStatement(sql)
            ps.setInt(1, id)
            val rs = ps.executeQuery()
            return buildResponseList(rs)
        }
    }

    fun myJoin(joinType: JoinType, leftTable: LeftTable): List<Response> {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = if (leftTable == LeftTable.Ide) ScriptsManager.joinIdeAndTextEditorScript.format(joinType) else
                ScriptsManager.joinTextEditorAndIdeScript.format(joinType)
            val rs = it.prepareStatement(sql).executeQuery()
            return buildResponseList(rs, AllClasses.IdeTextEditorJoined)
        }
    }

    fun myGroupBy(): List<Response> {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.groupTextEditorAndIdeJoined
            val rs = it.prepareStatement(sql).executeQuery()
            return buildResponseList(rs, AllClasses.IdeTextEditorGrouped)
        }
    }

    fun mySelectAndOrderDesc(): List<Response> {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.selectMediaViewerAndOrderByRank
            val rs = it.prepareStatement(sql).executeQuery()
            return buildResponseList(rs)
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

