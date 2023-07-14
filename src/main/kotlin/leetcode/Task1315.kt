package leetcode

import java.util.*

fun Task1315() {
    sumEvenGrandparent(BinaryTreeNode(6).apply {
        left = BinaryTreeNode(7).apply {
            left = BinaryTreeNode(2).apply {
                left = BinaryTreeNode(9)
            }
            right = BinaryTreeNode(7).apply {
                left = BinaryTreeNode(1)
                right = BinaryTreeNode(4)
            }
        }
        right = BinaryTreeNode(8).apply {
            left = BinaryTreeNode(1)
            right = BinaryTreeNode(3).apply {
                right = BinaryTreeNode(5)
            }
        }
    })
}

private fun sumEvenGrandparent(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    val queue: Queue<ParentAwareWrapper> = LinkedList()
    queue.add(ParentAwareWrapper(root, false))
    var sum = 0

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        val left = polled.node.left
        val right = polled.node.right

        val isEven = polled.node.value % 2 == 0
        val isEvenParent = polled.isEvenParent

        if (isEvenParent) {
            sum += left?.value ?: 0
            sum += right?.value ?: 0
        }

        if (left != null) queue.offer(ParentAwareWrapper(left, isEven))
        if (right != null) queue.offer(ParentAwareWrapper(right, isEven))
    }
    return sum
}

private data class ParentAwareWrapper(
    val node: BinaryTreeNode,
    val isEvenParent: Boolean
)