package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task993() {
    val res = isCousins(BinaryTreeNode(1).apply {
        left = BinaryTreeNode(2).apply {
            right = BinaryTreeNode(4)
        }
        right = BinaryTreeNode(3).apply {
            right = BinaryTreeNode(5)
        }
    }, 5, 4)

    println("dbug: $res")
}

fun isCousins(root: BinaryTreeNode?, x: Int, y: Int): Boolean {
    if (root == null) return false

    val levelContainer = mutableListOf<NodeWithParent>()
    levelContainer.add(NodeWithParent(root, null))

    while (levelContainer.isNotEmpty()) {
        val queue: Queue<NodeWithParent> = LinkedList()
        queue.addAll(levelContainer)

        for (node1 in levelContainer) {
            for (node2 in levelContainer) {
                if (node1.node.value == node2.node.value) continue
                if (isSameParent(node1, node2)) continue

                val val1 = node1.node.value
                val val2 = node2.node.value

                if((val1 == x && val2 == y) || (val1 == y && val2 == x)) {
                    return true
                }
            }
        }

        levelContainer.clear()

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            polled.node.left?.let {
                levelContainer.add(NodeWithParent(it, polled.node))
            }

            polled.node.right?.let {
                levelContainer.add(NodeWithParent(it, polled.node))
            }
        }
    }

    return false
}

private data class NodeWithParent(
    val node: BinaryTreeNode,
    val parent: BinaryTreeNode?
)

private fun isSameParent(node1: NodeWithParent, node2: NodeWithParent): Boolean {
    return node1.parent == node2.parent
}
