package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task111() {
    val res = minDepth(BinaryTreeNode(3).apply {
        left = BinaryTreeNode(9)
        right = BinaryTreeNode(20).apply {
            left = BinaryTreeNode(15)
            right = BinaryTreeNode(7)
        }
    })

    println("levels: $res")
}

private fun minDepth(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    var depth = 1

    val queue: Queue<BinaryTreeNode> = LinkedList()
    queue.offer(root)

    while (queue.isNotEmpty()) {
        val size = queue.size
        var i = 0
        while (i < size) {
            val node = queue.poll()
            if (node.left == null && node.right == null) return depth

            node.left?.let { queue.offer(it) }
            node.right?.let { queue.offer(it) }

            i++
        }

        depth++
    }

    return depth
}