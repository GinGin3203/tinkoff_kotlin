package domain_entities

import database.Connection

enum class TableClasses {
    Ide, TextEditor, MediaViewer
}

enum class AllClasses {
    Ide, TextEditor, MediaViewer, IdeTextEditorJoined, IdeTextEditorGrouped
}

enum class Platform {
    WINDOWS, LINUX, MACOS, MULTI
}

abstract class Application(open val name: String?, open val platform: Platform?)

// Class 1
data class Ide(
    override val name: String,
    override val platform: Platform,
    val primaryLang: String?,
    val isOpenSource: Boolean
) :
    Application(name, platform)

fun ideOf(map: Map<String, Any?>) = Ide(
    map["name"] as String,
    Platform.valueOf(map["platform"] as String),
    map["primaryLang"] as String?,
    (map["isOpenSource"] as String).toBoolean()
)

// Class 2
data class TextEditor(
    override val name: String,
    override val platform: Platform,
    val yearOfRelease: Int
) :
    Application(name, platform)

fun textEditorOf(map: Map<String, Any?>) = TextEditor(
    map["name"] as String,
    Platform.valueOf(map["platform"] as String),
    (map["yearOfRelease"] as String).toInt()

)

// Class 3
data class MediaViewer(
    override val name: String,
    override val platform: Platform,
    val createdBy: String
) :
    Application(name, platform)

fun mediaViewerOf(map: Map<String, Any?>) = MediaViewer(
    map["name"] as String,
    Platform.valueOf(map["platform"] as String),
    map["createdBy"] as String
)

// Composite class for join
data class IdeTextEditorJoined(
    override val platform: Platform?,
    val primaryLang: String?,
    val isOpenSource: Boolean?,
    val yearOfRelease: Int?
) : Application("IdeAndTextEditorCompositeByJoin", platform)

fun ideTextEditorJoinedOf(map: Map<String, Any?>) = IdeTextEditorJoined(
    if (map["platform"] != null) Platform.valueOf(map["platform"] as String) else null,
    map["primaryLang"] as String?,
    (map["isOpenSource"] as String?)?.toBoolean(),
    (map["yearOfRelease"] as String?)?.toInt()
)

data class IdeTextEditorGrouped(
    override val platform: Platform,
    val countIsOpenSource: Int,
    val maxYearOfRelease: Int
) : Application("IdeAndTextEditorCompositeGroupedBy${platform}", platform)

fun ideTextEditorGroupedOf(map: Map<String, Any?>) = IdeTextEditorGrouped(
    Platform.valueOf(map["platform"] as String),
    map["countIsOpenSource"] as Int,
    map["maxYearOfRelease"] as Int
)

fun applicationOf(response: Connection.Response) = when (response.className) {
    AllClasses.Ide -> ideOf(response.data)
    AllClasses.TextEditor -> textEditorOf(response.data)
    AllClasses.MediaViewer -> mediaViewerOf(response.data)
    AllClasses.IdeTextEditorJoined -> ideTextEditorJoinedOf(response.data)
    AllClasses.IdeTextEditorGrouped -> ideTextEditorGroupedOf(response.data)
}