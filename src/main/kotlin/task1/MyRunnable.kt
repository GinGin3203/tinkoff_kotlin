package main.thread_creation

class MyRunnable : Runnable {
    public override fun run() {
        println("${Thread.currentThread()} has run (was called by instance of MyRunnable)")
    }
}
