
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;


/**
 * 
 */

/**
 * @author steve
 *
 */
public class AITest {
	
	private Evaluator possibleWinsEval = ConsecutivePieceEvaluator.getInstance();
	private Evaluator chainEval = ChainEvaluator.getInstance();
	
	/**
	 * Test method for {@link AI#MinMax(Board, int, int)}.
	 */
	@Test
	public void testAI() {
		Board board = TestUtils.generateBoard("r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,b,r,b,r,s,s,s,b,g,g,r,b,g,g,r,b,r,b,r,r,g,b,r,r,b,r,g,s,s,s,s,g,s,s,s,s,s,s,s,b,r,b,r,g,s,s,s", 10, 8);
		Move move = AI.minMax(board, 3, possibleWinsEval);
		System.out.println(move.column);
	}
	
	/**
	 * Test method for {@link AI#MinMax(Board, int, int)}.
	 */
	@Test
	public void testBlockRed() {
		Board board = TestUtils.generateBoard("g,r,s,s,s,s,s,s,b,b,s,s,s,s,s,s,r,g,b,b,r,g,g,b,r,b,b,r,b,b,b,r,b,r,r,b,r,r,r,r,g,s,s,s,s,s,s,s,b,r,b,g,b,r,b,b,r,r,b,r,b,b,b,b,g,s,s,s,s,s,s,s,r,g,g,b,r,s,s,s", 10, 8);
		Move move = AI.minMax(board, 3, possibleWinsEval);
		assertEquals(8, move.column);
	}
	
	@Test
	public void testFutureWin() {
		Board board = TestUtils.generateBoard("r,g,r,b,s,s,s,s,g,b,b,r,r,s,s,s,g,r,b,r,b,r,g,s,b,r,g,s,s,s,s,s,r,b,r,b,b,g,r,b,r,b,r,b,r,r,g,b,b,g,r,b,r,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 3, possibleWinsEval);
		assertEquals(6, move.column);
		assertEquals(Board.BLUE, move.piece);
		
		board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,s,s,s,s,s,s,g,g,s,s,s,s,s,s,r,b,r,b,r,r,s,s,b,b,r,b,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		move = AI.minMax(board, 3, possibleWinsEval);
		assertEquals(9, move.column);
		assertEquals(Board.GREEN, move.piece);
	}
	
	@Test
	public void winInNine() {
		Board board = TestUtils.generateBoard("r,s,s,s,s,s,s,s,b,r,b,r,b,r,g,r,g,r,r,b,s,s,s,s,r,b,r,b,b,r,b,g,b,g,r,b,r,b,r,b,s,s,s,s,s,s,s,s,g,b,r,b,r,r,b,r,r,b,r,b,r,b,r,b,b,r,b,r,b,r,g,g,g,r,b,r,b,r,g,r", 10, 8);
		Move move = AI.minMax(board, 3, chainEval);
		assertEquals(2, move.column);
		assertEquals(Board.BLUE, move.piece);
	}
	
	@Test
	public void preventFutureLoss() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,b,r,b,s,s,s,g,s,s,s,s,s,s,s,r,b,r,b,r,r,s,s,b,b,r,b,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 3, possibleWinsEval);
		assertTrue(4 != move.column && Board.GREEN != move.column);
	}
	
	/**
	 * Test method for {@link AI#MinMax(Board, int, int)}.
	 */
	@Test
	public void testThread() {
		final Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		final Board board2 = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				//AI.minMax(board, 5, Board.BLUE, new Move[] {null, AI.bestMove[Board.RED], AI.bestMove[Board.BLUE]});
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//AI.minMax(board2, 5, Board.BLUE, new Move[] {null, AI.bestMove[Board.RED], AI.bestMove[Board.BLUE]});
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link AI#MinMax(Board, int, int)}.
	 */
	@Test
	public void testNoThread() {
		final Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		final Board board2 = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		
		//AI.minMax(board, 5, Board.BLUE, new Move[] {null, AI.bestMove[Board.RED], AI.bestMove[Board.BLUE]});
		//AI.minMax(board2, 5, Board.BLUE, new Move[] {null, AI.bestMove[Board.RED], AI.bestMove[Board.BLUE]});
	}
	
	/**
	 * Test method for {@link AI#MinMax(Board, int, int)}.
	 */
	@Test
	public void testEval() {
		final Board board = TestUtils.generateBoard("g,b,r,s,s,s,s,s," + "s,s,s,s,s,s,s,s," + "s,s,s,s,s,s,s,s," + "s,s,s,s,s,s,s,s," + "r,s,s,s,s,s,s,s," + "s,s,s,s,s,s,s,s," + "s,s,s,s,s,s,s,s," + "s,s,s,s,s,s,s,s," + "s,s,s,s,s,s,s,s," + "s,s,s,s,s,s,s,s", 10, 8);
		//assertEquals(8, AI.evaluate(board));
	}
	
}
