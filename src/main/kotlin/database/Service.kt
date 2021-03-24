package database

import domain_entities.Application
import domain_entities.TableClasses
import domain_entities.applicationOf


class Service(private val connection: Connection) {

    fun selectFirst(tableName: TableClasses): Application =
        applicationOf(connection.mySelectById(tableName, 1, '=').first())

    fun selectAllWhichHaveLargerId(tableName: TableClasses, id: Int): List<Application> =
        connection.mySelectById(tableName, id, '>').map { applicationOf(it) }

    // Кто в названии метода стоит слева, тот является левой таблицей при joinType=LEfT
    fun joinTextEditorAndIdeOnPlatform(joinType: JoinType): List<Application> =
        connection.myJoin(joinType, LeftTable.TextEditor).map { applicationOf(it) }

    fun joinIdeAndTextEditorOnPlatform(joinType: JoinType): List<Application> =
        connection.myJoin(joinType, LeftTable.Ide).map { applicationOf(it) }

    fun groupTextEditorAndIdeByPlatform(): List<Application> = connection.myGroupBy().map { applicationOf(it) }

}