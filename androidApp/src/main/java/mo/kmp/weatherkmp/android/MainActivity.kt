package mo.kmp.weatherkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import mo.kmp.weatherkmp.android.ui.LandingPage
import mo.kmp.weatherkmp.android.ui.LandingViewModel
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val landingViewModel = koinInject<LandingViewModel>()
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    LandingPage(viewModel = landingViewModel)
                }
            }
        }
    }
}
