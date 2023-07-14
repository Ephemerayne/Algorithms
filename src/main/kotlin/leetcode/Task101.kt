package leetcode

import java.util.LinkedList
import java.util.Queue
import kotlin.math.hypot

private data class TreeNode(val `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun Task101() {
    val res = isSymmetric(TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(3)
            right = TreeNode(4)
        }
        right = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(3)
        }
    })

    println("res: $res")
}

private fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) return false

    val queue: Queue<Pair<TreeNode?, TreeNode?>> = LinkedList()
    queue.offer(Pair(root.left, root.right))

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        val first = polled.first
        val second = polled.second

        if (first == null && second == null) continue
        if (first?.`val` != second?.`val`) return false

        queue.offer(Pair(first?.left, second?.right))
        queue.offer(Pair(first?.right, second?.left))
    }

    return true

}