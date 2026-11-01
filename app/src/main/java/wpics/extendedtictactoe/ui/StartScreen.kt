package wpics.extendedtictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import wpics.extendedtictactoe.ui.theme.ExtendedTicTacToeTheme
import wpics.extendedtictactoe.ui.theme.Green
import wpics.extendedtictactoe.ui.theme.WPI_Gray
import wpics.extendedtictactoe.ui.theme.White

/**
 * The main start screen.
 */
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WelcomeMsg(modifier)
        AppTitle(modifier)
        StartGameButton(
            onClick = {
                navController.navigate(
                    route = MainScreen.Setup.name
                )
            },
            modifier = modifier
        )
    }
}

/**
 * The welcome message
 */
@Composable
fun WelcomeMsg(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to CS 528",
        fontSize = 32.sp,
        color = WPI_Gray,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp,
        modifier = modifier
    )
}

/**
 * The application title
 */
@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Text(
        text = "ExtendedTicTacToe",
        fontSize = 20.sp,
        color = White,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic,
        modifier = modifier.padding(20.dp)
    )
}

/**
 * Start game button
 */
@Composable
fun StartGameButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = White
        ),
        modifier = modifier
    ) {
        Text("Start Game")
    }
}

/**
 * The preview screen
 */
@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    ExtendedTicTacToeTheme(dynamicColor = false) {
        StartScreen(navController = rememberNavController())
    }
}