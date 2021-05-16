package task2

import main.task2.Observer
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicInteger


class MyObservable(var data: AtomicInteger) {
    private val observers = CopyOnWriteArraySet<Observer>()
    private val observerQueue = ConcurrentLinkedQueue<Observer>()

    fun addObserver(t: Observer) = observerQueue.add(t)

    fun update(execute: AtomicInteger.() -> Unit) {
        synchronized(data as Any) {
            data.execute()

            // Чтобы два потока не читали одно и то же изменение
            observerQueue.poll().also {
                it.onObservableChanged(data)
            }.apply {
                observerQueue.add(this)
            }
        }
    }
}