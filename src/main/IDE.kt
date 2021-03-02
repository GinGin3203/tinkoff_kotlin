package main

class IDE(override val name: String, private val primaryLang: String, override val features: HashSet<String>) :
        Application() {
    override val applicationType: String = "IDE"
    override val purpose: String = "coding"

    override fun about() = println(getDescription() +
            " I was created primarily for the $primaryLang programming language.")

    fun showAnnoyingError() {
        val randomErrorCode = getRandomString((3..5).random())
        println("An annoying error occurred in your code! Error code: " +
                "${primaryLang.toUpperCase()}_ERROR_0x$randomErrorCode")
    }

    private fun getRandomString(string_length: Int): String {
        val allowedChars = ('A'..'F') + ('0'..'9')
        return (1..string_length)
                .map { allowedChars.random() }
                .joinToString("")
    }
}
