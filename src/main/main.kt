package main

import TextEditor
import MediaGallery
import IDE


fun main() {
    val vim = TextEditor("Vim", hashSetOf("modular", "extensible"))

    val windowsMediaViewer = MediaGallery(
        "Windows Media Viewer",
        "jpg", hashSetOf("ease of use")
    )

    val idea = IDE("IntelliJ IDEA", "Java", hashSetOf("cool autocompletion", "convenience"))


    vim.about()
    println(vim.makeCoolEdit("I like ice cream"))
    vim.feature()
    vim.feature("modular")
    vim.feature("easy for newcomers")
    println()

    windowsMediaViewer.about()
    println(windowsMediaViewer.shufflePeopleOnPhoto(listOf("Vasya", "Petya", "Katya", "Sasha", "Andrei")))
    println()

    idea.about()
    idea.showAnnoyingError()
}