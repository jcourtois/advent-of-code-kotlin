package com.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day2Part1Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun forward0isForward0() {
        assertThat(Day2Part1.eval("forward 0")).isEqualTo(0 to 0)
    }

    @Test
    fun forward1isForward1() {
        assertThat(Day2Part1.eval("forward 1")).isEqualTo(1 to 0)
    }

    @Test
    fun down1isDown1() {
        assertThat(Day2Part1.eval("down 1")).isEqualTo(0 to 1)
    }

    @Test
    fun down9isDown9() {
        assertThat(Day2Part1.eval("down 9")).isEqualTo(0 to 9)
    }

    @Test
    fun down9AndUp8isOne() {
        assertThat(Day2Part1.eval("down 9\nup 8")).isEqualTo(0 to 1)
    }

    @Test
    fun sampleInput() {
        assertThat(
            Day2Part1.eval(
                "forward 5\n" +
                        "down 5\n" +
                        "forward 8\n" +
                        "up 3\n" +
                        "down 8\n" +
                        "forward 2"
            )
        ).isEqualTo(15 to 10)
    }

    @Test
    fun answer() {
        val string = resourceLoader.getResource("day2.txt").file.readText()
        val (horizontal, depth) = Day2Part1.eval(string)
        assertThat(horizontal * depth).isEqualTo(1484118)
    }
}
