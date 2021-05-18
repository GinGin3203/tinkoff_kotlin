package me.ginzburggeorgy.gamesinfoservice.model

data class Game(
    val gameId: Int,
    val name: String,
    val yearOfRelease: Int,
    val companyId: Int
)