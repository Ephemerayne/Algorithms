package codewars

import kotlin.math.max
import kotlin.math.min

fun StatisticsForAnAthleticAssociation() {
    stat("01|15|59, 1|47|16, 01|17|20, 1|32|34,2|17|17")
    stat("02|15|59, 2|47|16,02|17|20, 2|32|34, 2|17|17, 2|22|00, 2|31|41")
    stat("1|1|0")
}

private fun stat(s: String): String {
    if (s.isBlank()) return ""

    val timesList = getListOfTimes(s).map {
        getTimeInSeconds(it)
    }

    val maxValue = timesList.reduce { a, b -> max(a , b) }
    val minValue = timesList.reduce { a, b -> min(a , b) }

    val range = maxValue - minValue
    val average = timesList.average().toInt()

    val sortedTimeList = timesList.sorted()
    val isEvenSize = sortedTimeList.size % 2 == 0

    val median = if (isEvenSize) {
        val medianIndex1 = sortedTimeList.size / 2
        val medianIndex2 = medianIndex1 - 1

        val median1 = sortedTimeList[medianIndex1]
        val median2 = sortedTimeList[medianIndex2]

        (median1.toDouble() + median2.toDouble()) / 2
    } else {
        (sortedTimeList[sortedTimeList.size / 2]).toDouble()
    }.toInt()

    println("debug:${"Range: ${getTimeInHMS(range)} Average: ${getTimeInHMS(average)} Median: ${getTimeInHMS(median)}"}")
    return "Range: ${getTimeInHMS(range)} Average: ${getTimeInHMS(average)} Median: ${getTimeInHMS(median)}"


}

private fun getListOfTimes(s: String): List<String> {
    return s.split(",")
}

private fun getTimeInSeconds(s: String): Int {
    val split = s.trim().split("|")
    val hours = split[0].toInt()
    val minutes = split[1].toInt()
    val seconds = split[2].toInt()

    return hours * 3600 + minutes * 60 + seconds
}

private fun getTimeInHMS(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val seconds = seconds % 60

    return "${hours.formattedTime()}|${minutes.formattedTime()}|${seconds.formattedTime()}"
}


private fun Int.formattedTime(): String {
    return if (this < 10) {
        "0$this"
    } else {
        "$this"
    }
}