package me.ginzburggeorgy.gamesinfoservice.api

import me.ginzburggeorgy.gamesinfoservice.dao.CompanyDao
import me.ginzburggeorgy.gamesinfoservice.exceptions.EntryNotFoundException
import me.ginzburggeorgy.gamesinfoservice.model.Company
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/company")
class CompanyController {
    val companyDao = CompanyDao()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): Company? = companyDao.getById(id) ?: throw EntryNotFoundException()
}