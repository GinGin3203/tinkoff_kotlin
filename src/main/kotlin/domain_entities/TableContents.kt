package domain_entities

class TableContents {
    companion object {
        val tableData = listOf(
            listOf(
                IDE("VSCode", Platform.MULTI, null, true),
                IDE("IDEA", Platform.MULTI, "Java", false),
                IDE("Visual Studio", Platform.WINDOWS, "C++", false),
                IDE("XCode", Platform.MACOS, null, false),
                IDE("Borland C++ Builder", Platform.WINDOWS, "C++", false),
                IDE("Brackets", Platform.MACOS, "JavaScript", false)
            ),
            listOf(
                TextEditor("Vim", Platform.MULTI, 1991),
                TextEditor("Emacs", Platform.MULTI, 1976),
                TextEditor("Gedit", Platform.LINUX, 1999),
                TextEditor("Notepad", Platform.WINDOWS, 1985),
                TextEditor("Atom", Platform.MULTI, 2008),
                TextEditor("Notepadqq", Platform.LINUX, 2012)
            ),
            listOf(
                MediaViewer("Windows Media Gallery", Platform.WINDOWS, "Microsoft"),
                MediaViewer("PhotoMoto", Platform.WINDOWS, "PhotoMoto"),
                MediaViewer("VLC", Platform.MULTI, "VideoLAN"),
                MediaViewer("MPV", Platform.LINUX, "mpv developers"),
                MediaViewer("Elmedia", Platform.MACOS, "Eltima"),
                MediaViewer("IINA", Platform.MACOS, "IINA Team")
            )
        )
    }
}