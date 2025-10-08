package foo.bar.inputtestdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("topbar") })
            }
        },
        bottomBar = { TabsBar() }
    ) { innerPadding ->

        val padding = if (WindowInsets.isImeVisible) {
            PaddingValues(0.dp)
        } else {
            innerPadding
        }

//      Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(padding)) {
//      Box {
            Contents()
        }
    }
}

val WindowInsets.Companion.isImeVisible: Boolean
    @Composable
    get() {
        val density = LocalDensity.current
        val ime = ime

        return remember(density) {
            derivedStateOf { ime.getBottom(density) > 0 }
        }.value
    }

@Composable
private fun Contents() {
    val focusManager = LocalFocusManager.current

    Box {
        Column(
            modifier = Modifier
                .imePadding()
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Main content area")
            }

            BasicText()
        }
    }
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
            .height(60.dp)
            .fillMaxWidth()
            .background(Color.Yellow),
    )
}

@Composable
fun TabsBar() {
    NavigationBar(
        modifier = Modifier.height(100.dp),
        containerColor = Color.Green,
    ) { }
}
