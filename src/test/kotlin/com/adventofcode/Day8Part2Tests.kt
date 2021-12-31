package com.adventofcode

import com.adventofcode.Day8Part2.Companion.decodeSignals
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day8Part2Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun test0() {
        val answerMapping = decodeSignals(listOf("be", "edb"))
        assertThat(answerMapping).isEqualTo(mapOf("be".toSet() to 1, "ebd".toSet() to 7))
    }

    @Test
    fun test1() {
        val answerMapping = decodeSignals(listOf("be", "edb", "acedgfb", "eafb"))
        assertThat(answerMapping).isEqualTo(
            mapOf("be".toSet() to 1, "eafb".toSet() to 4, "edb".toSet() to 7, "acedgfb".toSet() to 8))
    }

    @Test
    fun test2() {
        val answerMapping = decodeSignals(listOf("be", "edb", "acedgfb", "eafb", "cefabd"))
        assertThat(answerMapping).isEqualTo(
            mapOf("be".toSet() to 1, "eafb".toSet() to 4, "edb".toSet() to 7,
                "acedgfb".toSet() to 8, "cefabd".toSet() to 9))
    }

    @Test
    fun testSixSegmentNumbers() {
        val answerMapping = decodeSignals(listOf("acedgfb", "dab", "cefabd", "cdfgeb", "eafb", "cagedb", "ab"))
        assertThat(answerMapping).isEqualTo(
            mapOf("cagedb".toSet() to 0, "ab".toSet() to 1, "eafb".toSet() to 4, "cdfgeb".toSet() to 6,
                "dab".toSet() to 7, "acedgfb".toSet() to 8, "cefabd".toSet() to 9))
    }

    @Test
    fun testFiveSegmentNumbers() {
        val answerMapping = decodeSignals(
            listOf("acedgfb", "dab", "cefabd", "cdfgeb", "eafb", "cagedb", "ab", "cdfbe", "gcdfa", "fbcad"))
        assertThat(answerMapping).isEqualTo(
            mapOf("ab".toSet() to 1, "cagedb".toSet() to 0, "gcdfa".toSet() to 2, "fbcad".toSet() to 3,
                "eafb".toSet() to 4, "cdfbe".toSet() to 5, "cdfgeb".toSet() to 6, "dab".toSet() to 7,
                "acedgfb".toSet() to 8, "cefabd".toSet() to 9))
    }

    @Test
    fun testDecodeOutput() {
        val entry: Day8Part2.Entry = Day8Part2.parse(
            """acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf""").first()
        assertThat(entry.decodeOutput()).isEqualTo(5353)
    }

    @Test
    fun testDecodeExample() {
        val value: Int = Day8Part2.eval(
            """be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce""")
        assertThat(value).isEqualTo(61229)
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("Day8.txt").file.readText()
        val result = Day8Part2.eval(string)
        assertThat(result).isEqualTo(1016804)
    }
}
