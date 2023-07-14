package codewars

fun GetTheMiddleCharacter() {
    getMiddle("test")
    getMiddle("middle")
    getMiddle("testing")
    getMiddle("A")
}

private fun getMiddle(word: String): String {
    val wordLength = word.length
    val isLengthEven = wordLength % 2 == 0
    val result = StringBuilder()

    if (isLengthEven) {
        result.append(word[wordLength / 2 - 1]).append(word[wordLength / 2])
    } else {
        result.append(word[wordLength / 2])
    }

    return result.toString()
}