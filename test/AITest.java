

import static org.junit.Assert.*;

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
	private Evaluator dummyEvaluator = DummyEvaluator.getInstance();
	private Evaluator advancedChainEvaluator = AdvancedChainEvaluator.getInstance();
	
	/**
	 * Test method for {@link AI#MinMax(Board, int, int)}.
	 */
	@Test
	public void testAI() {
		Board board = new Board(6, 6);
		board.drop(3, Board.RED);
		board.drop(3, Board.BLUE);
		board.drop(2, Board.RED);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertTrue(move.column == 1 || move.column == 4);
		assertEquals(Board.BLUE, move.piece);
	}
	
	@Test
	public void testAI2() {
		Board board = TestUtils.generateBoard("b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,b,s,s,s,s,s,s,r,b,r,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 4, advancedChainEvaluator);
		assertEquals(0, move.column);
		assertEquals(Board.GREEN, move.piece);
	}
	
	@Test
	public void testAI3() {
		Board board = TestUtils.generateBoard("b,g,b,r,b,g,b,r,s,s,s,s,s,s,s,s,b,b,r,r,b,r,r,r,r,b,r,r,r,g,b,b,s,s,s,s,s,s,s,s,b,g,b,r,b,r,b,r,b,b,b,r,b,r,b,r,r,r,b,r,r,b,r,b,b,r,r,b,r,b,s,s,b,r,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(8, move.column);
		assertEquals(Board.GREEN, move.piece);
	}
	
	/**
	 * Test method for {@link AI#MinMax(Board, int, int)}.
	 */
	@Test
	public void testBlockRed() {
		Board board = TestUtils.generateBoard("g,r,s,s,s,s,s,s,b,b,s,s,s,s,s,s,r,g,b,b,r,g,g,b,r,b,b,r,b,b,b,r,b,r,r,b,r,r,r,r,g,s,s,s,s,s,s,s,b,r,b,g,b,r,b,b,r,r,b,r,b,b,b,b,g,s,s,s,s,s,s,s,r,g,g,b,r,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(8, move.column);
	}
	
	@Test
	public void odin8Jun192011() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,g,b,r,s,s,s,s,s,r,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,r,b,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 7, advancedChainEvaluator);
		assertEquals(1, move.column);
	}
	
	@Test
	public void testFutureWin() {
		Board board = TestUtils.generateBoard("r,g,r,b,s,s,s,s,g,b,b,r,r,s,s,s,g,r,b,r,b,r,g,s,b,r,g,s,s,s,s,s,r,b,r,b,b,g,r,b,r,b,r,b,r,r,g,b,b,g,r,b,r,b,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 7, advancedChainEvaluator);
		assertEquals(6, move.column);
		assertEquals(Board.BLUE, move.piece);
	}
	
	@Test
	public void testFutureWin2() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,s,s,s,s,s,s,g,g,s,s,s,s,s,s,r,b,r,b,r,r,s,s,b,b,r,b,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 7, advancedChainEvaluator);
		assertEquals(9, move.column);
		assertEquals(Board.GREEN, move.piece);
	}
	
	@Test
	public void testFutureWin3() {
		//Trogdor 9 Jul 09 2011
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,g,b,r,b,r,s,s,s,r,b,r,g,s,s,s,s,b,r,b,r,r,r,g,b,r,s,s,s,s,s,s,s,r,b,r,b,r,r,s,s,b,r,b,r,b,s,s,s,g,b,r,g,b,g,r,s,r,b,g,b,r,s,s,s,b,r,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(8, move.column);
		assertEquals(Board.BLUE, move.piece);
	}
	
	@Test
	public void testFutureWin4() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,r,b,r,s,s,s,s,b,r,s,s,s,s,s,s,r,b,b,b,r,r,s,s,b,b,r,r,r,r,s,s,g,g,g,s,s,s,s,s,r,r,b,b,s,s,s,s,b,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(9, move.column);
	}
	
	@Test
	public void testFutureWin5() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,b,b,g,r,s,s,s,s,r,r,b,s,s,s,s,s,g,g,b,r,b,r,s,s,b,b,r,r,b,r,r,s,r,b,r,r,r,g,b,s,b,r,s,s,s,s,s,s,b,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,r,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(9, move.column);
	}
	
	@Test
	public void winLateGame() {
		Board board = TestUtils.generateBoard("b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s,g,b,r,s,s,s,s,s,r,r,b,r,g,r,b,r,b,r,b,r,b,r,b,r,b,b,r,b,b,r,b,r,r,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,b,r,b,r,b,r,b,r,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 10, advancedChainEvaluator);
		assertTrue(move.column != 2);
	}
	
	@Test
	public void winInNine() {
		Board board = TestUtils.generateBoard("r,s,s,s,s,s,s,s,b,r,b,r,b,r,g,r,g,r,r,b,s,s,s,s,r,b,r,b,b,r,b,g,b,g,r,b,r,b,r,b,s,s,s,s,s,s,s,s,g,b,r,b,r,r,b,r,r,b,r,b,r,b,r,b,b,r,b,r,b,r,g,g,g,r,b,r,b,r,g,r", 10, 8);
		Move move = AI.minMax(board, 10, advancedChainEvaluator);
		assertEquals(2, move.column);
		assertEquals(Board.BLUE, move.piece);
	}
	
	@Test
	public void preventFutureLoss() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,r,b,r,b,s,s,s,g,s,s,s,s,s,s,s,r,b,r,b,r,r,s,s,b,b,r,b,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertTrue(4 != move.column && Board.GREEN != move.column);
	}
	
	@Test
	public void preventFutureLoss2() {
		Board board = TestUtils.generateBoard("b,g,b,r,s,s,s,s,s,s,s,s,s,s,s,s,b,b,r,s,s,s,s,s,r,b,r,r,r,r,s,s,s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(2, move.column);
		assertEquals(Board.BLUE, move.piece);
	}
	
	@Test
	public void preventFutureLoss3() {
		Board board = TestUtils.generateBoard("b,g,b,r,b,s,s,s,s,s,s,s,s,s,s,s,b,b,r,r,b,r,r,r,r,b,r,r,r,g,b,b,s,s,s,s,s,s,s,s,b,g,b,r,b,r,b,r,b,b,b,r,b,r,b,r,r,r,b,r,r,b,r,b,b,r,r,b,r,s,s,s,b,r,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(0, move.column);
		assertEquals(Board.BLUE, move.piece);
	}
	
	@Test
	public void preventFutureLoss4() {
		//Odin 7 Jul 09 2011
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,g,b,b,r,s,s,s,s,r,s,s,s,s,s,s,s,b,r,b,r,s,s,s,s,b,b,r,r,s,s,s,s,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertTrue(move.column != 1);
	}
	
	@Test
	public void preventFutureLoss5() {
		//Batman Jul 11 2011
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,r,b,b,s,s,s,s,s,b,r,s,s,s,s,s,s,b,r,s,s,s,s,s,s,g,s,s,s,s,s,s,s,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertEquals(6, move.column);
	}
	
	@Test
	public void preventFutureLoss6() {
		//Trogdor Jul 11 2011 7PM
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,r,b,r,s,s,s,s,s,g,s,s,s,s,s,s,s,r,r,b,r,b,s,s,s,b,b,r,b,s,s,s,s,g,b,b,r,r,s,s,s,b,r,r,s,s,s,s,s,r,b,s,s,s,s,s,s,g,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 6, advancedChainEvaluator);
		assertTrue(move.column != 1);
	}
	
	@Test
	public void testDelayLoss() {
		Board board = TestUtils.generateBoard("s,s,s,s,s,s,s,s,r,r,r,b,r,g,r,b,b,r,b,g,b,r,b,b,g,g,b,r,b,r,b,r,b,b,r,r,b,r,r,b,r,b,r,r,b,b,r,b,r,r,b,s,s,s,s,s,g,g,b,r,g,r,b,r,b,r,r,b,r,b,b,r,r,s,s,s,s,s,s,s", 10, 8);
		Move move = AI.minMax(board, 14, advancedChainEvaluator);
		assertEquals(9, move.column);
		assertEquals(Board.BLUE, move.piece);
	}
}
