package main.task2


import task2.MyObservable
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread


// Гуглинг похожих задач говорил, что такая штука делается паттерном Listener (Observer)
// Ну что я и сделал
fun readByMultipleThreads(value: AtomicInteger) {
    val obs = MyObservable(value)

    repeat(3) { i ->
        val tempObserver = MyObserver()
        obs.addObserver(tempObserver)
        Thread(tempObserver).also {
            it.name = "OBSERVER-$i"
            it.start()
        }
    }

    val writingThread = thread(name = "WRITER") {
        for (i in 1..10) {
            println("${Thread.currentThread().name} updating value")
            obs.update { incrementAndGet() }
        }
    }

}
