package main

import java.util.*

class Queue<T> {
    private val storage = MySinglyLinkedList<T>()

    fun enqueue(element: T) = storage.insertAtTail(element)

    fun dequeue(): T? = storage.removeFromHead()

    override fun toString(): String = storage.toString()
}

fun <T> queueOf(vararg elems: T): Queue<T> {
    val q = Queue<T>()
    for (elem in elems)
        q.enqueue(elem)

    return q
}
