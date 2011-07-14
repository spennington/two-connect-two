import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author steve
 *
 */
public class AdvancedColumnEvaluatorTest {
	private Evaluator advancedColumnEvaluator = AdvancedColumnEvaluator.getInstance();
	
	@Test
	public void testEvaluate() {
		Board board = TestUtils.generateBoard("b,r,b,r,b,r,s,s,r,r,b,s,s,s,s,s,g,b,r,b,r,b,r,r,b,r,r,b,r,r,g,b,b,b,b,r,b,r,r,b,r,g,b,r,b,s,s,s,r,b,r,b,g,b,r,r,s,s,s,s,s,s,s,s,b,r,r,b,r,g,r,b,r,b,r,b,r,b,s,s", 10, 8);
		assertEquals(3520, advancedColumnEvaluator.evaluate(board, 0, Board.BLUE));
	}
	
	@Test
	public void testOneWin() {
		Board board = TestUtils.generateBoard("b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s,g,b,r,s,s,s,s,s,r,r,b,r,g,r,b,r,b,r,b,r,b,r,b,r,b,b,r,b,b,r,b,r,r,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s", 10, 8);
		assertEquals(-500, advancedColumnEvaluator.evaluate(board, 0, Board.BLUE));
	}
	
	@Test
	public void highDoubleWin() {
		Board board = TestUtils.generateBoard("r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,b,r,b,r,g,g,r,r,b,r,b,r,b,b,r,b,r,b,b,r,b,g,s,s,s,s,s,s,s,s,b,r,b,r,g,r,b,b,s,s,s,s,s,s,s,s,r,g,b,b,r,g,s,s,g,b,s,s,s,s,s,s", 10, 8);
		assertEquals(-100000, advancedColumnEvaluator.evaluate(board, 0, Board.BLUE));
	}
	
	@Test
	public void redDoubleWin() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,b,s,s,s,s,s,s,b,r,b,r,b,r,r,s,r,b,r,r,b,b,r,b,g,g,b,r,g,r,b,g,b,r,r,b,r,s,s,s,r,b,r,b,r,s,s,s,g,s,s,s,s,s,s,s", 10, 8);
		assertEquals(500000, advancedColumnEvaluator.evaluate(board, 0, Board.BLUE));
	}
	
	@Test public void testWinNowBlue() {
		Board board = new Board(10, 8);
		board.drop(0, Board.BLUE);
		board.drop(1, Board.BLUE);
		board.drop(2, Board.GREEN);
		assertEquals(-50000000, advancedColumnEvaluator.evaluate(board, 0, Board.BLUE));
	}
	
	@Test public void testWinNowRed() {
		Board board = new Board(10, 8);
		board.drop(0, Board.RED);
		board.drop(1, Board.BLUE);
		board.drop(1, Board.RED);
		board.drop(2, Board.BLUE);
		board.drop(2, Board.RED);
		board.drop(2, Board.GREEN);
		board.drop(3, Board.RED);
		board.drop(3, Board.BLUE);
		board.drop(3, Board.RED);
		assertEquals(50000000, advancedColumnEvaluator.evaluate(board, 0, Board.RED));
	}

}
