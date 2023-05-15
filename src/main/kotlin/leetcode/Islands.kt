package leetcode

import java.util.*

private data class Coordinate(
    val y: Int,
    val x: Int
)

// Get islands number
class Solution {
    fun numIslands(grid: Array<IntArray>): Int {
        var islandsCount = 0
        val visitedIslands = mutableSetOf<Coordinate>()

        for (y in grid.indices) {
            for (x in grid.indices) {
                val cell = grid[y][x]
                val coordinate = Coordinate(y, x)

                if (visitedIslands.contains(coordinate)) continue

                visitedIslands.add(coordinate)

                if (cell == 1) {
                    islandsCount++

                    markCellAsIsland(grid, visitedIslands, coordinate)
                }
            }
        }

        println("islands count: $islandsCount")
        return islandsCount
    }

    private fun markCellAsIsland(
        grid: Array<IntArray>,
        visitedIslands: MutableSet<Coordinate>,
        coordinate: Coordinate
    ) {
        val queue: Queue<Coordinate> = LinkedList()

        queue.offer(coordinate)

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            visitedIslands.add(polled)

            val neighbours = getNeighbours(polled)
            val filteredNeighbours = neighbours.filter {
                isOnMap(it, grid) && isLand(grid, it) && !visitedIslands.contains(it)
            }

            queue.addAll(filteredNeighbours)
        }
    }

    private fun getNeighbours(
        coordinate: Coordinate,
    ): List<Coordinate> {
        val y = coordinate.y
        val x = coordinate.x

        val left = Coordinate(y, x - 1)
        val right = Coordinate(y, x + 1)
        val top = Coordinate(y - 1, x)
        val bottom = Coordinate(y + 1, x)

        return listOf(left, right, top, bottom)
    }

    private fun isOnMap(
        coordinate: Coordinate,
        grid: Array<IntArray>
    ): Boolean {
        val y = coordinate.y
        val x = coordinate.x

        return y >= 0 && x >= 0 && y <= grid.lastIndex && x <= grid[0].lastIndex
    }

    private fun isLand(
        grid: Array<IntArray>,
        coordinate: Coordinate
    ): Boolean {
        val cell = grid[coordinate.y][coordinate.x]

        return cell == 1
    }
}
