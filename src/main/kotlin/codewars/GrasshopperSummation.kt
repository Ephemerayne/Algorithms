package codewars

fun GrasshopperSummation() {
    summation(1)
    summation(8)
}

private fun summation(n: Int): Int {
    var i = 0
    var sum = 0

    while (i < n) {
        i++
        sum += i
    }

    return sum
}