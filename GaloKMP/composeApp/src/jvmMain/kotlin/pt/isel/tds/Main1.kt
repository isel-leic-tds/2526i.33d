package pt.isel.tds

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*

@Composable
private fun App() {
    print("App ")
    var text by remember { mutableStateOf("Hello, World!") }
    MaterialTheme {
        Button(onClick = {
            print("\nClick ")
            text = "Hello, TDS"
        }) {
            print("Button text = $text")
            Text(text)
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "GaloKMP",
    ) {
        print("\nWindow ")
        App()
    }
}

