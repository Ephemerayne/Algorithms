package codewars

import kotlin.math.absoluteValue

fun invertValues() {
    invert(intArrayOf(1, 2, 3, 4, 5))
    invert(intArrayOf(1, -2, 3, -4, 5))
    invert(intArrayOf())
    invert(intArrayOf(0))
}

fun invert(arr: IntArray): IntArray {
    val list = arrayListOf<Int>()
    for (digit in arr) {
        if (digit > 0) {
            list.add(-digit)
        } else {
            list.add(digit.absoluteValue)
        }
    }
    println(list)
    return list.toIntArray()
}

// best solution fun invert(arr: IntArray) = arr.map { -it }.toIntArray()