package main.thread_creation


class MyThread : Thread() {
    public override fun run() {
        println("${Thread.currentThread()} has run (was called by instance of MyThread)")
    }
}
