package leetcode

import java.util.LinkedList
import java.util.Queue
import kotlin.math.absoluteValue

fun Task783() {
    minDiffInBST(BinaryTreeNode(4).apply {
        left = BinaryTreeNode(2).apply {
            left = BinaryTreeNode(1)
            right = BinaryTreeNode(3)
        }
        right = BinaryTreeNode(6)
    })
}

private fun minDiffInBST(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    val queue: Queue<BinaryTreeNode> = LinkedList()
    val list = mutableListOf<Int>()
    var minDiff = Int.MAX_VALUE

    queue.offer(root)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        if (polled.left != null) queue.offer(polled.left)
        if (polled.right != null) queue.offer(polled.right)

        list.forEach { number ->
            var diff = number - polled.value

            if (diff < 0) {
                diff *= -1
            }

            if (minDiff > diff) {
                minDiff = diff
            }
        }

        list.add(polled.value)
    }

    println(minDiff)
    return minDiff
}