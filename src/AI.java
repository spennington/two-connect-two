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
		winMap[0] = Integer.MIN_VALUE;
		winMap[1] = Integer.MIN_VALUE - 1;
		winMap[2] = Integer.MIN_VALUE - 2;
		
		winMap[10] = Integer.MAX_VALUE;
		winMap[9] = Integer.MAX_VALUE - 1;
		winMap[8] = Integer.MAX_VALUE - 2;
		
		//RSSS
		comboMap[0x40] = 1;
		//SRSS
		comboMap[0x10] = 1;
		//SSRS
		comboMap[0x04] = 1;
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
		
		//RRGS
		comboMap[0x5C] = 8;
		//RRSG
		comboMap[0x53] = 8;
		//GSRR
		comboMap[0xC5] = 8;
		//SGRR
		comboMap[0x35] = 8;
		//RGSR
		comboMap[0x71] = 6;
		//RSGR
		comboMap[0x47] = 6;
		//GRRS
		comboMap[0xD4] = 6;
		//SRRG
		comboMap[0x1D] = 6;
		//RGRS
		comboMap[0x74] = 4;
		//RSRG
		comboMap[0x47] = 4;
		//GRSR
		comboMap[0xD1] = 4;
		//SRGR
		comboMap[0x1D] = 4;
		
		//GGRS
		comboMap[0xF4] = 8;
		//GGSR
		comboMap[0xF1] = 8;
		//RSGG
		comboMap[0x4F] = 8;
		//SRGG
		comboMap[0x1F] = 8;
		//GRSG
		comboMap[0xD3] = 6;
		//GSRG
		comboMap[0xC7] = 6;
		//RGGS
		comboMap[0x7C] = 6;
		//SGGR
		comboMap[0x3D] = 6;
		//GRGS
		comboMap[0xDC] = 4;
		//GSGR
		comboMap[0xCD] = 4;
		//RGSG
		comboMap[0x73] = 4;
		//SGRG
		comboMap[0x37] = 4;
		
		//BSSS
		comboMap[0x80] = -1;
		//SBSS
		comboMap[0x20] = -1;
		//SSBS
		comboMap[0x08] = -1;
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
		
		//BBGS
		comboMap[0xAC] = -8;
		//BBSG
		comboMap[0xA3] = -8;
		//GSBB
		comboMap[0xCA] = -8;
		//SGBB
		comboMap[0x3A] = -8;
		//BGSB
		comboMap[0xB2] = -6;
		//BSGB
		comboMap[0x8D] = -6;
		//GBBS
		comboMap[0xD8] = -6;
		//SBBG
		comboMap[0x2B] = -6;
		//BGBS
		comboMap[0xB8] = -4;
		//BSBG
		comboMap[0x8B] = -4;
		//GBSB
		comboMap[0xD2] = -4;
		//SBGB
		comboMap[0x2D] = -4;
		
		//GGBS
		comboMap[0xF8] = -8;
		//GGSB
		comboMap[0xF2] = -8;
		//BSGG
		comboMap[0x8F] = -8;
		//SBGG
		comboMap[0x2F] = -8;
		//GBSG
		comboMap[0xE3] = -6;
		//GSBG
		comboMap[0xCB] = -6;
		//BGGS
		comboMap[0xBC] = -6;
		//SGGB
		comboMap[0x3E] = -6;
		//GBGS
		comboMap[0xEC] = -4;
		//GSGB
		comboMap[0xCE] = -4;
		//BGSG
		comboMap[0xB3] = -4;
		//SGBG
		comboMap[0x3B] = -4;
	}
	
	public static Move minMax(Board board, int depth) {
		return min(board, depth);
	}

	private static Move max(Board board, int depth) {
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
			if(win != 0) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluate(board);
			} else {
				val = min(board, depth-1).score;
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
			if(win != 0) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluate(board);
			} else {
				val = min(board, depth-1).score;
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

	private static Move min(Board board, int depth) {
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
			if(win != 0) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluate(board);
			} else {
				val = max(board, depth-1).score;
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
			if(win != 0) {
				val = winMap[win + 5];
			}else if(depth == 0) {
				val = evaluate(board);
			} else {
				val = max(board, depth-1).score;
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
	
	private static int evaluate(Board board) {
		return winningCombos(board);
	}

	private static int winningCombos(Board board) {
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
	
	public static byte score(byte a, byte b, byte c, byte d) {
		return comboMap[(a << 6) | (b << 4) | (c << 2) | d];
	}

}
