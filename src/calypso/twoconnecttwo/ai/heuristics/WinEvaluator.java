package calypso.twoconnecttwo.ai.heuristics;

import calypso.twoconnecttwo.Board;
import calypso.twoconnecttwo.ai.WinChecker;

/**
 * Evaluates the board based on the existence of a winner.
 * Simply uses {@link WinChecker} to search for a winner.
 * 
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class WinEvaluator implements Evaluator{
	
	private static WinEvaluator instance = new WinEvaluator();
	
	private WinEvaluator () {}
	
	public static WinEvaluator getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see Evaluator#evaluate(Board)
	 */
	@Override
	public int evaluate(Board board, int lastCol) {
		return WinChecker.isWin(board, lastCol);
	}
}
