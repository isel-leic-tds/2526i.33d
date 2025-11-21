package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import galokmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource


@Composable
fun FrameWindowScope.App(onExit: ()->Unit) {
    val vm = remember { AppViewModel() }
    MenuBar {
        Menu("Game") {
            Item("start clash", onClick = vm::start)
            Item("join clash", onClick = vm::join)
            Item("refresh", enabled = vm.isRun , onClick = vm::refresh)
            Item("new board", enabled = vm.newAvailable ,onClick = vm::newBoard )
            Item("score", enabled = vm.isRun , onClick = vm::showScore)
            Item("exit", onClick = { vm.finish(); onExit() })
        }
    }
    MaterialTheme {
        if (vm.isRun) Column {
            Grid(vm.game.board, onClick = vm::play)
            StatusBar(vm.game.state, vm.you)
        }
        else
            Box(Modifier.width(GRID_SIDE).height(GRID_SIDE+STATUS_HEIGHT))
        if (vm.viewScore) ScoreInfo(vm.game.score, vm.name, onClose= vm::hideScore)
        vm.editMode?.let{ EditDialog(it, vm::cancelEdit, vm::doAction ) }
        vm.message?.let{ MessageInfo(it, vm::clearMessage) }
    }
}

fun main() = application {
    Window(
        onCloseRequest = {}, //::exitApplication,
        title = "GaloKMP",
        icon = painterResource(Res.drawable.cross),
        state = WindowState(size= DpSize.Unspecified),
        resizable = false,
    ) {
        App(::exitApplication)
    }
}
