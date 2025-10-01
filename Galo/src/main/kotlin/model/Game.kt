package model

const val EMPTY = ' '

data class Game(
    val first: Char = 'X',
    val turn: Char = first,
    val board: List<Char> = listOf(
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
    ),
)

fun Game.new() = Game(first = first.otherPlayer())

fun Game.canPlay(pos: Int) = board[pos] == EMPTY

fun Game.play(pos: Int) = copy(
    turn = turn.otherPlayer(),
    board = board.mapIndexed { idx, play ->
        if (idx==pos) turn else play
    }
)

fun Game.show() {
    board.chunked(3).forEachIndexed { idx, row ->
        println( row.joinToString("|") { " $it " })
        if (idx<2) println("---+---+---")
    }
    println( when {
        isWinner('X') -> "Winner X"
        isWinner('O') -> "Winner O"
        isDraw() -> "Draw"
        else -> "Turn: $turn"
    } )
}

fun Game.isWinner(player: Char) = false

fun Game.isDraw() = board.none{ it==' ' }

fun Char.otherPlayer() = if(this=='X') 'O' else 'X'