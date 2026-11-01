package wpics.extendedtictactoe.ui

/**
 * This class stores the current game state.
 *
 * @param numRows Number of rows
 * @param numCols Number of columns
 * @param numToWin Number required to win
 * @param numPlayers Number of players
 * @param markers Current markers on the board
 * @param currentPlayer Current player's marker
 * @param currentPlayerIndex Current player's marker index
 * @param isGameOver Boolean flag for game over
 * @param winner The winner's marker
 * @param message The current message displayed
 */
data class GameUIState(
    val numRows: Int= 3,
    val numCols: Int = 3,
    val numToWin: Int = 3,
    val numPlayers: Int = 2,
    val markers: MutableMap<Pair<Int, Int>, String> = mutableMapOf(),
    val useMemoryBoard: Boolean = false,
    val currentPlayer: Char = ' ',
    val currentPlayerIndex: Int = 0,
    val isGameOver: Boolean = false,
    val winner: Char? = null,
    val message: String = ""
)