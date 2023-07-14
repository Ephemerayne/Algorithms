package codewars

fun ConvertNumberToReversedArrayOfDigits() {
    digitize(35231)
    digitize(0)
}

private fun digitize(n: Long): IntArray {
    val reversedString = n.toString().reversed()

    return IntArray(reversedString.length) {
        reversedString[it].digitToInt()
    }
}