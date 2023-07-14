package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task637() {
    averageOfLevels(BinaryTreeNode(3).apply {
        left = BinaryTreeNode(9)
        right = BinaryTreeNode(20).apply {
            left = BinaryTreeNode(15)
            right = BinaryTreeNode(7)
        }
    })
}

private fun averageOfLevels(root: BinaryTreeNode?): DoubleArray {
    if (root == null) return doubleArrayOf()

    val levelContainer = mutableListOf<BinaryTreeNode>()
    levelContainer.add(root)

    val result = mutableListOf<Double>()
    while (levelContainer.isNotEmpty()) {
        val queue: Queue<BinaryTreeNode> = LinkedList()
        queue.addAll(levelContainer)

        val average = levelContainer.map { it.value }.average()
        result.add(average)

        levelContainer.clear()

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            polled.left?.let {
                levelContainer.add(it)
            }

            polled.right?.let {
                levelContainer.add(it)
            }
        }
    }

    println("debug: ${result}")
    return result.toDoubleArray()
}