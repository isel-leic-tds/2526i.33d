package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import galokmp.composeapp.generated.resources.Res
import galokmp.composeapp.generated.resources.circle
import galokmp.composeapp.generated.resources.cross
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import pt.isel.tds.ttt.model.Player

val CELL_SIDE = 200.dp


@Composable
@Preview
fun CellTest() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Cell(Player.CROSS)
        Cell(Player.BALL, Modifier.size(100.dp))
    }
}

@Composable
fun Cell(
    player: Player?,
    modifier: Modifier = Modifier.size(CELL_SIDE).background(Color.White),
    animation: Boolean = false,
    onClickInEmpty: ()->Unit = { }
) {
    val mod = modifier
    if (player==null) Box(mod.clickable(onClick = onClickInEmpty))
    else {
        val resource = when (player) {
            Player.CROSS -> Res.drawable.cross
            Player.BALL -> Res.drawable.circle
        }
        if (!animation)
            Image(painterResource(resource), null, modifier = mod)
        else {
            var zoom by remember { mutableStateOf(0.1f) }
            Image(painterResource(resource), null,
                modifier = mod.graphicsLayer(scaleX = zoom, scaleY = zoom)
            )
            LaunchedEffect(player) {
                while (zoom < 1f) {
                    delay(50)
                    zoom += 0.1f
                }
            }
        }
    }
}