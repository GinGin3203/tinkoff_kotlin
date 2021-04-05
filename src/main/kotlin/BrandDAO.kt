package main.kotlin

class BrandDAO {
    private val storage = listOf(
        Brand("Todd Shelton", 1350, "USA"),
        Brand("Gucci", 198, "Italy"),
        Brand("YSL", 1020, "France"),
        Brand("Sputnik 1985", 294, "Russia"),
        Brand("D&G", 1904, "Italy")
    )

    fun getAllData(): List<Brand> = storage

    fun getLatestModelId(latestModelId: Int): Brand? = storage.firstOrNull { it.latestModelId == latestModelId }
}
