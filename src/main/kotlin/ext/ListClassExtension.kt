package main

fun <T> List<T>.concatenateContents(): String {
    val iter =
        (this.takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException("Empty list is not accepted")).iterator()
    return buildString {
        while (iter.hasNext())
            this.append(iter.next())
    }
}