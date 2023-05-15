package leetcode

import java.util.LinkedList
import java.util.Queue

data class BinaryTreeNode(var value: Int) {
    var left: BinaryTreeNode? = null
    var right: BinaryTreeNode? = null

    override fun toString(): String {
        val sb = StringBuffer("[")

        val queue: Queue<BinaryTreeNode?> = LinkedList()
        queue.offer(this)

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            sb.append("${polled?.value}, ")

            if (polled==null) continue

            queue.offer(polled.left)
            queue.offer(polled.right)
        }

        sb.append("]")
        return sb.toString()
    }
}

data class TreeNode(var value: Int) {
    var children: List<TreeNode> = listOf()
}