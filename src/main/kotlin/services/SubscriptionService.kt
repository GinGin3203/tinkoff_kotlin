package services

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class SubscriptionService {
    suspend fun subscribe() = coroutineScope {
        val dataChannel = async {
            AuthorInfoProvider.generateEvents()
        }


        val resIter = dataChannel.await().iterator()
        while (resIter.hasNext()) {
            delay(1000L)
            println("${Thread.currentThread().name} is receiving")
            println("RECEIVED: ${resIter.next()}")
        }
    }
}