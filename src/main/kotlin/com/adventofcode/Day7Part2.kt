package com.adventofcode

import kotlin.math.abs

class Day7Part2 {

    companion object {

        fun eval(s: String): Int {
            val crabs = parse(s)
            val range = crabs.minOrNull()!!..crabs.maxOrNull()!!
            val distances = range.associateWith { position -> crabs.sumOf { fuelCost(abs(it - position)) } }
            return distances.values.minOrNull()!!
        }

        /**
         * The sequence for calculating fuel cost looks like the Triangle Numbers (1, 3, 6, 10, ...),
         * so we use the (n*(n+1))/2 formula from algebra to make our solution more efficient that calculating 1+2+3+...
         */
        private fun fuelCost(distance: Int) = (distance * (distance + 1)) / 2
        private fun parse(s: String): List<Int> = s.trim().split(',').map(String::toInt)
    }
}
