package codewars

import kotlin.math.pow
import kotlin.text.StringBuilder

fun HiddenCubicNumbers() {
    isSumOfCubes("aqdf&0#1xyz!22[153(777.777")
    isSumOfCubes("0 9026315 -827&()")
    isSumOfCubes("Once upon a midnight dreary, while100 I pondered, 9026315weak and weary -827&()")
    isSumOfCubes("Once 1000upon a midnight 110dreary, while100 I pondered, 9026315weak and weary -827&()")
}

private fun isSumOfCubes(s: String): String {
    val listOfNumbers = getNumbersFromString(s)
    val formattedString = StringBuilder()
    var cubicNumSum = 0

    var isLucky = false
    for (number in listOfNumbers) {
        val cubicNumber = getCubicNumber(number)

        if (number == cubicNumber) {
            isLucky = true
            formattedString.append(cubicNumber).append(" ")
            cubicNumSum += cubicNumber
        }
    }

    if (isLucky) {
        formattedString.append(cubicNumSum)
        formattedString.append(" Lucky")
    } else {
        formattedString.append("Unlucky")
    }

    println("debug: $formattedString")
    return formattedString.toString()
}

private fun getNumbersFromString(s: String): List<Int> {
    val stringNumbers = getListOfNumberStrings(s)

    val listOfNumbers = arrayListOf<Int>()

    stringNumbers.forEach { number ->
        var firstIndex = 0
        var lastIndex = 3

        while (firstIndex < number.length) {
            if (firstIndex >= number.lastIndex) {
                firstIndex = number.lastIndex
            }

            if (lastIndex >= number.length) {
                lastIndex = number.length
            }

            val newNum = number.substring(firstIndex, lastIndex).toIntOrNull() ?: break
            listOfNumbers.add(newNum)

            firstIndex += 3
            lastIndex += 3

        }

    }

    return listOfNumbers
}

private fun getListOfNumberStrings(s: String): List<String> {
    val result = mutableListOf<String>()

    val stringBuilder = StringBuilder()
    s.forEach {
        if (it.isDigit()) {
            stringBuilder.append(it)
        } else {
            val stringNumber = stringBuilder.toString()
            if (stringNumber.isNotEmpty()) {
                result.add(stringNumber)
                stringBuilder.clear()
            }
        }
    }

    val reminder = stringBuilder.toString()
    if (reminder.isNotEmpty()) {
        result.add(reminder)
    }

    return result
}

private fun getCubicNumber(number: Int): Int {
    val thousands = number / 1000
    val hundreds = (number % 1000) / 100
    val tens = (number % 100) / 10
    val units = number % 10

    return (powNumber(thousands) + powNumber(hundreds) + powNumber(tens) + powNumber(units)).toInt()
}

private fun powNumber(number: Int): Double {
    return number.toDouble().pow(3)
}