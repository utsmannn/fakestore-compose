import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.utsman.network.Greeting
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme {
        val scope = rememberCoroutineScope()
        val greeting = remember { Greeting() }

        var greetingText by remember { mutableStateOf("Hello, World!") }
        var showImage by remember { mutableStateOf(false) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                scope.launch {
                    greetingText = greeting.greeting()
                }
                showImage = !showImage
            }) {
                Text("anuan")
            }

            Text(greetingText)
        }
    }
}

expect fun getPlatformName(): String