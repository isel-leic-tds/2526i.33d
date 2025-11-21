package pt.isel.tds

import androidx.compose.material3.*
import androidx.compose.ui.window.*

fun log(lab: String) = println("$lab: ${Thread.currentThread().name}")

/**
 * A Compose Desktop application that logs the execution thread.
 */
fun main() {
    log("main") // main thread
    application(exitProcessOnExit = false) {
        log("application") // UI thread
        Window(onCloseRequest = ::exitApplication) {
            Button(onClick = {
                log("onClick") // UI thread
            }) { Text("Ok") }
        }
    }
    log("exit") // main thread
}
