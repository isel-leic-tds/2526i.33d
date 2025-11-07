package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.BOARD_SIZE
import pt.isel.tds.ttt.model.Board
import pt.isel.tds.ttt.model.Position

@Composable
@Preview
fun GridTest() {
    Grid(Board(), onClick = { } )
}

val LINE_THICKNESS = 5.dp
val GRID_SIDE = CELL_SIDE*BOARD_SIZE + LINE_THICKNESS*(BOARD_SIZE-1)

@Composable
fun Grid(
    board: Board,
    modifier: Modifier = Modifier,
    onClick: (Position)->Unit
) {
    Column(
        modifier.height(GRID_SIDE).background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(BOARD_SIZE) { row ->
            Row(
                modifier.width(GRID_SIDE),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(BOARD_SIZE) { col ->
                    val pos = Position(row*BOARD_SIZE+col)
                    Cell(board[pos]) { onClick(pos) }
                }
            }
        }
    }
}