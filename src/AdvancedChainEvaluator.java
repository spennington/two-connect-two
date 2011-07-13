/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class AdvancedChainEvaluator implements Evaluator {
	
	private static AdvancedChainEvaluator instance = new AdvancedChainEvaluator();
	private static Evaluator consecutivePieceEvaluator = ConsecutivePieceEvaluator.getInstance();
	private static Evaluator advancedColumnEvaluator = AdvancedColumnEvaluator.getInstance();
	
	private AdvancedChainEvaluator() {}
	
	public static AdvancedChainEvaluator getInstance() {
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see Evaluator#evaluate(calypso.twoconnecttwo.Board, int)
	 */
	@Override
	public int evaluate(Board board, int lastCol, byte next) {
		return advancedColumnEvaluator.evaluate(board, lastCol, next) + 200 * consecutivePieceEvaluator.evaluate(board, lastCol, next);
	}

}
