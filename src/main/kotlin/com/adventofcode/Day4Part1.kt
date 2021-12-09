package com.adventofcode

class Day4Part1 {

    companion object {

        fun eval(s: String): Pair<Int, Board> {
            val (callNumbers, boards) = parse(s)
            return playGame(callNumbers, boards)
        }

        fun playGame(startingBoards: List<Board>, numbers: List<Int>): Pair<Int, Board> {
            var boards = startingBoards
            for(calledNumber in numbers) {
                boards = boards.map { it.update(calledNumber) }
                val winner = boards.find { it.hasWon }
                if (winner != null) return calledNumber to winner
            }
            throw RuntimeException()
        }

        fun parse(s: String): Pair<List<Board>, List<Int>> {
            val split = s.split("\n\n")
            val callNumbers = split.first().trim().split(',').filterNot(String::isEmpty).map(String::toInt)
            val boards = split.drop(1).map(::parseBoard)
            return boards to callNumbers
        }

        fun parseBoard(s: String): Board {
            val rows: List<String> = s.split('\n').filterNot(String::isEmpty)
            return Board(rows.map { row ->
                Column(row
                    .split(Regex("\\s+"), 0)
                    .filterNot(String::isEmpty)
                    .map(String::toInt)
                    .map(::Square))
            })
        }
    }

    data class Board(val rows: List<Column>, val score: Int = 0, val hasWon: Boolean = false) {
        fun update(calledNumber: Int): Board {
            val newRows = rows.map { it.update(calledNumber) }
            val hasWinningRow = newRows.any { it.squares.all(Square::marked) }
            val hasWinningCol = (0..4).any { col -> newRows.all { it.squares[col].marked } }
            return when {
                !hasWinningCol && !hasWinningRow -> copy(rows = newRows)
                else                             -> copy(rows = newRows,
                    score = newRows.sumOf { it.squares.filterNot(Square::marked).sumOf(Square::value) },
                    hasWon = true)
            }
        }
    }

    data class Column(val squares: List<Square>) {
        fun update(calledNumber: Int): Column = copy(squares = squares.map { it.update(calledNumber) })
    }

    data class Square(val value: Int, val marked: Boolean = false) {
        fun update(calledNumber: Int): Square = copy(marked = marked || value == calledNumber)
    }
}
