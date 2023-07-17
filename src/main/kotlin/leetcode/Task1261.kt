package leetcode

import java.util.*

fun Task1261() {
    val fe = FindElements(BinaryTreeNode(-1).apply {
        left = BinaryTreeNode(-1).apply {
            left = BinaryTreeNode(-1)
            right = BinaryTreeNode(-1)
        }
        right = BinaryTreeNode(-1)
    })

    println(fe.find(1))
    println(fe.find(3))
    println(fe.find(5))
}

class FindElements(val root: BinaryTreeNode?) {
    fun find(target: Int): Boolean {
        if (root == null) return false

        root.value = 0

        val queue: Queue<BinaryTreeNode> = LinkedList()
        queue.offer(root)

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            if (polled.left != null) {
                polled.left?.value = 2 * polled.value + 1
                queue.offer(polled.left)
            }
            if (polled.right != null) {
                polled.right?.value = 2 * polled.value + 2
                queue.offer(polled.right)
            }

            if (polled.value == target) return true
        }

        return false
    }
}