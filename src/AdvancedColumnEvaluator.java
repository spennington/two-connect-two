/**
 * Evaluates the board based on the locations of
 * possible future wins. More specifically, looks at all
 * the empty squares on the board and determines if they 
 * are one piece away from a win.
 * 
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class AdvancedColumnEvaluator implements Evaluator {
	
	private static AdvancedColumnEvaluator instance = new AdvancedColumnEvaluator();
	
	private static int[] heightMap = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};

	private AdvancedColumnEvaluator() {}
	
	public static Evaluator getInstance() {
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
		
		int safeSpots = 0;
		int doubleHeight = Integer.MAX_VALUE;
		int factor = 0;
		int tmpScore = 0;
		int top = 0;
		int lastScore = 0;
		
		for(int i=0; i<columns; i++) {
			top = board.getTop(i);
			if(scores[i][rows + 1] == 0) {
				safeSpots += rows - top;
				continue;
			}
			
			lastScore = scores[i][top];
			for(int j=top + 1; j<rows && j-top<doubleHeight; j++) {
				tmpScore =  scores[i][j];
				if(lastScore != 0) {
					if(lastScore < 0 && tmpScore < 0) {
						doubleHeight = j - board.getTop(i);
						factor = -1;
					} else if(lastScore > 0 && tmpScore > 0) {
						doubleHeight = j - board.getTop(i);
						factor = 1;
					}
					break;
				} else if(tmpScore == 0) {
					safeSpots++;
				} else {
					lastScore = tmpScore;
				}
			}
			if(lastScore == 0 && top != rows) {
				safeSpots++;
			}
		}
		
		if(doubleHeight != Integer.MAX_VALUE) {
			return (rows - doubleHeight) * 100000 * factor;
		}
		
		//0 if even 1 if odd
		int type = safeSpots & 1;
		int height = 1;
		for(int i=0; i<columns; i++) {
			if(scores[i][rows + 1] == 0) {
				continue;
			}
			top = board.getTop(i);
			height = 1;
			for(int j=top + 1; j<rows; j++) {
				if(scores[i][j] > 0) {
					if((height & 1) != type) {
						score += scores[i][j] * 10 * heightMap[height];
					} else {
						score += scores[i][j] * heightMap[height];
					}
				} else if(scores[i][j] < 0) {
					if((height & 1) == type) {
						score += scores[i][j] * 10 * heightMap[height];
					} else {
						score += scores[i][j] * heightMap[height];
					}
				}
				height++;
			}
		}
		
		return score;
	}
}
