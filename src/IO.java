import java.io.InputStream;
import java.util.Scanner;

/**
 * A class to convert the string recieved from the Game
 * Framework to a {@link Board} and {@link GameState}
 */

/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class IO {
	
	private static final byte RED_CHAR = (byte) 'r';
	private static final byte BLUE_CHAR = (byte) 'b';
	private static final byte GREEN_CHAR = (byte) 'g';
	private static final byte SPACE_CHAR = (byte) 's';

	private static byte[] charMap = new byte[256];
	
	private static Scanner scanner;

	static {
		// Map to convert characters to their
		// corresponding 2 bit value
		charMap[RED_CHAR] = Board.RED;
		charMap[BLUE_CHAR] = Board.BLUE;
		charMap[GREEN_CHAR] = Board.GREEN;
		charMap[SPACE_CHAR] = Board.SPACE;
	}
	
	/**
	 * Sets the input stream used to retrieve data.
	 * Generally this will be <code>System.in</code>
	 * 
	 * Note that once <code>init</code> is called a subsequent
	 * call to {@link #close()} should be made after parsing the
	 * data.
	 * 
	 * @param stream
	 * 		Stream that data is read from
	 */
	public static void init(InputStream stream) {
		scanner = new Scanner(stream);
		// Use left parenthesis, right parenthesis, and comma to
		// split input tokens
		scanner.useDelimiter("[,]|[\\(]|[\\)]");
	}
	
	/**
	 * Releases resources used while reading from the stream.
	 * This should be called once after parsing data using 
	 * {@link #parseGameState()} and/or {@link #parseBoard(int, int)}
	 */
	public static void close() {
		if(scanner != null) {
			scanner.close();
		}
	}
	
	/**
	 * Parse the current state of the game. Note that
	 * {@link #init(InputStream)} must have been called prior
	 * to invoking this method.
	 * 
	 * @return
	 * 		Parsed game state
	 */
	public static GameState parseGameState() {
		GameState gameState = new GameState();

		gameState.numCols = scanner.nextInt();
		gameState.numRows = scanner.nextInt();
		gameState.lastCol = scanner.nextInt();
		gameState.gameTime = scanner.nextInt();
		gameState.playerOneTime = scanner.nextInt();
		gameState.lastMoveTime = scanner.nextInt();
		
		return gameState;
	}
	
	/**
	 * Parse a game board of the given size. Note that
	 * {@link #init(InputStream)} must have been called prior
	 * to invoking this method.
	 * 
	 * @param numCols
	 * 		Number of columns in the board to parse (width)
	 * @param numRows
	 * 		Number of rows in the board to parse (height)
	 * 
	 * @return
	 * 		Parsed board
	 */
	public static Board parseBoard(int numCols, int numRows) {
		Board board = new Board(numCols, numRows);

		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				byte piece = scanner.next().getBytes()[0];
				board.put(i, j, charMap[piece]);
			}
		}
		
		return board;
	}
}
