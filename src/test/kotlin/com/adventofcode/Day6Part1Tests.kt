package com.adventofcode

import com.adventofcode.Day5Part1.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day6Part1Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test0() {
        val result = Day6Part1.eval("3,4,3,1,2", 1)
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun test1() {
        val result = Day6Part1.eval("3,4,3,1,2", 2)
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun test3() {
        val result = Day6Part1.eval("3,4,3,1,2", 26)
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("day6.txt").file.readText()
        val result = Day6Part1.eval(string, 80)
        assertThat(result).isEqualTo(0)
    }
}
