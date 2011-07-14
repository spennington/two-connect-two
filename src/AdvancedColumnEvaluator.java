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
	
	private static int[] heightMap = {200, 90, 80, 70, 60, 50, 40, 30, 20, 10};

	private AdvancedColumnEvaluator() {}
	
	public static Evaluator getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see Evaluator#evaluate(Board, int)s
	 */
	@Override
	public int evaluate(Board board, int lastCol, byte next) {
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
		boolean seenMax;
		boolean seenMin;
		
		for(int i=0; i<columns; i++) {
			top = board.getTop(i);
			if(scores[i][rows + 1] == 0) {
				safeSpots += rows - top;
				continue;
			}
			
			lastScore = scores[i][top];
			if((lastScore < 0 && next == Board.BLUE) || (lastScore > 0 && next == Board.RED)) {
				return lastScore * 10000000;
			}
			
			seenMax = lastScore > 0;
			seenMin = lastScore < 0;
			for(int j=top + 1; j<rows && j-top<doubleHeight; j++) {
				tmpScore = scores[i][j];
				if(lastScore != 0) {
					if((tmpScore < 0 && seenMax) || (tmpScore > 0 && seenMin) || (seenMax && seenMin)) {
						break;
					} else if(lastScore < 0 && tmpScore < 0) {
						doubleHeight = j - board.getTop(i);
						factor = -1;
					} else if(lastScore > 0 && tmpScore > 0) {
						doubleHeight = j - board.getTop(i);
						factor = 1;
					}
				} else if(tmpScore == 0 && !seenMax && ! seenMin) {
					safeSpots++;
				}
				lastScore = tmpScore;
				seenMax |= lastScore > 0;
				seenMin |= lastScore < 0;
			}
		}
		
		if(doubleHeight != Integer.MAX_VALUE) {
			return (rows - doubleHeight) * 100000 * factor;
		}
		
		//0 if even 1 if odd
		int type = safeSpots & 1;
		int height = 1;
		//What about wins at height 0???
		int columnCount = 0;
		
		if(next == Board.BLUE) {
			for(int i=0; i<columns; i++) {
				if(scores[i][rows + 1] == 0) {
					continue;
				}
				top = board.getTop(i);
				height = 1;
				columnCount = 0;
				for(int j=top + 1; j<rows; j++) {
					if(scores[i][j] > 0) {
						if((height & 1) != type) {
							score += scores[i][j] * 10 * heightMap[columnCount++];
						} else {
							score += scores[i][j] * heightMap[columnCount++];
						}
					} else if(scores[i][j] < 0) {
						if((height & 1) == type) {
							score += scores[i][j] * 10 * heightMap[columnCount++];
						} else {
							score += scores[i][j] * heightMap[columnCount++];
						}
					}
					height++;
				}
			}
		} else {
			for(int i=0; i<columns; i++) {
				if(scores[i][rows + 1] == 0) {
					continue;
				}
				top = board.getTop(i);
				height = 1;
				columnCount = 0;
				for(int j=top + 1; j<rows; j++) {
					if(scores[i][j] > 0) {
						if((height & 1) == type) {
							score += scores[i][j] * 10 * heightMap[columnCount++];
						} else {
							score += scores[i][j] * heightMap[columnCount++];
						}
					} else if(scores[i][j] < 0) {
						if((height & 1) != type) {
							score += scores[i][j] * 10 * heightMap[columnCount++];
						} else {
							score += scores[i][j] * heightMap[columnCount++];
						}
					}
					height++;
				}
			}
		}
		
		return score;
	}
}
