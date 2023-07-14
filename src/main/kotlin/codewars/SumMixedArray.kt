package codewars

import java.lang.Exception

fun SumMixedArray() {
  val res =
    sum(listOf(5, "5"))
    sum(listOf(9, 3, "7", "3"))
    sum(listOf("5", "0", 9, 3, 2, 1, "9", 6, 7))
    sum(listOf("3", 6, 6, 0, "5", 8, 5, "6", 2, "0"))
    sum(listOf(8, 0, 0, 8, 5, 7, 2, 3, 7, 8, 6, 7))

    println("debug: $res")
}

private fun sum(mixed: List<Any>): Int {
    var sum = 0

    for (value in mixed) {
        when (value) {
            is Int -> sum += value
            is String -> sum += value.toIntOrNull() ?: 0
        }
    }

    return sum
}