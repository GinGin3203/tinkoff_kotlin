package main

import java.util.*

class Queue<T> {
    private val storage: LinkedList<T> = LinkedList()

    fun enqueue(element: T) {
        storage.addLast(element)
    }

    fun dequeue(): T {
        return storage.removeFirst()
    }

    override fun toString(): String {
        if (storage.isEmpty())
            return storage.toString()
        return "HEAD " + storage.toString().replace(",", " ->")
    }
}

fun <T> queueOf(vararg elems: T): Queue<T> {
    val q = Queue<T>()
    for (elem in elems)
        q.enqueue(elem)

    return q
}
