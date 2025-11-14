package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.*

@Composable
fun ScoreInfo(score: Score, onClose: ()->Unit ) = AlertDialog(
    onDismissRequest = onClose,
    title = { Text("Score") },
    text = { Placard(score) },
    confirmButton = { Button(onClick = onClose){ Text("Close") } }
)

@Composable
@Preview
fun PlacardTest() {
    val score = mapOf(Player.CROSS to 3, Player.BALL to 2, null to 2)
    Placard(score)
}

@Composable
fun Placard(score: Score) {
    val style = MaterialTheme.typography.displaySmall
    Row(Modifier
        .width(GRID_SIDE * (3f/5)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Player.entries.forEach {
                Row {
                    Cell(it, Modifier.size(40.dp))
                    Text(" - ${score[it]}", style = style)
                }
            }
        }
        Text("Draw - ${score[null]}", style = style)
    }
}