package main

import java.util.*

class Stack<T> {
    private val storage = MySinglyLinkedList<T>()

    fun push(element: T) = storage.insertAtHead(element)

    fun pop(): T? = storage.removeFromHead()

    override fun toString(): String = storage.toString()
}

fun <T> stackOf(vararg elems: T): Stack<T> {
    val st = Stack<T>()
    for (elem in elems)
        st.push(elem)

    return st
}
