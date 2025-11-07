package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import galokmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import pt.isel.tds.ttt.model.*


@Composable
@Preview
private fun App() {
    var game by remember{ mutableStateOf(Game()) }
    MaterialTheme {
        Grid(game.board, onClick = { pos ->
            if (game.state is Run) game = game.play(pos)
        } )
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "GaloKMP",
        icon = painterResource(Res.drawable.cross),
        state = WindowState(size= DpSize.Unspecified),
        resizable = false,
    ) {
        App()
    }
}