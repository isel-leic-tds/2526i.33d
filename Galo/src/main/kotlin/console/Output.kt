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
    Position.values
        .map{ board[it] }
        .chunked(BOARD_SIZE)
        .forEachIndexed { idx, row ->
            println( row.joinToString("|") { " ${it.toChar()} " })
            if (idx<BOARD_SIZE-1) println(separator)
    }
    println( when (state) {
        is Win -> "Winner ${state.winner.toChar()}"
        is Draw -> "Draw"
        is Run -> "Turn: ${state.turn.toChar()}"
    } )
}

fun Score.show() = entries
        .map{ (k,v) -> "${k?.toChar() ?: "Draw"} = $v" }
        .forEach(::println)

fun Clash.show() =
    (this as? ClashRun)?.game?.show()
