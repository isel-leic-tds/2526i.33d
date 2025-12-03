package pt.isel.tds.ttt.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.tds.storage.Storage

typealias GameStorage = Storage<Name,Game>

/**
 * Clash represents a game session between two players.
 * Concrete subclasses manage the game state and player actions.
 * @property gs The game storage to manage game states.
 */
open class Clash(val gs: GameStorage) {
    private fun notStarted(): Nothing = error("Clash not started")
    open fun play(pos: Position): Clash = notStarted()
    open fun new(): Clash = notStarted()
    open suspend fun refresh(auto :Boolean = false): Clash = notStarted()
    fun start(name: Name) = ClashRun(
        gs, name, Player.CROSS, Game().also { gs.create(name,it) }
    ).also { deleteIfOwner() }
    fun join(name: Name) = ClashRun(
        gs, name, Player.BALL, gs.read(name) ?: error("Game not found")
    ).also { deleteIfOwner() }
    open fun finish() { }
}

/**
 * ClashRun represents an active game session with a specific player.
 * @property name The name of the game session.
 * @property side The player side (CROSS or BALL).
 * @property game The current game state.
 */
class ClashRun(
    gs: GameStorage,
    val name: Name,
    val side: Player,
    val game: Game
) : Clash(gs) {
    override fun play(pos: Position) =
        copy(game = game.play(pos)).also {
            check(side == (game.state as Run).turn) { "Not your turn" }
            gs.update(name,it.game)
        }
    override fun new(): ClashRun {
        check(newAvailable()) { "new not available" }
        return copy(game = game.new()).also { gs.update(name, it.game) }
    }
    override suspend fun refresh(auto: Boolean) =
        copy( gs.slowRead(name)
            ?.also { check(auto || it != game) { "No changes" } }
            ?: throw GameNotFoundException()
        )

    override fun finish() { deleteIfOwner() }
}

fun ClashRun.copy(game: Game = this.game) =
    ClashRun(gs,name,side,game)

fun Clash.deleteIfOwner() {
    if (this is ClashRun && side== Player.CROSS) gs.delete(name)
}

fun ClashRun.newAvailable() =
    side == when(val state = game.state) {
        is Run -> state.turn
        else -> game.first.other
    }

class GameNotFoundException : IllegalStateException("Game not found")

suspend fun GameStorage.slowRead(name: Name): Game? {
    //println(Thread.currentThread().name)
    return withContext(Dispatchers.IO) {
        //println(Thread.currentThread().name)
        //Thread.sleep(5000)
        read(name)
    } //.also { println(Thread.currentThread().name) }
}
