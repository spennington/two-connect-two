
/**
 * @author Steve Pennington and Julie Sparrow
 *
 */
public class TwoConnectTwo {

	/**
	 * Main entry point into the application
	 * @param args
	 * 		command line arguments
	 */
	public static void main(String[] args) {
		IO.init(System.in);
		GameState gameState = IO.parseGameState();
		Board board = IO.parseBoard(gameState.numCols, gameState.numRows);
		IO.close();
		if(board.isEmpty()) {
			System.out.println("0");
		} else {
			// Input assumes first column is 1 we use first column as 0, so subtract 1
			System.out.println(WinChecker.isWin(board, gameState.lastCol - 1));
		}
	}
}
