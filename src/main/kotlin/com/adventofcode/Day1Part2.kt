package com.adventofcode

class Day1Part2 {

    companion object {
        fun eval(s: String, separator: Char = ' '): Int {
            return fold(s, separator).acc
        }

        internal fun fold(s: String, separator: Char) = s
            .trim()
            .split(separator)
            .map { it.toInt() }
            .fold(Accumulator(0, null, null, null), ::countIncreases)

        private fun countIncreases(acc: Accumulator, current: Int): Accumulator {
            return acc.update(current)
        }
    }

    data class Accumulator(val acc: Int, val previousResult: Int?, val twoAgo: Int?, val oneAgo: Int?) {
        fun update(current: Int): Accumulator {
            val currentResult = pastTwo()?.plus(current)
            val increase = when {
                hasEmptyFields()                   -> 0
                currentResult!! > previousResult!! -> 1
                else                               -> 0
            }
            return copy(oneAgo = current, twoAgo = oneAgo, acc = acc + increase, previousResult = currentResult)
        }

        private fun pastTwo(): Int? {
            return if (oneAgo == null || twoAgo == null) null else oneAgo + twoAgo
        }

        private fun hasEmptyFields(): Boolean {
            return oneAgo == null || twoAgo == null || previousResult == null
        }
    }
}
