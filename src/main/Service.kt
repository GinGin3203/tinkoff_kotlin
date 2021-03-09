package main

import java.util.function.Predicate

class Service {
    fun getSportPants(dao: SportPantsDAO) = dao.getAllData()

    fun getFancyTrousers(dao: FancyTrousersDAO) = dao.getAllData()

    fun getPants(sportPantsDAO: SportPantsDAO,
                 fancyTrousersDAO: FancyTrousersDAO): List<Pants> =
            getSportPants(sportPantsDAO).map {
                val obj2 = fancyTrousersDAO.getByStore(it.store)
                Pants(it.store, it.material, it.coldResistant, obj2.brand, obj2.color)
            }

    fun sortPantsByColor(pants: List<Pants>) = pants.sortedBy { it.color }

    fun groupPantsByMaterial(pants: List<Pants>) = pants.groupBy { it.material }

    fun getNumByCondition(pants: List<Pants>, pred: (T: Pants) -> Boolean) = pants.filter { pred(it) }.size
}