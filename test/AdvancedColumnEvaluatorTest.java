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
		assertEquals(9, advancedColumnEvaluator.evaluate(board, 0));
	}
	
	@Test
	public void testOneWin() {
		Board board = TestUtils.generateBoard("b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s,g,b,r,s,s,s,s,s,r,r,b,r,g,r,b,r,b,r,b,r,b,r,b,r,b,b,r,b,b,r,b,r,r,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s", 10, 8);
		assertEquals(-5, advancedColumnEvaluator.evaluate(board, 0));
	}
	
	@Test
	public void highDoubleWin() {
		Board board = TestUtils.generateBoard("r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,b,r,b,r,g,g,r,r,b,r,b,r,b,b,r,b,r,b,b,r,b,g,s,s,s,s,s,s,s,s,b,r,b,r,g,r,b,b,s,s,s,s,s,s,s,s,r,g,b,b,r,g,s,s,g,b,s,s,s,s,s,s", 10, 8);
		assertEquals(-5, advancedColumnEvaluator.evaluate(board, 0));
	}

}
