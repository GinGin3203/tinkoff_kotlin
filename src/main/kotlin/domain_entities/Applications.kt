package domain_entities

import database.Connection

enum class TableClasses {
    Ide, TextEditor, MediaViewer
}

enum class AllClasses {
    Ide, TextEditor, MediaViewer, IdeTextEditorJoined
}

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

fun applicationOf(response: Connection.Response) = when (AllClasses.valueOf(response.className)) {
    AllClasses.Ide -> ideOf(response.data)
    AllClasses.TextEditor -> textEditorOf(response.data)
    AllClasses.MediaViewer -> mediaViewerOf(response.data)
    AllClasses.IdeTextEditorJoined -> ideTextEditorJoinedOf(response.data)
    else -> null
}