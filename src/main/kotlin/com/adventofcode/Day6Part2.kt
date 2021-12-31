@file:Suppress("DuplicatedCode")

package com.adventofcode

class Day6Part2 {

    companion object {

        fun eval(s: String, days: Int): Long {
            var fish = mutableListOf(*parse(s))

            for (day in 1..days) {
                val babyCohort: Cohort? = fish.mapNotNull(Cohort::update).firstOrNull()
                if (babyCohort != null) fish.add(babyCohort)
                fish = consolidateCohorts(fish)
            }
            return fish.sumOf { it.count }
        }

        private fun consolidateCohorts(fish: MutableList<Cohort>) =
            fish.groupBy { it.timer }
                .mapValues { (timer, cohorts) -> Cohort(timer, cohorts.sumOf(Cohort::count)) }
                .values.toMutableList()

        fun parse(s: String): Array<Cohort> {
            val birthdays = s.trim().split(',').map(String::toInt)
            return birthdays.groupBy { it }.mapValues { it.value.size }
                .map { (timer, count) -> Cohort(timer, count.toLong()) }.toTypedArray()
        }
    }

    data class Cohort(var timer: Int, var count: Long) {
        fun update(): Cohort? {
            return when {
                timer > 0 -> { timer--; null }
                else      -> { timer = 6; babyFish(count) }
            }
        }

        companion object {
            fun babyFish(size: Long) = Cohort(timer = 8, size)
        }
    }
}
