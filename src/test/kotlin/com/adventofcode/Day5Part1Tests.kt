package com.adventofcode

import com.adventofcode.Day5Part1.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day5Part1Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test0() {
        val grid = Grid(1, 5)
        assertThat(grid.render()).isEqualTo("""
.
.
.
.
.""".trim())
    }

    @Test
    fun test1() {
        val grid = Grid(5, 1)
        assertThat(grid.render()).isEqualTo("""
. . . . .""".trim())
    }

    @Test
    fun test2() {
        val grid = Grid(5, 5)
        grid.add(Line(Coordinate(1, 1), Coordinate(3, 3)))
        assertThat(grid.render()).isEqualTo("""
. . . . .
. 1 . . .
. . 1 . .
. . . 1 .
. . . . .""".trim())
    }

    @Test
    fun testDiagonalLineOnGrid() {
        val grid = Grid(5, 5)
        grid.add(Line(Coordinate(4, 0), Coordinate(0, 4)))
        assertThat(grid.render()).isEqualTo("""
. . . . 1
. . . 1 .
. . 1 . .
. 1 . . .
1 . . . .""".trim())
    }

    @Test
    fun testMultipleLines() {
        val grid = Grid(5, 5)
        grid.add(Line(Coordinate(4, 0), Coordinate(0, 4)))
        grid.add(Line(Coordinate(1, 0), Coordinate(4, 0)))
        grid.add(Line(Coordinate(3, 0), Coordinate(3, 3)))
        grid.add(Line(Coordinate(3, 0), Coordinate(3, 4)))
        grid.add(Line(Coordinate(1, 1), Coordinate(3, 3)))
        assertThat(grid.render()).isEqualTo("""
. 1 1 3 2
. 1 . 3 .
. . 2 2 .
. 1 . 3 .
1 . . 1 .""".trim())
    }

    @Test
    fun testMultipleLinesNoDiagonals() {
        val grid = Grid(5, 5)
        grid.add(Line(Coordinate(1, 0), Coordinate(4, 0)))
        grid.add(Line(Coordinate(3, 0), Coordinate(3, 3)))
        grid.add(Line(Coordinate(3, 0), Coordinate(3, 4)))
        assertThat(grid.render()).isEqualTo("""
. 1 1 3 1
. . . 2 .
. . . 2 .
. . . 2 .
. . . 1 .""".trim())
    }

    @Test
    fun testMultipleLinesDangerZoneCount() {
        val grid = Grid(5, 5)
        grid.add(Line(Coordinate(4, 0), Coordinate(0, 4)))
        grid.add(Line(Coordinate(1, 0), Coordinate(4, 0)))
        grid.add(Line(Coordinate(3, 0), Coordinate(3, 3)))
        grid.add(Line(Coordinate(3, 0), Coordinate(3, 4)))
        grid.add(Line(Coordinate(1, 1), Coordinate(3, 3)))
        assertThat(grid.numberOfDangerZones()).isEqualTo(4)
    }

    @Test
    fun test3() {
        val grid = Grid(5, 5)
        grid.add(Line(Coordinate(1,1), Coordinate(1, 3)))
        assertThat(grid.render()).isEqualTo("")
    }

    @Test
    fun testVerticalLine() {
        val line = Line(Coordinate(1, 1), Coordinate(1, 3))
        assertThat(line.length).isEqualTo(3)
        assertThat(line.coordinates).isEqualTo(listOf(Coordinate(1, 1), Coordinate(1, 2), Coordinate(1, 3)))
    }

    @Test
    fun testHorizontalLine() {
        val line = Line(Coordinate(0, 0), Coordinate(3, 0))
        assertThat(line.length).isEqualTo(4)
        assertThat(line.coordinates).isEqualTo(listOf(Coordinate(0, 0), Coordinate(1, 0), Coordinate(2, 0), Coordinate(3, 0)))
    }

    @Test
    fun testDiagonalLine() {
        val line = Line(Coordinate(0, 0), Coordinate(3, 3))
        assertThat(line.length).isEqualTo(4)
        assertThat(line.coordinates).isEqualTo(listOf(Coordinate(0, 0), Coordinate(1, 1), Coordinate(2, 2), Coordinate(3, 3)))
    }

    @Test
    fun testDiagonalLine2() {
        val line = Line(Coordinate(3, 0), Coordinate(0, 3))
        assertThat(line.length).isEqualTo(4)
        assertThat(line.coordinates).isEqualTo(listOf(Coordinate(3, 0), Coordinate(2, 1), Coordinate(1, 2), Coordinate(0, 3)))
    }

    @Test
    fun testParse() {
        val lines: List<Line> = Day5Part1.parse("1,1 -> 444,100")
        assertThat(lines).isEqualTo(listOf(Line(Coordinate(1,1), Coordinate(444,100))))
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("day5.txt").file.readText()
        val result = Day5Part1.eval(string)
        assertThat(result).isEqualTo(5774)
    }
}
