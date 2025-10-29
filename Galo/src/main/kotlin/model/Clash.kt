package model

import storage.Storage

typealias GameStorage = Storage<Name,Game>

open class Clash(val gs: GameStorage) {
    open fun play(pos: Position): Clash { error("Clash not started") }
    open fun new(): Clash { error("Clash not started") }
}

class ClashRun(
    gs: GameStorage,
    val name: Name,
    val side: Player,
    val game: Game
) : Clash(gs) {
    override fun play(pos: Position) =
        copy(game = game.play(pos)).also { gs.update(name,it.game) }
    override fun new() =
        copy(game = game.new()).also { gs.create(name,it.game) }


    fun copy(gs: GameStorage = this.gs,
             name: Name = this.name,
             side: Player = this.side,
             game: Game = this.game)
    = ClashRun(gs,name,side,game)

}