package task2

import main.task2.Observer
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicInteger


class MyObservable(var data: AtomicInteger) {
    private val observers = CopyOnWriteArraySet<Observer>()

    fun addObserver(t: Observer) = observers.add(t)

    fun update(task: AtomicInteger.() -> Unit) {
        synchronized(data as Any) {
            data.task()
            for (observer in observers) {
                observer.onObservableChanged(data.get())
            }
        }
    }
}