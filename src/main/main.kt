package main

fun main() {
    val queue = Queue<Int>()
    val stack = Stack<Int>()

    // Basic functionality
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)


    stack.push(4)
    stack.push(5)
    stack.push(6)
    println(queue)
    println(stack)

    queue.dequeue()
    println(queue)

    stack.pop()

    println(stack)

    // DSL for classes
    var stackFromDSL = stackOf("dog", "cat", "giraffe")
    println(stackFromDSL)
    stackFromDSL = stackOf("elephant")
    println(stackFromDSL)

    var queueFromDSL = queueOf("dog", "cat", "giraffe")
    println(queueFromDSL)
    queueFromDSL = queueOf("elephant")
    println(queueFromDSL)

    // toString for empty container
    queueFromDSL.dequeue()
    println(queueFromDSL)
}
