package com.adventofcode

class Day3Part1 {

    companion object {
        fun powerConsumption(s: String): Int {
            val (gammaRate, epsilonRate) = analyze(s)
            return gammaRate * epsilonRate
        }

        fun analyze(s: String): Pair<Int, Int> {
            val (lines, counts) = count(s)
            val bitList = counts.map { if (it - lines.toFloat() / 2 > 0) 1 else 0 }
            val gammaRate = bitList.toInt()
            val epsilonRate = bitList.toInvInt()

            return gammaRate to epsilonRate
        }

        fun parse(s: String): List<List<Int>> {
            return s.trim().split('\n').map { it.map(Char::digitToInt) }
        }

        fun count(s: String): Analysis {
            val array = parse(s)
            require(array.isNotEmpty())
            return Analysis(
                lines = array.size,
                columnCounts = array[0].mapIndexed { index, _ -> array.sumOf { it[index] } })
        }

        fun List<Int>.toInt(): Int {
            return fold(0) { acc, i -> acc shl 1 or i }
        }

        fun List<Int>.toInvInt(): Int {
            return fold(0) { acc, i -> acc shl 1 or (1 - i) }
        }
    }
}

data class Analysis(val lines: Int, val columnCounts: List<Int>)
