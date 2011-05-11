import java.util.Scanner;

public class TwoConnectTwo {

	private static final byte RED_CHAR = (byte) 'r';
	private static final byte BLUE_CHAR = (byte) 'b';
	private static final byte GREEN_CHAR = (byte) 'g';
	private static final byte SPACE_CHAR = (byte) 's';

	public static final byte RED = 1;
	public static final byte BLUE = 2;
	public static final byte GREEN = 3;
	public static final byte SPACE = 0;

	private static byte[] charMap = new byte[256];
	private static byte[] scoreMap = new byte[256];

	private static int[] columnTops;

	private static boolean isFull = true;

	static {
		// Map to convert characters to their
		// corresponding 2 bit value
		charMap[RED_CHAR] = RED;
		charMap[BLUE_CHAR] = BLUE;
		charMap[GREEN_CHAR] = GREEN;
		charMap[SPACE_CHAR] = SPACE;

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
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Use left parenthesis, right parenthesis, and comma to
		// split input tokens
		scanner.useDelimiter("[,]|[\\(]|[\\)]");

		int numCols = scanner.nextInt();
		int numRows = scanner.nextInt();
		// Input assumes first column is 1, we use first column as 0
		int lastCol = scanner.nextInt() - 1;
		int gameTime = scanner.nextInt();
		int playerOneTime = scanner.nextInt();
		int lastMoveTime = scanner.nextInt();

		columnTops = new int[numCols];

		byte[][] board = new byte[numCols + 6][numRows + 6];
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				byte piece = scanner.next().getBytes()[0];
				putPiece(i, j, charMap[piece], board);
				if (piece != SPACE_CHAR) {
					columnTops[i] = j;
				} else {
					isFull = false;
				}
			}
		}

		System.out.println(isWin(board, numCols, numRows, lastCol));
	}

	public static int isWin(byte[][] board, int cols, int rows, int lastCol) {
		if (isFull) {
			return 1;
		}

		int lastRow = columnTops[lastCol];

		int scores[] = new int[13];
		int scoreIndex = 0;

		// horizontal
		scores[scoreIndex++] = score(getPiece(lastCol - 3, lastRow, board),
				getPiece(lastCol - 2, lastRow, board),
				getPiece(lastCol - 1, lastRow, board),
				getPiece(lastCol, lastRow, board));
		scores[scoreIndex++] = score(getPiece(lastCol - 2, lastRow, board),
				getPiece(lastCol - 1, lastRow, board),
				getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow, board));
		scores[scoreIndex++] = score(getPiece(lastCol - 1, lastRow, board),
				getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow, board),
				getPiece(lastCol + 2, lastRow, board));
		scores[scoreIndex++] = score(getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow, board),
				getPiece(lastCol + 2, lastRow, board),
				getPiece(lastCol + 3, lastRow, board));

		// vertical
		scores[scoreIndex++] = score(getPiece(lastCol, lastRow, board),
				getPiece(lastCol, lastRow - 1, board),
				getPiece(lastCol, lastRow - 2, board),
				getPiece(lastCol, lastRow - 3, board));

		// bottom left to top right
		scores[scoreIndex++] = score(getPiece(lastCol - 3, lastRow - 3, board),
				getPiece(lastCol - 2, lastRow - 2, board),
				getPiece(lastCol - 1, lastRow - 1, board),
				getPiece(lastCol, lastRow, board));
		scores[scoreIndex++] = score(getPiece(lastCol - 2, lastRow - 2, board),
				getPiece(lastCol - 1, lastRow - 1, board),
				getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow + 1, board));
		scores[scoreIndex++] = score(getPiece(lastCol - 1, lastRow - 1, board),
				getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow + 1, board),
				getPiece(lastCol + 2, lastRow + 2, board));
		scores[scoreIndex++] = score(getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow + 1, board),
				getPiece(lastCol + 2, lastRow + 2, board),
				getPiece(lastCol + 3, lastRow + 3, board));

		// bottom top left to bottom right
		scores[scoreIndex++] = score(getPiece(lastCol - 3, lastRow + 3, board),
				getPiece(lastCol - 2, lastRow + 2, board),
				getPiece(lastCol - 1, lastRow + 1, board),
				getPiece(lastCol, lastRow, board));
		scores[scoreIndex++] = score(getPiece(lastCol - 2, lastRow + 2, board),
				getPiece(lastCol - 1, lastRow + 1, board),
				getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow - 1, board));
		scores[scoreIndex++] = score(getPiece(lastCol - 1, lastRow + 1, board),
				getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow - 1, board),
				getPiece(lastCol + 2, lastRow - 2, board));
		scores[scoreIndex++] = score(getPiece(lastCol, lastRow, board),
				getPiece(lastCol + 1, lastRow - 1, board),
				getPiece(lastCol + 2, lastRow - 2, board),
				getPiece(lastCol + 3, lastRow - 3, board));
		
		return getFinalGameScore(scores);
	}

	/**
	 * Place a piece in the two dimensional board array. This method is a helper
	 * to make the padding transparent
	 * 
	 * @param x
	 *            X position of the piece to retrieve. from 0 to board width
	 * @param y
	 *            Y position of the piece to retrieve. from 0 to board height
	 * @param piece
	 *            Piece to place in the board. Should be one
	 *            {@link TwoConnectTwo#BLUE}, {@link TwoConnectTwo#RED}, or
	 *            {@link TwoConnectTwo#GREEN}
	 * @param board
	 *            Two dimensional array representing the board
	 */
	public static void putPiece(int x, int y, byte piece, byte[][] board) {
		board[x + 3][y + 3] = piece;
	}

	/**
	 * Retrieve a piece from the two dimensional board array. This method is a
	 * helper to make the padding transparent.
	 * 
	 * @param x
	 *            X position of the piece to retrieve. from 0 to board width
	 * @param y
	 *            Y position of the piece to retrieve. from 0 to board height
	 * @param board
	 *            Two dimensional array representing the board
	 * 
	 * @return Piece at the given x y coordinates. Will be one of
	 *         {@link TwoConnectTwo#BLUE}, {@link TwoConnectTwo#RED},
	 *         {@link TwoConnectTwo#GREEN}, or {@link TwoConnectTwo#SPACE}
	 */
	public static byte getPiece(int x, int y, byte[][] board) {
		return board[x + 3][y + 3];
	}

	public static int score(byte a, byte b, byte c, byte d) {
		return scoreMap[(a << 6) | (b << 4) | (c << 2) | d];
	}
	
	public static int getFinalGameScore(int scores[]) {		
		int blueScore = 0;
		int redScore = 0;
		int redMax = 0;
		int blueMax = 0;

		for (int i = 0; i < scores.length; i++) {
			if (scores[i] < 0) {
				blueScore += scores[i];
				blueMax = Math.min(blueMax, scores[i]);
			} else if (scores[i] > 0) {
				redScore += scores[i];
				redMax = Math.max(redMax, scores[i]);
			}
		}

		if (redScore == blueScore * -1 && redScore != 0) {
			return 1;
		} else if (redScore > blueScore * -1) {
			return redMax;
		} else if (redScore < blueScore * -1) {
			return blueMax;
		}

		return 0;
	}
}
