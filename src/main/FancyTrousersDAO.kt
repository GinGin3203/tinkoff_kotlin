package main

class FancyTrousersDAO {
    private val storage = listOf(
            FancyTrousers("store_9", "giorgio armani", "blue"),
            FancyTrousers("store_8", "giorgio armani", "black"),
            FancyTrousers("store_5", "gucci", "white"),
            FancyTrousers("store_2", "gucci", "blue"),
            FancyTrousers("store_15", "h&m", "green"),
    )

    fun getAllData(): List<FancyTrousers> = storage

    fun getByStore(store: String): FancyTrousers? = storage.firstOrNull { it.store == store }
}
