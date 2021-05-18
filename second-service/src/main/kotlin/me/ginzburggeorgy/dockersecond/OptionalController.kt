package me.ginzburggeorgy.dockersecond

import me.ginzburggeorgy.dockersecond.model.OptionalDataPack
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.DriverManager

@RestController
class OptionalController {
    private val dbUrl = System.getProperty("dbUrl")
    private val username = System.getProperty("username")
    private val password = System.getProperty("password")

    val baseUrl = "jdbc:mysql://$dbUrl"

    @GetMapping("/optional")
    fun supplyOptional(): ResponseEntity<OptionalDataPack> {
        val connection = DriverManager.getConnection(baseUrl, username, password)
        val statement = connection.createStatement()
        val rs = statement.executeQuery("SELECT * FROM optional;")
        var data: String? = null
        while (rs.next()) {
            data = rs.getString("data")
            break
        }
        return ResponseEntity.ok(OptionalDataPack(data))
    }
}