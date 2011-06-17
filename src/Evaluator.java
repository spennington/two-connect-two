package calypso.twoconnecttwo.ai.heuristics;

import calypso.twoconnecttwo.Board;

/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public interface Evaluator {
	/**
	 * Determine the "score" of the current board
	 * @param board
	 * 		Game board to evaluate
	 * @param lastCol
	 * 		Column in which the last piece was played
	 * @return Score of the board
	 */
	public int evaluate(Board board, int lastCol);
}
