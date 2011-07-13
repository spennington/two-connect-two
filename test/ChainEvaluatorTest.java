/**
 * 
 */


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author steve
 *
 */
public class ChainEvaluatorTest {

	/**
	 * Test method for {@link calypso.twoconnecttwo.ai.heuristics.ChainEvaluator#evaluate(calypso.twoconnecttwo.Board, int)}.
	 */
	@Test
	public void testEvaluate() {
		Board boardWithBlue = TestUtils.generateBoard("b,g,b,r,b,g,b,r,s,s,s,s,s,s,s,s,b,b,r,r,b,r,r,r,r,b,r,r,r,g,b,b,s,s,s,s,s,s,s,s,b,g,b,r,b,r,b,r,b,b,b,r,b,r,b,r,r,r,b,r,r,b,r,b,b,r,r,b,r,b,b,s,b,r,s,s,s,s,s,s", 10, 8);
		Board boardWithGreen = TestUtils.generateBoard("b,g,b,r,b,g,b,r,s,s,s,s,s,s,s,s,b,b,r,r,b,r,r,r,r,b,r,r,r,g,b,b,s,s,s,s,s,s,s,s,b,g,b,r,b,r,b,r,b,b,b,r,b,r,b,r,r,r,b,r,r,b,r,b,b,r,r,b,r,b,g,s,b,r,s,s,s,s,s,s", 10, 8);
	
		Evaluator evaluator = ChainEvaluator.getInstance();
		int withBlue = evaluator.evaluate(boardWithBlue, 0, Board.BLUE);
		int withGreen = evaluator.evaluate(boardWithGreen, 0, Board.BLUE);
		
		assertTrue(withGreen < withBlue);
	}
	
	/**
	 * Test method for {@link calypso.twoconnecttwo.ai.heuristics.ChainEvaluator#evaluate(calypso.twoconnecttwo.Board, int)}.
	 */
	@Test
	public void testBlock() {
		Board boardWithBlue = TestUtils.generateBoard("b,g,b,r,s,s,s,s,s,s,s,s,s,s,s,s,b,b,r,b,s,s,s,s,r,b,r,r,r,r,s,s,s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Board boardWithGreen = TestUtils.generateBoard("b,g,b,r,s,s,s,s,s,s,s,s,s,s,s,s,b,b,r,g,s,s,s,s,r,b,r,r,r,r,s,s,s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
	
		Evaluator evaluator = ChainEvaluator.getInstance();
		int withBlue = evaluator.evaluate(boardWithBlue, 0, Board.BLUE);
		int withGreen = evaluator.evaluate(boardWithGreen, 0, Board.BLUE);
		
		assertTrue(withBlue < withGreen);
	}
	
	@Test
	public void testDoubleWin() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,r,b,r,s,s,s,s,b,r,s,s,s,s,s,s,r,b,b,b,r,r,s,s,b,b,r,r,r,r,s,s,g,g,s,s,s,s,s,s,r,r,b,b,s,s,s,s,b,s,s,s,s,s,s,s", 10, 8);
		Evaluator evaluator = ChainEvaluator.getInstance();
		assertEquals(10, evaluator.evaluate(board, 0, Board.BLUE));
	}

}
