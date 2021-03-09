package main

class FancyTrousersDAO {
    private val storage = listOf(
            FancyTrousers("store_1", "giorgio armani", "blue"),
            FancyTrousers("store_2", "giorgio armani", "black"),
            FancyTrousers("store_3", "gucci", "white"),
            FancyTrousers("store_4", "gucci", "blue")
    )

    fun getAllData(): List<FancyTrousers> = storage

    fun getByStore(store: String): FancyTrousers = storage.single { it.store == store }
}
