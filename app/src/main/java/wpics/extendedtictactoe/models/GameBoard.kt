//David Kohorn

package wpics.extendedtictactoe.models

/**
 * This class is an implementation of [IGameBoard] that uses a 2D array to store the information.
 *
 * @version 1.0
 */
class GameBoard(
    override val numRows: Int,
    override val numColumns: Int,
    override val numToWin: Int
) : AbsGameBoard(), IGameBoard {

    val board: Array<CharArray> = Array(numRows) { CharArray(numColumns) { ' ' } }

    override fun isPlayerAtPos(pos: BoardPosition, player: Char): Boolean {
        return whatsAtPos(pos) == player
    }

    override fun placeMarker(marker: BoardPosition, player: Char) {
        board[marker.row][marker.column] = player
    }

    override fun whatsAtPos(pos: BoardPosition): Char {
        return board[pos.row][pos.column]
    }
}