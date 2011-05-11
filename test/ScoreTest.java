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
}
