package codewars

import java.util.LinkedList

fun task() {
    DirReduction.dirReduc(arrayOf("NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"))
}


object DirReduction {
    fun dirReduc(arr: Array<String>): Array<String> {
        if (arr.size <= 1) return arr

        val stack = java.util.Stack<String>()
        val oppositeOf = mapOf(
            "WEST" to "EAST", "EAST" to "WEST",
            "SOUTH" to "NORTH", "NORTH" to "SOUTH"
        )

        for (direction in arr) when {
            stack.empty() -> stack.push(direction)
            stack.peek() == oppositeOf[direction] -> stack.pop()
            else -> stack.push(direction)
        }

        return stack.toTypedArray()
    }
}
