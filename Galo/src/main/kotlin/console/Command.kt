package console

import model.*


class Command (
    val syntaxArgs: String = "",
    val isTerminate: Boolean = false,
    val execute: Clash.(args: List<String>) -> Clash = { this }
)

val Play = Command("<position>") { args ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
    val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg" }
    play(pos)
}

fun clashCommand(fx: Clash.(Name)->Clash) = Command("<name>") { args ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing name" }
    fx(Name(arg))
}

fun getCommands() = mapOf(
    "EXIT" to Command(isTerminate = true) { finish(); this },
    "NEW" to Command{ new() },
    "PLAY" to Play,
    "SCORE" to Command { this.also { (it as? ClashRun)?.game?.score?.show() } },
    "START" to clashCommand { start(it) },
    "JOIN" to clashCommand { join(it) },
    "REFRESH" to Command{ refresh() }
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

