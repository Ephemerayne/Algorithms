package codewars

fun ReduceButGrow() {
    grow(intArrayOf(1, 2, 3))
    grow(intArrayOf(4, 1, 1, 1, 4))
    grow(intArrayOf(2, 2, 2, 2, 2, 2))
}

private fun grow(arr: IntArray): Int {
    var result = 1

    for (num in arr) {
        result *= num
    }

    return result
}