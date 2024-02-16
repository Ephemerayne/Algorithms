package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task938() {
    rangeSumBST(BinaryTreeNode(10).apply {
        left = BinaryTreeNode(5).apply {
            left = BinaryTreeNode(3)
            right = BinaryTreeNode(7)
        }
        right = BinaryTreeNode(15).apply {
            right = BinaryTreeNode(18)
        }
    }, 7, 15)
}

fun rangeSumBST(root: BinaryTreeNode?, low: Int, high: Int): Int {
    if (root == null) return 0

    val queue: Queue<BinaryTreeNode> = LinkedList()
    var sum = 0

    queue.offer(root)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        polled.left?.let {
            queue.offer(it)
        }

        polled.right?.let {
            queue.offer(it)
        }

        if (polled.value in low..high) {
            sum += polled.value
        }
    }

    return sum
}