import kotlinx.coroutines.runBlocking
import services.SubscriptionService

fun printSubscriptions() = runBlocking {
    val startTime = System.currentTimeMillis()
    SubscriptionService().subscribe()
    val endTime = System.currentTimeMillis()
    println()
    println(
        "Producing service contains 5 items and sends with 1s delay. Receiving service" +
                " also consumes with 1s delay.\nAs they are active in different coroutines, total time should be ~5s.\n" +
                "Actual time: ${endTime - startTime}"
    )
}