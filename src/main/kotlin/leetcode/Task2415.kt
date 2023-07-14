package leetcode

import java.util.*

fun Task2415() {
    reverseOddLevels(BinaryTreeNode(2).apply {
        left = BinaryTreeNode(3).apply {
            left = BinaryTreeNode(8)
            right = BinaryTreeNode(13)
        }
        right = BinaryTreeNode(5).apply {
            left = BinaryTreeNode(21)
            right = BinaryTreeNode(34)
        }
    })
}

private fun reverseOddLevels(root: BinaryTreeNode?): BinaryTreeNode? {
    if (root == null) return null

    var levelIndex = 0
    val levelContainer = mutableListOf<BinaryTreeNode>()
    levelContainer.add(root)

    while (levelContainer.isNotEmpty()) {
        val queue: Queue<BinaryTreeNode> = LinkedList()
        queue.addAll(levelContainer)

        if (levelIndex % 2 == 1) {
            var i = 0
            val reversedValues = levelContainer.map { it.value }.reversed()

            while (i <= levelContainer.lastIndex) {
                val reversedValue = reversedValues[i]
                levelContainer[i].value = reversedValue
                i++
            }
        }


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


         levelIndex++
    }

    return root
}