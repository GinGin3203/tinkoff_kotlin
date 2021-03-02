package main

class TextEditor(override val name: String, override val features: HashSet<String>) : Application() {
    override val applicationType: String = "text editor"
    override val purpose: String = "editing text"

    fun makeCoolEdit(text: String): String = text.reversed()
}
