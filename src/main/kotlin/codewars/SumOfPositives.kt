package codewars

fun SumOfPositives() {
    sum(intArrayOf(1, 2, 3, 4, 5))
    sum(intArrayOf(1, -2, 3, 4, 5))
    sum(intArrayOf())
    sum(intArrayOf(-1, -2, -3, -4, -5))
    sum(intArrayOf(-1, 2, 3, 4, -5))
}

fun sum(numbers: IntArray): Int {

    return numbers.sumOf { if (it > 0) it else 0 }
}