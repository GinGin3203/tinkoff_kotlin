package database

import java.sql.Types

class Service(val connection: database.Connection) {

    fun selectFirst(tableName: String) {
        val rs = connection.selectById(tableName, 1)
        val md = rs.metaData
        while(rs.next()) {
            for (i in 1..md.columnCount) {
                val type: Int = md.getColumnType(i)
                if (type == Types.VARCHAR || type == Types.CHAR) {
                    println(rs.getString(i))
                } else {
                    println(rs.getInt(i))
                }
            }
        }
    }
}