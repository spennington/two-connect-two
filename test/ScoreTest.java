import static org.junit.Assert.*;

import org.junit.Test;


public class ScoreTest {
	
	static final int NUM_COLS = 10;
	static final int NUM_ROWS = 8;
	
	@Test
	public void scoreTest() {
		assertEquals(5, TwoConnectTwo.score(TwoConnectTwo.RED, TwoConnectTwo.RED, TwoConnectTwo.GREEN, TwoConnectTwo.GREEN));
		assertEquals(5, TwoConnectTwo.score(TwoConnectTwo.GREEN, TwoConnectTwo.GREEN, TwoConnectTwo.RED, TwoConnectTwo.RED));
		
		assertEquals(-5, TwoConnectTwo.score(TwoConnectTwo.BLUE, TwoConnectTwo.BLUE, TwoConnectTwo.GREEN, TwoConnectTwo.GREEN));
		assertEquals(-5, TwoConnectTwo.score(TwoConnectTwo.GREEN, TwoConnectTwo.GREEN, TwoConnectTwo.BLUE, TwoConnectTwo.BLUE));
		
		assertEquals(4, TwoConnectTwo.score(TwoConnectTwo.RED, TwoConnectTwo.GREEN, TwoConnectTwo.GREEN, TwoConnectTwo.RED));
		assertEquals(4, TwoConnectTwo.score(TwoConnectTwo.GREEN, TwoConnectTwo.RED, TwoConnectTwo.RED, TwoConnectTwo.GREEN));
		
		assertEquals(-4, TwoConnectTwo.score(TwoConnectTwo.BLUE, TwoConnectTwo.GREEN, TwoConnectTwo.GREEN, TwoConnectTwo.BLUE));
		assertEquals(-4, TwoConnectTwo.score(TwoConnectTwo.GREEN, TwoConnectTwo.BLUE, TwoConnectTwo.BLUE, TwoConnectTwo.GREEN));
		
		assertEquals(3, TwoConnectTwo.score(TwoConnectTwo.RED, TwoConnectTwo.GREEN, TwoConnectTwo.RED, TwoConnectTwo.GREEN));
		assertEquals(3, TwoConnectTwo.score(TwoConnectTwo.GREEN, TwoConnectTwo.RED, TwoConnectTwo.GREEN, TwoConnectTwo.RED));
		
		assertEquals(-3, TwoConnectTwo.score(TwoConnectTwo.BLUE, TwoConnectTwo.GREEN, TwoConnectTwo.BLUE, TwoConnectTwo.GREEN));
		assertEquals(-3, TwoConnectTwo.score(TwoConnectTwo.GREEN, TwoConnectTwo.BLUE, TwoConnectTwo.GREEN, TwoConnectTwo.BLUE));
	}
	
	@Test
	public void testNoWinnerFinalGameScore() {
		int[] scores = {};
		assertEquals(0, TwoConnectTwo.getFinalGameScore(scores));
		int[] scoresTwo = {0,0};
		assertEquals(0, TwoConnectTwo.getFinalGameScore(scoresTwo));
	}
	
	@Test
	public void testBlueWinsFinalGameScore() {
		int[] scores = {-5,0};
		assertEquals(-5, TwoConnectTwo.getFinalGameScore(scores));
		int[] scoresMultipleVals = {-3,-5,-4};
		assertEquals(-5, TwoConnectTwo.getFinalGameScore(scoresMultipleVals));
	}
	
	@Test
	public void testRedWinsFinalGameScore() {
		int[] scores = {0,0,4};
		assertEquals(4, TwoConnectTwo.getFinalGameScore(scores));
		int[] scoresMultipleVals = {3,4,5};
		assertEquals(5, TwoConnectTwo.getFinalGameScore(scoresMultipleVals));
	}
	
	@Test
	public void testTieFinalGameScore() {
		int[] scores = {5,-5};
		assertEquals(1, TwoConnectTwo.getFinalGameScore(scores));
		int[] scoresMultipleVals = {5,-4,4,-5};
		assertEquals(1, TwoConnectTwo.getFinalGameScore(scoresMultipleVals));
	}
	
	@Test
	public void testOneWinnerFinalGameScore() {
		int[] scores = {5,-5,-3,-4};
		assertEquals(-5, TwoConnectTwo.getFinalGameScore(scores));
		int[] scoresTwo = {3,-4,0,3,3};
		assertEquals(3, TwoConnectTwo.getFinalGameScore(scoresTwo));
	}
	
	@Test
	public void testVerticalIsWin() {
		byte[][] board;
		board = generateBoard("b,g,g,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s");
		assertEquals(-4, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 0));
		board = generateBoard("s,s,s,s,b,b,g,g,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s");
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 0));
	}
	
	@Test
	public void testHorizontalIsWin() {
		byte[][] board;
		board = generateBoard("b,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,g,s,s,s,s,s,s,s,g,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s");
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 0));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 1));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 2));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 3));
		
		board = generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,g,s,s,s,s,s,s,s,g,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s");
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 6));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 7));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 8));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 9));
	}
	
	@Test
	public void testDiagonalIsWin() {
		byte[][] board;
		board = generateBoard("b,s,s,s,s,s,s,s,s,b,s,s,s,s,s,s,s,s,g,s,s,s,s,s,s,s,s,g,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s");
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 0));
		board = generateBoard("s,s,s,b,s,s,s,s,s,s,b,s,s,s,s,s,s,g,s,s,s,s,s,s,g,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s");
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 0));
		board = generateBoard("s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,r,r,r,r,g,s,s,s,r,r,r,r,r,g,s,s,r,r,r,r,r,r,b,s,r,r,r,r,r,r,r,b");
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 6));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 7));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 8));
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 9));
	}
	
	@Test
	public void testMultipleWinnerIsWin() {
		byte[][] board;
		board = generateBoard("b,b,g,g,s,s,s,s,r,r,r,r,s,s,s,s,r,r,r,g,s,s,s,s,r,r,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s");
		assertEquals(-5, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 0));
		board = generateBoard("b,b,g,g,s,s,s,s,r,r,r,r,s,s,s,s,r,g,g,g,s,s,s,s,r,r,r,r,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s");
		assertEquals(3, TwoConnectTwo.isWin(board, NUM_COLS, NUM_ROWS, 0));
	}
	
	private byte[][] generateBoard(String config) {
		TwoConnectTwo.columnTops = new int[NUM_COLS];
		String[] boardChars = config.split("[,]|[\\(]|[\\)]");
		byte[][] board = new byte[NUM_COLS + 6][NUM_ROWS + 6];
		int index = 0;
		for (int i = 0; i < NUM_COLS; i++) {
			for (int j = 0; j < NUM_ROWS; j++) {
				byte piece = (byte)boardChars[index++].getBytes()[0];
				TwoConnectTwo.putPiece(i, j, TwoConnectTwo.charMap[piece], board);
				if (piece != TwoConnectTwo.SPACE_CHAR) {
					TwoConnectTwo.columnTops[i] = j;
				} else {
					TwoConnectTwo.isFull = false;
				}
			}
		}
		return board;
	}
	
}