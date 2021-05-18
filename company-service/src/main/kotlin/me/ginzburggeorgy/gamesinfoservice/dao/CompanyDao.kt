package me.ginzburggeorgy.gamesinfoservice.dao

import me.ginzburggeorgy.gamesinfoservice.model.Company

class CompanyDao {
    private val storage = listOf(
        Company("Blizzard", "USA", 1),
        Company("Ubisoft", "France", 2),
        Company("Microsoft", "USA", 3),
        Company("Nintendo", "Japan", 4)
    )

    fun getById(id: Int): Company? = storage.firstOrNull {
        it.companyId == id
    }
}