package pt.isel.tds.ttt.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import pt.isel.tds.ttt.model.Name

@Composable
fun EditDialog(mode: EditMode, onCancel: ()->Unit, onAction: (Name)->Unit) {
    var name by remember { mutableStateOf("") }
    val isValid = Name.isValid(name)
    val fr = remember { FocusRequester() }
    fun keyHandler(ke: KeyEvent): Boolean =
        if (ke.key == Key.Enter && ke.type == KeyEventType.KeyDown) {
            if (isValid) onAction(Name(name))
            true
        } else false

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Name for ${mode.text}") },
        text = {
            OutlinedTextField(
                value = name,
                label = { Text("clash name") },
                isError = !isValid,
                singleLine = true,
                onValueChange = { name = it },
                modifier = Modifier.onKeyEvent(::keyHandler).focusRequester(fr)
            )
        },
        dismissButton = { Button(onCancel) { Text("Cancel") } },
        confirmButton = { Button({ onAction(Name(name)) }, enabled= isValid) { Text(mode.text) } }
    )
    LaunchedEffect(Unit) {
        fr.requestFocus()
    }
}