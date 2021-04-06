package services

import domain_entities.AuthorInfo

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
    }
}