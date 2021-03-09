package main

fun main() {
    val spDAO = SportPantsDAO()
    val ftDAO = FancyTrousersDAO()

    val service = Service()
    val pants = service.getPants(spDAO, ftDAO)
    println("Merging data source objects result: " + pants)
    println()
    println("Sorted by color: " + service.sortPantsByColor(pants))
    println()
    println("Grouped by material: " + service.groupPantsByMaterial(pants))
    println()
    println("Counting by condition: " + service.getNumByCondition(pants) { it -> it.coldResistant })
    println()
    println("Counting by another condition: " + service.getNumByCondition(pants) { it -> it.brand == "gucci" })


}
