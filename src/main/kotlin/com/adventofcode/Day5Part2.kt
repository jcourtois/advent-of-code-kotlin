@file:Suppress("DuplicatedCode")

package com.adventofcode

import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.max

class Day5Part2 {

    companion object {

        fun eval(s: String): Int {
            val grid = Grid()
            for(line in parse(s)) grid.add(line)
            return grid.numberOfDangerZones()
        }

        fun parse(s: String): List<Line> {
            val lines = s.trim().split('\n')
            return lines.map { line: String ->
                val (start, end) = line.split(" -> ")
                    .map { coordinate ->
                        val (x, y) = coordinate.trim().split(',').map(String::toInt)
                        Coordinate(x, y)
                    }
                Line(start, end)
            }
        }
    }

    data class Grid(val xAxis: Int = 1000, val yAxis: Int = 1000) : Iterable<Column> {
        val rows = List(yAxis) { Column(points = List(xAxis) { Point() }) }
        val lines = mutableListOf<Line>()

        fun add(line: Line) {
            lines.add(line)
            for(c in line.coordinates) this[c.x, c.y].add(line)
        }

        override fun iterator(): Iterator<Column> = rows.iterator()
        operator fun get(x: Int, y: Int): Point = rows[y][x]
        fun render() = rows.joinToString("\n") { it.points.joinToString(" ", transform = Point::render) }
        fun numberOfDangerZones() = rows.sumOf(Column::numberOfDangerZones)
    }

    data class Column(val points: List<Point>) : Iterable<Point> {
        fun numberOfDangerZones() = points
            .map { it.lines.size }
            .filter { it > 1 }.size
        override fun iterator(): Iterator<Point> = points.iterator()
        operator fun get(x: Int): Point = points[x]
    }

    data class Point(val lines: MutableList<Line> = mutableListOf()) {
        fun add(line: Line) {
            lines.add(line)
        }

        fun render(): String {
            val size = lines.size
            return if (size > 0) size.toString() else "."
        }
    }

    data class Line(val start: Coordinate, val end: Coordinate) : Iterable<Coordinate> {
        val length = start.distanceTo(end) + 1

        val coordinates = coordinateRange(start.x, end.x, length)
            .zip(coordinateRange(start.y, end.y, length))
            .map { (x, y) -> Coordinate(x, y) }

        override fun iterator(): Iterator<Coordinate> = coordinates.iterator()

        private fun coordinateRange(start: Int, end: Int, length: Int): Iterable<Int> {
            return when {
                start == end -> List(length) { start }
                start < end  -> start.rangeTo(end)
                start > end  -> start.downTo(end)
                else         -> throw IllegalArgumentException("unexpected arguments to coordinate range")
            }
        }
    }


    data class Coordinate(val x: Int, val y: Int) {
        fun distanceTo(other: Coordinate): Int = max(abs(x - other.x), abs(y - other.y))
    }
}
