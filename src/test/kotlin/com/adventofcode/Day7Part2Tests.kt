package com.adventofcode

import com.adventofcode.Day5Part1.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day7Part2Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test0() {
        val result = Day7Part2.eval("16,1,2,0,4,2,7,1,2,14")
        assertThat(result).isEqualTo(168)
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("Day7.txt").file.readText()
        val result = Day7Part2.eval(string)
        assertThat(result).isEqualTo(96744904)
    }
}
