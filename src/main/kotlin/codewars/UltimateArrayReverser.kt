package codewars

fun UltimateArrayReverser() {
    reverse(listOf("I", "like", "big", "butts", "and", "I", "cannot", "lie!"))
    reverse(
        listOf(
            "?kn",
            "ipnr",
            "utotst",
            "ra",
            "tsn",
            "iksr",
            "uo",
            "yer",
            "ofebta",
            "eote",
            "vahu",
            "oyodpm",
            "ir",
            "hsyn",
            "amwoH"
        )
    )
}

private fun reverse(a: List<String>): List<String> {
    val sb = StringBuilder()
    val lengths = IntArray(a.size)

    for ((i, word) in a.withIndex()) {
        sb.append(word)
        lengths[i] = word.length
    }

    val string = sb.toString()
    val reversed = string.reversed()

    val result = mutableListOf<String>()

    var lastIndex = 0
    for (length in lengths) {
        result.add(reversed.substring(lastIndex, length + lastIndex))
        lastIndex += length
    }

    println("result: $result")
    return result
}

// best solution
fun reverseTwo(a: List<String>): List<String> {
    var contentsReversed = a.joinToString("").reversed()
    val output = mutableListOf<String>()
    for (word in a) {
        val newWord = contentsReversed.take(word.length)
        contentsReversed = contentsReversed.drop(word.length)
        output.add(newWord)
    }
    return output
}