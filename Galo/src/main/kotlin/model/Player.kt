package model
import model.Player.*

enum class Player {
    CROSS, BALL
}

val Player.other get() = if (this==CROSS) BALL else CROSS

fun String.toPlayerOrNull() = Player.entries.firstOrNull { it.name==this }

fun String.toPlayer() = Player.valueOf(this)