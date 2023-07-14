package codewars

fun OppositeNumber() {
    opposite(1)
    opposite(0)
    opposite(-25)
}

private fun opposite(number: Int): Int {
    return number * -1
}