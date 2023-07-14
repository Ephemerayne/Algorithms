package codewars

import java.lang.StringBuilder

fun FramedReflection() {
    mirror("Hello World a")
    mirror("Codewars")
}

private fun mirror(text: String): String {
    val split = text.split(" ")
    val wordMaxLength = split.fold(0) { acc, cur -> if (acc < cur.length) cur.length else acc }
    val builder = StringBuilder()

    repeat(wordMaxLength + 4) { builder.append("*") }

    for (word in split) {
        val diff = wordMaxLength - word.length
        builder.append("\n* ${word.reversed()} ")
        repeat(diff) { builder.append(" ") }
        builder.append("*")
    }
    builder.append("\n")
    repeat(wordMaxLength + 4) { builder.append("*") }

    println("$builder")
    return builder.toString()
}