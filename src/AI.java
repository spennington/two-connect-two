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
	 * 		game.
	 * @param evaluator
	 * 		Heuristic to apply once the game tree has reached a terminal node.
	 * 		The implementation will only apply the evaluator if the terminal node
	 * 		is not a winning game state.
	 * @return "Best" move to make given the current game state
	 */
	public static Move minMax(Board board, int depth, Evaluator evaluator) {
		return min(board, depth, evaluator, null);
	}

	private static Move max(Board board, int depth, Evaluator evaluator, Move lastMove) {
		int win = WinChecker.isWin(board, lastMove.column);
		if(win != 0 && win != 1) {
			lastMove.score = winMap[win + 5];
			return lastMove;
		}else if(depth == 0) {
			lastMove.score = evaluator.evaluate(board, lastMove.column);
			return lastMove;
		}
			
		Move move = new Move();
		int numMoves = board.getWidth();
		int val;
		int best = Integer.MIN_VALUE;
		for(int i=0; i<numMoves; i++) {
			if(board.getTop(i) == board.getHeight()) {
				continue;
			}
			
			board.drop(i, Board.RED);
			lastMove.piece = Board.RED;
			lastMove.column = i;
			val = min(board, depth-1, evaluator, lastMove).score;
			board.remove(i);
			if(val > best) {
				best = val;
				move.column = i;
				move.score = best;
				move.piece = Board.RED;
			}
			
			board.drop(i, Board.GREEN);
			lastMove.piece = Board.GREEN;
			lastMove.column = i;
			val = min(board, depth-1, evaluator, lastMove).score;
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

	private static Move min(Board board, int depth, Evaluator evaluator, Move lastMove) {
		if(lastMove == null) {
			lastMove = new Move();
		} else {
			int win = WinChecker.isWin(board, lastMove.column);
			if(win != 0 && win != 1) {
				lastMove.score = winMap[win + 5];
				return lastMove;
			}else if(depth == 0) {
				lastMove.score = evaluator.evaluate(board, lastMove.column);
				return lastMove;
			}
		}
		
		Move move = new Move();
		int numMoves = board.getWidth();
		int val;
		int best = Integer.MAX_VALUE;
		for(int i=0; i<numMoves; i++) {
			if(board.getTop(i) == board.getHeight()) {
				continue;
			}
			
			board.drop(i, Board.BLUE);
			lastMove.piece = Board.BLUE;
			lastMove.column = i;
			val = max(board, depth-1, evaluator, lastMove).score;
			board.remove(i);
			if(val < best) {
				best = val;
				move.column = i;
				move.score = best;
				move.piece = Board.BLUE;
			}
			
			board.drop(i, Board.GREEN);
			lastMove.piece = Board.GREEN;
			lastMove.column = i;
			val = max(board, depth-1, evaluator, lastMove).score;
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
