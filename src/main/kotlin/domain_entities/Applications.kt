package domain_entities

enum class Platform {
    WINDOWS, LINUX, MACOS, MULTI
}

abstract class Application(open val name: String, open val platform: Platform)

// Class 1
fun ideOf(map: Map<String, Any?>) = Ide(
    map["name"] as String,
    Platform.valueOf(map["platform"] as String),
    map["primaryLang"] as String?,
    (map["isOpenSource"] as String).toBoolean()
)

data class Ide(
    override val name: String,
    override val platform: Platform,
    val primaryLang: String?,
    val isOpenSource: Boolean
) :
    Application(name, platform)

// Class 2
fun textEditorOf(map: Map<String, Any?>) = TextEditor(
    map["name"] as String,
    Platform.valueOf(map["platform"] as String),
    (map["yearOfRelease"] as String).toInt()

)

data class TextEditor(
    override val name: String,
    override val platform: Platform,
    val yearOfRelease: Int
) :
    Application(name, platform)

// Class 3

fun mediaViewerOf(map: Map<String, Any?>) = MediaViewer(
    map["name"] as String,
    Platform.valueOf(map["platform"] as String),
    map["createdBy"] as String
)

data class MediaViewer(
    override val name: String,
    override val platform: Platform,
    val createdBy: String
) :
    Application(name, platform)

// Composite class for join
fun ideTextEditorJoinedOf(map: Map<String, Any?>) = IdeTextEditorJoined(
    map["name"] as String,
    Platform.valueOf(map["platform"] as String),
    map["primaryLang"] as String?,
    (map["isOpenSource"] as String).toBoolean(),
    (map["yearOfRelease"] as String).toInt()
)

data class IdeTextEditorJoined(
    override val name: String,
    override val platform: Platform,
    val primaryLang: String?,
    val isOpenSource: Boolean,
    val yearOfRelease: Int
) : Application(name, platform)
