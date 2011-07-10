/**
 * 
 */

/**
 * @author steve
 *
 */
public class DummyEvaluator implements Evaluator {

private static DummyEvaluator instance = new DummyEvaluator();
	
	private DummyEvaluator () {}
	
	public static DummyEvaluator getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see Evaluator#evaluate(Board)
	 */
	@Override
	public int evaluate(Board board, int lastCol) {
		return 0;
	}

}
