package foo.bar.inputtestdemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import inputtestdemo.composeapp.generated.resources.Res
import inputtestdemo.composeapp.generated.resources.compose_multiplatform
import kotlin.math.log
import kotlinx.coroutines.flow.first

@Composable
@Preview
fun App() {
    var textValue by remember { mutableStateOf("") }
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

                OutlinedTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    placeholder = { Text("Enter text...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                )
            }
        }
    }
}
