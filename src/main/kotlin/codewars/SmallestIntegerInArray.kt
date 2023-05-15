package codewars

import kotlin.math.min

fun exampleTests() {
    val sif: SmallestIntegerFinder = SmallestIntegerFinder()
    val result = sif.findSmallestInt(listOf(15, 20, 10, 17, 22, 9001))
    println(result)
}

class SmallestIntegerFinder {

    /* my solution */
    fun findSmallestInt(nums: List<Int>): Int {
        return realization(nums)
//        val sortedList = nums.sorted()
//        return sortedList.first()
    }

    fun bestSolution(nums: List<Int>) = nums.minOf { it }

    fun realization(nums: List<Int>): Int {
        var minNumber = Int.MAX_VALUE

        for (num in nums) {
            if (num < minNumber) {
                minNumber = num
            }
        }

        return minNumber
    }
}