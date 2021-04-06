import domain_entities.AuthorAndBooks
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import services.AuthorInfoProvider
import services.AvailableBooksProvider

fun getConcurrentlyAndJoin() = runBlocking {

    val startTime = System.currentTimeMillis()
    val authorsDef = async {
        delay(1000L)
        AuthorInfoProvider.getAllData()
    }
    val booksDef = async {
        delay(1000L)
        AvailableBooksProvider.getAllData()
    }

    val authors = authorsDef.await()
    val books = booksDef.await()

    val endTime = System.currentTimeMillis()
    val joinedData = authors.mapNotNull { author ->
        books.firstOrNull { it.authorName == author.name }?.let { authorBooksData ->
            AuthorAndBooks(
                author.name, author.yearOfBirth, authorBooksData.books
            )
        }
    }

    println(
        "Concurrent execution should take about ~1000ms. Actual time: ${endTime - startTime}"
    )
    println(joinedData)
}

