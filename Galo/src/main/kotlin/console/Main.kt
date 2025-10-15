package console

import model.*

fun main() {
    var game = Game()
    val cmds: Map<String,Command> = getCommands()
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