/**
 * Implementation of the minimax algorithm to determine a "good"
 * two connect two move
 * 
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class AI {
	private static int[] winMap = new int[11];
	
	static {
		winMap[0] = Integer.MIN_VALUE + 1;
		winMap[1] = Integer.MIN_VALUE + 2;
		winMap[2] = Integer.MIN_VALUE + 3;
		
		winMap[10] = Integer.MAX_VALUE - 1;
		winMap[9] = Integer.MAX_VALUE - 2;
		winMap[8] = Integer.MAX_VALUE - 3;
	}
	
	/**
	 * Determine the best twoconnecttwo move based on the
	 * provided board.
	 * 
	 * @param board
	 * 		Starting point of the twoconnecttwo game
	 * @param depth
	 * 		Number of pieces to play before evaluating the
	 * 		game. Depth of 0 drops 1 piece, depth of 1 drops 2 pieces
	 * 		etc...
	 * @param evaluator
	 * 		Heuristic to apply once the game tree has reached a terminal node.
	 * 		The implementation will only apply the evaluator if the terminal node
	 * 		is not a winning game state.
	 * @return "Best" move to make given the current game state
	 */
	public static Move minMax(Board board, int depth, Evaluator evaluator) {
		return min(board, depth, evaluator);
	}

	private static Move max(Board board, int depth, Evaluator evaluator) {
		Move move = new Move();
		int numMoves = board.getWidth();
		int val;
		int best = Integer.MIN_VALUE;
		for(int i=0; i<numMoves; i++) {
			if(board.getTop(i) == board.getHeight()) {
				continue;
			}
			board.drop(i, Board.RED);
			int win = WinChecker.isWin(board, i);
			if(win != 0 && win != 1) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluator.evaluate(board, i);
			} else {
				val = min(board, depth-1, evaluator).score;
			}
			board.remove(i);
			if(val > best) {
				best = val;
				move.column = i;
				move.score = best;
				move.piece = Board.RED;
			}
		}
		
		for(int i=0; i<numMoves; i++) {
			if(board.getTop(i) == board.getHeight()) {
				continue;
			}
			board.drop(i, Board.GREEN);
			int win = WinChecker.isWin(board, i);
			if(win != 0 && win != 1) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluator.evaluate(board, i);
			} else {
				val = min(board, depth-1, evaluator).score;
			}
			board.remove(i);
			if(val > best) {
				best = val;
				move.column = i;
				move.score = best;
				move.piece = Board.GREEN;
			}
		}
		
		return move;
	}

	private static Move min(Board board, int depth, Evaluator evaluator) {
		Move move = new Move();
		int numMoves = board.getWidth();
		int val;
		int best = Integer.MAX_VALUE;
		for(int i=0; i<numMoves; i++) {
			if(board.getTop(i) == board.getHeight()) {
				continue;
			}
			board.drop(i, Board.BLUE);
			int win = WinChecker.isWin(board, i);
			if(win != 0 && win != 1) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluator.evaluate(board, i);
			} else {
				val = max(board, depth-1, evaluator).score;
			}
			board.remove(i);
			if(val < best) {
				best = val;
				move.column = i;
				move.score = best;
				move.piece = Board.BLUE;
			}
		}
		
		for(int i=0; i<numMoves; i++) {
			if(board.getTop(i) == board.getHeight()) {
				continue;
			}
			board.drop(i, Board.GREEN);
			int win = WinChecker.isWin(board, i);
			if(win != 0 && win != 1) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluator.evaluate(board, i);
			} else {
				val = max(board, depth-1, evaluator).score;
			}
			board.remove(i);
			if(val < best) {
				best = val;
				move.column = i;
				move.score = best;
				move.piece = Board.GREEN;
			}
		}
		
		return move;
	}
	
}
