package com.adventofcode

import com.adventofcode.Day3Part1.Analysis
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day3Part1Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test1() {
        assertThat(Day3Part1.parse("0")).isEqualTo(listOf(listOf(0)))
    }

    @Test
    fun test2() {
        assertThat(Day3Part1.parse("1")).isEqualTo(listOf(listOf(1)))
    }

    @Test
    fun test3() {
        assertThat(Day3Part1.parse("01")).isEqualTo(listOf(listOf(0, 1)))
    }

    @Test
    fun test4() {
        assertThat(Day3Part1.parse("01\n10")).isEqualTo(listOf(listOf(0, 1), listOf(1, 0)))
    }

    @Test
    fun test5() {
        assertThat(Day3Part1.count("01\n10")).isEqualTo(Analysis(lines = 2, columnCounts = listOf(1, 1)))
    }

    @Test
    fun test6() {
        assertThat(Day3Part1.count("00\n00")).isEqualTo(Analysis(lines = 2, columnCounts = listOf(0, 0)))
    }

    @Test
    fun test7() {
        assertThat(Day3Part1.count("11\n11\n10\n10\n10")).isEqualTo(Analysis(lines = 5, columnCounts = listOf(5, 2)))
    }

    @Test
    fun test8() {
        assertThat(
            Day3Part1.count(
                "00100\n11110\n10110\n10111\n10101\n01111\n00111\n11100\n10000\n11001\n00010\n01010"))
            .isEqualTo(Analysis(lines = 12, columnCounts = listOf(7, 5, 8, 7, 5)))
    }

    @Test
    fun test9() {
        with (Day3Part1) { assertThat(listOf(0).toInt()).isEqualTo(0) }
    }

    @Test
    fun test10() {
        with(Day3Part1) { assertThat(listOf(1).toInt()).isEqualTo(1) }
    }

    @Test
    fun test11() {
        with(Day3Part1) { assertThat(listOf(1, 1).toInt()).isEqualTo(3) }
    }

    @Test
    fun test12() {
        with(Day3Part1) { assertThat(listOf(1, 0).toInt()).isEqualTo(2) }
    }

    @Test
    fun test13() {
        with(Day3Part1) { assertThat(listOf(1, 0, 1, 1, 0).toInt()).isEqualTo(22) }
    }


    @Test
    fun test14() {
        with(Day3Part1) { assertThat(listOf(1).toInvInt()).isEqualTo(0) }
    }

    @Test
    fun test15() {
        with(Day3Part1) { assertThat(listOf(1, 1).toInvInt()).isEqualTo(0) }
    }

    @Test
    fun test16() {
        with (Day3Part1) { assertThat(listOf(1, 0).toInvInt()).isEqualTo(1) }
    }

    @Test
    fun test17() {
        with (Day3Part1) { assertThat(listOf(1, 0, 1, 1, 0).toInvInt()).isEqualTo(9) }
    }

    @Test
    fun test18() {
        assertThat(
            Day3Part1.analyze(
                "00100\n11110\n10110\n10111\n10101\n01111\n00111\n11100\n10000\n11001\n00010\n01010"))
            .isEqualTo(22 to 9)
    }

    @Test
    fun test19() {
        val result = Day3Part1.powerConsumption(
            "00100\n11110\n10110\n10111\n10101\n01111\n00111\n11100\n10000\n11001\n00010\n01010"
        )
        assertThat(result).isEqualTo(198)
    }

    @Test
    fun test20() {
        val string = resourceLoader.getResource("day3.txt").file.readText()
        assertThat(Day3Part1.powerConsumption(string)).isEqualTo(3148794)
    }
}
