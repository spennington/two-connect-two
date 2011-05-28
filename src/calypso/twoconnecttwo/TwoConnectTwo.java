package calypso.twoconnecttwo;

import calypso.twoconnecttwo.ai.AI;
import calypso.twoconnecttwo.ai.Move;
import calypso.twoconnecttwo.ai.heuristics.ChainEvaluator;
import calypso.twoconnecttwo.ai.heuristics.Evaluator;

/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class TwoConnectTwo {

	/**
	 * Main entry point into the application
	 * @param args
	 * 		command line arguments
	 */
	public static void main(String[] args) {
		IO.init(System.in);
		GameState gameState = IO.parseGameState();
		Board board = IO.parseBoard(gameState.numCols, gameState.numRows);
		IO.close();
		Evaluator evaluator = ChainEvaluator.getInstance();
		long time = System.currentTimeMillis();
		Move move;
		move = AI.minMax(board, 3, evaluator);
		time = System.currentTimeMillis() - time;
		System.err.println("took " + time);
		System.err.println("depth 5 " + time*400);
		System.out.println("(" + (move.column + 1)+ ", " + IO.pieceMap[move.piece] + ")");
	}
}
