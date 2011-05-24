
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
		Evaluator evaluator = new AI.WinningCombosEvaluator();
		Move move = AI.minMax(board, 3, evaluator);
		System.out.println("(" + (move.column + 1)+ ", " + IO.pieceMap[move.piece] + ")");
	}
}
