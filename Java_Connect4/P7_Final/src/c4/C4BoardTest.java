package c4;
import static org.junit.Assert.*;
import org.junit.Test;
public class C4BoardTest
{
    @Test
    public void testConstructor1()
    {
        C4Board testBoard = new C4Board(10,12);
        // check row
        assertEquals(testBoard.rows, 10);
        // check column
        assertEquals(testBoard.cols, 12);
        // check current player
        assertEquals(testBoard.currPlayer, 1);
        // check empty ties
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 12; j++)
                assertEquals(0, testBoard.board[i][j]);
    }
    
    @Test
    public void testConstructor2()
    {
        try 
        {
            // check column < 4
            C4Board b = new C4Board(10, 3);
            fail("Should throw IllegalArgumentException!");
        }
        catch (IllegalArgumentException e)
        {           
        }
        try 
        {
            // check row < 4
            C4Board b = new C4Board(1, 12);
            fail("Should throw IllegalArgumentException!");
        }
        catch (IllegalArgumentException e)
        {           
        }
    }
    @Test
    public void testNewGame()
    {
        C4Board testBoard = new C4Board(10,12);
        // make some moves
        testBoard.moveTo(1);
        testBoard.moveTo(2);
        testBoard.moveTo(1);
        testBoard.moveTo(2);
        testBoard.moveTo(3);
        testBoard.moveTo(0);
        // test new game
        testBoard.newGame();
        // check status
        assertEquals(0, testBoard.status);
        // check empty ties
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 12; j++)
                assertEquals(0, testBoard.board[i][j]);
        // check player alternative
        assertEquals(1, testBoard.whoStart);
        testBoard.newGame();
        assertEquals(2, testBoard.whoStart);
        testBoard.newGame();
        assertEquals(1, testBoard.whoStart);
        
    }
    @Test
    public void testMoveTo1()
    {
        C4Board testBoard = new C4Board(6,8);
        // make some illegal moves
        try
        {
            testBoard.moveTo(-1);
            fail("Should throw IllegalArgumentException!");
        }
        catch (IllegalArgumentException e)
        {           
        }
        try
        {
            testBoard.moveTo(8);
            fail("Should throw IllegalArgumentException!");
        }
        catch (IllegalArgumentException e)
        {           
        }
    }
    @Test
    public void testMoveTo_getOccupant_getToMove_getWins()
    {
        C4Board testBoard = new C4Board(6,8);
        testBoard.moveTo(0); // red at (5,0)
        // check tie is set correctly
        assertEquals(1, testBoard.board[5][0]);
        // check player is changed alternatively
        assertEquals(2, testBoard.currPlayer);
        testBoard.moveTo(0); // yellow at (4,0)
        // check tie is set correctly
        assertEquals(-1, testBoard.board[4][0]);
        // check player is changed alternatively
        assertEquals(1, testBoard.currPlayer);
        
        // now check for a win on row
        testBoard.moveTo(1); // red at (5,1)
        // check Occupant
        int tie = testBoard.getOccupant(0, 1); // (0,1) here is (5,1) above and should be red
        assertEquals(1, tie);
        testBoard.moveTo(1); // yellow at (4,1)
        // check Occupant
        tie = testBoard.getOccupant(1, 1); // (1,1) here is (4,1) above and should be yellow
        assertEquals(2, tie);
        testBoard.moveTo(2); // red at (5,2)
        // check getToMove: red just moved so now should be yellow
        assertEquals(2, testBoard.getToMove());
        testBoard.moveTo(2); // yellow at (4,2)
        testBoard.moveTo(3); // red at (5,3) => red win!
        assertEquals(1, testBoard.status);
        // check getToMove: red win show now should be 0
        assertEquals(0, testBoard.getToMove());
        
        // check counting wins
        assertEquals(1, testBoard.winsP1);
        
        
        // check for a win on column
        testBoard.newGame(); // P2 starts first
        testBoard.moveTo(0); // yellow at (5,0)
        testBoard.moveTo(1);
        testBoard.moveTo(0); // yellow at (4,0)
        testBoard.moveTo(1);
        testBoard.moveTo(0); // yellow at (3,0)
        testBoard.moveTo(1);
        testBoard.moveTo(0); // yellow at (2,0) => yellow win!
        assertEquals(2, testBoard.status);
        
        // check counting wins
        assertEquals(1, testBoard.winsP2);
        
        // check for a win on \ diagonal
        testBoard.newGame(); // P1 starts first
        testBoard.moveTo(0);
        testBoard.moveTo(0);
        testBoard.moveTo(0);
        testBoard.moveTo(0); // yellow at (2,0)
        
        testBoard.moveTo(1);
        testBoard.moveTo(2);
        testBoard.moveTo(1);
        testBoard.moveTo(1); // yellow at (3,1)
        
        testBoard.moveTo(4);
        testBoard.moveTo(3); // yellow at (5,3)
        
        testBoard.moveTo(3);
        testBoard.moveTo(2); // yellow at (4,2) => yellow diagonal win!!
        assertEquals(2, testBoard.status);
        
        // check counting wins
        assertEquals(2, testBoard.winsP2);
        
        // check for a win on / diagonal
        testBoard.newGame(); // P2 starts first
        testBoard.moveTo(0); // yellow at (5,0)
        testBoard.moveTo(1);
        testBoard.moveTo(1); // yellow at (4,1)
        testBoard.moveTo(2);
        testBoard.moveTo(3);
        testBoard.moveTo(2);
        testBoard.moveTo(2); // yellow at (3,2)
        testBoard.moveTo(4);
        testBoard.moveTo(3);
        testBoard.moveTo(3);
        testBoard.moveTo(3); // yellow at (2,3) => yellow diagonal win!
        assertEquals(2,  testBoard.status);
        
        // check counting wins
        assertEquals(3, testBoard.winsP2);
    }
}
