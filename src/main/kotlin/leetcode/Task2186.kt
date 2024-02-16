package leetcode

fun Task2186() {
    createBinaryTree(
        arrayOf(
            intArrayOf(20, 15, 1),
            intArrayOf(20, 17, 0),
            intArrayOf(50, 20, 1),
            intArrayOf(50, 80, 0),
            intArrayOf(80, 19, 1),
        )
    )
}

private fun createBinaryTree(descriptions: Array<IntArray>): BinaryTreeNode? {
    val storage = HashMap<Int, BinaryTreeNode>()

    val parentsValues = mutableSetOf<Int>()
    val childrenValues = mutableSetOf<Int>()

    for (description in descriptions) {
        val nodeValue = description[0]
        val nodeChildValue = description[1]
        val isLeft = description[2] == 1

        parentsValues.add(nodeValue)
        childrenValues.add(nodeChildValue)

        var treeNode = storage[nodeValue]
        if (treeNode == null) {
            treeNode = BinaryTreeNode(nodeValue)
        }

        var childTreeNode = storage[nodeChildValue]
        if (childTreeNode == null) {
            childTreeNode = BinaryTreeNode(nodeChildValue)
        }

        if (isLeft) {
            treeNode.left = childTreeNode
        } else {
            treeNode.right = childTreeNode
        }

        storage[nodeValue] = treeNode
        storage[nodeChildValue] = childTreeNode
    }

    val rootNodeValue = (parentsValues - childrenValues).first()

    println(storage[rootNodeValue])

    return storage[rootNodeValue]
}