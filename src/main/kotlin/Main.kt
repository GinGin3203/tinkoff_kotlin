import database.Service

fun main() {
    val conn = database.Connection("jdbc:sqlite:my_database.sqlite")
    conn.initializeTables()
    val service = Service(conn)
    println(service.selectFirst("IDE"))
    println(service.selectAllWhichHaveLargerId("MediaViewer", 2))
    conn.clean()

}