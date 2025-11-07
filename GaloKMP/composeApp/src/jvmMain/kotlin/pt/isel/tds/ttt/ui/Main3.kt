package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource
import galokmp.composeapp.generated.resources.*
import pt.isel.tds.ttt.model.*


@Composable
@Preview
private fun App() {
    var player by remember{ mutableStateOf(Player.CROSS) }
    MaterialTheme {
        PlayerView(player = player,
            modifier = Modifier.clickable( onClick = { player = player.other })
        )
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