package com.adventofcode

import kotlin.math.abs

class Day7Part1 {

    companion object {

        fun eval(s: String): Int {
            val crabs = parse(s)
            val range = crabs.minOrNull()!!..crabs.maxOrNull()!!
            val distances = range.associateWith { position -> crabs.sumOf { abs(it - position) } }
            return distances.values.minOrNull()!!
        }

        private fun parse(s: String): List<Int> = s.trim().split(',').map(String::toInt)
    }
}
