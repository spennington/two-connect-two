import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * 
 */

/**
 * @author steve
 *
 */
public class PerformanceTests {

	@Test
	public void testAdvancedColumn() {
		Evaluator advancedColumnEvaluator = AdvancedColumnEvaluator.getInstance();
		Board board = TestUtils.generateBoard("b,r,b,r,b,r,s,s,r,r,b,s,s,s,s,s,g,b,r,b,r,b,r,r,b,r,r,b,r,r,g,b,b,b,b,r,b,r,r,b,r,g,b,r,b,s,s,s,r,b,r,b,g,b,r,r,s,s,s,s,s,s,s,s,b,r,r,b,r,g,r,b,r,b,r,b,r,b,s,s", 10, 8);
		for(int i=0; i<10000000; i++) {
			advancedColumnEvaluator.evaluate(board, 0, Board.BLUE);
		}	
	}
	
	@Test
	public void testFutureWin() {
		Evaluator futureWinEvaluator = FutureWinEvaluator.getInstance();
		Board board = TestUtils.generateBoard("b,r,b,r,b,r,s,s,r,r,b,s,s,s,s,s,g,b,r,b,r,b,r,r,b,r,r,b,r,r,g,b,b,b,b,r,b,r,r,b,r,g,b,r,b,s,s,s,r,b,r,b,g,b,r,r,s,s,s,s,s,s,s,s,b,r,r,b,r,g,r,b,r,b,r,b,r,b,s,s", 10, 8);
		for(int i=0; i<10000000; i++) {
			futureWinEvaluator.evaluate(board, 0, Board.BLUE);
		}	
	}
}
