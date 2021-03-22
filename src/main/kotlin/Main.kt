import database.Service
import java.sql.DriverManager

fun main() {
    val conn = database.Connection("jdbc:sqlite:my_database.sqlite")
    conn.initializeTables()
    val service = Service(conn)
    service.selectFirst("IDE")
    conn.clean()
//    println(TablesManager.generateTableCreationScript(TableContents.tableData["ides"]!!.first()))
//    println(TablesManager.generateInsertScript(TableContents.tableData.first()))

    }