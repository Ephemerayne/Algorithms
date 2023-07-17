import codewars.*
import leetcode.*
import leetcode.Task733
import org.w3c.dom.ranges.Range
import java.util.*
import kotlin.collections.List

private data class Singleton private constructor(val age: Int) {

    companion object {
        var instance: Singleton? = null
        fun create(age: Int): Singleton {
            if (instance == null) {
                instance = Singleton(age)
            }

            return instance!!
        }
    }
}

private fun mapIntToDouble(a: Int, b: Int): Int {
    return a + b
}

val x = 1
fun main(args: Array<String>) {
    Task1448()
//    var prop = "abc"::length
//    println(prop.get())   // выведет "3"

//    println(::x.get())
//    println(::x.name)

//    val ints = listOf<Int>(1, 2, 3, 4, 5)
//    println(ints)
//
//    val test = ::mapIntToDouble
//    println(test(8,9))
//
//    val doubles = ints.reduce { a, b -> a + b }
//    val doubles = ints.reduce(::mapIntToDouble)
//    val doubles = ints.map{a,b -> if () else {} }
//    println(doubles)

//    val sum = {a: Int , b: Int -> a + b}
//    val sum2 = fun (a: Int): Int { return 1 }
//
//    var num1 = 4
//    outer@ while (num1 > 0) {
//        var num2 = 4
//        inner@ while (num2 > 0) {
//            if (num1==2)
//                break@outer
//            println("num1 = $num1, num2 = $num2")
//            num2--
//        }
//        num1--
//    }
}


//fun main(args: Array<String>) {
//    KeepHydrated()

//    val a : Int = 1
//    println(a as? Long)
//}

//    val q = MyCircularQueue(5)
//    q.enQueue(1)
//    q.enQueue(2)
//    q.deQueue()
//    q.enQueue(3)
//    println(q.Front())
//    NumberOfClosedIslands().closedIsland(
//        arrayOf(
//            intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
//            intArrayOf(1, 0, 0, 0, 0, 1, 1, 0),
//            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0),
//            intArrayOf(1, 0, 0, 0, 0, 1, 0, 1),
//            intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
//        )
//    )

//fun outOfMemory() {
//    val list = LinkedList<ByteArray>()
//
//    var index = 1
//    while (true) {
//        val b = ByteArray(10 * 1024 * 1024 * 100)
//        list.add(b)
//        val rt = Runtime.getRuntime()
//
//        System.out.printf("[%3s] Available heap memory: %s%n", index++, rt.freeMemory());
//    }
//}