/**
 * A class to determine if a given board has a winner using
 * the win conditions outlined in the Two Connect Two specification
 * found at http://www.ece.uvic.ca/~sneville/Teaching/CEng420/Project/Course%20Project.pdf
 */

/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class WinChecker {
	private static byte[] scoreMap = new byte[256];
	private static byte[] maxMap = new byte[1<<10 + 1];
	private static int[] maxMask = new int[2];
	
	static {
		// Map for all possible scoring combinations
		// RRGG
		scoreMap[0x5F] = 5;
		// GGRR
		scoreMap[0xF5] = 5;
		// BBGG
		scoreMap[0xAF] = -5;
		// GGBB
		scoreMap[0xFA] = -5;

		// RGGR
		scoreMap[0x7D] = 4;
		// GRRG
		scoreMap[0xD7] = 4;
		// BGGB
		scoreMap[0xBE] = -4;
		// GBBG
		scoreMap[0xEB] = -4;

		// RGRG
		scoreMap[0x77] = 3;
		// GRGR
		scoreMap[0xDD] = 3;
		// BGBG
		scoreMap[0xBB] = -3;
		// GBGB
		scoreMap[0xEE] = -3;
		
		//when determine winners bits are marked
		//if a certain "type" of win has occurred.
		//These bits are
		//10: 5 point win (red)
		// 9: 4 point win (red)
		// 8: 3 point win (red)
		// 5: no winner
		// 2: -3 point win (blue)
		// 1: -4 point win (blue)
		// 0: -5 point win (blue)
		//
		//Consider only bits 10, 9, and 8. Used when
		//it is determined that red is the winner.
		maxMask[0] = 0x7 << 8;
		//Consider only bits 0, 1, and 2. Used when it
		//is determined that blue is the winner.
		maxMask[1] = 0x7;
		
		
		//Map winning combinations to their
		//correct values. This map is used to determine
		//a players score given their "collection" of wins.
		//Essentially this is a map that provides an efficient
		//way of computing the maximum win
		//
		//If the player has a 5 point win they get 5 points
		maxMap[1<<10] = 5;
		maxMap[1<<10 | 1<<9] = 5;
		maxMap[1<<10 | 1<<8] = 5;
		maxMap[1<<10 | 1<<9 | 1<<8] = 5;
		//4 point wins take precedence of 3 point wins
		maxMap[1<<9] = 4;
		maxMap[1<<9 | 1<<8] = 4;
		//3 point wins occur only if no 4 point or 5 point
		//wins were also encountered
		maxMap[1<<8] = 3;
		
		//same rational as the 5, 4, and 3 point wins but now
		//considering the case where blue has won
		maxMap[1] = -5;
		maxMap[1 | 1<<1] = -5;
		maxMap[1 | 1<<2] = -5;
		maxMap[1 | 1<<1 | 1<<2] = -5;
		
		maxMap[1<<1] = -4;
		maxMap[1<<1 | 1<<2] = -4;
		
		maxMap[1<<2] = -3;
	}
	
	public static int isWin(Board board, final int lastCol) {
		int lastRow = board.getTop(lastCol);

		//used to keep track of the cumulative score
		//for both players
		byte total = 0;
		
		//stores the "type" of wins by each opponent
		//bit mappings are explained in the maxMask comment
		int winValues = 0;
		
		byte score;
		
		//Each possible winning combination is added to the cumulative
		//score and the value is recorded in winValue
		// horizontal
		score = score(board.get(lastCol - 3, lastRow),
				board.get(lastCol - 2, lastRow),
				board.get(lastCol - 1, lastRow),
				board.get(lastCol, lastRow));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol - 2, lastRow),
				board.get(lastCol - 1, lastRow),
				board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol - 1, lastRow),
				board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow),
				board.get(lastCol + 2, lastRow));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow),
				board.get(lastCol + 2, lastRow),
				board.get(lastCol + 3, lastRow));
		total += score;
		winValues |= 1 << (score + 5);

		// vertical
		score = score(board.get(lastCol, lastRow),
				board.get(lastCol, lastRow - 1),
				board.get(lastCol, lastRow - 2),
				board.get(lastCol, lastRow - 3));
		total += score;
		winValues |= 1 << (score + 5);

		// bottom left to top right
		score = score(board.get(lastCol - 3, lastRow - 3),
				board.get(lastCol - 2, lastRow - 2),
				board.get(lastCol - 1, lastRow - 1),
				board.get(lastCol, lastRow));
		total += score;
		winValues |= 1 << (score + 5);

		score = score(board.get(lastCol - 2, lastRow - 2),
				board.get(lastCol - 1, lastRow - 1),
				board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow + 1));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol - 1, lastRow - 1),
				board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow + 1),
				board.get(lastCol + 2, lastRow + 2));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow + 1),
				board.get(lastCol + 2, lastRow + 2),
				board.get(lastCol + 3, lastRow + 3));
		total += score;
		winValues |= 1 << (score + 5);

		// top left to bottom right
		score = score(board.get(lastCol - 3, lastRow + 3),
				board.get(lastCol - 2, lastRow + 2),
				board.get(lastCol - 1, lastRow + 1),
				board.get(lastCol, lastRow));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol - 2, lastRow + 2),
				board.get(lastCol - 1, lastRow + 1),
				board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow - 1));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol - 1, lastRow + 1),
				board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow - 1),
				board.get(lastCol + 2, lastRow - 2));
		total += score;
		winValues |= 1 << (score + 5);
		score = score(board.get(lastCol, lastRow),
				board.get(lastCol + 1, lastRow - 1),
				board.get(lastCol + 2, lastRow - 2),
				board.get(lastCol + 3, lastRow - 3));
		total += score;
		winValues |= 1 << (score + 5);
		
		//if total is 0 we either have a tie or no wins.
		//if winValues contains a win we have a tie.
		//if winValues contains no wins the maxMap will
		//give a score of 0 indicating no winner
		if(total == 0 && (winValues != 1 << 5)) {
			return 1;
		} else {
			//Mask winValues so we only consider relevant bits, then look
			//these up to determine the win value.
			return maxMap[winValues & maxMask[(total & (1 << 7)) >> 7]];
		}
	}

	public static byte score(byte a, byte b, byte c, byte d) {
		return scoreMap[(a << 6) | (b << 4) | (c << 2) | d];
	}
}
