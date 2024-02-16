package leetcode

import java.util.*
import kotlin.collections.HashMap

fun Task1123() {
    println(
        "debug: ${
            lcaDeepestLeaves(BinaryTreeNode(1).apply {
                left = BinaryTreeNode(2).apply {
                    left = BinaryTreeNode(3).apply {
                        right = BinaryTreeNode(6)
                    }
                    right = BinaryTreeNode(4).apply {
                        right = BinaryTreeNode(5)
                    }
                }
            })
        }"
    )
}

private fun lcaDeepestLeaves(root: BinaryTreeNode?): BinaryTreeNode? {
    if (root == null) return null

    var i = 0
    val levelContainer = mutableListOf<Wrap>()
    levelContainer.add(
        Wrap(
            hashMapOf(i to root),
            root,
        )
    )

    val lowestLevel = mutableListOf<Wrap>()

    while (levelContainer.isNotEmpty()) {
        val queue: Queue<Wrap> = LinkedList()
        queue.addAll(levelContainer)

        lowestLevel.clear()
        lowestLevel.addAll(levelContainer)

        levelContainer.clear()

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            val parentHistory = polled.history

            polled.node.left?.let {
                val leftHistory = hashMapOf<Int, BinaryTreeNode>().apply {
                    putAll(parentHistory)
                    put(i, polled.node)
                }
                levelContainer.add(
                    Wrap(leftHistory, it)
                )
            }

            polled.node.right?.let {
                val rightHistory = hashMapOf<Int, BinaryTreeNode>().apply {
                    putAll(parentHistory)
                    put(i, polled.node)
                }
                levelContainer.add(
                    Wrap(rightHistory, it)
                )
            }
        }

        i++
    }

     for ((index, lowestNode) in lowestLevel.withIndex()) {
        println("value: ${lowestNode.node.value}")
        println("value: ${lowestNode.history}")
        println("value: ====")



    }

    return null
}

data class Wrap(
    val history: HashMap<Int, BinaryTreeNode>,
    val node: BinaryTreeNode
)
