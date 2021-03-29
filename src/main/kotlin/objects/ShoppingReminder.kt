package objects

import java.time.LocalDate

class ShoppingReminder() {
    var toBuy: List<String> = listOf()
    var shoppingDate: LocalDate = LocalDate.now()

    fun remind(supermarket: String) = buildString {
        this.append("$shoppingDate you need to go shopping at $supermarket and buy")
        val iter = toBuy.iterator()
        while (iter.hasNext()) {
            this.append(" ${iter.next()}")
            if (iter.hasNext())
                this.append(",")
        }
    }
}

fun shoppingReminder(block: ShoppingReminder.() -> Unit) = ShoppingReminder().apply(block)