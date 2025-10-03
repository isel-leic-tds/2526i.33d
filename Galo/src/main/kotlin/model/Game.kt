package model
import model.Player.*

val EMPTY = null
const val BOARD_SIZE = 3
const val BOARD_CELLS = BOARD_SIZE*BOARD_SIZE

data class Game(
    val first: Player = CROSS,
    val turn: Player = first,
    val board: List<Player?> = List(BOARD_CELLS) { EMPTY },
)

fun Game.new() = Game(first = first.other)

fun Game.play(pos: Int): Game {
    require(pos in board.indices) { "Position $pos out of bounds" }
    check(board[pos] == EMPTY) { "position already used" }
    return copy(
        turn = turn.other,
        board = board.mapIndexed { idx, play -> if (idx == pos) turn else play }
    )
}

fun Game.isWinner(player: Player) =
    (0..6 step 3).any{ row -> (0..2).all{ board[it+row]==player } } ||
    (0..2).any { col -> (0..6 step 3).all {board[it+col]==player} } ||
    (0..8 step 4).all{ board[it]==player } ||
    (2..6 step 2).all{ board[it]==player }

fun Game.isDraw() = board.none{ it==EMPTY }