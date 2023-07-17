import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.seiko.imageloader.LocalImageLoader
import com.utsman.core.AppImageLoader
import com.utsman.fakestore.home.ui.Home

@Composable
fun App() {
    CompositionLocalProvider(
        LocalImageLoader provides AppImageLoader()
    ) {
        MaterialTheme {
            Home()
        }
    }
}

expect fun getPlatformName(): String