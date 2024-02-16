package leetcode

import java.util.*

fun Task897() {
    increasingBST(BinaryTreeNode(2).apply {
        left = BinaryTreeNode(1)
        right = BinaryTreeNode(4).apply {
            left = BinaryTreeNode(3)
        }
    })
}

fun increasingBST(root: BinaryTreeNode?): BinaryTreeNode? {
    if (root == null) return null

    val queue: Queue<BinaryTreeNode> = LinkedList()
    val priorityQueue: PriorityQueue<BinaryTreeNode> = PriorityQueue { a, b ->
        a.value.compareTo(b.value)
    }

    queue.add(root)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        polled.left?.let {
            queue.offer(it)
        }

        polled.right?.let {
            queue.offer(it)
        }

        priorityQueue.add(polled)
    }

    println(priorityQueue.map { it.value })

    val newRoot = priorityQueue.poll()
    newRoot.left = null

    var parentNode = newRoot
    while (priorityQueue.isNotEmpty()) {
        val polled = priorityQueue.poll()

        polled.left = null
        parentNode.right = polled
        parentNode = polled
    }

    println(newRoot)
    return newRoot
}

//fun increasingBST(root: BinaryTreeNode?): BinaryTreeNode? {
//    if (root == null) return null
//
//    val queue: Queue<BinaryTreeNode> = LinkedList()
//    val priorityQueue: PriorityQueue<BinaryTreeNode> = PriorityQueue { a, b ->
//        a.value.compareTo(b.value)
//    }
//
//    queue.add(root)
//
//    while (queue.isNotEmpty()) {
//        val polled = queue.poll()
//
//        polled.left?.let {
//            queue.offer(it)
//        }
//
//        polled.right?.let {
//            queue.offer(it)
//        }
//
//        priorityQueue.add(polled)
//    }
//
//    println(priorityQueue.map { it.value })
//
//    val newRoot = priorityQueue.poll()
//    newRoot.left = null
//
//    var parentNode = newRoot
//    while (priorityQueue.isNotEmpty()) {
//        parentNode.left = null
//
//        val polled = priorityQueue.poll()
//        parentNode.right = polled
//        parentNode = polled
//    }
//
//    println(newRoot)
//    return newRoot
//}
