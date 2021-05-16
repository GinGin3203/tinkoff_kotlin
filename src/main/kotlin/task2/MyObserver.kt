package main.task2

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

interface Observer {
    fun onObservableChanged(newVal: AtomicInteger)
}

class MyObserver : Runnable, Observer {
    val updates = ConcurrentLinkedQueue<Int>()

    override fun onObservableChanged(newVal: AtomicInteger) {
        updates.add(newVal.get())
    }

    override fun run() {
        // После секунды без апдейтов выходим
        // Вообще было бы круто обойтись без поллинга,
        // но я не знаю как
        val startTime = System.currentTimeMillis()
        while ((System.currentTimeMillis() - startTime) < 1000) {
            if (updates.isNotEmpty()) {
                println("${Thread.currentThread().name} is now active and reading ${updates.poll()}")
            }
        }
    }
}