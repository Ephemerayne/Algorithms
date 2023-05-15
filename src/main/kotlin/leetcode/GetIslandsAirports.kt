package leetcode

import java.util.*

class GetIslandsAirports {

    data class Coordinate(
        val y: Int,
        val x: Int
    )

    data class IslandInfo(
        val hasAirport: Boolean,
        val islandSize: Int
    )

    fun getAirportsNumber(grid: Array<CharArray>): Int {
        var needAirportIslandsCount = 0
        val visitedCells = mutableSetOf<Coordinate>()

        for (y in grid.indices) {
            for (x in grid.indices) {
                val cell = grid[y][x]
                val coordinate = Coordinate(y, x)

                if (visitedCells.contains(coordinate)) continue

                if (cell == '1') {
                    val islandInfo = markIsland(grid, coordinate, visitedCells)

                    println("islandInfo: $islandInfo")

                    if (islandInfo.islandSize >= 5 && !islandInfo.hasAirport) {
                        needAirportIslandsCount++
                    }
                }
            }
        }

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

            if(isAirport(grid, polled)) {
                hasAirport = true
            }

            if (visitedCells.contains(polled)) {
                continue
            }

            visitedCells.add(polled)

            islandSize++

            val neighbours = getNeighbours(polled)

            val filteredNeighbours = neighbours.filter {
                isOnMap(grid, it) && isLand(grid, it) && !visitedCells.contains(it)
            }

            queue.addAll(filteredNeighbours)
        }

        return IslandInfo(hasAirport, islandSize)
    }

    private fun getNeighbours(
        coordinate: Coordinate
    ): List<Coordinate> {
        return listOf(
            Coordinate(coordinate.y - 1, coordinate.x),
            Coordinate(coordinate.y + 1, coordinate.x),
            Coordinate(coordinate.y, coordinate.x - 1),
            Coordinate(coordinate.y, coordinate.x + 1),
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
