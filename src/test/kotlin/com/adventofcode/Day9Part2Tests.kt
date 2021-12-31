package com.adventofcode

import com.adventofcode.Day9Part1.Companion.parse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day9Part2Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test0() {
        val input = """1111
            |2222
            |3333
        """.trimMargin()
        val grid = Day9Part1.makeGrid(parse(input))
        assertThat(grid.size).isEqualTo(4)
        grid.iterator().forEach { assertThat(it.size).isEqualTo(3) }
        assertThat(grid[1][1].westNeighbor).isEqualTo(grid[0][1])
        assertThat(grid[1][1].eastNeighbor).isEqualTo(grid[2][1])
        assertThat(grid[1][1].northNeighbor).isEqualTo(grid[1][0])
        assertThat(grid[1][1].southNeighbor).isEqualTo(grid[1][2])
        assertThat(grid[0][1].westNeighbor).isNull()
        assertThat(grid[3][1].eastNeighbor).isNull()
        assertThat(grid[3][2].southNeighbor).isNull()
        assertThat(grid[3][2].eastNeighbor).isNull()
    }

    @Test
    fun test1() {
        val input = """2199943210
3987894921
9856789892
8767896789
9899965678""".trimMargin()
        assertThat(Day9Part2.eval(input)).isEqualTo(1134)
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("day9.txt").file.readText()
        val result = Day9Part2.eval(string)
        assertThat(result).isEqualTo(1056330)
    }
}
