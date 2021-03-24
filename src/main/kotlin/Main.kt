import database.JoinType
import database.Service
import domain_entities.TableClasses

fun main() {
    val conn = database.Connection("jdbc:sqlite:my_database.sqlite")
    conn.initializeTables()
    val service = Service(conn)
    println()
    println("SELECTING FIRST OF IDE")
    println(service.selectFirst(TableClasses.Ide))
    println()
    println("SELECTING FIRST OF TEXTEDITOR")
    println(service.selectFirst(TableClasses.TextEditor))
    println()
    println("SELECTING FROM MEDIAVIEWER WHERE ID > 2")
    println(service.selectAllWhichHaveLargerId(TableClasses.MediaViewer, 2))
    println()
    println("INNER JOIN ON IDE AND TEXT EDITOR TABLES")
    println(service.ideAndTextEditorJoin(JoinType.INNER))
    println()
    println("LEFT JOIN WHERE LEFT IS TEXT EDITOR")
    println(service.textEditorAndIdeJoin(JoinType.LEFT))
    println()
    println("LEFT JOIN WHERE LEFT IS IDE")
    println(service.ideAndTextEditorJoin(JoinType.LEFT))
    conn.clean()

}