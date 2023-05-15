package leetcode

import java.util.LinkedList
import java.util.Queue


fun Task559() {
    maxDepth(TreeNode(1).apply {
        children = listOf(
            TreeNode(2),
            TreeNode(3).apply {
                children = listOf(
                    TreeNode(6),
                    TreeNode(7).apply {
                        children = listOf(TreeNode(11).apply {
                            children = listOf(TreeNode(14))
                        })
                    })
            },
            TreeNode(4).apply {
                children = listOf(TreeNode(8).apply {
                    children = listOf(TreeNode(12))
                })
            },
            TreeNode(5).apply {
                children = listOf(
                    TreeNode(9).apply {
                        children = listOf(TreeNode(13))
                    },
                    TreeNode(10)
                )
            }
        )
    })
}

fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0

    val levelContainer = mutableListOf<TreeNode>()
    var levelCount = 0
    levelContainer.add(root)

    while (levelContainer.isNotEmpty()) {
        val queue: Queue<TreeNode> = LinkedList()

        queue.addAll(levelContainer)
        levelContainer.clear()

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            levelContainer.addAll(polled.children.filterNotNull())
        }

        levelCount++
        println("====")
    }

    println("debug: $levelCount")
    return levelCount
}