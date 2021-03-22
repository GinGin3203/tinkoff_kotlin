package database

import domain_entities.Application
import kotlin.reflect.full.declaredMemberProperties

abstract class ScriptsManager {

    companion object {

        /*
         * Не стал писать скрипты, а сделал генерацию через Reflection API. Было сложно, но зато я познакомился с
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

        const val selectByIdScriptTemplate: String = "select * from %s where id %c ?"

        /**
        Мб не очень здорово заставлять порядок колонок зависеть от порядка названий полей, но джава не дает никаких
        гарантий о порядке полей в классе
         **/
        private fun <T : Application> getPropIterator(classObj: T) =
            classObj::class.declaredMemberProperties.sortedBy { it.name }.iterator()

        val dropTablesScripts = listOf("drop table IDE;\n", "drop table TextEditor;\n", "drop table MediaViewer;")
    }
}
