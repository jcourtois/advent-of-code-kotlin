package com.adventofcode

class Day1Part1 {

    companion object {
        fun eval(s: String, separator: Char = ' '): Int {
            return s
                .trim()
                .split(separator)
                .map { it.toInt() }
                .fold(null to 0, ::countIncreases).first!!
        }

        private fun countIncreases(acc: Pair<Int?, Int>, current: Int): Pair<Int?, Int> {
            return when {
                acc.first == null    -> 0
                acc.second < current -> acc.first!! + 1
                else                 -> acc.first!!
            } to current
        }
    }
}