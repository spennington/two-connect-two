
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;


/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class IOTest {

	/**
	 * Test method for {@link IO#parseGameState()}.
	 */
	@Test
	public void testParseGameState() {
		String gameStateStr = "(10,8,7,828,34,2," +
						    "b,b,r,b,r,r,b,b,r,g," +
						    "g,g,g,b,r,r,s,s,s,s," +
						    "s,s,s,s,b,b,r,b,r,b," +
						    "r,b,r,b,r,b,r,r,b,r," +
						    "r,g,r,b,g,b,r,r,b,r," +
						    "b,r,s,s,s,s,b,r,b,r," +
						    "b,b,r,b,r,b,b,r,b,b," +
						    "g,r,g,r,r,b,r,r,b,r)";
		IO.init(new ByteArrayInputStream(gameStateStr.getBytes()));
		GameState gameState = IO.parseGameState();
		IO.close();
		assertEquals(10, gameState.numCols);
		assertEquals(8, gameState.numRows);
		assertEquals(7, gameState.lastCol);
		assertEquals(828, gameState.gameTime);
		assertEquals(34, gameState.playerOneTime);
		assertEquals(2, gameState.lastMoveTime);
	}

	/**
	 * Test method for {@link IO#parseBoard(int, int)}.
	 */
	@Test
	public void testParseBoard() {
		String gameStateStr =
	    "b,b,r,b,r,r,b,b,r,g," +
	    "g,g,g,b,r,r,s,s,s,s," +
	    "s,s,s,s,b,b,r,b,r,b," +
	    "r,b,r,b,r,b,r,r,b,r," +
	    "r,g,r,b,g,b,r,r,b,r," +
	    "b,r,s,s,s,s,b,r,b,r," +
	    "b,b,r,b,r,b,b,r,b,b," +
	    "g,r,g,r,r,b,r,r,b,r)";
		IO.init(new ByteArrayInputStream(gameStateStr.getBytes()));
		Board board = IO.parseBoard(10, 8);
		IO.close();
		
		assertEquals(Board.BLUE, board.get(0, 0));
		assertEquals(Board.BLUE, board.get(0, 1));
		assertEquals(Board.RED, board.get(0, 2));
		assertEquals(Board.BLUE, board.get(0, 3));
		assertEquals(Board.RED, board.get(0, 4));
		assertEquals(Board.RED, board.get(0, 5));
		assertEquals(Board.BLUE, board.get(0, 6));
		assertEquals(Board.BLUE, board.get(0, 7));
		
		assertEquals(Board.RED, board.get(1, 0));
		assertEquals(Board.GREEN, board.get(1, 1));
		assertEquals(Board.GREEN, board.get(1, 2));
		assertEquals(Board.GREEN, board.get(1, 3));
		assertEquals(Board.GREEN, board.get(1, 4));
		assertEquals(Board.BLUE, board.get(1, 5));
		assertEquals(Board.RED, board.get(1, 6));
		assertEquals(Board.RED, board.get(1, 7));
		
		assertEquals(Board.GREEN, board.get(9, 0));
		assertEquals(Board.RED, board.get(9, 1));
		assertEquals(Board.RED, board.get(9, 2));
		assertEquals(Board.BLUE, board.get(9, 3));
		assertEquals(Board.RED, board.get(9, 4));
		assertEquals(Board.RED, board.get(9, 5));
		assertEquals(Board.BLUE, board.get(9, 6));
		assertEquals(Board.RED, board.get(9, 7));
	}

}
