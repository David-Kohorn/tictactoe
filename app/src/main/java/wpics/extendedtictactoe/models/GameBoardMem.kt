//David Kohorn

package wpics.extendedtictactoe.models

import kotlin.collections.mutableListOf

/**
 * This class is an implementation of [IGameBoard] that uses a map of players (key) to
 * list of occupied positions (value) to store the information.
 *
 * @version 1.0
 */
class GameBoardMem(
    override val numRows: Int,
    override val numColumns: Int,
    override val numToWin: Int
) : AbsGameBoard(), IGameBoard {
    val board: HashMap<Char, MutableList<BoardPosition>> = hashMapOf()

    override fun isPlayerAtPos(pos: BoardPosition, player: Char): Boolean {
        return board[player]?.contains(pos) ?: false
    }

    override fun placeMarker(marker: BoardPosition, player: Char) {
        board.getOrPut(player) { mutableListOf() }.add(marker)
    }

    override fun whatsAtPos(pos: BoardPosition): Char {
        for ((key, positions) in board) {
            if (positions.contains(pos)) {
                return key
            }
        }
        return ' '
    }
}