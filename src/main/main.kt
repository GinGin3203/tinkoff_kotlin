package main

fun main() {
    val queue = Queue<Int>()
    val stack = Stack<Int>()

    // Basic functionality

    queue.enqueue(1)
    queue.enqueue(2)

    stack.push(3)
    stack.push(4)
    println(queue)
    println(stack)

    queue.dequeue()
    println(queue)

    stack.pop()

    println(stack)

    // DSL for classes
    var stack_2 = stackOf("dog", "cat", "giraffe")
    println(stack_2)
    stack_2 = stackOf("elephant")
    println(stack_2)

    var queue_2 = queueOf("dog", "cat", "giraffe")
    println(queue_2)
    queue_2 = queueOf("elephant")
    println(queue_2)

    // toString for empty container
    queue_2.dequeue()
    println(queue_2)
}