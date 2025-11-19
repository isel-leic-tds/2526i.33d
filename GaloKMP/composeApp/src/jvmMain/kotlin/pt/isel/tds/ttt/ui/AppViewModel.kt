package pt.isel.tds.ttt.ui

import androidx.compose.runtime.*
import pt.isel.tds.storage.TextFileStorage
import pt.isel.tds.ttt.model.*

enum class EditMode(val text: String) {
    START("Start"), JOIN("Join")
}

class AppViewModel {
    private val storage = TextFileStorage<Name,_>("games", GameSerializer)

    var clash by mutableStateOf(Clash(storage))
    private set

    val isRun get() = clash is ClashRun

    var editMode by mutableStateOf<EditMode?>(null)
    private set

    fun start() { editMode = EditMode.START }
    fun join() { editMode = EditMode.JOIN }
    fun cancelEdit() { editMode = null }
    fun doAction(name: Name) {
        clash = if (editMode== EditMode.START) clash.start(name)
                else clash.join(name)
        editMode = null
    }

    /**
     * The current game state
     */
    val game get() = (clash as ClashRun).game

    fun play(pos: Position) {
        if (game.state is Run) clash = clash.play(pos)
    }
    fun newBoard() { clash = clash.new() }

    /**
     * Indicates if the score info dialog is being shown
     */
    var viewScore by mutableStateOf(false)
    private set

    fun showScore() { viewScore = true }
    fun hideScore() { viewScore = false }
}