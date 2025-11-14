package pt.isel.tds.ttt.ui

import androidx.compose.runtime.*
import pt.isel.tds.ttt.model.*

class AppViewModel {
    /**
     * The current game state
     */
    var game by mutableStateOf(Game())
    private set

    fun play(pos: Position) {
        if (game.state is Run) game = game.play(pos)
    }
    fun newBoard() { game = game.new() }

    /**
     * Indicates if the score info dialog is being shown
     */
    var viewScore by mutableStateOf(false)
    private set

    fun showScore() { viewScore = true }
    fun hideScore() { viewScore = false }
}