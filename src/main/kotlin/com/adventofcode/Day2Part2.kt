package com.adventofcode

class Day2Part2 {

    companion object {
        fun eval(s: String): Sub {
            return s
                .trim()
                .split('\n')
                .fold(Sub(0, 0, 0)) { sub, next -> sub.processCommand(next) }
        }

        data class Sub(val position: Long, val depth: Long, val aim: Long) {

            fun processCommand(s: String): Sub {
                val (command, delta) = s.split(' ')
                return when (command) {
                    "forward" -> moveForward(delta.toLong())
                    "down"    -> moveDown(delta.toLong())
                    "up"      -> moveUp(delta.toLong())
                    else      -> throw RuntimeException("invalid command supplied <$command>")
                }
            }

            private fun moveUp(delta: Long) = copy(aim = aim - delta)
            private fun moveDown(delta: Long) = copy(aim = aim + delta)
            private fun moveForward(delta: Long) = copy(position = position + delta, depth = depth + aim * delta)
        }
    }
}
