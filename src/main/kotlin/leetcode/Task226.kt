package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task226() {
invertTree(
    BinaryTreeNode(4).apply {
        left = BinaryTreeNode(2).apply {
            left = BinaryTreeNode(1)
            right = BinaryTreeNode(3)
        }
        right = BinaryTreeNode(7).apply {
            left = BinaryTreeNode(6)
            right = BinaryTreeNode(9)
        }
    }
)
}

fun invertTree(root: BinaryTreeNode?): BinaryTreeNode? {
   if (root == null) return null

    val queue: Queue<BinaryTreeNode> = LinkedList()
    queue.offer(root)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        if (polled.left != null) {
            queue.offer(polled.left)
        }

        if (polled.right != null) {
            queue.offer(polled.right)
        }

        val leftRoot = polled.left

        polled.left = polled.right
        polled.right = leftRoot
    }

    return root
}