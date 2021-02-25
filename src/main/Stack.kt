package main

import java.util.*

class Stack<T> {
    private val storage: LinkedList<T> = LinkedList()

    fun push(element: T) {
        storage.addLast(element)
    }

    fun pop(): T {
        return storage.removeLast()
    }

    override fun toString(): String {
        if (storage.isEmpty())
            return storage.toString()
        return "HEAD " + storage.toString().replace(",", " ->")
    }
}

fun <T> stackOf(vararg elems: T): Stack<T> {
    val st = Stack<T>()
    for (elem in elems)
        st.push(elem)

    return st
}
