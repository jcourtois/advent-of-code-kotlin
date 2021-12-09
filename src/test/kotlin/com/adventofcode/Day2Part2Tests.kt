package com.adventofcode

import com.adventofcode.Day2Part2.Companion.Sub
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day2Part2Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun forward0isForward0() {
        assertThat(Day2Part2.eval("forward 0")).isEqualTo(Sub(0, 0, 0))
    }

    @Test
    fun forward1isForward1() {
        assertThat(Day2Part2.eval("forward 5")).isEqualTo(Sub(5, 0, 0))
    }

    @Test
    fun down1isDown1() {
        assertThat(Day2Part2.eval("down 5\nforward 5")).isEqualTo(Sub(5, 25, 5))
    }

    @Test
    fun down9isDown9() {
        assertThat(Day2Part2.eval("down 5\nforward 5\nup 5")).isEqualTo(Sub(5, 25, 0))
    }

    @Test
    fun down9AndUp8isOne() {
        assertThat(Day2Part2.eval("down 5\nforward 5\nup 5\nforward 100")).isEqualTo(Sub(105, 25, 0))
    }

    @Test
    fun sampleInput() {
        assertThat(
            Day2Part2.eval(
                "forward 5\n" +
                        "down 5\n" +
                        "forward 8\n" +
                        "up 3\n" +
                        "down 8\n" +
                        "forward 2"
            )
        ).isEqualTo(Sub(15, 60, 10))
    }

    @Test
    fun answer() {
        val string = resourceLoader.getResource("day2.txt").file.readText()
        val (horizontal, depth) = Day2Part2.eval(string)
        assertThat(horizontal * depth).isEqualTo(1463827010)
    }
}
