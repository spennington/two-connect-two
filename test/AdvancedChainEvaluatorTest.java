import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * 
 */

/**
 * @author steve
 *
 */
public class AdvancedChainEvaluatorTest {

	private Evaluator advancedChainEvaluator = AdvancedChainEvaluator.getInstance();
	@Test
	public void winInABit() {
		Board board = TestUtils.generateBoard("b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s,g,b,r,s,s,s,s,s,r,r,b,r,g,r,b,r,b,r,b,r,b,r,b,r,b,b,r,b,b,r,b,r,r,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s", 10, 8);
		assertEquals(-471, advancedChainEvaluator.evaluate(board, 0, Board.BLUE));
		
		board = TestUtils.generateBoard(",b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s,g,b,r,b,r,s,s,s,r,r,b,r,g,r,b,r,b,r,b,r,b,r,b,r,b,b,r,b,b,r,b,r,r,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s", 10, 8);
		assertEquals(41, advancedChainEvaluator.evaluate(board, 0, Board.BLUE));
	}
}
