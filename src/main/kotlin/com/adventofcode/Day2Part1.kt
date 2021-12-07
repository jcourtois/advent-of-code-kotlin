package com.adventofcode

class Day2Part1 {

    companion object {
        fun eval(s: String): Pair<Int, Int> {
            Float
            return s
                .trim()
                .split('\n')
                .map(::processCommand)
                .reduce { acc, pair -> Pair(acc.first + pair.first, acc.second + pair.second) }
        }

        private fun processCommand(s: String): Pair<Int, Int> {
            val (command, magnitude) = s.split(' ')
            return when (command) {
                "forward" -> moveForward(magnitude.toInt())
                "down"    -> moveDown(magnitude.toInt())
                "up"      -> moveUp(magnitude.toInt())
                else      -> 0 to 0
            }
        }

        private fun moveForward(magnitude: Int) = magnitude to 0
        private fun moveDown(magnitude: Int) = 0 to magnitude
        private fun moveUp(magnitude: Int) = 0 to -magnitude
    }
}
