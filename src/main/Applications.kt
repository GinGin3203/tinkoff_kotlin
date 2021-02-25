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

class TextEditor(override val name: String, override val features: HashSet<String>) : Application() {
    override val applicationType: String = "text editor"
    override val purpose: String = "editing text"

    fun makeCoolEdit(text: String): String = text.reversed()

}

class MediaGallery(
        override val name: String,
        private val supportedFileFormat: String,
        override val features: HashSet<String>
) : Application() {
    override val applicationType: String = "media gallery"
    override val purpose: String = "viewing media"
    private val seed: Int = 198274

    override fun about() = println(getDescription() +
            " I only support files in $supportedFileFormat format")


    fun shufflePeopleOnPhoto(people: List<String>): List<String> = people.shuffled(Random(seed))


}

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