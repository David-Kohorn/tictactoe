package wpics.extendedtictactoe.ui

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import wpics.extendedtictactoe.ui.theme.*

/**
 * This composable provides code for the extended tic-tac-toe game.
 *
 * @param modifier A modifier.
 * @param gameViewModel A view model that accompanies this game screen view.
 * @param navController A navigation controller.
 */
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
    navController: NavHostController
) {
    // Create a game board object if we have the correct information
    LaunchedEffect(navController) {
        gameViewModel.createGameBoard(navController)
    }

    Column(
        modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: Add your game layout here. You will need to add functions in GameViewModel to handle button clicks.
        DisplayGameTitle(
            modifier = modifier
        )
        TextContextBox(
            modifier = modifier,
            gameViewModel = gameViewModel
        )
        GenerateBoard(
            modifier = Modifier.weight(1f),
            gameViewModel = gameViewModel
        )
        StartNewGameButton(
            onClick = {
                navController.navigate(
                    route = MainScreen.Setup.name
                )
            },
            modifier = modifier.padding(bottom = 30.dp)
        )
    }


}

/**
 * Start game button
 */
@Composable
fun StartNewGameButton(
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
        Text("Start New Game")
    }
}

@Composable
fun DisplayGameTitle(modifier: Modifier = Modifier) {
    Text(
        text = "ExtendedTicTacToe",
        fontSize = 26.sp,
        color = Green,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        lineHeight = 26.sp,
        modifier = modifier.statusBarsPadding().padding(25.dp) //Keep below the camera/status bar
    )
}

@Composable
fun TextContextBox(modifier: Modifier = Modifier, gameViewModel: GameViewModel) {
    Box(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth(0.95f)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            modifier = modifier.padding(10.dp),
            text = gameViewModel.uiState.message,
            color = Color.White,
            )
    }

}
@Composable
fun GenerateBoard(modifier: Modifier = Modifier, gameViewModel: GameViewModel) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (x in 0..< gameViewModel.uiState.numCols) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (y in 0..< gameViewModel.uiState.numRows) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .clickable {gameViewModel.placePiece(x, y)},
                        contentAlignment = Alignment.Center
                    ) {
                        BasicText(
                            text = gameViewModel.getPiece(x, y),
                            maxLines = 1,
                            autoSize = TextAutoSize.StepBased(
                                minFontSize = 10.sp,
                                maxFontSize = 30.sp
                            ),
                            style = TextStyle(
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}


/**
 * The preview screen
 */
@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    ExtendedTicTacToeTheme(dynamicColor = false) {
        GameScreen(navController = rememberNavController())
    }
}