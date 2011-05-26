/**
 * 
 */

/**
 * @author steve
 *
 */
public class AI {
	private static int[] winMap = new int[11];
	private static byte[] comboMap = new byte[256];
	
	static {
		winMap[0] = Integer.MIN_VALUE + 1;
		winMap[1] = Integer.MIN_VALUE + 2;
		winMap[2] = Integer.MIN_VALUE + 3;
		
		winMap[10] = Integer.MAX_VALUE - 1;
		winMap[9] = Integer.MAX_VALUE - 2;
		winMap[8] = Integer.MAX_VALUE - 3;
		
		//RSSS
		comboMap[0x40] = 1;
		//SSRS
		comboMap[0x04] = 1;
		//SRSS
		comboMap[0x10] = 1;
		//SSSR
		comboMap[0x01] = 1;
		
		//RRSS
		comboMap[0x50] = 4;
		//SSRR
		comboMap[0x05] = 4;
		//RSSR
		comboMap[0x41] = 3;
		//SRRS
		comboMap[0x14] = 3;
		//RSRS
		comboMap[0x44] = 2;
		//SRSR
		comboMap[0x11] = 2;
		
		//SRGS
		comboMap[0x1C] = 8;
		//GSSR
		comboMap[0xC1] = 8;
		//SGRS
		comboMap[0x34] = 8;
		//RSSG
		comboMap[0x43] = 8;
		//RSGS
		comboMap[0x4C] = 8;
		//GSRS
		comboMap[0xC4] = 8;
		//SRSG
		comboMap[0x13] = 8;
		//SGSR
		comboMap[0x31] = 8;
		//GRSS
		comboMap[0xD0] = 6;
		//SSGR
		comboMap[0x0D] = 6;
		//RGSS
		comboMap[0x70] = 6;
		//SSRG
		comboMap[0x07] = 6;
		
		//RRGS
		comboMap[0x5C] = 20;
		//GSRR
		comboMap[0xC5] = 20;
		//RRSG
		comboMap[0x53] = 20;
		//SGRR
		comboMap[0x35] = 20;
		//RGSR
		comboMap[0x71] = 15;
		//SRRG
		comboMap[0x17] = 15;
		//RSGR
		comboMap[0x4D] = 15;
		//GRRS
		comboMap[0xD4] = 15;
		//RGRS
		comboMap[0x74] = 10;
		//RSRG
		comboMap[0x47] = 10;
		//GRSR
		comboMap[0xD1] = 10;
		//SRGR
		comboMap[0x1D] = 10;
		
		//GGRS
		comboMap[0xF4] = 20;
		//RSGG
		comboMap[0x4F] = 20;
		//GGSR
		comboMap[0xF1] = 20;
		//SRGG
		comboMap[0x1F] = 20;
		//GRSG
		comboMap[0xD3] = 15;
		//SGGR
		comboMap[0x3D] = 15;
		//GSRG
		comboMap[0xC7] = 15;
		//RGGS
		comboMap[0x7C] = 15;
		//GRGS
		comboMap[0xDC] = 10;
		//GSGR
		comboMap[0xCD] = 10;
		//RGSG
		comboMap[0x73] = 10;
		//SGRG
		comboMap[0x37] = 10;
		
		//BSSS
		comboMap[0x80] = -1;
		//SSBS
		comboMap[0x08] = -1;
		//SBSS
		comboMap[0x20] = -1;
		//SSSB
		comboMap[0x02] = -1;
		
		//BBSS
		comboMap[0xA0] = -4;
		//SSBB
		comboMap[0x0A] = -4;
		//BSSB
		comboMap[0x82] = -3;
		//SBBS
		comboMap[0x28] = -3;
		//BSBS
		comboMap[0x88] = -2;
		//SBSB
		comboMap[0x22] = -2;
		
		//SGBS
		comboMap[0x38] = -8;
		//BSSG
		comboMap[0x83] = -8;
		//GSSB
		comboMap[0xC2] = -8;
		//SBGS
		comboMap[0x2C] = -8;
		//BGSS
		comboMap[0xB0] = -6;
		//SSBG
		comboMap[0x0B] = -6;
		//GBSS
		comboMap[0xE0] = -6;
		//SSGB
		comboMap[0x0E] = -6;
		//BSGS
		comboMap[0x8C] = -8;
		//GSBS
		comboMap[0xC8] = -8;
		//SBSG
		comboMap[0x23] = -8;
		//SGSB
		comboMap[0x32] = -8;
		
		//BBGS
		comboMap[0xAC] = -20;
		//GSBB
		comboMap[0xCA] = -20;
		//BBSG
		comboMap[0xA3] = -20;
		//SGBB
		comboMap[0x3A] = -20;
		//BGSB
		comboMap[0xB2] = -15;
		//SBBG
		comboMap[0x2B] = -15;
		//BSGB
		comboMap[0x8E] = -15;
		//GBBS
		comboMap[0xE8] = -15;
		//BGBS
		comboMap[0xB8] = -10;
		//BSBG
		comboMap[0x8B] = -10;
		//GBSB
		comboMap[0xE2] = -10;
		//SBGB
		comboMap[0x2E] = -10;
		
		//GGBS
		comboMap[0xF8] = -20;
		//BSGG
		comboMap[0x8F] = -20;
		//GGSB
		comboMap[0xF2] = -20;
		//SBGG
		comboMap[0x2F] = -20;
		//GBSG
		comboMap[0xE3] = -15;
		//SGGB
		comboMap[0x3E] = -15;
		//GSBG
		comboMap[0xCB] = -15;
		//BGGS
		comboMap[0xBC] = -15;
		//GBGS
		comboMap[0xEC] = -10;
		//GSGB
		comboMap[0xCE] = -10;
		//BGSG
		comboMap[0xB3] = -10;
		//SGBG
		comboMap[0x3B] = -10;
	}
	
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
			if(val >= best) {
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
			if(val >= best) {
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
			if(val <= best) {
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
			if(val <= best) {
				best = val;
				move.column = i;
				move.score = best;
				move.piece = Board.GREEN;
			}
		}
		
		return move;
	}

	public static class WinningCombosEvaluator implements Evaluator {

		/* (non-Javadoc)
		 * @see Evaluator#evaluate(Board)
		 */
		@Override
		public int evaluate(Board board, int lastCol) {
			int score = 0;
			
			//Horizontal
			for(int i=0; i<board.getWidth()-4; i++) {
				for(int j=0; j<board.getHeight(); j++) {
					score += score(board.get(i, j), board.get(i+1, j), board.get(i+2, j), board.get(i+3, j));
				}
			}
			
			//Vertical
			for(int i=0; i<board.getWidth(); i++) {
				for(int j=0; j<board.getHeight()-4; j++) {
					score += score(board.get(i, j), board.get(i, j+1), board.get(i, j+2), board.get(i, j+3));
				}
			}
			
			//Bottom Left to Top Right
			for(int i=0; i<board.getWidth()-4; i++) {
				for(int j=0; j<board.getHeight()-4; j++) {
					score += score(board.get(i, j), board.get(i+1, j+1), board.get(i+2, j+2), board.get(i+3, j+3));
				}
			}
			
			//Top Left to Bottom Right
			for(int i=0; i<board.getWidth()-4; i++) {
				for(int j=0; j<board.getHeight()-4; j++) {
					score += score(board.get(i, j+3), board.get(i+1, j+2), board.get(i+2, j+1), board.get(i+3, j));
				}
			}
			
			return score;
		}
		
		public byte score(byte a, byte b, byte c, byte d) {
			return comboMap[(a << 6) | (b << 4) | (c << 2) | d];
		}
		
	}
	
	public static class WinCheckEvaluator implements Evaluator {

		/* (non-Javadoc)
		 * @see Evaluator#evaluate(Board)
		 */
		@Override
		public int evaluate(Board board, int lastCol) {
			return WinChecker.isWin(board, lastCol);
		}
		
	}
}
