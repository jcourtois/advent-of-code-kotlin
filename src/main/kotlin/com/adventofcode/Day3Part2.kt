package com.adventofcode

class Day3Part2 {

    companion object {
        fun lifeSupportRating(s: String): Int {
            val (gammaRate, epsilonRate) = analyze(s)
            return gammaRate * epsilonRate
        }

        fun analyze(s: String): Pair<Int, Int> {
            val array = parse(s)

            val oxygenValue = findValue(array, ::oxygenBitCriteria).toInt()
            val carbonValue = findValue(array, ::carbonBitCriteria).toInt()

            return oxygenValue to carbonValue
        }

        private fun findValue(array: List<List<Int>>, expectation: (Int, Int) -> Int): List<Int> {
            var candidates = array
            for (i in array.first().indices) {
                val ones = candidates.sumOf { it[i] }
                val zeros = candidates.size - ones

                candidates = candidates.filter { it[i] == expectation(ones, zeros) }
                if (candidates.size == 1) return candidates.first()
            }
            throw RuntimeException()
        }

        private fun oxygenBitCriteria(ones: Int, zeros: Int): Int {
            return when {
                ones > zeros -> 1
                ones < zeros -> 0
                else         -> 1
            }
        }

        private fun carbonBitCriteria(ones: Int, zeros: Int): Int {
            return when {
                ones < zeros -> 1
                ones > zeros -> 0
                else         -> 0
            }
        }

        fun parse(s: String): List<List<Int>> {
            return s.trim().split('\n').map { it.map(Char::digitToInt) }
        }

        fun List<Int>.toInt(): Int {
            return fold(0) { acc, i -> acc shl 1 or i }
        }
    }
}
