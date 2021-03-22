package database

import domain_entities.TableContents
import java.sql.DriverManager
import java.sql.ResultSet

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
            val rs = conn.getMetaData().getTables(null, null, null, null)
            while (rs.next()) {
                println(rs.getString("TABLE_NAME"));
            }
        }
    }

    fun selectById(tableName: String, id: Int): ResultSet {

        val conn = DriverManager.getConnection(db_url)

        conn.use {
            val sql = ScriptsManager.selectByIdScriptTemplate.replace(14, 16, tableName)
            val ps = it.prepareStatement(sql.toString())
            ps.setInt(1, id)
            return ps.executeQuery()
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
