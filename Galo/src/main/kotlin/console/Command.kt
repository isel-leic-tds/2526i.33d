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

/*
fun storageCommand(fx: (String, Game)->Game) = Command("<name>") { args, game ->
    val name = requireNotNull(args.firstOrNull()) { "Missing name" }
    fx(name,game)
}
*/

fun getCommands() = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to Command{ new() },
    "PLAY" to Play,
    "SCORE" to Command { this.also { (it as? ClashRun)?.game?.score?.show() } },
//    "SAVE" to storageCommand { name, game -> game.also{ gs.create(name,game) } },
//    "LOAD" to storageCommand { name, _ -> checkNotNull(gs.read(name)) { "Game $name not found"} }
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

