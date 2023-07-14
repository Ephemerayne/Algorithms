package codewars

import kotlin.math.sqrt

fun PredictYourAge() {
    predictAge(65, 60, 75, 55, 60, 63, 64, 45)
    predictAge(32, 54, 76, 65, 34, 63, 64, 45)
}

private fun predictAge(age1: Int, age2: Int, age3: Int, age4: Int, age5: Int, age6: Int, age7: Int, age8: Int): Int {
    val listOfAges = listOf(age1, age2, age3, age4, age5, age6, age7, age8)
    var sum = 0

    for (age in listOfAges) {
        sum += age * age
    }

    val squareRoot = sqrt(sum.toDouble())
    val result = (squareRoot / 2).toInt()

    return result
}