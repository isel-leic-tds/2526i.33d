package pt.isel.tds.ttt.model
import pt.isel.tds.ttt.model.Player.*

enum class Player {
    CROSS, BALL
}

val Player.other get() = if (this==CROSS) BALL else CROSS

fun String.toPlayerOrNull() = entries.firstOrNull { it.name==this }

fun String.toPlayer() = valueOf(this)