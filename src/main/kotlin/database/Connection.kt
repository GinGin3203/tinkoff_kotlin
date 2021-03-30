package database

import domain_entities.AllClasses
import domain_entities.TableClasses
import domain_entities.TableContents
import org.sqlite.SQLiteException
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

data class Connection(val db_url: String) {

    // Если вдруг кто-то еще будет компилить этот код:
    // для использования use с AutoCloseable нужно подключить либу в build.gradle,
    // см https://youtrack.jetbrains.com/issue/KT-41507

    data class Response(val className: AllClasses, val data: Map<String, Any?>)


    private fun handleSyntaxError(block: () -> PreparedStatement): PreparedStatement? =
        try {
            block.invoke()
        } catch (e: SQLiteException) {
            println(e.errorCode)
            val syntaxError = e.message?.contains("syntax error")
            if (syntaxError == null || !syntaxError)
                throw e
            System.err.println("Syntax error!!!")
            null
        }

    fun initializeTables() {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            for (contentList in TableContents.tableData) {
                // Без создания и заполнения таблиц мы не сможем совершать никакие дальнейшие операции, поэтому,
                // наверное, не стоит ловить исключения в этом методе
                var stat = it.createStatement()
                stat.execute(ScriptsManager.generateTableCreationScript(contentList.first()))
                stat = it.createStatement()
                val changedRowsNum = stat.executeUpdate(ScriptsManager.generateInsertScript(contentList))
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

    fun mySelectById(tableName: TableClasses, id: Int, condition: Char): List<Response>? {

        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.selectByIdScriptTemplate.format(tableName, condition)
            val rs = handleSyntaxError {
                val ps = it.prepareStatement(sql)
                ps.setInt(1, id)
                ps
            }?.executeQuery() ?: return null
            return buildResponseList(rs)
        }
    }

    fun myJoin(joinType: JoinType, leftTable: LeftTable): List<Response>? {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = if (leftTable == LeftTable.Ide) ScriptsManager.joinIdeAndTextEditorScript.format(joinType) else
                ScriptsManager.joinTextEditorAndIdeScript.format(joinType)
            val rs = handleSyntaxError {
                it.prepareStatement(sql)
            }?.executeQuery() ?: return null
            return buildResponseList(rs, AllClasses.IdeTextEditorJoined)
        }
    }

    fun myGroupBy(): List<Response>? {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.groupTextEditorAndIdeJoined
            val rs = handleSyntaxError { it.prepareStatement(sql) }?.executeQuery() ?: return null
            return buildResponseList(rs, AllClasses.IdeTextEditorGrouped)
        }
    }

    fun mySelectAndOrderDesc(): List<Response>? {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.selectMediaViewerAndOrderByRank
            val rs = handleSyntaxError { it.prepareStatement(sql) }?.executeQuery() ?: return null
            return buildResponseList(rs)
        }
    }

    fun doSyntaxError(): List<Response>? {
        val conn = DriverManager.getConnection(db_url)
        conn.use {
            val sql = ScriptsManager.syntaxErrorProducingScript
            val rs = handleSyntaxError { it.prepareStatement(sql) }?.executeQuery() ?: return null
            return buildResponseList(rs)
        }
    }


    fun clean() {
        val conn = DriverManager.getConnection(db_url)

        conn.use {
            for (sql in ScriptsManager.dropTablesScripts) {
                val rs = handleSyntaxError {
                    it.prepareStatement(sql)
                }?.execute()
                if (rs == null)
                    System.err.println("A table has not dropped")
            }
        }
        println("DATABASE CLEANUP FINISHED")
    }
}

