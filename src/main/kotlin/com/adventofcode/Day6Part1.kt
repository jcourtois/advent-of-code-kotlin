@file:Suppress("DuplicatedCode")

package com.adventofcode

import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.max

class Day6Part1 {

    companion object {

        fun eval(s: String, days: Int): Int {
            val fish = mutableListOf<Fish>()
            fish.addAll(parse(s))

            for (day in 1..days) {
                val newBabies = mutableListOf<Fish>()
                for (f in fish) {
                    val maybeBaby = f.update()
                    if (maybeBaby != null) newBabies.add(maybeBaby)
                }
                fish.addAll(newBabies)
            }
            return fish.size
        }

        fun parse(s: String): List<Fish> {
            return s.trim().split(',').map(String::toInt).map(::Fish)
        }
    }

    data class Fish(var timer: Int) {
        fun update(): Fish? {
            return when {
                timer > 0 -> { timer--; null }
                else      -> { timer = 6; babyFish() }
            }
        }

        companion object {
            fun babyFish() = Fish(timer = 8)
        }
    }
}
