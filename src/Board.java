/**
 * This class represents a Two Connect Two game board
 * and provides functionality to manipulate pieces
 */

/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class Board implements Cloneable {
	
	/**
	 * Encoding of the red piece
	 */
	public static final byte RED = 1;
	/**
	 * Encoding of the blue piece
	 */
	public static final byte BLUE = 2;
	/**
	 * Encoding of the green piece
	 */
	public static final byte GREEN = 3;
	/**
	 * Encoding on an empty board location
	 */
	public static final byte SPACE = 0;
	
	private static final int PADDING = 6;
	
	private byte[][] pieces;
	private int[] tops;
	private int pieceCount;
	
	private int width;
	private int height;
	
	/**
	 * Create a new Two Connect Two board with the specified
	 * width and height
	 * 
	 * @param width
	 * 		width of the game board
	 * @param height
	 * 		height of the game board
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		pieces = new byte[width + PADDING][height + PADDING];
		tops = new int[width];
	}
	
	/**
	 * Place a piece in the board
	 * 
	 * @param x
	 *            X position of the piece to place. from 0 to board width
	 * @param y
	 *            Y position of the piece to place. from 0 to board height
	 * @param piece
	 *            Piece to place in the board. Should be one
	 *            {@link Board#BLUE}, {@link Board#RED}, or
	 *            {@link Board#GREEN}
	 */
	public void put(int x, int y, byte piece) {
		if(piece != SPACE) {
			pieceCount++;
			this.tops[x] = y;
		}
		this.pieces[x + 3][y + 3] = piece;
	}

	/**
	 * Retrieve a piece from the board.
	 * 
	 * @param x
	 *            X position of the piece to retrieve. from 0 to board width
	 * @param y
	 *            Y position of the piece to retrieve. from 0 to board height
	 * 
	 * @return Piece at the given x y coordinates. Will be one of
	 *         {@link Board#BLUE}, {@link Board#RED},
	 *         {@link Board#GREEN}, or {@link Board#SPACE}
	 */
	public byte get(int x, int y) {
		return this.pieces[x + 3][y + 3];
	}
	
	/**
	 * Determine if the board is full
	 * 
	 * @return <code>true</code> if the board is full,
	 * or in other words has no empty spaces. <code>false</code>
	 * if the board has at least one empty space
	 * 
	 */
	public boolean isFull() {
		return pieceCount == this.width * this.height;
	}
	
	/**
	 * Retrieve the y location of the top piece in the
	 * specified column. Note that if the column is empty, 0 will be returned.
	 * This is the same value as a column with one piece in it. A column
	 * that is full will return board height
	 * 
	 * @param x
	 * 		Column to retrieve top piece from
	 * 
	 * @return y location of the top piece for the specified
	 * column. 
	 */
	public int getTop(int x) {
		return this.tops[x];
	}
}
