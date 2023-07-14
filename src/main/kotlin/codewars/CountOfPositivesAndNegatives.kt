package codewars

fun CountOfPositivesAndNegatives() {
    countPositivesSumNegatives(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15))
    countPositivesSumNegatives(arrayOf(0, 2, 3, 0, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14))
    val result = countPositivesSumNegatives(arrayOf(0, 0, 0, 0))
    println("result: ${result.toList()}")
}

fun countPositivesSumNegatives(input: Array<Int>?): Array<Int> {
    if (input == null) return emptyArray()

    var sumOfNegatives = 0
    var countOfPositives = 0

    for (number in input) {
        if (number < 0) {
            sumOfNegatives += number
        } else if (number > 0) {
            countOfPositives++
        }
    }

    return if (sumOfNegatives == 0 && countOfPositives == 0) {
        emptyArray()
    } else {
        arrayOf(countOfPositives, sumOfNegatives)
    }
}