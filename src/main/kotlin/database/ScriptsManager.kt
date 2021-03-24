package database

import domain_entities.Application
import kotlin.reflect.full.declaredMemberProperties

enum class LeftTable {
    Ide, TextEditor
}

enum class JoinType {
    INNER, LEFT
}

abstract class ScriptsManager {

    companion object {

        /*
         * Сделал генерацию через Reflection API настолько, насколько это было уместно (ы). Было сложно, но зато я познакомился с
         * Reflection API :)
         */

        fun <T : Application> generateTableCreationScript(classObj: T) = buildString {
            this.append("create table if not exists ${classObj::class.simpleName} (\n")
            this.append("id integer primary key autoincrement,\n")

            val propIterator = getPropIterator(classObj)
            while (propIterator.hasNext()) {
                val prop = propIterator.next()
                this.append(prop.name)
                /*
                    Давайте для простоты считать все поля классов строками. Я знаю, что это нехорошо,
                    но пока давайте так
                 */
                this.append(" text")
                this.append(if (propIterator.hasNext()) ",\n" else "\n);")
            }
        }

        fun <T : Application> generateInsertScript(items: List<T>) = buildString {
            this.append("insert into ${items.first()::class.simpleName}(")
            var propIterator = getPropIterator(items.first())
            while (propIterator.hasNext()) {
                val prop = propIterator.next()
                this.append(prop.name)
                this.append(if (propIterator.hasNext()) ", " else ")")
            }
            this.append(" values")

            val itemIterator = items.iterator()
            while (itemIterator.hasNext()) {
                this.append(" (")
                val item = itemIterator.next()
                propIterator = getPropIterator(item)
                while (propIterator.hasNext()) {
                    val prop = propIterator.next()
                    this.append("\"${prop.getter.call(item)}\"")
                    this.append(if (propIterator.hasNext()) ", " else ")")
                }

                this.append(if (itemIterator.hasNext()) ", " else ";")
            }
        }

        /**
        Мб не очень здорово заставлять порядок колонок зависеть от порядка названий полей, но джава не дает никаких
        гарантий о порядке полей в классе
         **/
        private fun <T : Application> getPropIterator(classObj: T) =
            classObj::class.declaredMemberProperties.sortedBy { it.name }.iterator()

        val joinTextEditorAndIdeScript: String =
            "select * from TextEditor %s join Ide on TextEditor.platform = Ide.platform"

        val joinIdeAndTextEditorScript: String =
            "select * from Ide %s join TextEditor on TextEditor.platform = Ide.platform"

        const val selectByIdScriptTemplate: String = "select * from %s where id %c ?"

        val groupTextEditorAndIdeJoined = "select TextEditor.platform as platform, " +
                "sum(case when Ide.isOpenSource = 'true' Then 1 else 0 end) " +
                "as countIsOpenSource, max(cast(TextEditor.yearOfRelease as integer)) as maxYearOfRelease " +
                "from Ide inner join TextEditor on TextEditor.platform = Ide.platform group by Ide.platform;"

        val selectMediaViewerAndOrderByRank= "select * from MediaViewer order by cast(rankByReviews as integer) desc;"

        val dropTablesScripts = listOf("drop table Ide;\n", "drop table TextEditor;\n", "drop table MediaViewer;")
    }
}
