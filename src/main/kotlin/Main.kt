import domain_entities.AuthorAndBooks
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import services.AuthorInfoProvider
import services.AvailableBooksProvider

fun main() = runBlocking {

    val authorsDef = async { AuthorInfoProvider.getAllData() }
    val booksDef = async { AvailableBooksProvider.getAllData() }

    val authors = authorsDef.await()
    val books = booksDef.await()

    val joinedData = authors.mapNotNull { author ->
        books.firstOrNull { it.authorName == author.name }?.let { authorBooksData ->
            AuthorAndBooks(
                author.name, author.yearOfBirth, authorBooksData.books
            )
        }
    }

    println(joinedData)
}
