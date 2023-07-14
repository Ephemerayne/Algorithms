package codewars

import java.lang.StringBuilder

fun GeometricProgressionSequence() {
    geometricSequenceElements(2, 3, 5)
    geometricSequenceElements(2, 2, 10)
    geometricSequenceElements(1, -2, 10)
}

private fun geometricSequenceElements(a: Int, r: Int, n: Int): String {
    // a - число для умножения
    // r - на что умножать
    // n - количество итераций

    var list = arrayListOf<Int>()
    var prev = a
    list.add(prev)

    var i = 1
    while (i in 1 until n) {
        prev = list.last()
        list.add(prev * r)

        i++
    }
    return StringBuilder(list.joinToString(", ")).toString()
}
