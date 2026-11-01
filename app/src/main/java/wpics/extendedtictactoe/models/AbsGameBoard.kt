//David Kohorn

package wpics.extendedtictactoe.models

/**
 * This abstract class contains the overridden [Object.toString] method for all game boards.
 *
 * @version 1.0
 */
abstract class AbsGameBoard : IGameBoard {

    /**
     * This overrides the default [Object.toString] implementation.
     *
     * @return A string representation for this object.
     */
    override fun toString(): String {
        // Building the label row
        val sb = StringBuilder("   ")
        for (i in 0 until numColumns) {
            if (i < 10) {
                sb.append(" ")
            }
            sb.append(i)
            sb.append("|")
        }
        sb.append("\n")

        // Building each row of the game board
        for (curRow in 0 until numRows) {
            if (curRow < 10) {
                sb.append(" ")
            }
            sb.append(curRow)
            sb.append("|")
            for (curCol in 0 until numColumns) {
                sb.append(whatsAtPos(BoardPosition(curRow, curCol)))
                sb.append(" |")
            }
            sb.append("\n")
        }

        return sb.toString()
    }
}