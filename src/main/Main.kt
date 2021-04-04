package main

fun main() {
    val spDAO = SportPantsDAO()
    val ftDAO = FancyTrousersDAO()

    // DAO demo
    println("All SportPantsDAO data: " + spDAO.getAllData())
    println()
    println("Get by store_42: " + spDAO.getByStore("store_42"))
    println()

    // Service demo
    val service = Service()
    val pants = service.getPants(spDAO, ftDAO)
    println("Merging data source objects result: " + pants)
    println()
    println("Sorted by store: " + service.sortPantsByStore(pants))
    println()
    println("Grouped by material: " + service.groupPantsByMaterial(pants))
    println()
    println("Counting by condition: " + service.getNumByCondition(pants) { it.coldResistant })
    println()
    println("Counting by another condition: " + service.getNumByCondition(pants) { it.brand == "gucci" })


}
