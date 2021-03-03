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

        length++
    }

    fun removeFromHead(): T? {
        if (length < 1)
            return null

        length--
        val retVal = head!!.nodeData
        head = head!!.next
        return retVal
    }

    override fun toString(): String = buildString {
        this.append("HEAD [")
        var temp = head
        while (temp != null) {
            this.append(" " + temp.nodeData)
            temp = temp.next
        }
        this.append(" ] TAIL")
    }

    private data class Node<T>(val nodeData: T, var next: Node<T>? = null)
}