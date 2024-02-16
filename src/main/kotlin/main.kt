import kotlinx.coroutines.*
import leetcode.Task1123
import leetcode.Task783
import leetcode.Task897
import leetcode.Task938
import java.util.*

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
suspend fun main(args: Array<String>) {
    Task1123()
  /*  val test = IntArray(10)
    println("debug: ${test.toList()}")*/
/*    val mapTest: SortedMap<String, Int> = sortedMapOf(
        "Test" to 5,
        "Meow" to 10,
        "Abc" to 29
    )

    println("debug: $mapTest")*/
    /*CoroutineScope(Dispatchers.Unconfined).launch {

        launch {
            delay(1000)
            println("FIRST LAUNCH: ${Thread.currentThread().name}")
        }*/

//        (1..1000000).forEach {
//            println("FIRST LAUNCH: ${Thread.currentThread().name}")
//        }
//        delay(10)
//        println("Second LAUNCH: ${Thread.currentThread().name}")

       /* launch {
            println("SECOND LAUNCH BEFORE DELAY: ${Thread.currentThread().name})")
            delay(1000)
            println("SECOND LAUNCH AFTER DELAY: ${Thread.currentThread().name})")
        }

        withContext(Dispatchers.Unconfined) {
            println("THIRD LAUNCH WITH CONTEXT: ${Thread.currentThread().name})")
        }
*/
      //  println("FOURTH LAUNCH:  ${Thread.currentThread().name}")
    }

//    Thread.sleep(3000)
    //   QueueTest()
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
//}


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