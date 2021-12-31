package com.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ResourceLoader

@SpringBootTest
class Day10Part1Tests {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun testIncorrectBraceYields1197() {
        val line = Day10Part1.Line("{([(<{}[<>[]}>{[]{[(<()>")
        assertThat(line.validate()).isEqualTo(1197)
    }

    @Test
    fun testIncorrectParenthesisYields3() {
        val line = Day10Part1.Line("[[<[([]))<([[{}[[()]]]")
        assertThat(line.validate()).isEqualTo(3)
    }

    @Test
    fun testIncorrectBracketYields57() {
        val line = Day10Part1.Line("[{[{({}]{}}([{[{{{}}([]")
        assertThat(line.validate()).isEqualTo(57)
    }

    @Test
    fun testIncorrectAngleBracketYields25137() {
        val line = Day10Part1.Line("<{([([[(<>()){}]>(<<{{")
        assertThat(line.validate()).isEqualTo(25137)
    }

    @Test
    fun testIncompleteButValidYields0() {
        val line = Day10Part1.Line("[({(<(())[]>[[{[]{<()<>>")
        assertThat(line.validate()).isEqualTo(0)
    }

    @Test
    fun testSimpleExample() {
        val result = Day10Part1.eval("""[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]""")
        assertThat(result).isEqualTo(26397)
    }

    @Test
    fun testSolvePuzzle() {
        val string = resourceLoader.getResource("day10.txt").file.readText()
        val result = Day10Part1.eval(string)
        assertThat(result).isEqualTo(321237)
    }
}
