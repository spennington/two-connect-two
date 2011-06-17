/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class ChainEvaluator implements Evaluator {
	
	private static ChainEvaluator instance = new ChainEvaluator();
	private static Evaluator winningCombos = ConsecutivePieceEvaluator.getInstance();
	private static Evaluator futureWin = FutureWinEvaluator.getInstance();
	
	private ChainEvaluator() {}
	
	public static ChainEvaluator getInstance() {
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see Evaluator#evaluate(calypso.twoconnecttwo.Board, int)
	 */
	@Override
	public int evaluate(Board board, int lastCol) {
		return futureWin.evaluate(board, lastCol) * 10 + winningCombos.evaluate(board, lastCol);
	}

}
