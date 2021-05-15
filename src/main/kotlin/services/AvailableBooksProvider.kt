package services

import domain_entities.AuthorBooksData


class AvailableBooksProvider {
    companion object {
        val data = listOf(
            AuthorBooksData("Tolstoy", listOf("War and Peace", "Anna Karenina", "Hadji Murat")),
            AuthorBooksData("Dostoevsky", listOf("Crime and Punishment", "The Gambler", "The Idiot")),
            AuthorBooksData("Bulgakov", listOf("The Master and Margarita", "Heart of a Dog"))
        )

        fun getAllData() = data
    }
}