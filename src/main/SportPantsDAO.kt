package main

class SportPantsDAO {
    private val storage = listOf(
            SportPants("store_4", "cotton", false),
            SportPants("store_5", "polyesther", true),
            SportPants("store_2", "cotton", true),
            SportPants("store_9", "synthetic", false),
            SportPants("store_8", "synthetic", true),
            SportPants("store_20", "cotton", false),
            SportPants("store_42", "textile", false),
    )

    fun getAllData(): List<SportPants> = storage

    fun getByStore(store: String): SportPants? = storage.firstOrNull { it.store == store }
}
