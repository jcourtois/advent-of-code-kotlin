@file:Suppress("DuplicatedCode")

package com.adventofcode

import kotlin.math.abs

class Day7Part1 {

    companion object {

        fun eval(s: String): Int {
            val crabs = parse(s)
            val range = crabs.minOrNull()!!..crabs.maxOrNull()!!
            val distances = range.groupBy { it }
                .mapKeys { (position, _) -> crabs.sumOf { abs(it - position) } }
            return distances.keys.minOrNull()!!
        }

        fun parse(s: String): List<Int> {
            return s.trim().split(',').map(String::toInt)
        }
    }
}
