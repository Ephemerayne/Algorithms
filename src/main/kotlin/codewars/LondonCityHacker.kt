package codewars

import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun LondonCityHacker() {
    londonCityHacker(arrayOf(12, "Central", "Circle", 21))
    londonCityHacker(arrayOf("Piccidilly", 56))
    londonCityHacker(arrayOf("Northern", "Central", "Circle"))
    londonCityHacker(arrayOf("Piccidilly", 56, 93, 243))
    londonCityHacker(arrayOf(386, 56, 1, 876))
    londonCityHacker(arrayOf())
}

private fun londonCityHacker(journey: Array<Any>): String {
    val tubeCost = 2.4
    val busCost = 1.5

    var sum = 0.0

    var skipBusIndex = -1
    for (i in 0.. journey.lastIndex) {
        val currentType = journey[i]

        if (currentType is String) {
            sum += tubeCost
        } else if (currentType is Int) {
            if (i == skipBusIndex) {
                skipBusIndex = -1
                continue
            }

            sum += busCost
            skipBusIndex = i + 1
        }
    }

    var result = "${sum.toFloat()}" // вместо расширения приводим к флоату
    val isOneDigitAfterDot = result.split(".")[1].length == 1

    if (isOneDigitAfterDot) {
        result += "0"
    }

    println("result: $result")
    return "£$result"
}

//private val Double.roundToHundreds get(): Double {
//    return (this * 100).roundToInt().toDouble() / 100
//}
