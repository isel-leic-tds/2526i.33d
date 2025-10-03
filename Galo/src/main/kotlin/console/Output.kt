package console

import model.*
import model.Player.*

private val separator = "---" + "+---".repeat(BOARD_SIZE-1)

fun Player?.toChar() = when(this) {
    CROSS -> 'X'
    BALL -> 'O'
    null -> ' '
}

fun Game.show() {
    board.chunked(BOARD_SIZE).forEachIndexed { idx, row ->
        println( row.joinToString("|") { " ${it.toChar()} " })
        if (idx<BOARD_SIZE-1) println(separator)
    }
    println( when {
        isWinner(CROSS) -> "Winner X"
        isWinner(BALL) -> "Winner O"
        isDraw() -> "Draw"
        else -> "Turn: ${turn.toChar()}"
    } )
}
