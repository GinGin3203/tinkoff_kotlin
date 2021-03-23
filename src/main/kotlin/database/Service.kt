package database


class Service(private val connection: Connection) {

    fun selectFirst(tableName: String) = connection.selectById(tableName, 1, '=').first()

    fun selectAllWhichHaveLargerId(tableName: String, id: Int) = connection.selectById(tableName, id, '>')

    enum class JoinType {
        INNER, LEFT
    }

//    fun textEditorAndIdeJoin(joinType: String) = connection.join(JoinType.valueOf(joinType))
//    fun ideAndTextEditorJoin(joinType: String) = connection.join(JoinType.valueOf(joinType))
}