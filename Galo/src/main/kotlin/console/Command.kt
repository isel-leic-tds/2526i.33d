package console

import model.*

open class Command(
    val syntaxArgs: String = "",
    val isTerminate: Boolean = false
) {
    open fun execute(args: List<String>, game: Game?): Game? = game
}

object Play: Command("<position>") {
    override fun execute(args: List<String>, game: Game?): Game {
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()) { "Invalid position $arg" }
        return checkNotNull(game) { "Game not created" }.play(pos)
    }
}

fun getCommands() = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to object : Command() {
        override fun execute(args: List<String>, game: Game?) = game?.new() ?: Game()
    },
    "PLAY" to Play,
)