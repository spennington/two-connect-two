import static org.junit.Assert.*;

import org.junit.Test;


public class ScoreTest {
	
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
		assertEquals(TwoConnectTwo.getFinalGameScore(scores),0);
		int[] scoresTwo = {0,0};
		assertEquals(TwoConnectTwo.getFinalGameScore(scoresTwo),0);
	}
	
	@Test
	public void testBlueWinsFinalGameScore() {
		int[] scores = {-5,0};
		assertEquals(TwoConnectTwo.getFinalGameScore(scores),-5);
		int[] scoresMultipleVals = {-3,-5,-4};
		assertEquals(TwoConnectTwo.getFinalGameScore(scoresMultipleVals),-5);
	}
	
	@Test
	public void testRedWinsFinalGameScore() {
		int[] scores = {0,0,4};
		assertEquals(TwoConnectTwo.getFinalGameScore(scores),4);
		int[] scoresMultipleVals = {3,4,5};
		assertEquals(TwoConnectTwo.getFinalGameScore(scoresMultipleVals),5);
	}
	
	@Test
	public void testTieFinalGameScore() {
		int[] scores = {5,-5};
		assertEquals(TwoConnectTwo.getFinalGameScore(scores),1);
		int[] scoresMultipleVals = {5,-4,4,-5};
		assertEquals(TwoConnectTwo.getFinalGameScore(scoresMultipleVals),1);
	}
	
	@Test
	public void testOneWinnerFinalGameScore() {
		int[] scores = {5,-5,-3,-4};
		assertEquals(TwoConnectTwo.getFinalGameScore(scores),-5);
		int[] scoresTwo = {3,-4,0,3,3};
		assertEquals(TwoConnectTwo.getFinalGameScore(scoresTwo),3);
	}
	
}
