package pt.isel.tds.ttt.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import pt.isel.tds.ttt.model.Name

@Composable
fun EditDialog(mode: EditMode, onCancel: ()->Unit, onAction: (Name)->Unit) {
    var name by remember { mutableStateOf("") }
    val isValid = Name.isValid(name)
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Name for ${mode.text}") },
        text = { OutlinedTextField(
            value = name,
            label = { Text("clash name") },
            isError = !isValid,
            onValueChange = { name = it },
        ) },
        dismissButton = { Button(onCancel) { Text("Cancel") } },
        confirmButton = { Button({ onAction(Name(name)) }, enabled= isValid) { Text(mode.text) } }
    )
}