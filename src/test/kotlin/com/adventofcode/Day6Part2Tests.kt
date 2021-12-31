package com.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day6Part2Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test0() {
        val result = listOf(1, 2, 1, 2, 2, 4)
        assertThat(result.groupBy { it }).isEqualTo(mapOf(1 to listOf(1, 1), 2 to listOf(2, 2, 2), 4 to listOf(4)))
    }

    @Test
    fun test2() {
        val result = Day6Part2.eval("3,4,3,1,2", 1)
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun test3() {
        val result = Day6Part2.eval("3,4,3,1,2", 2)
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun test4() {
        val result = Day6Part2.eval("3,4,3,1,2", 5)
        assertThat(result).isEqualTo(10)
    }

    @Test
    fun test5() {
        val result = Day6Part2.eval("3,4,3,1,2", 80)
        assertThat(result).isEqualTo(5934)
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("day6.txt").file.readText()
        val result = Day6Part2.eval(string, 256)
        assertThat(result).isEqualTo(1754000560399L)
    }
}
