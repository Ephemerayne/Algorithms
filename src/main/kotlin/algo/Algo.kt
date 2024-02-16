package algo

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

fun main() {

}

fun test() {
    val queue = PriorityQueue<Person>(MyComparator())
    queue.offer(Person(10, "Vasia"))
    println(queue)
    queue.offer(Person(20, "Oleg"))
    println(queue)
    queue.offer(Person(30, "Igor"))
    println(queue)
    queue.offer(Person(40, "Bonya"))
    println(queue)

    println("debug: smallFunc")
}

fun test2() {


    println("debug: smallFunc")
}

fun smallFunc() {
    println("debug: smallFunc")
}



private fun algo() {
    val queue = PriorityQueue<Person>(MyComparator())
    queue.offer(Person(10, "Vasia"))
    println(queue)
    queue.offer(Person(20, "Oleg"))
    println(queue)
    queue.offer(Person(30, "Igor"))
    println(queue)
    queue.offer(Person(40, "Bonya"))
    println(queue)
}

private class MyComparator : Comparator<Person> {
    override fun compare(a: Person?, b: Person?): Int {
        return a?.name?.compareTo(b?.name!!)!!
    }
}

data class Person(
    var age: Int = 0,
    val name: String
)
