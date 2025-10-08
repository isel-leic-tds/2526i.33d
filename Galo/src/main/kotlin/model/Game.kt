package model
import model.Player.*

const val BOARD_SIZE = 3
const val BOARD_CELLS = BOARD_SIZE*BOARD_SIZE

typealias Board = Map<Position, Player>

data class Game(
    val first: Player = CROSS,
    val turn: Player = first,
    val board: Board = emptyMap(),
)

fun Game.new() = Game(first = first.other)

fun Game.play(pos: Position): Game {
    check(pos !in board) { "position already used" }
    return copy(
        turn = turn.other,
        board = board + Pair(pos,turn)
    )
}

fun Game.isWinner(player: Player): Boolean {
    val places = board.filter { (_,value) -> value==player }.keys
    if (places.size < BOARD_SIZE) return false
    return (0..<BOARD_SIZE).any { row -> places.count{ it.row==row }==BOARD_SIZE } ||
           (0..<BOARD_SIZE).any { col -> places.count{ it.col==col }==BOARD_SIZE } ||
            places.count{ it.backSlash } == BOARD_SIZE ||
            places.count{ it.slash } == BOARD_SIZE
}

fun Game.isDraw() = board.size == BOARD_CELLS