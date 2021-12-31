package com.adventofcode

class Day8Part1 {

    companion object {
        private val uniqueDigits = listOf(2, 3, 4, 7)

        fun eval(s: String): Int {
            val entries = parse(s)
            return entries.sumOf { (_, output) -> uniqueDigits(output) }
        }

        private fun uniqueDigits(digits: List<String>) = digits.map(String::length).filter(uniqueDigits::contains).size
        private fun parse(s: String): List<Entry> = s.trim().split('\n')
            .map {
                val (sig, out) = it.trim().split('|')
                Entry(sig.trim().split(' '), out.trim().split(' '))
            }
    }

    data class Entry(val signals: List<String>, val output: List<String>)
}
