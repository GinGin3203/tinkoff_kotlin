package me.ginzburggeorgy.gamesinfoservice.api

import io.swagger.annotations.ApiOperation
import me.ginzburggeorgy.gamesinfoservice.dao.GamesDao
import me.ginzburggeorgy.gamesinfoservice.exceptions.EntryNotFoundException
import me.ginzburggeorgy.gamesinfoservice.integration.CompanyIntegrationComponent
import me.ginzburggeorgy.gamesinfoservice.model.GameInfo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gamesinfo")
class GamesInfoController {
    private val gamesDao = GamesDao()
    private val companyIntegrator = CompanyIntegrationComponent()

    @ApiOperation("Вывести весь список игр")
    @GetMapping
    fun listAllGames(): List<GameInfo> = gamesDao.getAll().map {
        GameInfo(it, companyIntegrator.getCompanyById(it.companyId)!!)
    }

    @ApiOperation("Найти игру по ID")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): GameInfo =
        gamesDao.getById(id)
            ?.let { game -> companyIntegrator.getCompanyById(game.companyId)?.let { GameInfo(game, it) } }
            ?: throw EntryNotFoundException()

    @ApiOperation("Добавить студента")
    @PostMapping
    fun addGameInfo(@RequestBody gameInfo: GameInfo) = println("Added new game info: $gameInfo")


    @ApiOperation("Отредактировать запись")
    @PutMapping("/{id}")
    fun editGameInfoById(@PathVariable id: Int, @RequestBody gameInfo: GameInfo) {
        println("Edited game info: $id. New value: $gameInfo")
    }

    @ApiOperation("Удалить запись")
    @DeleteMapping("/{id}")
    fun deleteGameInfoById(@PathVariable id: Int) {
        println("Deleted game info: $id")
    }
}