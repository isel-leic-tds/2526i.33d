package pt.isel.tds

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import kotlinx.coroutines.*

/**
 * Simple application to alternate the enable button.
 * The "Click me" button when clicked becomes disabled for 5 seconds
 * or until the "Enable Click" button is clicked.
 */
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val scope = rememberCoroutineScope()
        var clickable by remember { mutableStateOf(true) }
        Row {
            Button(enabled = clickable, onClick = {
                println("Clicked")
                clickable = false
                scope.launch {
                    repeat(5) {
                        print(".")
                        delay(1000)
                    }
                    clickable = true
                }
            } ) { Text("Click me") }
            Button(enabled = !clickable, onClick = {
                clickable = true
            } ) { Text("Enable Click") }
        }
    }
}
