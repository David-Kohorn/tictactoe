package wpics.extendedtictactoe.models

import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test for [BoardPosition], which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * @version 1.0
 */
class BoardPositionUnitTest {

    // ===========================================================
    // Public Methods (Testing BoardPosition)
    // ===========================================================

    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    /**
     * This is a test case for testing [BoardPosition]'s constructor.
     */
    @Test(timeout = 100)
    fun testBoardPosition_Constructor() {
        val pos = boardPositionFactory(2, 3)
        Assert.assertEquals("2,3", pos.toString())
    }

    // -----------------------------------------------------------
    // equals
    // -----------------------------------------------------------

    /**
     * This is a test case for testing [BoardPosition.equals] for having same rows, but different columns.
     */
    @Test(timeout = 100)
    fun testBoardPosition_equals_SameRow_DiffCol() {
        Assert.assertNotEquals(boardPositionFactory(1, 5), boardPositionFactory(1, 4))
    }

    /**
     * This is a test case for testing [BoardPosition.equals] for having different rows, but same columns.
     */
    @Test(timeout = 100)
    fun testBoardPosition_equals_DiffRow_SameCol() {
        Assert.assertNotEquals(boardPositionFactory(5, 2), boardPositionFactory(4, 2))
    }

    /**
     * This is a test case for testing [BoardPosition.equals] for having same rows and columns.
     */
    @Test(timeout = 100)
    fun testBoardPosition_equals_True() {
        Assert.assertEquals(boardPositionFactory(2, 3), boardPositionFactory(2, 3))
    }

    /**
     * This is a test case for testing [BoardPosition.equals] begin able to handle different objects.
     */
    @Test(timeout = 100)
    fun testBoardPosition_equals_DiffObject() {
        Assert.assertNotEquals(boardPositionFactory(1, 5), Any())
    }

    /**
     * This is a test case for testing [BoardPosition.equals] begin able to handle the same object.
     */
    @Test(timeout = 100)
    fun testBoardPosition_equals_SameObject() {
        val pos = boardPositionFactory(1, 5)
        Assert.assertEquals(pos, pos)
    }

    // -----------------------------------------------------------
    // getRow
    // -----------------------------------------------------------

    /**
     * This is a test case for testing [BoardPosition.getRow] method.
     */
    @Test(timeout = 100)
    fun testBoardPosition_getRow() {
        val pos = boardPositionFactory(2, 3)
        Assert.assertEquals(2, pos.row.toLong())
    }

    // -----------------------------------------------------------
    // getColumn
    // -----------------------------------------------------------

    /**
     * This is a test case for testing [BoardPosition.getColumn] method.
     */
    @Test(timeout = 100)
    fun testBoardPosition_getColumn() {
        val pos = boardPositionFactory(2, 3)
        Assert.assertEquals(3, pos.column.toLong())
    }

    // ===========================================================
    // Private Methods
    // ===========================================================

    /**
     * This method is a factory for creating [BoardPosition]s.
     *
     * @param row A row number.
     * @param col A column number.
     *
     * @return A [BoardPosition] object.
     */
    private fun boardPositionFactory(row: Int, col: Int): BoardPosition {
        return BoardPosition(row, col)
    }
}