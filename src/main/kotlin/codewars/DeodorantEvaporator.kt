package codewars

fun DeodorantEvaporator() {
    evaporator(10.0, 10.0, 10.0)
    evaporator(10.0, 10.0, 5.0)
    evaporator(100.0, 5.0, 5.0)
}

private fun evaporator(content: Double, evap_per_day: Double, threshold: Double): Int {
    var value: Double = content
    val thresholdPercent = (threshold / 100) * content

    var i = 0
    while (value > thresholdPercent) {
        value -= value * (evap_per_day / 100)
        i++
    }

    return i
}