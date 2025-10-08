package model

@JvmInline
value class Position private constructor(val index: Int) {
    val row get() = index / BOARD_SIZE
    val col get() = index % BOARD_SIZE
    val backSlash get() = row == col
    val slash get() = row + col == BOARD_SIZE-1

    companion object {
        val values = List(BOARD_CELLS) { Position(it) }
        operator fun invoke(idx: Int): Position = values[idx]
    }
}


fun String.toPositionOrNull(): Position? {
    val idx = toIntOrNull() ?: return null
    return if (idx !in 0..<BOARD_CELLS) null
           else Position(idx)
}
