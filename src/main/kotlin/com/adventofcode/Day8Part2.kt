package com.adventofcode

class Day8Part2 {

    companion object {

        fun eval(s: String): Int {
            val entries = parse(s)
            return entries.sumOf(Entry::decodeOutput)
        }

        fun parse(s: String): List<Entry> = s.trim().split('\n')
            .map {
                val (sig, out) = it.trim().split('|')
                Entry(sig.trim().split(' '), out.trim().split(' '))
            }

        fun decodeSignals(rawSignals: List<String>): Map<Set<Char>, Int> {
            val signalsToProcess = rawSignals.map(String::toSet).toMutableList()
            val answerMapping = mutableMapOf<Int, Set<Char>>()
            val segmentsFrom = { number: Int -> answerMapping[number]!!.toSet() }
            val digitsToCriteria = mapOf(
                1 to { s: Set<Char> -> s.size == 2 },
                4 to { s: Set<Char> -> s.size == 4 },
                7 to { s: Set<Char> -> s.size == 3 },
                8 to { s: Set<Char> -> s.size == 7 },
                9 to { s: Set<Char> -> s.size == 6 && s.containsAll(segmentsFrom(1) union segmentsFrom(4)) },
                0 to { s: Set<Char> -> s.size == 6 && s.containsAll(segmentsFrom(1)) },
                6 to { s: Set<Char> -> s.size == 6 },
                3 to { s: Set<Char> -> s.size == 5 && s.containsAll(segmentsFrom(1)) },
                5 to { s: Set<Char> -> s.size == 5 && (segmentsFrom(6) - s).size == 1 },
                2 to { s: Set<Char> -> s.size == 5 }
            )

            for((digit, matchingCriterion) in digitsToCriteria) {
                val matchingSignal = signalsToProcess.find(matchingCriterion) ?: continue
                signalsToProcess.remove(matchingSignal)
                answerMapping[digit] = matchingSignal
            }

            return answerMapping.entries.associate { (digit, signal) -> signal to digit }.toMap()
        }
    }

    data class Entry(val signals: List<String>, val output: List<String>) {
        fun decodeOutput(): Int {
            val decoding: Map<Set<Char>, Int> = decodeSignals(signals)
            return output.map(String::toSet)
                .map(decoding::getValue)
                .joinToString("", transform = Int::toString).toInt()
        }
    }
}
