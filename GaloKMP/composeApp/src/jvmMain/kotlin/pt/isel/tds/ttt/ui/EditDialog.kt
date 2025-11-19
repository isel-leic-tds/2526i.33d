package pt.isel.tds.ttt.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import pt.isel.tds.ttt.model.Name

@Composable
fun EditDialog(mode: EditMode, onCancel: ()->Unit, onAction: (Name)->Unit) {
    var name by mutableStateOf("")
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Name for ${mode.text}") },
        text = {
            OutlinedTextField(
                value = name,
                label = { Text("clash name") },
                onValueChange = { if (Name.isValid(it)) name = it }
            )
        },
        dismissButton = { Button(onClick = onCancel) { Text("Cancel") } },
        confirmButton = { Button(onClick = { onAction(Name(name)) }) { Text(mode.text) } }
    )
}