package rohegde.ktm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import rohegde.ktm.swipe.SwipeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SwipeScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}