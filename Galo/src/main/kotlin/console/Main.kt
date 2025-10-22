package console

import model.*
import storage.TextFileStorage

fun main() {
    val gameStorage = TextFileStorage<String,Game>("games", GameSerializer)
    var game = Game()
    val cmds: Map<String,Command> = getCommands(gameStorage)
    game.show()
    while(true) {
        val (name, args) = readCommand()
        val cmd = cmds[name]
        if (cmd == null) println("Invalid command $name")
        else try {
            game = cmd.execute(args,game)
            if (cmd.isTerminate) return
            game.show()
        } catch (ex: IllegalArgumentException) {
            println(ex.message)
            println("Use: $name ${cmd.syntaxArgs}")
        } catch (ex: IllegalStateException) {
            println(ex.message)
        }
    }
}