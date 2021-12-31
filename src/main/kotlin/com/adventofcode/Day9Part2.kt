package com.adventofcode

class Day9Part2 {

    companion object {

        fun eval(s: String): Int {
            val grid = makeGrid(parse(s))
            val minima = grid.flatMap { it.filter(Point::isLocalMinimum) }
            val basins = minima.map(::resolveBasin)
            return basins.map(Basin::size).sortedDescending().take(3).reduce(Int::times)
        }

        fun parse(input: String): Array<Array<Int>> {
            return input.trim().split('\n')
                .map { s: String -> s.trim().toList().map(Char::toString).map(String::toInt) }
                .map(List<Int>::toTypedArray).toTypedArray()
        }

        private fun makeGrid(input: Array<Array<Int>>): Array<Array<Point>> {
            val height = input.size
            val width = input[0].size
            val grid = Array(width) { Array(height) { Point(0) } }
            for(x in 0 until width) {
                for(y in 0 until height) {
                    grid[x][y].xPos = x
                    grid[x][y].yPos = y
                    grid[x][y].height = input[y][x]
                    if (x != 0) {
                        grid[x][y].westNeighbor = grid[x - 1][y]
                        grid[x - 1][y].eastNeighbor = grid[x][y]
                    }
                    if (y != 0) {
                        grid[x][y].northNeighbor = grid[x][y - 1]
                        grid[x][y - 1].southNeighbor = grid[x][y]
                    }
                }
            }
            return grid
        }

        private fun resolveBasin(minimum: Point): Basin {
            val basin = Basin(points = mutableListOf())
            return recurse(basin, mutableListOf(minimum))
        }

        private fun recurse(basin: Basin, pointsToTraverse: List<Point>): Basin {
            val nextPointsToTraverse = mutableListOf<Point>()
            for (point in pointsToTraverse) {
                for (neighbor in point.neighbors()) {
                    if (neighbor.height == 9 || neighbor.traversed || nextPointsToTraverse.contains(neighbor)) continue
                    nextPointsToTraverse.add(neighbor)
                }
                basin.points.add(point)
                point.traversed = true
            }
            return if (nextPointsToTraverse.isNotEmpty()) recurse(basin, nextPointsToTraverse) else basin
        }
    }

    data class Point(var height: Int, var xPos: Int? = null, var yPos: Int? = null, var traversed: Boolean = false,
                     var northNeighbor: Point? = null, var eastNeighbor: Point? = null,
                     var southNeighbor: Point? = null, var westNeighbor: Point? = null) {
        fun isLocalMinimum(): Boolean = listOfNotNull(northNeighbor, eastNeighbor, southNeighbor, westNeighbor)
            .all { it.height > height }
        fun neighbors() = listOfNotNull(northNeighbor, eastNeighbor, southNeighbor, westNeighbor)

        override fun toString(): String = "Point[$xPos,$yPos](height=$height)"
    }

    data class Basin(val points: MutableList<Point>) {
        fun size() = points.size
    }
}
