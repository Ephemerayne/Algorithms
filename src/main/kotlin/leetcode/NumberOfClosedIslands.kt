package leetcode

import java.util.LinkedList
import java.util.Queue

class NumberOfClosedIslands {

    data class Coordinate(
        val y: Int,
        val x: Int
    )

    fun closedIsland(grid: Array<IntArray>): Int {
        var numOfClosedIslands = 0
        val visitedCells = mutableSetOf<Coordinate>()

        for (y in grid.indices) {
            for (x in grid[0].indices) {
                val coordinate = Coordinate(y, x)

                if (visitedCells.contains(coordinate)) continue


                if (isLand(grid, coordinate)) {
                    val isNearBorder = isIslandNearBorder(grid, coordinate, visitedCells)

                    if (!isNearBorder) {
                        numOfClosedIslands++
                    }
                }
            }
        }

        println("debug: numOfClosedIslands: $numOfClosedIslands")
        return numOfClosedIslands
    }

    private fun isIslandNearBorder(
        grid: Array<IntArray>,
        coordinate: Coordinate,
        visitedCells: MutableSet<Coordinate>
    ): Boolean {
        val queue: Queue<Coordinate> = LinkedList()
        queue.offer(coordinate)

        var isIslandNearBorder = false

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            if (visitedCells.contains(polled)) continue
            visitedCells.add(polled)

            if (isCellNearBorder(grid, polled)) {
                isIslandNearBorder = true
            }

            val filteredNeighbours = getNeighbours(polled).filter {
                isOnMap(grid, it) && isLand(grid, it) && !visitedCells.contains(it)
            }

            queue.addAll(filteredNeighbours)
        }

        return isIslandNearBorder
    }

    private fun isLand(grid: Array<IntArray>, coordinate: Coordinate): Boolean {
        val cell = grid[coordinate.y][coordinate.x]
        return cell == 0
    }

    private fun isOnMap(grid: Array<IntArray>, coordinate: Coordinate): Boolean {
        return coordinate.y >= 0 && coordinate.x >= 0 && coordinate.y <= grid.lastIndex && coordinate.x <= grid[0].lastIndex
    }

    private fun getNeighbours(coordinate: Coordinate): List<Coordinate>{
        return listOf(
            Coordinate(coordinate.y, coordinate.x + 1),
            Coordinate(coordinate.y, coordinate.x - 1),
            Coordinate(coordinate.y + 1, coordinate.x),
            Coordinate(coordinate.y - 1, coordinate.x),
        )
    }

    private fun isCellNearBorder(grid: Array<IntArray>, coordinate: Coordinate): Boolean {
      return coordinate.y == 0 || coordinate.x == 0 || coordinate.y == grid.lastIndex || coordinate.x == grid[0].lastIndex
    }
}