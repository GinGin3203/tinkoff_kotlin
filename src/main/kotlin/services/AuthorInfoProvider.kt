package services

import domain_entities.AuthorInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay

class AuthorInfoProvider {

    companion object {
        val data = listOf(
            AuthorInfo("Dostoevsky", 1821),
            AuthorInfo("Gogol", 1809),
            AuthorInfo("Tolstoy", 1828),
            AuthorInfo("Chekhov", 1860),
            AuthorInfo("Bulgakov", 1891)
        )

        fun getAllData() = data

        fun generateEvents() = GlobalScope.produce {
            Thread.currentThread().name = "ProducingThread"
            for (entry in data) {
                println("${Thread.currentThread().name} is sending")
                delay(1000L)
                send(entry)
            }
        }

    }
}
