package codewars

fun TwoSum() {
    twoSum(intArrayOf(1, 2, 3), 4)
    twoSum(intArrayOf(1234, 5678, 9012), 14690)
    twoSum(intArrayOf(2, 2, 3), 4)

}

private fun twoSum(numbers: IntArray, target: Int): Pair<Int, Int> {
    for (i in numbers.indices) {
        for (j in numbers.indices) {
            if (i == j) continue

            val num1 = numbers[i]
            val num2 = numbers[j]
            val sum = num1 + num2

            if (sum == target) {
                return Pair(i, j)
            }
        }
    }

    return 0 to 0
}
