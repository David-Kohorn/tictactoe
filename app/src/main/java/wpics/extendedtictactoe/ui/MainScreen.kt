package wpics.extendedtictactoe.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * Enumeration for the different screens in our app.
 */
enum class MainScreen() {
    Start(),
    Setup(),
    Game()
}

/**
 * This composable provides code for setting up the navigation between screens.
 */
@Composable
fun ExtendedTicTacToeApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreen.Start.name
    ) {
        composable(route = MainScreen.Start.name) {
            StartScreen(navController = navController)
        }
        composable(route = MainScreen.Setup.name) {
            SetupScreen(navController = navController)
        }
        composable(route = MainScreen.Game.name) {
            GameScreen(navController = navController)
        }
    }
}