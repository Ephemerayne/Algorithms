package leetcode

import java.util.LinkedList
import java.util.Queue


fun Task559() {
    maxDepth(TreeNodeWithChildrens(1).apply {
        children = listOf(
            TreeNodeWithChildrens(2),
            TreeNodeWithChildrens(3).apply {
                children = listOf(
                    TreeNodeWithChildrens(6),
                    TreeNodeWithChildrens(7).apply {
                        children = listOf(TreeNodeWithChildrens(11).apply {
                            children = listOf(TreeNodeWithChildrens(14))
                        })
                    })
            },
            TreeNodeWithChildrens(4).apply {
                children = listOf(TreeNodeWithChildrens(8).apply {
                    children = listOf(TreeNodeWithChildrens(12))
                })
            },
            TreeNodeWithChildrens(5).apply {
                children = listOf(
                    TreeNodeWithChildrens(9).apply {
                        children = listOf(TreeNodeWithChildrens(13))
                    },
                    TreeNodeWithChildrens(10)
                )
            }
        )
    })
}

private fun maxDepth(root: TreeNodeWithChildrens?): Int {
    if (root == null) return 0

    val levelContainer = mutableListOf<TreeNodeWithChildrens>()
    var levelCount = 0
    levelContainer.add(root)

    while (levelContainer.isNotEmpty()) {
        val queue: Queue<TreeNodeWithChildrens> = LinkedList()

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