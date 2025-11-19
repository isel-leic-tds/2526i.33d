package pt.isel.tds.ttt.model

import pt.isel.tds.storage.*

object GameSerializer: Serializer<Game> {
    override fun serialize(data: Game) = buildString {
        appendLine(data.first)
        appendLine(StateSerializer.serialize(data.state))
        appendLine(data.board.entries.joinToString(" ") { (k,v) -> "${k.index}:$v" })
        appendLine(data.score.entries.joinToString(" ") { (k,v) -> "$k=$v"})
    }

    override fun deserialize(txt: String): Game {
        val (l1,l2,l3,l4) = txt.split('\n')
        return Game(
            first = Player.valueOf(l1),
            state = StateSerializer.deserialize(l2),
            board = if (l3.isBlank()) emptyMap()
                    else l3.split(' ').associate {
                val (pos, ply) = it.split(':')
                Position(pos.toInt()) to ply.toPlayer()
            },
            score = l4.split(' ').associate {
                val (ply,pts) = it.split('=')
                ply.toPlayerOrNull() to pts.toInt()
            }
        )
    }
}


object StateSerializer: Serializer<GameState> {
    override fun serialize(data: GameState) = when(data) {
        is Run -> "Run:${data.turn}"
        is Win -> "Win:${data.winner}"
        is Draw -> "Draw:"
    }

    override fun deserialize(txt: String): GameState {
        val (type,player) = txt.split(":")
        return when(type) {
            "Run" -> Run(player.toPlayer())
            "Win" -> Win(player.toPlayer())
            "Draw" -> Draw
            else -> error("Invalid game state $type")
        }
    }

}