package main

class SportPantsDAO {
    private val storage = listOf(
            SportPants("store_1", "cotton", false),
            SportPants("store_2", "polyesther", true),
            SportPants("store_3", "cotton", true),
            SportPants("store_4", "synthetic", false)
    )

    fun getAllData(): List<SportPants> = storage

    fun getByStore(store: String): SportPants? = storage.firstOrNull { it.store == store }
}
