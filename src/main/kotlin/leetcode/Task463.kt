package leetcode

import java.util.LinkedList
import java.util.Queue

fun Task463() {
    islandPerimeter(
        arrayOf(
            intArrayOf(1, 1),
            intArrayOf(1, 1),
        )
    )
}

private fun islandPerimeter(grid: Array<IntArray>): Int {
    val visitedCells = mutableSetOf<NumberOfClosedIslands.Coordinate>()
    var islandPerimeter = 0

    for (y in grid.indices) {
        for (x in grid[0].indices) {
            val coordinate = NumberOfClosedIslands.Coordinate(y, x)

            if (visitedCells.contains(coordinate)) continue

            if (isLand(grid, coordinate)) {
                islandPerimeter = getIslandPerimeter(grid, coordinate, visitedCells)
            }
        }
    }

    println("debug: $islandPerimeter")
    return islandPerimeter
}

private fun isLand(
    grid: Array<IntArray>,
    coordinate: NumberOfClosedIslands.Coordinate
): Boolean {
    val cell = grid[coordinate.y][coordinate.x]
    return cell == 1
}

private fun isWater(
    grid: Array<IntArray>,
    coordinate: NumberOfClosedIslands.Coordinate
): Boolean {
    return !isOnMap(grid, coordinate) || !isLand(grid, coordinate)
}

private fun getIslandPerimeter(
    grid: Array<IntArray>,
    coordinate: NumberOfClosedIslands.Coordinate,
    visitedCells: MutableSet<NumberOfClosedIslands.Coordinate>
): Int {
    val queue: Queue<NumberOfClosedIslands.Coordinate> = LinkedList()
    queue.offer(coordinate)

    var totalWaterNeighbours = 0

    while (queue.isNotEmpty()) {
        val polled = queue.poll()

        if (visitedCells.contains(polled)) continue
        visitedCells.add(polled)

        val neighbours = getNeighbours(polled)
        val waterNeighboursCount = neighbours.size - neighbours.filterNot { isWater(grid, it) }.size

        println("polled: $polled")
        println("waterNeighboursCount: $waterNeighboursCount")

        totalWaterNeighbours += waterNeighboursCount

        val filteredNeighbours = neighbours.filter {
            isOnMap(grid, it) && isLand(grid, it)
        }

        queue.addAll(filteredNeighbours)
    }

    return totalWaterNeighbours
}

private fun getNeighbours(
    coordinate: NumberOfClosedIslands.Coordinate
): List<NumberOfClosedIslands.Coordinate> {
    return listOf(
        NumberOfClosedIslands.Coordinate(coordinate.y - 1, coordinate.x),
        NumberOfClosedIslands.Coordinate(coordinate.y + 1, coordinate.x),
        NumberOfClosedIslands.Coordinate(coordinate.y, coordinate.x - 1),
        NumberOfClosedIslands.Coordinate(coordinate.y, coordinate.x + 1),
    )
}

private fun isOnMap(
    grid: Array<IntArray>,
    coordinate: NumberOfClosedIslands.Coordinate
): Boolean {
    return coordinate.y >= 0 && coordinate.x >= 0 && coordinate.y <= grid.lastIndex && coordinate.x <= grid[0].lastIndex
}