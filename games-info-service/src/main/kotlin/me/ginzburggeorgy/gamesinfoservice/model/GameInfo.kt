package me.ginzburggeorgy.gamesinfoservice.model

data class GameInfo(
    val game: Game,
    val company: Company
) {
    constructor(
        gameId: Int,
        name: String,
        yearOfRelease: Int,
        companyId: Int,
        companyName: String,
        companyCountry: String,
    ) : this(
        Game(
            gameId,
            name,
            yearOfRelease,
            companyId
        ),
        Company(
            companyName,
            companyCountry,
            companyId
        )
    )
}