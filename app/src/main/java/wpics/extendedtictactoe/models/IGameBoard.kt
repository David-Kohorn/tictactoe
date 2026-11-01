//David Kohorn

package wpics.extendedtictactoe.models

/**
 * IGameBoard represents abstractly a 2 dimensional game board of characters and will be implemented
 * by the implementing classes.
 *
 * @version 1.0
 */
interface IGameBoard {

    /**
     * This method checks if a player has won because they have [numToWin] diagonally on the
     * game board using the last placed position.
     *
     * @param lastPos The last position where we placed a marker
     * @param player  The player token placed on lastPos
     *
     * @return True if there are [numToWin] characters diagonally that includes the specified
     * position, false otherwise.
     */
    fun checkDiagonalWin(lastPos: BoardPosition, player: Char): Boolean {
        //Check diagonal from NW to SE
        var numConsecutive: Int = 1 //Include the space we just put down

        //Count consecutive same pieces SE
        var currentCol: Int = lastPos.column + 1
        var currentRow: Int = lastPos.row + 1
        while (currentCol < numColumns && currentRow < numRows && isPlayerAtPos(BoardPosition(currentRow, currentCol), player)) {
            numConsecutive++
            currentCol++
            currentRow++
        }

        //Count consecutive same pieces NW
        currentCol = lastPos.column - 1
        currentRow = lastPos.row - 1
        while (currentCol >= 0 && currentRow >= 0 && isPlayerAtPos(BoardPosition(currentRow, currentCol), player)) {
            numConsecutive++
            currentCol--
            currentRow--
        }

        if (numConsecutive >= numToWin) return true

        //Check diagonal from NE to SW
        numConsecutive = 1 //Include the space we just put down

        //Count consecutive same pieces NE
        currentCol = lastPos.column + 1
        currentRow = lastPos.row - 1
        while (currentCol < numColumns && currentRow >= 0 && isPlayerAtPos(BoardPosition(currentRow, currentCol), player)) {
            numConsecutive++
            currentCol++
            currentRow--
        }

        //Count consecutive same pieces SW
        currentCol = lastPos.column - 1
        currentRow = lastPos.row + 1
        while (currentCol >= 0 && currentRow < numRows && isPlayerAtPos(BoardPosition(currentRow, currentCol), player)) {
            numConsecutive++
            currentCol--
            currentRow++
        }

        return numConsecutive >= numToWin
    }

    /**
     * This method checks if this is a tie game.
     *
     * @return True if there are no more board positions to be filled and no player has won the game, false otherwise.
     */
    fun checkForDraw(): Boolean {
        for (row in 0..< numRows) {
            for (col in 0..< numColumns) {
                if (checkSpace(BoardPosition(row, col))) {
                    return false
                }
            }
        }
        return true
    }

    /**
     * This method checks if the last placed character in lastPost result in a win.
     *
     * @param lastPos The last position where we placed a marker
     *
     * @return True if there are NUM_TO_WIN characters vertically, horizontally or diagonally for the last placed
     * character in column c, false otherwise.
     */
    fun checkForWinner(lastPos: BoardPosition): Boolean {
        val player: Char = whatsAtPos(lastPos)
        return checkVerticalWin(lastPos, player) || checkHorizontalWin(lastPos, player) || checkDiagonalWin(lastPos, player)
    }

    /**
     * This method checks if a player has won because they have NUM_TO_WIN horizontally on the game board using the last
     * placed position.
     *
     * @param lastPos The last position where we placed a marker
     * @param player  The player token placed on lastPos
     *
     * @return True if there are NUM_TO_WIN characters horizontally that includes the specified position, false
     * otherwise.
     */
    fun checkHorizontalWin(lastPos: BoardPosition, player: Char): Boolean {
        var numConsecutive: Int = 1 //Include the space we just put down
        var currentCol: Int = lastPos.column + 1

        //Count consecutive same pieces right
        while (currentCol < numColumns && isPlayerAtPos(BoardPosition(lastPos.row, currentCol), player)) {
            numConsecutive++
            currentCol++
        }

        //Count consecutive same pieces left
        currentCol = lastPos.column - 1
        while (currentCol >= 0 && isPlayerAtPos(BoardPosition(lastPos.row, currentCol), player)) {
            numConsecutive++
            currentCol--
        }

        return numConsecutive >= numToWin
    }

    /**
     * This method checks if board position pos is available.
     *
     * @param pos A board position
     *
     * @return True if the specified position is a space in pos, false otherwise.
     */
    fun checkSpace(pos: BoardPosition): Boolean {
        if (pos.row !in 0..numRows || pos.column !in 0..numColumns) return false
        return whatsAtPos(pos) == ' '
    }

    /**
     * This method checks if a player has won because they have NUM_TO_WIN vertically on the game board using the last
     * placed position.
     *
     * @param lastPos The last position where we placed a marker
     * @param player  The player token placed on lastPos
     *
     * @return True if there are NUM_TO_WIN characters vertically that includes the specified position, false otherwise.
     */
    fun checkVerticalWin(lastPos: BoardPosition, player: Char): Boolean {
        var numConsecutive: Int = 1 //Include the space we just put down
        var currentRow: Int = lastPos.row + 1

        //Count consecutive same pieces downwards
        while (currentRow < numRows && isPlayerAtPos(BoardPosition(currentRow, lastPos.column), player)) {
            numConsecutive++
            currentRow++
        }

        //Count consecutive same pieces upwards
        currentRow = lastPos.row - 1
        while (currentRow >= 0 && isPlayerAtPos(BoardPosition(currentRow, lastPos.column), player)) {
            numConsecutive++
            currentRow--
        }

        return numConsecutive >= numToWin
    }

    /**
     * This method returns the number of columns in this game board.
     *
     * @return The number of columns
     */
    val numColumns: Int

    /**
     * This method returns the number of rows in this game board.
     *
     * @return The number of rows
     */
    val numRows: Int

    /**
     * This method returns the number of markers in a row needed to win the game.
     *
     * @return The number in a row needed to win
     */
    val numToWin: Int

    /**
     * This method checks if a player token is located at the specified board position.
     *
     * @param pos    A board position.
     * @param player A player token.
     *
     * @return True if the player token is at that board position, false otherwise.
     */
    fun isPlayerAtPos(pos: BoardPosition, player: Char): Boolean

    /**
     * This method places a marker into the specified position on the board, where it was initially a blank space.
     *
     * @param marker The position where we want to place the marker.
     * @param player The character to be placed.
     */
    fun placeMarker(marker: BoardPosition, player: Char)

    /**
     * This method checks what character is located at that position.
     *
     * @param pos A board position on our game board.
     *
     * @return The character stored at the specified position
     */
    fun whatsAtPos(pos: BoardPosition): Char
}