package wpics.extendedtictactoe.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/* Constants for setting up our screen */
const val MAX_SIZE = 20
const val MIN_SIZE = 3
const val MIN_TO_WIN = 3
const val MEM_CUTOFF = 64

/**
 * This class is the ViewModel class for the [SetupScreen].
 */
class SetupViewModel : ViewModel() {

    /**
     * A mutable state containing the number of rows entered via the UI
     */
    var numRowsInput by mutableStateOf("")
    var hasErrorNumRowsInput by mutableStateOf("")

    /**
     * A mutable state containing the number of columns entered via the UI
     */
    var numColsInput by mutableStateOf("")
    var hasErrorNumColsInput by mutableStateOf("")

    /**
     * A mutable state containing the number to win entered via the UI
     */
    var numToWinInput by mutableStateOf("")
    var hasErrorNumToWinInput by mutableStateOf("")

    /**
     * A flag that checks if our dropdown menu is expanded
     */
    var expanded by mutableStateOf(false)

    /**
     * A mutable state containing the number of players entered via the UI
     */
    var numPlayersInput by mutableStateOf("")
    var hasErrorNumPlayersInput by mutableStateOf("")

    /**
     * This is a callback function that handles the submit form validation
     *
     * @return True if there are no errors, false otherwise.
     */
    fun validateSubmitForm(): Boolean {
        // Clear old error messages
        hasErrorNumRowsInput = ""
        hasErrorNumColsInput = ""
        hasErrorNumToWinInput = ""
        hasErrorNumPlayersInput = ""

        // Obtain number of rows
        val numRows = numRowsInput.toIntOrNull() ?: 0

        // Obtain number of columns=
        val numCols = numColsInput.toIntOrNull() ?: 0

        // Obtain number to win
        val numToWin = numToWinInput.toIntOrNull() ?: 0

        // Obtain number of players
        val numPlayers = numPlayersInput.trim().toIntOrNull() ?: 0

        // Validate data
        if (numRows !in MIN_SIZE..MAX_SIZE) {
            hasErrorNumRowsInput = "Rows must be between $MIN_SIZE and $MAX_SIZE";
        } else {
            if (numCols !in MIN_SIZE..MAX_SIZE) {
                hasErrorNumColsInput = "Columns must be between $MIN_SIZE and $MAX_SIZE";
            } else {
                if (numToWin > numRows) {
                    hasErrorNumToWinInput = "Can't have more to win than the number of rows";
                } else if (numToWin > numCols) {
                    hasErrorNumToWinInput = "Can't have more to win than the number of columns";
                } else if (numToWin < MIN_TO_WIN) {
                    hasErrorNumToWinInput = "Number to win must be at least $MIN_TO_WIN";
                } else if (numPlayers !in 2..10) {
                    hasErrorNumPlayersInput = "Must be between 2 and 10 players"
                }
            }
        }

        return hasErrorNumRowsInput.isEmpty() && hasErrorNumColsInput.isEmpty() &&
                hasErrorNumToWinInput.isEmpty() && hasErrorNumPlayersInput.isEmpty()
    }
}