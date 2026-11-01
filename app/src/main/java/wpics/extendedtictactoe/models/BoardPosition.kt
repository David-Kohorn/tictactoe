//David Kohorn

package wpics.extendedtictactoe.models

/**
 * This class keeps track of a single location on a board.
 *
 * @version 1.0
 */
data class BoardPosition(
    val row: Int,
    val column: Int
) {

    // ===========================================================
    // Public Methods
    // ===========================================================

    /**
     *
     * This overrides the default [Object.toString] implementation.
     *
     * @return A string representation using the format "<row>,<column>" for this object.
     */
    override fun toString(): String = "$row,$column"
}