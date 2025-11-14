package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import androidx.compose.ui.window.MenuBar
import galokmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource


@Composable
@Preview
fun FrameWindowScope.App(onExit: ()->Unit) {
    val vm = remember { AppViewModel() }
    MenuBar {
        Menu("Game") {
            Item("new board", onClick = vm::newBoard )
            Item("score", onClick = vm::showScore)
            Item("exit", onClick = onExit)
        }
    }
    MaterialTheme {
        Column {
            Grid(vm.game.board, onClick = vm::play)
            StatusBar(vm.game.state)
        }
        if (vm.viewScore) ScoreInfo(vm.game.score, onClose= vm::hideScore)
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
        App(::exitApplication)
    }
}
