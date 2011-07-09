/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class TwoConnectTwo {

	private static final int BRUTE_FORCE_DEPTH = 17;
	private static final int START_DEPTH = 6;
	
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
		
		Move move;
		int piecesRemaining = board.getWidth() * board.getHeight() - board.getPieceCount();
		if(piecesRemaining <= BRUTE_FORCE_DEPTH) {
			Evaluator evaluator = WinEvaluator.getInstance();
			move = AI.minMax(board, piecesRemaining, evaluator);
		} else {
			Evaluator evaluator = ChainEvaluator.getInstance();
			move = AI.minMax(board, START_DEPTH, evaluator);
		}
		
		System.out.println("(" + (move.column + 1)+ ", " + IO.pieceMap[move.piece] + ")");
	}
}
