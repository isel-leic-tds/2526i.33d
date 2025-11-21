package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.*


val STATUS_HEIGHT = 50.dp

@Composable
@Preview
fun StatusBarTest() {
    StatusBar(Run(Player.CROSS), Player.BALL)
}

@Composable
fun StatusBar(state: GameState, you: Player) = Row(
    Modifier
        .height(STATUS_HEIGHT)
        .background(Color.LightGray)
        .width(GRID_SIDE),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    LabeledCell("You:",you)
    Spacer(Modifier.width(STATUS_HEIGHT))
    val (txt,player) = when(state) {
        is Run -> "Turn:" to state.turn
        is Win -> "Winner:" to state.winner
        is Draw -> "Draw" to null
    }
    LabeledCell(txt, player)
}

@Composable
fun LabeledCell(txt: String, player: Player?) {
    Text(txt, style = MaterialTheme.typography.displaySmall)
    Cell(player, Modifier.background(Color.LightGray).padding(4.dp))
}