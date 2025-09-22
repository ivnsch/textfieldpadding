package foo.bar.inputtestdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val focusManager = LocalFocusManager.current

    Scaffold { innerPadding ->
        Box {
            Column(
                modifier = Modifier
//                    .safeContentPadding()
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus()
                        })
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Main content area")
                }

//                OutlinedText()
                BasicText()
            }
        }
    }
}

@Composable
private fun OutlinedText() {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textValue,
        onValueChange = { textValue = it },
        placeholder = { Text("Enter text...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp), // ignored
    )
}

@Composable
private fun BasicText() {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("..."))
    }

    BasicTextField(
        value = textState,
        onValueChange = { textState = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp), // ignored
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Gray,
            fontSize = 16.sp
        ),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier
                .background(Color.Yellow)
                .padding(bottom = 40.dp)) {
                Text(
                    text ="...",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = LocalTextStyle.current.copy(fontSize = 16.sp)
                )
                innerTextField()
            }
        }
    )
}
