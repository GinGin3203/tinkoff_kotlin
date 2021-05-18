package me.ginzburggeorgy.gamesinfoservice.dao

import me.ginzburggeorgy.gamesinfoservice.model.Game

class GamesDao {
    private val storage = listOf(
        Game(1, "Warcraft 3", 2002, 1),
        Game(2, "Sniper Elite", 2005, 2),
        Game(3, "Mario 64", 1996, 4),
        Game(5, "Halo 2", 2004, 3),
        Game(6, "The Legend of Zelda", 1981, 4),
        Game(4, "Assassin's Creed", 2007, 2),
    )

    fun getAll() = storage

    fun getById(id: Int) = storage.firstOrNull {
        it.gameId == id
    }

}