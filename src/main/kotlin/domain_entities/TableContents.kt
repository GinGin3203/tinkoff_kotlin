package domain_entities

class TableContents {
    companion object {
        val tableData = listOf(
            listOf(
                Ide("VSCode", Platform.MULTI, null, true),
                Ide("IDEA", Platform.MULTI, "Java", false),
                Ide("Visual Studio", Platform.WINDOWS, "C++", false),
                Ide("XCode", Platform.MACOS, null, false),
                Ide("Borland C++ Builder", Platform.WINDOWS, "C++", false),
                Ide("Brackets", Platform.MACOS, "JavaScript", false),
                Ide("QT Creator", Platform.LINUX, "QT", true)
            ),
            listOf(
                TextEditor("Vim", Platform.MULTI, 1991),
                TextEditor("Emacs", Platform.MULTI, 1976),
                TextEditor("Gedit", Platform.LINUX, 1999),
                TextEditor("Notepad", Platform.WINDOWS, 1985),
                TextEditor("Atom", Platform.MULTI, 2008),
                TextEditor("Notepadqq", Platform.LINUX, 2012),
                TextEditor("TextEdit", Platform.MACOS, 1996)
            ),
            listOf(
                MediaViewer("Windows Media Gallery", Platform.WINDOWS, "Microsoft", 9),
                MediaViewer("PhotoMoto", Platform.WINDOWS, "PhotoMoto", 6),
                MediaViewer("VLC", Platform.MULTI, "VideoLAN", 1),
                MediaViewer("MPV", Platform.LINUX, "mpv developers", 10),
                MediaViewer("Elmedia", Platform.MACOS, "Eltima", 5),
                MediaViewer("IINA", Platform.MACOS, "IINA Team", 3)
            )
        )
    }
}