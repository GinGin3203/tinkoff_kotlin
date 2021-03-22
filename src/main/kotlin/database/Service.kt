package database


class Service(private val connection: Connection) {

    fun selectFirst(tableName: String) = connection.selectById(tableName, 1, '=')

    fun selectAllWhichHaveLargerId(tableName: String, id: Int) = connection.selectById(tableName, id, '>')
}