package various

fun main() {
//    variousUniqueNumbersQuantity(listOf(0, 1, 2, 1230, 12, 23))
//    variousUniqueNumbersQuantity(listOf(0, 1, 1, 2, 2, 3))
//    variousUniqueNumbersQuantity(listOf(0, 2, 2, 3, 3, 3))
    increment()
}

fun variousUniqueNumbersQuantity(array: List<Int>): Boolean {
    val numMaps: MutableMap<Int, Int> = mutableMapOf()
    var count = 0

    for (num1 in array) {
        for (num2 in array) {
            if (num1 == num2) {
                count++
                numMaps[num1] = count
            }
        }
        count = 0
    }

    println(numMaps.values.size == numMaps.values.toSet().size)
    return numMaps.values.size == numMaps.values.toSet().size
}

fun increment() {
    var i = 0
    println("1: ${i++}")
    println("2: $i")

    println("====")

    i = 0
    println("3: ${++i}")
    println("4: $i")
}