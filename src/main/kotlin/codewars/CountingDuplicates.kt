package codewars

fun CountingDuplicates() {
    duplicateCount("abcde")
    duplicateCount("abcdea")
    duplicateCount("indivisibility")
    duplicateCount("aabBcde")
}

private fun duplicateCount(text: String): Int {
    var duplicatedLetters: MutableSet<Char> = mutableSetOf()
    var countDuplicates = 0

    for (i in text.indices) {
        for (j in text.indices) {
            if (i == j) continue

            val letter1 = text[i].toLowerCase()
            val letter2 = text[j].toLowerCase()

            if (letter1 == letter2) {
                if (duplicatedLetters.contains(letter1)) continue

                countDuplicates++

                duplicatedLetters.add(letter1)
            }
        }
    }
    println("debug: $countDuplicates")
    return countDuplicates
}