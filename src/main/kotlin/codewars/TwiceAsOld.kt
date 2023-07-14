package codewars

import kotlin.math.absoluteValue

fun TwiceAsOld() {
    twiceAsOld(36, 7)
    twiceAsOld(55, 30)
    twiceAsOld(42, 21)
    twiceAsOld(22, 1)
    twiceAsOld(29, 0)
}

fun twiceAsOld(dadYearsOld: Int, sonYearsOld: Int): Int {
    val doubleSonYears = sonYearsOld * 2
    var difference = dadYearsOld - doubleSonYears

    if (difference < 0) {
        difference *= -1
    }

    return difference
}