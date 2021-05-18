package me.ginzburggeorgy.gamesinfoservice.integration

import me.ginzburggeorgy.gamesinfoservice.model.Company
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class CompanyIntegrationComponent {
    private val webClient = WebClient.create("http://localhost:8081")

    fun getCompanyById(id: Int): Company? {
        return webClient
            .get()
            .uri("/company/$id")
            .retrieve()
            .bodyToMono(Company::class.java)
            .block()
    }
}