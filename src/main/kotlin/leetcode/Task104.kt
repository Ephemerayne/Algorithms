package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task104() {
    maxDepth(BinaryTreeNode(3).apply {
        left = BinaryTreeNode(9)
        right = BinaryTreeNode(20).apply {
            left = BinaryTreeNode(15)
            right = BinaryTreeNode(7)
        }
    })
}

fun maxDepth(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    val levelContainer = mutableListOf<BinaryTreeNode>()
    var levelCount = 0
    levelContainer.add(root)

    while (levelContainer.isNotEmpty()) {
        val queue: Queue<BinaryTreeNode> = LinkedList()


        queue.addAll(levelContainer)

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

        levelCount++
        println("====")
    }

    println("debug: $levelCount")
    return levelCount
}