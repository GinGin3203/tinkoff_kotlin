package main

import main.thread_creation.MyRunnable
import main.thread_creation.MyThread
import kotlin.concurrent.thread

fun createThreadsInDifferentWays() {
    val t1 = MyThread().start()
    val t2 = MyRunnable().run()
    val t3 =
        Thread {
            println("${Thread.currentThread()} has run (was called by instance of Thread instantiated with lambda)")
            Thread.sleep(1000L)
        }.start()

    val t4 = thread {
        println("${Thread.currentThread()} has run (was called by instance created by DSL)")
        Thread.sleep(1000L)
    }
}

fun createThreadsWithDifferentPriorities() {
    // Можно было, конечно, и через Thread
    val t1 =
        thread(
            priority = 6,
            start = false
        ) { println("${Thread.currentThread()} has priority ${Thread.currentThread().priority}") }

    val t2 = Thread { println("${Thread.currentThread()} has priority ${Thread.currentThread().priority}") }
    t2.priority = 10

    t1.start()
    t2.start()
}

fun createDaemonThread() {
    val t = thread(isDaemon = true) {
        println("Is ${Thread.currentThread()} a daemon thread? ${Thread.currentThread().isDaemon}")
    }
}

