package main.task3


import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun comparePoolsizes() {
    val measurements = mutableListOf<Pair<Int, Long>>()
    val poolSizes = listOf(10, 20, 30)

    for (size in poolSizes) {
        val pool = Executors.newFixedThreadPool(size)
        val startTime = System.nanoTime()
        var cnt = 0

        repeat(size) {
            pool.submit {
                synchronized(cnt) {
                    while (cnt < 1_000_000)
                        cnt++
                }
            }
        }
        pool.shutdown()
        measurements.add(Pair<Int, Long>(size, System.nanoTime() - startTime))
    }
    measurements.sortedBy { it.second }.forEach {
        println(
            "POOLSIZE: ${it.first} EXECUTION TIME: ${
                TimeUnit.MILLISECONDS.convert(
                    it.second,
                    TimeUnit.NANOSECONDS
                )
            } ns"
        )
    }
}