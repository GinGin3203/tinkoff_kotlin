package main

import java.util.function.Predicate

class Service {
    fun getSportPants(dao: SportPantsDAO) = dao.getAllData()

    fun getFancyTrousers(dao: FancyTrousersDAO) = dao.getAllData()

    /*  Такая реализация позволяет джойнить объекты классов 1 и 2 даже если их ключи
        совпадают не 1 к 1
     */
    fun getPants(sportPantsDAO: SportPantsDAO,
                 fancyTrousersDAO: FancyTrousersDAO): List<Pants> =
            getSportPants(sportPantsDAO).mapNotNull {
                val ft: FancyTrousers? = fancyTrousersDAO.getByStore(it.store)
                if (ft != null)
                    Pants(it.store, it.material, it.coldResistant, ft.brand, ft.color)
                else
                    null
            }

    fun sortPantsByColor(pants: List<Pants>) = pants.sortedBy { it.color }

    fun groupPantsByMaterial(pants: List<Pants>) = pants.groupBy { it.material }

    fun getNumByCondition(pants: List<Pants>, pred: (T: Pants) -> Boolean) = pants.filter { pred(it) }.size
}