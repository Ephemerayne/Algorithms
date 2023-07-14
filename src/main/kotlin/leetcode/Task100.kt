package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task100() {
    val res = isSameTree(p = BinaryTreeNode(1).apply {
        left = BinaryTreeNode(2)
        right = BinaryTreeNode(3)
    },
        q = BinaryTreeNode(1).apply {
            left = BinaryTreeNode(2)
            right = BinaryTreeNode(3)
        })

    println("res: $res")
}

fun isSameTree(p: BinaryTreeNode?, q: BinaryTreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p?.value != q?.value) return false

    val queue1: Queue<BinaryTreeNode> = LinkedList()
    val queue2: Queue<BinaryTreeNode> = LinkedList()

    queue1.offer(p)
    queue2.offer(q)

    while (queue1.isNotEmpty()) {
        while (queue2.isNotEmpty()) {
            val polled1 = queue1.poll()
            val polled2 = queue2.poll()

            if (polled1.left?.value != polled2.left?.value || polled1.right != polled2.right) {
                return false
            }

            if (polled1.left != null) {
                queue1.offer(polled1.left)
            }

            if (polled1.right != null) {
                queue1.offer(polled1.right)
            }

            if (polled2.left != null) {
                queue2.offer(polled2.left)
            }

            if (polled2.right != null) {
                queue2.offer(polled2.right)
            }
        }
    }
    return true
}