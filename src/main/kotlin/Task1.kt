import domain_entities.AuthorAndBooks
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import services.AuthorInfoProvider
import services.AvailableBooksProvider

fun getConcurrentlyAndJoin() = runBlocking {

    val authorsDef = async { AuthorInfoProvider.getAllData() }
    val booksDef = async { AvailableBooksProvider.getAllData() }

    val joinedData = authorsDef.await().mapNotNull { author ->
        booksDef.await().firstOrNull { it.authorName == author.name }?.let { authorBooksData ->
            AuthorAndBooks(
                author.name, author.yearOfBirth, authorBooksData.books
            )
        }
    }
    println(joinedData)
}

