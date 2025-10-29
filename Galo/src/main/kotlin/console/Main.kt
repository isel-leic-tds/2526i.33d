package console

import model.*
import storage.TextFileStorage

fun main() {
    val gameStorage = TextFileStorage<Name,Game>("games", GameSerializer)
    var clash = Clash(gameStorage)
    val cmds: Map<String,Command> = getCommands()
    while(true) {
        val (name, args) = readCommand()
        val cmd = cmds[name]
        if (cmd == null) println("Invalid command $name")
        else try {
            clash = cmd.execute(args,clash)
            if (cmd.isTerminate) return
            clash.show()
        } catch (ex: IllegalArgumentException) {
            println(ex.message)
            println("Use: $name ${cmd.syntaxArgs}")
        } catch (ex: IllegalStateException) {
            println(ex.message)
        }
    }
}