//David Kohorn

package wpics.extendedtictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import wpics.extendedtictactoe.ui.ExtendedTicTacToeApp
import wpics.extendedtictactoe.ui.theme.ExtendedTicTacToeTheme

/**
 * The class that handles the main entry point to our application
 */
class MainActivity : ComponentActivity() {

    /**
     * This overrides the [onCreate] function to start our app.
     *
     * @param savedInstanceState the saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExtendedTicTacToeTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExtendedTicTacToeApp()
                }
            }
        }
    }
}