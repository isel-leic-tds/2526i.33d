package pt.isel.tds.ttt

import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource
import galokmp.composeapp.generated.resources.*
import pt.isel.tds.ttt.model.*

@Composable
fun PlayerView(player: Player) {
    val resource = when(player) {
        Player.CROSS -> Res.drawable.cross
        Player.BALL -> Res.drawable.circle
    }
    Image(painterResource(resource),"cross")
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
    ) {
        App()
    }
}