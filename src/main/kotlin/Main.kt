package main

import main.task2.readByMultipleThreads
import main.task3.comparePoolsizes
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {

    println("TASK 1")
    println()
    println("CREATING THREADS")
    println()
    thread {
        println("JUST WAYS TO CREATE THREADS")
        createThreadsInDifferentWays()
    }.join()

    thread {
        println()
        println("CREATING THREADS WITH DIFFERENT PRIORITIES")
        createThreadsWithDifferentPriorities()
    }.join()

    thread {
        println()
        println("CREATING A DAEMON THREAD")
        createDaemonThread()
    }.join()

    thread {
        println()
        println("TASK 2")
        readByMultipleThreads(AtomicInteger(0))
        Thread.sleep(100L)
    }.join()

    thread {
        println()
        println("TASK 3")
        comparePoolsizes()
    }.join()
}

