package me.ginzburggeorgy.dockerfirst

import me.ginzburggeorgy.dockerfirst.model.MyEntity
import me.ginzburggeorgy.dockerfirst.model.OptionalDataPack
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import java.sql.DriverManager
import java.time.Duration

@RestController
class MainController {
    private val dbUrl = System.getProperty("dbUrl")
    private val username = System.getProperty("username")
    private val password = System.getProperty("password")

    private val optionalRequestTimeoutMs: Long = 1000

    val baseUrl = "jdbc:postgresql://$dbUrl"
    val webClient = WebClient.create("http://optional-service:8080")

    @GetMapping("/main")
    fun getMainData(): ResponseEntity<MyEntity> {
        val connection = DriverManager.getConnection(baseUrl, username, password)
        val statement = connection.createStatement()
        val rs = statement.executeQuery("SELECT * FROM main;")
        val resp = MyEntity()
        while (rs.next()) {
            resp.necessaryData = rs.getString("data")
        }

        try {
            val optionalData = webClient
                .get()
                .uri("/optional")
                .retrieve()
                .bodyToMono(OptionalDataPack::class.java)
                .timeout(Duration.ofMillis(optionalRequestTimeoutMs))
                .block()
                ?.data
            resp.optionalData = optionalData
        } catch (e: Exception) {
        }
        return ResponseEntity.ok(resp)
    }
}

