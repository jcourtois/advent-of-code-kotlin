package com.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day1Part1Tests {

	@Autowired
	private lateinit var resourceLoader: ResourceLoader

	@Test
	fun oneNumberIsZero() {
		assertThat(Day1Part1.eval("1")).isEqualTo(0)
	}

	@Test
	fun increasingIsOne() {
		assertThat(Day1Part1.eval("0 12")).isEqualTo(1)
	}

	@Test
	fun decreasingIsZero() {
		assertThat(Day1Part1.eval("12 0")).isEqualTo(0)
	}

	@Test
	fun increasingTwiceIsTwo() {
		assertThat(Day1Part1.eval("0 12 13")).isEqualTo(2)
	}

	@Test
	fun increasingThreeTimesIsThree() {
		assertThat(Day1Part1.eval("0 12 13 15")).isEqualTo(3)
	}

	@Test
	fun theAnswerIsOneThousandOneHundredEightyFour() {
		val string = resourceLoader.getResource("day1.txt").file.readText()
		assertThat(Day1Part1.eval(string, '\n')).isEqualTo(1184)
	}
}
