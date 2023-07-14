package leetcode

import java.util.HashSet
import java.util.LinkedList
import java.util.Queue

fun Task1971() {
    val res = validPath(
        n = 3,
        edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(2, 0),
        ),
        source = 0,
        destination = 2
    )

    println("res: $res")
}

/*
* n - количество вершин
* source - источник начала вершины
* destination - окончательная точка вершины
*  */
private fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {

    val parents = IntArray(n) { it }

    edges.forEach { union(it[0], it[1], parents) }

    return findParent(source, parents) == findParent(destination, parents)
}

private fun findParent(node: Int, parents: IntArray): Int {
    parents[node] = if (parents[node] == node) {
        node
    } else {
        findParent(parents[node], parents)
    }

    return parents[node]
}

private fun union(node1: Int, node2: Int, parents: IntArray) {
    parents[findParent(node1, parents)] = findParent(node2, parents)
}