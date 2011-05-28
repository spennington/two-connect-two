/**
 * 
 */


import java.io.ByteArrayInputStream;

/**
 * @author steve
 *
 */
public class TestUtils {
	public static Board generateBoard(String config, int numCols, int numRows) {
		IO.init(new ByteArrayInputStream(config.getBytes()));
		Board board = IO.parseBoard(numCols, numRows);
		IO.close();
		return board;
	}
}
