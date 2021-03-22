package database

import java.sql.Types

class Service(val connection: database.Connection) {

    fun selectFirst(tableName: String) = connection.selectById(tableName, 1)
}