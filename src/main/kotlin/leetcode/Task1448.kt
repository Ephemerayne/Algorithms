package leetcode

import java.util.*

fun Task1448() {
    goodNodes(BinaryTreeNode(2).apply {
        right = BinaryTreeNode(4).apply {
            left = BinaryTreeNode(10)
            right = BinaryTreeNode(8).apply {
                left = BinaryTreeNode(4)
            }
        }
    })
   /* goodNodes(BinaryTreeNode(9).apply {
        right = BinaryTreeNode(4).apply {
            left = BinaryTreeNode(6)
        }
    })*/
}

private fun goodNodes(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    val queue: Queue<MaxNodeInPathAwareWrapper> = LinkedList()
    queue.offer(MaxNodeInPathAwareWrapper(root.value, root))

    var goodNodesCount = 1

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        val maxNodeInPath = polled.maxNodeInPath

        polled.node.left?.let {
            var leftSideMaxNodeInPath = maxNodeInPath

            if (leftSideMaxNodeInPath <= it.value) {
                leftSideMaxNodeInPath = it.value
                goodNodesCount++
            }

            queue.offer(MaxNodeInPathAwareWrapper(leftSideMaxNodeInPath, it))
        }

        polled.node.right?.let {
            var rightSideMaxNodeInPath = maxNodeInPath

            if (rightSideMaxNodeInPath <= it.value) {
                rightSideMaxNodeInPath = it.value
                goodNodesCount++
            }

            queue.offer(MaxNodeInPathAwareWrapper(rightSideMaxNodeInPath, it))
        }
    }

    println(goodNodesCount)
    return goodNodesCount
}

private data class MaxNodeInPathAwareWrapper(
    val maxNodeInPath: Int,
    val node: BinaryTreeNode
)
