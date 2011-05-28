package calypso.twoconnecttwo.ai.heuristics;

import static org.junit.Assert.*;

import org.junit.Test;

import calypso.twoconnecttwo.Board;
import calypso.twoconnecttwo.TestUtils;
import calypso.twoconnecttwo.ai.heuristics.Evaluator;
import calypso.twoconnecttwo.ai.heuristics.FutureWinEvaluator;

/**
 * 
 */

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
		assertEquals(-504, adjacentEvaluator.evaluate(board, 0));
		
		board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,b,r,s,s,s,s,s,r,b,r,b,r,s,s,s,b,g,r,b,r,b,s,s,r,s,s,s,s,s,s,s,r,g,s,s,s,s,s,s,g,s,s,s,s,s,s,s", 10, 8);
		assertEquals(-501, adjacentEvaluator.evaluate(board, 0));
		
		board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,b,r,b,r,r,b,r,b,g,g,r,b,g,g,r,b,r,b,r,r,g,b,r,r,b,r,g,s,s,s,s,g,s,s,s,s,s,s,s,b,r,b,s,s,s,s,s", 10, 8);
		assertEquals(396, adjacentEvaluator.evaluate(board, 0));
		
		board = TestUtils.generateBoard("r,s,s,s,s,s,s,s,b,r,b,r,b,r,g,r,g,r,r,b,s,s,s,s,r,b,r,b,b,r,b,g,b,g,r,b,r,b,r,b,s,s,s,s,s,s,s,s,g,b,r,b,r,r,b,r,r,b,r,b,r,b,r,b,b,r,b,r,b,r,g,g,g,r,b,r,b,r,g,r", 10, 8);
		assertEquals(-10, adjacentEvaluator.evaluate(board, 0));
		
		board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,g,r,b,s,s,s,r,b,r,b,r,g,s,s,b,g,s,s,s,s,s,s,b,r,b,r,r,b,r,s,r,b,r,b,r,b,r,s,g,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		assertEquals(-13, adjacentEvaluator.evaluate(board, 0));
	}

}
