package com.adventofcode

class Day9Part1 {

    companion object {

        fun eval(s: String): Int {
            val grid = makeGrid(parse(s))
            return sumOfLowPointRiskLevels(grid)
        }

        private fun sumOfLowPointRiskLevels(grid: Array<Array<Point>>) =
            grid.flatMap { it.filter(Point::isLocalMinimum).map(Point::riskLevel) }.sum()

        fun parse(input: String): Array<Array<Int>> {
            return input.trim().split('\n')
                .map { s: String -> s.trim().toList().map(Char::toString).map(String::toInt) }
                .map(List<Int>::toTypedArray).toTypedArray()
        }

        fun makeGrid(input: Array<Array<Int>>): Array<Array<Point>> {
            val height = input.size
            val width = input[0].size
            val grid = Array(width) { Array(height) { Point(0, null, null, null, null) } }
            for(x in 0 until width) {
                for(y in 0 until height) {
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
    }

    data class Point(var height: Int, var northNeighbor: Point?, var eastNeighbor: Point?, var southNeighbor: Point?, var westNeighbor: Point?) {
        fun riskLevel(): Int = 1 + height
        fun isLocalMinimum(): Boolean = listOfNotNull(northNeighbor, eastNeighbor, southNeighbor, westNeighbor)
            .all { it.height > height }

        override fun toString(): String = "Point(height=$height)"
    }
}
