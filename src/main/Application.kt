package main

import kotlin.random.Random

abstract class Application {
    protected abstract val name: String
    protected abstract val applicationType: String
    protected abstract val purpose: String
    protected abstract val features: HashSet<String>

    open fun about() = println(getDescription())

    fun getDescription(): String = "Hi! My name is $name, I am $applicationType. My primary purpose is $purpose."

    fun feature(feature: String) = if (feature !in features) println(
            "Sorry, I don't have this feature :(")
    else println("Yep, I can do that! $feature is one of the best things about me!")

    fun feature() = println("My features include but are not limited to: $features")
}
