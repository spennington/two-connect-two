/**
 * Evaluates the board based on the locations of
 * possible future wins. More specifically, looks at all
 * the empty squares on the board and determines if they 
 * are one piece away from a win.
 * 
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class FutureWinEvaluator implements Evaluator {
	
	private static FutureWinEvaluator instance = new FutureWinEvaluator();
	
	public static FutureWinEvaluator getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see Evaluator#evaluate(Board, int)
	 */
	@Override
	public int evaluate(Board board, int lastCol) {
		int columns = board.getWidth();
		int rows = board.getHeight();
		
		int[][] scores = ColumnWinSearcher.getColumnScores(board);
		int score = 0;
		
		int height = board.getHeight();
		
		for(int i=0; i<columns; i++) {
			if(scores[i][rows + 1] == 0) {
				continue;
			}
			for(int j=board.getTop(i); j<rows-1 && j - board.getTop(i)<height; j++) {
				if(scores[i][j] != 0) {
					if((scores[i][j] > 0 && scores[i][j+1] > 0) || (scores[i][j] < 0 && scores[i][j+1] < 0)) {
						height = j - board.getTop(i);
						score = scores[i][j] * 100 * (board.getHeight() - height);
						break;
					} else {
						score += scores[i][j] * ((board.getHeight() - j - board.getTop(i)) * 5);
					}
				}
			}
		}
		
		return score;
	}
}
