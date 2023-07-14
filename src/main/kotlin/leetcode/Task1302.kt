package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task1302() {
    deepestLeavesSum(BinaryTreeNode(1).apply {
        left = BinaryTreeNode(2).apply {
            left = BinaryTreeNode(4).apply {
                left = BinaryTreeNode(7)
            }
            right = BinaryTreeNode(5)
        }
        right = BinaryTreeNode(3).apply {
            right = BinaryTreeNode(6).apply {
                right = BinaryTreeNode(8)
            }
        }
    })
}

private fun deepestLeavesSum(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    val levelContainer = mutableListOf<BinaryTreeNode>()
    levelContainer.add(root)

    val lastLevelContainer = mutableListOf<BinaryTreeNode>()

    while (levelContainer.isNotEmpty()) {
        val queue: Queue<BinaryTreeNode> = LinkedList()
        queue.addAll(levelContainer)

        lastLevelContainer.addAll(levelContainer)

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

        if (levelContainer.isNotEmpty()) {
            lastLevelContainer.clear()
        }
    }

    var sum = 0
    levelContainer.forEach {
        sum += it.value
    }

    return sum
}