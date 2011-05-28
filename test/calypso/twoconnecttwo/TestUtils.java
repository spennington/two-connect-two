package calypso.twoconnecttwo;
/**
 * 
 */


import java.io.ByteArrayInputStream;

import calypso.twoconnecttwo.Board;
import calypso.twoconnecttwo.IO;

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
