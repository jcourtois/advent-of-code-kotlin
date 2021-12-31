package com.adventofcode

import java.util.*

class Day10Part2 {

    companion object {

        fun eval(s: String): Long {
            val autocompleteScores = parse(s).filter { line -> line.validate() == 0 }
                .map(Line::autocomplete)
                .map(::scoreAutocomplete)
                .sorted()
            return autocompleteScores[(autocompleteScores.size - 1) / 2]
        }

        fun parse(input: String): List<Line> {
            return input.trim().split('\n').map(::Line)
        }

        fun scoreAutocomplete(autocompleteString: String): Long {
            var score: Long = 0
            for(c in autocompleteString) {
                score *= 5
                score += autocompleteValue[c]!!
            }
            return score
        }

        val initializers = listOf('(', '[', '{', '<')
        val matches = mapOf('(' to ')', '[' to ']', '<' to '>', '{' to '}')
        val errorValue = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        val autocompleteValue = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
    }

    data class Line(val content: String) {
        fun validate(): Int {
            val deque = ArrayDeque<Char>()
            for(c in content) when (c) {
                in initializers       -> deque.push(c)
                matches[deque.peek()] -> deque.pop()
                else                  -> return errorValue[c]!!
            }
            return 0
        }

        fun autocomplete(): String {
            val deque = ArrayDeque<Char>()
            val output = StringBuffer()
            for(c in content) when (c) {
                in initializers       -> deque.push(c)
                matches[deque.peek()] -> deque.pop()
                else                  -> throw RuntimeException()
            }
            for(c in deque) output.append(matches[c]!!)
            return output.toString()
        }
    }
}
