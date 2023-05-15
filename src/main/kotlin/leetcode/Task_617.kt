package leetcode

import java.util.*

fun task617() {
    mergeTrees(
        BinaryTreeNode(1).apply {
            left = BinaryTreeNode(3).apply {
                left = BinaryTreeNode(5)
            }
            right = BinaryTreeNode(2)
        },
        BinaryTreeNode(2).apply {
            left = BinaryTreeNode(1).apply {
                right = BinaryTreeNode(4)
            }
            right = BinaryTreeNode(3).apply {
                right = BinaryTreeNode(7)
            }
        }
    )
}

fun mergeTrees(root1: BinaryTreeNode?, root2: BinaryTreeNode?): BinaryTreeNode? {
    if (root1 == null) return root2
    if (root2 == null) return root1

    val firstQueue: Queue<BinaryTreeNode> = LinkedList()
    val secondQueue: Queue<BinaryTreeNode> = LinkedList()

    firstQueue.add(root1)
    secondQueue.add(root2)

    while (firstQueue.isNotEmpty() && secondQueue.isNotEmpty()) {
        val firstNode = firstQueue.poll()
        val secondNode = secondQueue.poll()

        firstNode.value += secondNode.value

        if (firstNode.left != null && secondNode.left != null) {
            firstQueue.add(firstNode.left)
            secondQueue.add(secondNode.left)
        }

        if (firstNode.right != null && secondNode.right != null) {
            firstQueue.add(firstNode.right)
            secondQueue.add(secondNode.right)
        }

        if (firstNode.left == null && secondNode.left != null) firstNode.left = secondNode.left
        if (firstNode.right == null && secondNode.right != null) firstNode.right = secondNode.right
    }

    println("debug: ${root1}")
    return root1
}
