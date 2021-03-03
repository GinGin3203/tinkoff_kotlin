package main

class MySinglyLinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var length: Int = 0

    fun insertAtTail(elem: T) {
        if (tail == null || head == null) {
            head = Node(elem)
            tail = head
        } else {
            val newNode = Node(elem)
            tail!!.next = newNode
            tail = newNode
        }
        length++
    }

    fun insertAtHead(elem: T) {
        if (tail == null || head == null) {
            head = Node(elem)
            tail = head
        } else {
            val newNode = Node(elem)
            newNode.next = head
            head = newNode
        }

        length++;
    }

    fun removeFromHead(): T? {
        if (length < 1)
            return null

        length--
        val retVal = head!!.nodeData
        head = head!!.next
        return retVal
    }

    override fun toString(): String {
        val sb = StringBuilder("HEAD [")
        var temp = head
        while (temp != null) {
            sb.append(" " + temp.nodeData)
            temp = temp.next
        }
        sb.append(" ] TAIL")
        return sb.toString()
    }

    private data class Node<T>(val nodeData: T, var next: Node<T>? = null)
}