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
	 * Place a piece in the next available space for a given column.
	 * Do not call this method with {@link Board#SPACE}, instead call
	 * {@link #put(int, int, byte)}. 
	 * 
	 * @param x
	 * 		Column to place the piece in
	 * @param piece
	 * 		One of {@link Board#RED}, {@link Board#BLUE}, or {@link Board#GREEN}
	 */
	public void drop(int x, byte piece) {
		this.pieces[x + 3][this.tops[x]++ + 3] = piece;
		pieceCount++;
	}
	
	/**
	 * Remove the top piece from a column.
	 * 
	 * @param x
	 * 		Column to remove a piece from
	 */
	public void remove(int x) {
		this.pieces[x + 3][--this.tops[x] + 3] = Board.SPACE;
		pieceCount--;
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
	 * Determine if the the board is empty
	 * 
	 * @return <code>true</code> if the board has no pieces in it,
	 * otherwise <code>false</code>
	 */
	public boolean isEmpty() {
		return this.pieceCount == 0;
	}
	
	/**
	 * Retrieve the y location of the first empty spot in the
	 * specified column. Note that if the column is empty, 0 will be returned.
	 * A column that is full will return board height
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
	
	/**
	 * Get the board width
	 * @return width of the board
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the board height
	 * @return height of the board
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Get the number of non-empty pieces in the board
	 * @return Number of non-empty pieces in the board
	 */
	public int getPieceCount() {
		return pieceCount;
	}
}
