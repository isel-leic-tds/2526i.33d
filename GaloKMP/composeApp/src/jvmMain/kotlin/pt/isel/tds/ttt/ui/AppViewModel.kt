package pt.isel.tds.ttt.ui

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.isel.tds.storage.TextFileStorage
import pt.isel.tds.ttt.model.*
import pt.isel.tds.ttt.model.Clash
import pt.isel.tds.ttt.model.GameNotFoundException

enum class EditMode(val text: String) {
    START("Start"), JOIN("Join")
}

class AppViewModel(val scope: CoroutineScope) {
    private val storage = TextFileStorage<Name,_>("games", GameSerializer)

    /**
     * Clash state (Clash or ClashRun)
     */
    var clash by mutableStateOf(Clash(storage))
        private set
    val isRun get() = clash is ClashRun

    fun newBoard() {
        oper { new() }
    }
    fun play(pos: Position) {
        if (game.state is Run && !isWaiting) {
            oper { play(pos) }
            waitForOther()
        }
    }
    fun doAction(name: Name) {
        cancelWaiting()
        oper {
            if (editMode == EditMode.START) start(name)
            else join(name)
        }
        editMode = null
        waitForOther()
    }
    //fun refresh() = oper { refresh() }
    fun finish() {
        cancelWaiting()
        clash.finish()
    }

    /**
     * Indicates if the edit dialog is being shown
     */
    var editMode by mutableStateOf<EditMode?>(null)
        private set
    fun start() { editMode = EditMode.START }
    fun join() { editMode = EditMode.JOIN }
    fun cancelEdit() { editMode = null }

    /**
     * Properties to access ClashRun info
     */
    val game get() = (clash as ClashRun).game
    val you get() = (clash as ClashRun).side
    val newAvailable get() = (clash as? ClashRun)?.newAvailable() ?: false
    val name get() = (clash as ClashRun).name

    /**
     * Performs an operation on the clash, catching exceptions to set the message property
     */
    private fun oper(op: Clash.()->Clash ) {
        try {
            clash = clash.op()
        } catch (ex: Exception) {
            if (ex is IllegalStateException || ex is IllegalArgumentException) {
                message = ex.message
            }
            else throw ex
        }
    }

    /**
     * Indicates if the score info dialog is being shown
     */
    var viewScore by mutableStateOf(false)
        private set
    fun showScore() { viewScore = true }
    fun hideScore() { viewScore = false }

    /**
     * Message
     */
    var message: String? by mutableStateOf(null)
        private set
    fun clearMessage() { message=null }

    /**
     * Auto-refresh job
     */
    private var job by mutableStateOf<Job?>(null)
    val isWaiting get() = job != null

    private fun cancelWaiting() {
        job?.cancel()
        job = null
    }
    private fun waitForOther() {
        if (clash !is ClashRun || newAvailable) return
        job = scope.launch {
            do {
                delay(3000)
                try {
                    clash = clash.refresh(auto = true)
                } catch (ex: Exception) {
                    if (ex is IllegalStateException || ex is IllegalArgumentException) {
                        message = ex.message
                        if (ex is GameNotFoundException) {
                            clash = Clash(storage)
                            break
                        }
                    } else throw ex
                }
            } while (!newAvailable)
            job = null
        }
    }
}


