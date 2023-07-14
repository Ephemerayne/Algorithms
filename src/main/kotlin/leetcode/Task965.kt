package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task965() {
    isUnivalTree(BinaryTreeNode(2).apply {
        left = BinaryTreeNode(2).apply {
            left = BinaryTreeNode(5)
            right = BinaryTreeNode((2))
        }
        right = BinaryTreeNode(2)
    })
}

private fun isUnivalTree(root: BinaryTreeNode?): Boolean {
    if (root == null) return false

    val value = root.value

    val queue: Queue<BinaryTreeNode> = LinkedList()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        if (polled.value != value) return false

        if (polled.left != null) queue.offer(polled.left)
        if (polled.right != null) queue.offer(polled.right)


    }

    return true
}