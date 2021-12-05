package com.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day1Part2Tests {

	@Autowired
	private lateinit var resourceLoader: ResourceLoader

	@Test
	fun test1() {
		val triple = Day1Part2.fold("199 200 208", ' ')
		assertThat(triple.previousResult).isEqualTo(607)
		assertThat(triple.oneAgo).isEqualTo(208)
		assertThat(triple.twoAgo).isEqualTo(200)
		assertThat(triple.acc).isEqualTo(0)
	}

	@Test
	fun test2() {
		val triple = Day1Part2.fold("199 200 208 210", ' ')
		assertThat(triple.previousResult).isEqualTo(618)
		assertThat(triple.oneAgo).isEqualTo(210)
		assertThat(triple.twoAgo).isEqualTo(208)
		assertThat(triple.acc).isEqualTo(1)
	}


	@Test
	fun test3() {
		val triple = Day1Part2.fold("199 200 208 210 200", ' ')
		assertThat(triple.previousResult).isEqualTo(618)
		assertThat(triple.oneAgo).isEqualTo(200)
		assertThat(triple.twoAgo).isEqualTo(210)
		assertThat(triple.acc).isEqualTo(1)
	}

	@Test
	fun test4() {
		val triple = Day1Part2.fold("199 200 208 210 200 207", ' ')
		assertThat(triple.previousResult).isEqualTo(617)
		assertThat(triple.oneAgo).isEqualTo(207)
		assertThat(triple.twoAgo).isEqualTo(200)
		assertThat(triple.acc).isEqualTo(1)
	}

	@Test
	fun test5() {
		val triple = Day1Part2.fold("199 200 208 210 200 207 240", ' ')
		assertThat(triple.previousResult).isEqualTo(647)
		assertThat(triple.oneAgo).isEqualTo(240)
		assertThat(triple.twoAgo).isEqualTo(207)
		assertThat(triple.acc).isEqualTo(2)
	}

	@Test
	fun test6() {
		val triple = Day1Part2.fold("199 200 208 210 200 207 240 269", ' ')
		assertThat(triple.previousResult).isEqualTo(716)
		assertThat(triple.oneAgo).isEqualTo(269)
		assertThat(triple.twoAgo).isEqualTo(240)
		assertThat(triple.acc).isEqualTo(3)
	}

	@Test
	fun test7() {
		val triple = Day1Part2.fold("199 200 208 210 200 207 240 269 260", ' ')
		assertThat(triple.previousResult).isEqualTo(769)
		assertThat(triple.oneAgo).isEqualTo(260)
		assertThat(triple.twoAgo).isEqualTo(269)
		assertThat(triple.acc).isEqualTo(4)
	}

	@Test
	fun test8() {
		val triple = Day1Part2.fold("199 200 208 210 200 207 240 269 260 263", ' ')
		assertThat(triple.previousResult).isEqualTo(792)
		assertThat(triple.oneAgo).isEqualTo(263)
		assertThat(triple.twoAgo).isEqualTo(260)
		assertThat(triple.acc).isEqualTo(5)
	}

	@Test
	fun theAnswerIsOneThousandOneHundredEightyFour() {
		val string = resourceLoader.getResource("day1.txt").file.readText()
		assertThat(Day1Part2.eval(string, '\n')).isEqualTo(1158)
	}
}
