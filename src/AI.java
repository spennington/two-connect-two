/**
 * Implementation of the minimax algorithm to determine a "good"
 * two connect two move
 * 
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class AI {
	private static int[] winMap = new int[11];
	private static int[][] columnMap = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{1, 2, 0, 3, 0, 0, 0, 0, 0, 0},
			{1, 2, 0, 3, 4, 0, 0, 0, 0, 0},
			{2, 3, 1, 4, 0, 5, 0, 0, 0, 0},
			{2, 3, 1, 4, 0, 5, 6, 0, 0, 0},
			{3, 4, 2, 5, 1, 6, 0, 7, 0, 0},
			{3, 4, 2, 5, 1, 6, 0, 7, 8, 0},
			{4, 5, 3, 6, 2, 7, 1, 8, 0, 9}
	};
	
	static {
		winMap[0] = Integer.MIN_VALUE + 1;
		winMap[1] = Integer.MIN_VALUE + 2;
		winMap[2] = Integer.MIN_VALUE + 3;
		
		winMap[6] = 0;
		
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
	 * 		game.
	 * @param evaluator
	 * 		Heuristic to apply once the game tree has reached a terminal node.
	 * 		The implementation will only apply the evaluator if the terminal node
	 * 		is not a winning game state.
	 * @return "Best" move to make given the current game state
	 */
	public static Move minMax(Board board, int depth, Evaluator evaluator) {
		return min(board, depth, evaluator, null, new Move(0, Integer.MIN_VALUE, Board.RED), new Move(0, Integer.MAX_VALUE, Board.BLUE));
	}

	private static Move max(Board board, int depth, Evaluator evaluator, Move lastMove, Move alpha, Move beta) {
		int win = WinChecker.isWin(board, lastMove.column);
		if(win != 0) {
			lastMove.score = winMap[win + 5];
			return lastMove;
		}else if(depth == 0) {
			lastMove.score = evaluator.evaluate(board, lastMove.column);
			return lastMove;
		}
			
		int numMoves = board.getWidth();
		Move val;
		for(int i=0; i<numMoves; i++) {
			int column = columnMap[numMoves][i];
			if(board.getTop(column) == board.getHeight()) {
				continue;
			}
			
			board.drop(column, Board.RED);
			lastMove.piece = Board.RED;
			lastMove.column = column;
			val = min(board, depth-1, evaluator, lastMove, alpha.clone(), beta.clone());
			board.remove(column);
			if(val.score > alpha.score) {
				alpha.score = val.score;
				alpha.piece = Board.RED;
				alpha.column = column;
			}
			
			if(alpha.score >= beta.score) {
				return alpha;
			}
			
			board.drop(column, Board.GREEN);
			lastMove.piece = Board.GREEN;
			lastMove.column = column;
			val = min(board, depth-1, evaluator, lastMove, alpha.clone(), beta.clone());
			board.remove(column);
			if(val.score > alpha.score) {
				alpha.score = val.score;
				alpha.piece = Board.GREEN;
				alpha.column = column;
			}
			
			if(alpha.score >= beta.score) {
				return alpha;
			}
		}
		
		return alpha;
	}

	private static Move min(Board board, int depth, Evaluator evaluator, Move lastMove, Move alpha, Move beta) {
		if(lastMove == null) {
			lastMove = new Move();
		} else {
			int win = WinChecker.isWin(board, lastMove.column);
			if(win != 0) {
				lastMove.score = winMap[win + 5];
				return lastMove;
			}else if(depth == 0) {
				lastMove.score = evaluator.evaluate(board, lastMove.column);
				return lastMove;
			}
		}
		
		int numMoves = board.getWidth();
		Move val;
		for(int i=0; i<numMoves; i++) {
			int column = columnMap[numMoves][i];
			if(board.getTop(column) == board.getHeight()) {
				continue;
			}
			
			board.drop(column, Board.BLUE);
			lastMove.piece = Board.BLUE;
			lastMove.column = column;
			val = max(board, depth-1, evaluator, lastMove, alpha.clone(), beta.clone());
			board.remove(column);
			if(val.score < beta.score) {
				beta.score = val.score;
				beta.piece = Board.BLUE;
				beta.column = column;
			}
			
			if(alpha.score >= beta.score) {
				return beta;
			}
			
			board.drop(column, Board.GREEN);
			lastMove.piece = Board.GREEN;
			lastMove.column = column;
			val = max(board, depth-1, evaluator, lastMove, alpha.clone(), beta.clone());
			board.remove(column);
			if(val.score < beta.score) {
				beta.score = val.score;
				beta.piece = Board.GREEN;
				beta.column = column;
			}
			
			if(alpha.score >= beta.score) {
				return beta;
			}
		}
		
		return beta;
	}
	
}
