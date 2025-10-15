package model

import storage.Serializer

object GameSerializer: Serializer<Game> {
    override fun serialize(data: Game): String {
        TODO("Not yet implemented")
    }

    override fun deserialize(txt: String): Game {
        TODO("Not yet implemented")
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
            "Run" -> Run(Player.valueOf(player))
            "Win" -> Win(Player.valueOf(player))
            "Draw" -> Draw
            else -> error("Invalid game state $type")
        }
    }

}