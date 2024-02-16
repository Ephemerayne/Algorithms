package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task841() {
    val test = canVisitAllRooms(
        listOf(
         /*   listOf(1),
            listOf(2),
            listOf(3),
            listOf(),*/
            listOf(1, 3),
            listOf(3, 0, 1),
            listOf(2),
            listOf(0),
        )
    )
    println(test)
}

private fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
    val queue: Queue<Int> = LinkedList()
    queue.addAll(rooms[0])

    val visited = mutableSetOf<Int>(0)

    while (queue.isNotEmpty()) {
        val key = queue.poll()

        if (visited.contains(key)) continue
        visited.add(key)

        val keysFromRoom = rooms[key]
        queue.addAll(keysFromRoom)
    }

    return visited.size == rooms.size
}