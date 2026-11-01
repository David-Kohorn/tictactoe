package wpics.extendedtictactoe.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import wpics.extendedtictactoe.models.BoardPosition
import wpics.extendedtictactoe.models.GameBoard
import wpics.extendedtictactoe.models.GameBoardMem
import wpics.extendedtictactoe.models.IGameBoard

/**
 * This class is the ViewModel class for the [GameScreen].
 */
class GameViewModel : ViewModel() {

    /** Variables for implementing your game */
    private lateinit var gameBoard: IGameBoard

    // There will be at most 10 players!
    // Feel free to change these if you want to use other characters!
    private var playerTokens: List<Char> =
        listOf('X', 'O', 'L', 'P', 'A', 'B', 'T', 'M', 'J', 'V')

    //Ensure we stop game when it is over
    private var gameOver: Boolean = false
    /**
     * A mutable state containing all the necessary items to render our screen
     */
    var uiState by mutableStateOf(GameUIState())
        private set

    /**
     * A helper function that creates a new game board using the information from the
     * set up screen.
     *
     * @param navController A navigation controller.
     */
    fun createGameBoard(navController: NavHostController) {
        gameOver = false
        val numRows = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("numRows")
        val numColumns = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("numCols")
        val numToWin = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("numToWin")
        val numPlayers =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>("numPlayers")
        val memCutOff =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>("memCutOff")
        if (numRows == null || numColumns == null || numToWin == null ||
            numPlayers == null || memCutOff == null) {
            navController.navigate(
                route = MainScreen.Setup.name
            )
        } else {
            gameBoard = if (numRows * numColumns <= memCutOff) GameBoard(
                numRows,
                numColumns,
                numToWin
            ) else GameBoardMem(numRows, numColumns, numToWin)
        }

        // Update the display message with the first player's turn
        val message = "It's ${playerTokens.first()}'s turn!"

        // Create a new GameUIState
        uiState = GameUIState(
            numRows = numRows!!,
            numCols = numColumns!!,
            numToWin = numToWin!!,
            numPlayers = numPlayers!!,
            useMemoryBoard = numRows * numColumns <= memCutOff!!,
            currentPlayer = playerTokens[0],
            currentPlayerIndex = 0,
            message = message,
        )

        // Note: To just make adjustments to an existing GameUIState, call uiState.copy(...)
        // Example:
        //   uiState = uiState.copy(
        //     message = displayMsg
        //   )
    }

    fun placePiece(x: Int, y: Int) {
        if (gameOver) {
            val newMessage = "Click \"Start New Game\" at the bottom to play again."
            uiState = uiState.copy(
                message = newMessage
            )
            return
        }
        val bp = BoardPosition(x, y)
        if (gameBoard.whatsAtPos(bp) != ' ') {
            val newMessage = "Nice try ${uiState.currentPlayer}, pick again."
            uiState = uiState.copy(
                message = newMessage
            )
        }
        else {
            gameBoard.placeMarker(bp, uiState.currentPlayer)
            if (gameBoard.checkForWinner(bp)) {
                val newMessage = "${uiState.currentPlayer} wins!"
                uiState = uiState.copy(
                    message = newMessage
                )
                gameOver = true
            }
            else if (gameBoard.checkForDraw()) {
                val newMessage = "Draw game!"
                uiState = uiState.copy(
                    message = newMessage
                )
                gameOver = true
            }
            else {
                var newIndex = uiState.currentPlayerIndex + 1
                if (newIndex + 1 > uiState.numPlayers) {newIndex = 0}//Loop back on max player count

                val newPlayer = playerTokens[newIndex]
                val newMessage = "It's $newPlayer's turn!"
                uiState = uiState.copy(
                    currentPlayerIndex = newIndex,
                    currentPlayer = newPlayer,
                    message = newMessage
                )
            }
        }
    }

    fun getPiece(x: Int, y: Int): String {
        //On first loadup, gameBoard not initialized yet so default to empty spaces
        if (!::gameBoard.isInitialized) {
            return ""
        }
        return gameBoard.whatsAtPos(BoardPosition(x, y)).toString()
    }

}