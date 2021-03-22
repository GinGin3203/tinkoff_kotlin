package database


class Service(val connection: database.Connection) {

    fun selectFirst(tableName: String) = connection.selectById(tableName, 1, '=')

    fun selectAllWhichHaveLargerId(tableName: String, id: Int) = connection.selectById(tableName, id, '>')
}