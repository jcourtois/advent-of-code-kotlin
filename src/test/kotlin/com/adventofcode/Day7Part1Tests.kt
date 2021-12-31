package com.adventofcode

import com.adventofcode.Day5Part1.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day7Part1Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test0() {
        val result = Day7Part1.eval("16,1,2,0,4,2,7,1,2,14")
        assertThat(result).isEqualTo(37)
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("Day7.txt").file.readText()
        val result = Day7Part1.eval(string)
        assertThat(result).isEqualTo(0)
    }
}
