package codewars

import kotlin.math.roundToInt

fun KeepHydrated() {
    litres(2.0)
    litres(1.4)
    litres(12.3)
    litres(0.82)
    litres(11.8)
    litres(1787.0)
    litres(0.0)
}

private fun litres(time: Double): Int {
    val litresPerHour = 0.5

    val result = litresPerHour * time
    println("${result.toInt()}")
    return result.roundToInt()
}