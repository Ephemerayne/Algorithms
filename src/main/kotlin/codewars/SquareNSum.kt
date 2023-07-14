package codewars

fun SquareNSum() {
    squareSum(arrayOf(1, 2))
    squareSum(arrayOf(0, 3, 4, 5))
    squareSum(arrayOf())
}

private fun squareSum(n: Array<Int>): Int {
    var sum = 0

    for (num in n) {
       sum += num * num
    }

    return sum
}