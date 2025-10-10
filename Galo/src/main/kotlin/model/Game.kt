package model
import model.Player.*

const val BOARD_SIZE = 3
const val BOARD_CELLS = BOARD_SIZE*BOARD_SIZE

typealias Board = Map<Position, Player>
typealias Score = Map<Player?, Int>

fun Score.advance(player: Player?) = plus(player to getValue(player)+1)

data class Game(
    val first: Player = CROSS,
    val board: Board = emptyMap(),
    val state: GameState = Run(first),
    val score: Score = (Player.entries + null).associateWith { 0 }
)

sealed class GameState
class Run(val turn: Player) : GameState()
class Win(val winner: Player) : GameState()
object Draw : GameState()

fun Game.new() = Game(
    first = first.other,
    score = if (state is Run) score.advance(state.turn.other)
            else score
)

fun Game.play(pos: Position): Game {
    check(state is Run) { "game already terminated" }
    check(pos !in board) { "position already used" }
    val newBoard = board + Pair(pos,state.turn)
    val newState = when {
        newBoard.isWinnerIn(pos) -> Win(state.turn)
        newBoard.size == BOARD_CELLS -> Draw
        else -> Run(state.turn.other)
    }
    return copy(
        board = newBoard,
        state = newState,
        score = when (newState) {
            is Win -> score.advance(newState.winner)
            is Draw -> score.advance(null)
            is Run -> score
        }
    )
}

fun Board.isWinnerIn(p: Position): Boolean {
    val player = getValue(p)
    val places = filterValues { it==player }.keys
    if (places.size < BOARD_SIZE) return false
    return places.count { it.col==p.col } == BOARD_SIZE ||
           places.count { it.row==p.row } == BOARD_SIZE ||
           p.backSlash && places.count { it.backSlash } == BOARD_SIZE ||
           p.slash && places.count { it.slash } == BOARD_SIZE
}


