package leetcode

import java.util.LinkedList
import java.util.Queue

private data class PixelCoordinate(
    val y: Int,
    val x: Int
)
fun Task733() {
    floodFill(
        arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 0, 1),
        ), 1, 1, 2
    )
}

private fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
    val startCoordinate = PixelCoordinate(sr, sc)
    val visitedPixels = mutableSetOf<PixelCoordinate>()

    val queue: Queue<PixelCoordinate> = LinkedList()
    queue.offer(startCoordinate)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        println("debug: $polled")

        if (visitedPixels.contains(polled)) continue
        visitedPixels.add(polled)

        val neighbours = getNeighbours(polled).filter {
            isOnMap(image, it) && isSameColor(image, polled, it)
        }

        neighbours.forEach { queue.offer(it) }

        image[polled.y][polled.x] = color
    }

    return image
}

private fun getNeighbours(coordinate: PixelCoordinate): List<PixelCoordinate> {
    return listOf(
        PixelCoordinate(coordinate.y - 1, coordinate.x),
        PixelCoordinate(coordinate.y + 1, coordinate.x),
        PixelCoordinate(coordinate.y, coordinate.x - 1),
        PixelCoordinate(coordinate.y, coordinate.x + 1),
    )
}

private fun isSameColor(image: Array<IntArray>, c1: PixelCoordinate, c2: PixelCoordinate): Boolean {
    val color1 = image[c1.y][c1.x]
    val color2 = image[c2.y][c2.x]

    return color1 == color2
}

private fun isOnMap(
    image: Array<IntArray>,
    coordinate: PixelCoordinate
):Boolean {
    return coordinate.y >= 0 && coordinate.x >=0 && coordinate.y <= image.lastIndex && coordinate.x <= image[0].lastIndex
}
