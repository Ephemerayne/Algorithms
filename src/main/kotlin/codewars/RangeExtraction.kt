package codewars

import java.lang.StringBuilder

fun RangeExtraction() {
    rangeExtraction(intArrayOf(-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20))
//    rangeExtraction(intArrayOf(-3, -2, -1, 2, 10, 15, 16, 18, 19, 20))
}

private fun rangeExtraction(arr: IntArray): String {
    val sb = StringBuilder()
    val rangesContainer = mutableListOf<Int>()

    for (number in arr) {
        if (rangesContainer.isEmpty()) {
            rangesContainer.add(number)
        } else {
            val diff = number - rangesContainer.last()
            if (diff == 1) {
                rangesContainer.add(number)
            } else {
                val rangeString = getRangeString(rangesContainer)
                sb.append("$rangeString,")

                rangesContainer.clear()
                rangesContainer.add(number)
            }
        }
    }

    val lastRange = getRangeString(rangesContainer)
    sb.append(lastRange)

    val result = sb.toString()
    println(result)
    return result
}

private fun getRangeString(range: List<Int>): String {
    return if (range.size > 2) {
        "${range.first()}-${range.last()}"
    } else {
        val sb = StringBuilder()
        for (number in range) {
            sb.append("$number,")
        }

        sb.deleteCharAt(sb.lastIndex)
        sb.toString()
    }
}
