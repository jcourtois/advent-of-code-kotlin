package com.adventofcode

import com.adventofcode.Day3Part2.Companion.parse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day3Part2Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test1() {
        assertThat(Day3Part2.parse("0")).isEqualTo(listOf(listOf(0)))
    }

    @Test
    fun test2() {
        assertThat(Day3Part2.parse("1")).isEqualTo(listOf(listOf(1)))
    }

    @Test
    fun test3() {
        assertThat(Day3Part2.parse("01")).isEqualTo(listOf(listOf(0, 1)))
    }

    @Test
    fun test4() {
        assertThat(Day3Part2.parse("01\n10")).isEqualTo(listOf(listOf(0, 1), listOf(1, 0)))
    }

    @Test
    fun test9() {
        with (Day3Part2) { assertThat(listOf(0).toInt()).isEqualTo(0) }
    }

    @Test
    fun test10() {
        with(Day3Part2) { assertThat(listOf(1).toInt()).isEqualTo(1) }
    }

    @Test
    fun test11() {
        with(Day3Part2) { assertThat(listOf(1, 1).toInt()).isEqualTo(3) }
    }

    @Test
    fun test12() {
        with(Day3Part2) { assertThat(listOf(1, 0).toInt()).isEqualTo(2) }
    }

    @Test
    fun test13() {
        with(Day3Part2) { assertThat(listOf(1, 0, 1, 1, 0).toInt()).isEqualTo(22) }
    }


    @Test
    fun test18() {
        assertThat(
            Day3Part2.analyze(
                "00100\n11110\n10110\n10111\n10101\n01111\n00111\n11100\n10000\n11001\n00010\n01010"))
            .isEqualTo(23 to 10)
    }

    @Test
    fun test19() {
        val result = Day3Part2.lifeSupportRating(
            "00100\n11110\n10110\n10111\n10101\n01111\n00111\n11100\n10000\n11001\n00010\n01010"
        )
        assertThat(result).isEqualTo(230)
    }

    @Test
    fun test20() {
        val string = resourceLoader.getResource("day3.txt").file.readText()
        assertThat(Day3Part2.lifeSupportRating(string)).isEqualTo(2795310)
    }
}
