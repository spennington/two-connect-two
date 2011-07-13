

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author steve
 *
 */
public class AdjacentWinCheckerTest {

	/**
	 * Test method for {@link AdjacentWinChecker#evaluate(Board, int)}.
	 */
	@Test
	public void testEvaluate() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,b,r,s,s,s,s,s,r,b,r,b,r,s,s,s,b,g,r,b,r,b,s,s,s,s,s,s,s,s,s,s,r,g,s,s,s,s,s,s,g,s,s,s,s,s,s,s", 10, 8);
		Evaluator adjacentEvaluator = FutureWinEvaluator.getInstance();
		assertEquals(-504, adjacentEvaluator.evaluate(board, 0, Board.BLUE));
		
		board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,b,r,s,s,s,s,s,r,b,r,b,r,s,s,s,b,g,r,b,r,b,s,s,r,s,s,s,s,s,s,s,r,g,s,s,s,s,s,s,g,s,s,s,s,s,s,s", 10, 8);
		assertEquals(-501, adjacentEvaluator.evaluate(board, 0, Board.BLUE));
		
		
		
		
		board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,b,r,b,r,r,b,r,b,g,g,r,b,g,g,r,b,r,b,r,r,g,b,r,r,b,r,g,s,s,s,s,g,s,s,s,s,s,s,s,b,r,b,s,s,s,s,s", 10, 8);
		assertEquals(396, adjacentEvaluator.evaluate(board, 0, Board.BLUE));
		
		board = TestUtils.generateBoard("r,s,s,s,s,s,s,s,b,r,b,r,b,r,g,r,g,r,r,b,s,s,s,s,r,b,r,b,b,r,b,g,b,g,r,b,r,b,r,b,s,s,s,s,s,s,s,s,g,b,r,b,r,r,b,r,r,b,r,b,r,b,r,b,b,r,b,r,b,r,g,g,g,r,b,r,b,r,g,r", 10, 8);
		assertEquals(-10, adjacentEvaluator.evaluate(board, 0, Board.BLUE));
		
		board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,g,r,b,s,s,s,r,b,r,b,r,g,s,s,b,g,s,s,s,s,s,s,b,r,b,r,r,b,r,s,r,b,r,b,r,b,r,s,g,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		assertEquals(-13, adjacentEvaluator.evaluate(board, 0, Board.BLUE));
	}
	
	@Test 
	public void odin8() {
		Evaluator adjacentEvaluator = ChainEvaluator.getInstance();
		
		//Odin8 Jun 19
		Board actual = TestUtils.generateBoard("s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,g,b,r,s,s,s,s,s,r,b,r,b,s,s,s,s,s,s,s,s,s,s,s,s,b,r,r,b,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Board wanted = TestUtils.generateBoard("s,s,s,s,s,s,s,s,b,g,s,s,s,s,s,s,g,b,r,s,s,s,s,s,r,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,r,b,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		
		assertEquals(-4050, adjacentEvaluator.evaluate(wanted, 0, Board.BLUE));
		assertEquals(-25, adjacentEvaluator.evaluate(actual, 0, Board.BLUE));
	}

}
