package leetcode

import java.util.*

fun Task112() {
    val res = hasPathSum(root = BinaryTreeNode(5).apply {
        left = BinaryTreeNode(4).apply {
            left = BinaryTreeNode(11).apply {
                left = BinaryTreeNode(7)
                right = BinaryTreeNode(2)
            }
        }
        right = BinaryTreeNode(8).apply {
            left = BinaryTreeNode(13)
            right = BinaryTreeNode(4).apply {
                right = BinaryTreeNode(1)
            }
        }
    }, targetSum = 22)

    println("res: $res")
}

private fun hasPathSum(root: BinaryTreeNode?, targetSum: Int): Boolean {
    if (root == null) return false
    return rec(root, targetSum, 0)
}

private fun rec(root: BinaryTreeNode?, targetSum: Int, prevSum: Int): Boolean {
    if (root == null) return false
    val isLeaf = root.left == null && root.right == null
    val rootVal = root.value
    return if (isLeaf) {
        prevSum + rootVal == targetSum
    } else {
        rec(root.left, targetSum, prevSum + rootVal) || rec(root.right, targetSum, prevSum + rootVal)
    }
}