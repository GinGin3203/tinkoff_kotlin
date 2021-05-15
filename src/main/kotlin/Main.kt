package main.kotlin

fun main() {
    val pantsDao = PantsDAO()
    val brandDao = BrandDAO()
    // DAO demo
    println("All PantsDAO data: " + pantsDao.getAllData())
    println()
    println("Get by modelId 198: " + pantsDao.getByModelId(198))
    println()
    println("Get brand by latestModelId: " + brandDao.getLatestModelId(1020))
    println()

    // Service demo
    val service = Service(pantsDao, brandDao)
    println("Merging data source objects: " + service.getBrandedPants())
    println()
    println("Sorted by modelId: " + service.sortPantsByModelId())
    println()
    println("Grouped by color: " + service.groupPantsByColor())
    println()
    println("Counting by condition {color is black}: " + service.getNumByCondition() { it.color == "black" })
    println()
    println("Counting by another condition {material is cotton}: " + service.getNumByCondition() { it.material == "cotton" })


}
