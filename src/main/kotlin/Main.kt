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
    var resList = service.selectAllWhichHaveLargerId(TableClasses.MediaViewer, 2)
    println(resList)
    println(resList?.size)
    println()
   
    println("SYNTAX ERROR HANDLING")
    System.err.println(conn.doSyntaxError())

    println("INNER JOIN ON IDE AND TEXT EDITOR TABLES")
    resList = service.joinIdeAndTextEditorOnPlatform(JoinType.INNER)
    println(resList)
    println(resList?.size)
    println()
    println("LEFT JOIN WHERE LEFT IS TEXT EDITOR")
    resList = service.joinTextEditorAndIdeOnPlatform(JoinType.LEFT)
    println(resList)
    println(resList?.size)
    println()
    println("LEFT JOIN WHERE LEFT IS IDE")
    resList = service.joinIdeAndTextEditorOnPlatform(JoinType.LEFT)
    println(resList)
    println(resList?.size)
    println()
    println("IDE AND TEXT EDITOR GROUPED BY PLATFORM AND COUNTED OPEN SOURCE APPS AND MAX YEAR OF RELEASE")
    resList = service.groupTextEditorAndIdeByPlatform()
    println(resList)
    println(resList?.size)
    println()
    println("SELECT MEDIA VIEWERS AND SORT BY RANK IN DESCENDING ORDER")
    resList = service.selectMediaViewersAndOrderByRankDesc()
    println(resList)
    println(resList?.size)



    conn.clean()
}