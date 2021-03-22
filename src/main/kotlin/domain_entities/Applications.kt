package domain_entities

enum class Platform {
    WINDOWS, LINUX, MACOS, MULTI
}

abstract class Application(open val name: String, open val platform: Platform)

// Будем джойнить по полю Platform

// Class 1
data class IDE(
    override val name: String,
    override val platform: Platform,
    val primaryLang: String?,
    val isOpenSource: Boolean
) :
    Application(name, platform)

// Class 2
data class TextEditor(
    override val name: String,
    override val platform: Platform,
    val yearOfRelease: Int
) :
    Application(name, platform)

// Class 3
data class MediaViewer(
    override val name: String,
    override val platform: Platform,
    val createdBy: String
) :
    Application(name, platform)