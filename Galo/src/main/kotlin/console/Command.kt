package console

import model.*

class Command (
    val syntaxArgs: String = "",
    val isTerminate: Boolean = false,
    val execute: (List<String>,Game) -> Game = { _, game -> game }
)

val Play = Command("<position>") { args, game ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
    val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg" }
    game.play(pos)
}

fun getCommands() = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to Command{ _, g -> g.new() },
    "PLAY" to Play,
    "SCORE" to Command { _, g -> g.also{ it.score.show() } },
)

/*
open class Command(
    val syntaxArgs: String = "",
    val isTerminate: Boolean = false
) {
    open fun execute(args: List<String>, game: Game): Game = game
}

object Play: Command("<position>") {
    override fun execute(args: List<String>, game: Game): Game {
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg" }
        return game.play(pos)
    }
}

fun getCommands() = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to object : Command() {
        override fun execute(args: List<String>, game: Game) = game.new()
    },
    "PLAY" to Play,
    "SCORE" to object : Command() {
        override fun execute(args: List<String>, game: Game): Game {
            game.score.show()
            return game
        }
    },
)
*/

