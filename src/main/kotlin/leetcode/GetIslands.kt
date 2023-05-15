package leetcode

import java.util.LinkedList
import java.util.Queue

class GetIslands {
    data class Coordinate(
        val y: Int,
        val x: Int
    )

    data class IslandInfo(
        val size: Int,
        val hasAirport: Boolean
    )

    fun getNumberOfIslands(grid: Array<CharArray>): Int {
        var islandsNum = 0
        val visitedCells = mutableSetOf<Coordinate>()
        var needAirportIslandsCount = 0

        for (x in grid[0].indices) {
            for (y in grid.indices) {
                val cell = grid[x][y]
                val coordinate = Coordinate(x, y)

                if (visitedCells.contains(coordinate)) continue

                if (isLand(grid, coordinate)) {
                    val islandInfo = markIsland(grid, coordinate, visitedCells)
                    islandsNum++

                    println("debug: $islandInfo")

                    if (islandInfo.size >= 3 && !islandInfo.hasAirport) {
                       needAirportIslandsCount++
                    }
                }
            }
        }

        println("islandsNum: $islandsNum")
        println("needAirportIslandsCount: $needAirportIslandsCount")
        return needAirportIslandsCount
    }

    private fun markIsland(
        grid: Array<CharArray>,
        coordinate: Coordinate,
        visitedCells: MutableSet<Coordinate>
    ): IslandInfo {
        val queue: Queue<Coordinate> = LinkedList()
        queue.offer(coordinate)

        var islandSize = 0
        var hasAirport = false

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            if (isAirport(grid, polled)) {
                hasAirport = true
            }

            if (visitedCells.contains(polled)) continue

            visitedCells.add(polled)

            islandSize++

            val neighbours = getNeighbours(polled)

            val filteredNeighbours = neighbours.filter {
                isOnMap(grid, it) && isLand(grid, it) && !visitedCells.contains(it)
            }

            queue.addAll(filteredNeighbours)
        }
        return IslandInfo(islandSize, hasAirport)
    }

    private fun getNeighbours(coordinate: Coordinate): List<Coordinate> {
        return listOf(
            Coordinate(coordinate.y - 1, coordinate.x),
            Coordinate(coordinate.y + 1, coordinate.x),
            Coordinate(coordinate.y, coordinate.x - 1),
            Coordinate(coordinate.y, coordinate.x + 1)
        )
    }

    private fun isLand(
        grid: Array<CharArray>,
        coordinate: Coordinate
    ): Boolean {
        val cell = grid[coordinate.y][coordinate.x]
        return cell == '1' || isAirport(grid, coordinate)
    }

    private fun isAirport(
        grid: Array<CharArray>,
        coordinate: Coordinate
    ): Boolean {
        val cell = grid[coordinate.y][coordinate.x]

        return cell == 'A'
    }

    private fun isOnMap(
        grid: Array<CharArray>,
        coordinate: Coordinate
    ): Boolean {
        return coordinate.y >= 0 && coordinate.x >= 0 && coordinate.y <= grid.lastIndex && coordinate.x <= grid[0].lastIndex
    }
}