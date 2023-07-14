package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task653() {
    val result = findTarget(BinaryTreeNode(5).apply {
        left = BinaryTreeNode(3).apply {
            left = BinaryTreeNode(2)
            right = BinaryTreeNode(4)
        }
        right = BinaryTreeNode(6).apply {
            right = BinaryTreeNode(7)
        }
    }, 9)

    println("res: $result")
}

private fun findTarget(root: BinaryTreeNode?, k: Int): Boolean {
    if (root == null) return false

    val queue: Queue<BinaryTreeNode> = LinkedList()
    val list = mutableListOf<Int>()

    queue.offer(root)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        if (polled.left != null) {
            queue.offer(polled.left)
        }

        if (polled.right != null) {
            queue.offer(polled.right)
        }

        list.forEach { a ->
            if (a + polled.value == k) {
                return true
            }
        }

        list.add(polled.value)
    }

    return false
}