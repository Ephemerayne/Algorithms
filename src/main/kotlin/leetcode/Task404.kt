package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task404() {
    sumOfLeftLeaves(
        BinaryTreeNode(1).apply {
            left = BinaryTreeNode(2).apply {
                left = BinaryTreeNode(4)
                right = BinaryTreeNode(5)
            }
            right = BinaryTreeNode(3)
        }
    )
}

private fun sumOfLeftLeaves(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    val queue: Queue<OrientedNode> = LinkedList()
    var sumOfLeftNodes = 0
    queue.offer(OrientedNode(Orientation.ROOT, root))

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        if (polled.node.left != null) {
            queue.offer(OrientedNode(Orientation.LEFT, polled.node.left!!))
        }

        if (polled.node.right != null) {
            queue.offer(OrientedNode(Orientation.RIGHT, polled.node.right!!))
        }

        if (isLeaf(polled.node) && polled.orientation == Orientation.LEFT) {
            sumOfLeftNodes += polled.node.value
        }

    }

    println("debug: $sumOfLeftNodes")
    return sumOfLeftNodes
}

private fun isLeaf(node: BinaryTreeNode): Boolean {
    return node.left == null && node.right == null
}

private data class OrientedNode(
    val orientation: Orientation,
    val node: BinaryTreeNode
)

private enum class Orientation {
    LEFT,
    RIGHT,
    ROOT
}
