package leetcode

import java.util.*

private data class CellCoordinate(
    val y: Int,
    val x: Int
)

fun Task695() {
    maxAreaOfIsland(
        arrayOf(
//            intArrayOf(1, 1, 0, 0, 0),
//            intArrayOf(1, 1, 0, 0, 0),
//            intArrayOf(0, 0, 0, 1, 1),
//            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0),
        )
    )
}

private fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    var maxIslandSize = 0
    val visitedCells = mutableSetOf<CellCoordinate>()

    for (x in grid[0].indices) {
        for (y in grid.indices) {
            val cell = grid[y][x]
            val coordinate = CellCoordinate(y, x)

            if (visitedCells.contains(coordinate)) continue

            if (cell == 1) {
                val islandSize = markIsland(grid, coordinate, visitedCells)
                println("islandSize: $islandSize")

                if (islandSize > maxIslandSize) {
                    maxIslandSize = islandSize
                }
            }
        }
    }

    println("maxIslandSize: $maxIslandSize")
    return maxIslandSize
}

private fun markIsland(
    grid: Array<IntArray>,
    coordinate: CellCoordinate,
    visitedCells: MutableSet<CellCoordinate>
): Int {
    val queue: Queue<CellCoordinate> = LinkedList()

    queue.offer(coordinate)

    var islandSize = 0

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        if (visitedCells.contains(polled)) continue
        visitedCells.add(polled)

        islandSize++

        val neighbours = getNeighbours(polled)

        val filteredNeighbours = neighbours.filter {
            isOnMap(grid, it) && isLand(grid, it) && !visitedCells.contains(it)
        }

        queue.addAll(filteredNeighbours)
    }

    return islandSize
}

private fun getNeighbours(coordinate: CellCoordinate): List<CellCoordinate> {
    return listOf(
        CellCoordinate(coordinate.y - 1, coordinate.x),
        CellCoordinate(coordinate.y + 1, coordinate.x),
        CellCoordinate(coordinate.y, coordinate.x - 1),
        CellCoordinate(coordinate.y, coordinate.x + 1),
    )
}

private fun isLand(
    grid: Array<IntArray>,
    coordinate: CellCoordinate
): Boolean {
    val cell = grid[coordinate.y][coordinate.x]

    return cell == 1
}

private fun isOnMap(
    grid: Array<IntArray>,
    coordinate: CellCoordinate
): Boolean {
    return coordinate.y >= 0 && coordinate.x >= 0 && coordinate.y <= grid.lastIndex && coordinate.x <= grid[0].lastIndex
}