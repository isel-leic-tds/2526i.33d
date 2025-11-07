package pt.isel.tds.ttt.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource
import galokmp.composeapp.generated.resources.*
import pt.isel.tds.ttt.model.*


@Composable
fun PlayerView(player: Player, modifier: Modifier = Modifier) {
    val resource = when(player) {
        Player.CROSS -> Res.drawable.cross
        Player.BALL -> Res.drawable.circle
    }
    val mod = modifier.size(CELL_SIDE).background(Color.White)
    Image(painterResource(resource),null, modifier = mod)
}

@Composable
private fun App() {
    var player by remember{ mutableStateOf(Player.CROSS) }
    MaterialTheme {
        PlayerView(player)
        Button(onClick = { player = player.other }) {
            Text("Change Player")
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "GaloKMP",
        icon = painterResource(Res.drawable.cross),
        state = WindowState(size= DpSize.Unspecified)
    ) {
        App()
    }
}