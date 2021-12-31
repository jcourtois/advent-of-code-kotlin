package com.adventofcode

import java.util.*

class Day10Part1 {

    companion object {

        fun eval(s: String): Int {
            return parse(s).sumOf(Line::validate)
        }

        fun parse(input: String): List<Line> {
            return input.trim().split('\n').map(::Line)
        }


        val initializers = listOf('(', '[', '{', '<')
        val matches = mapOf('(' to ')', '[' to ']','<' to '>', '{' to '}')
        val errorValue = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    }

    data class Line(val content: String) {
        fun validate(): Int {
            val deque = ArrayDeque<Char>()
            for (c in content) {
                when (c) {
                    in initializers       -> deque.push(c)
                    matches[deque.peek()] -> deque.pop()
                    else                  -> return errorValue[c]!!
                }
            }
            return 0
        }
    }
}
