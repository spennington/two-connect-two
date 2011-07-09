/**
 * @author Steven Pennington and Julie Sparrow
 *
 */
public class Move {
	public int column;
	public int score;
	public byte piece;
	
	public Move() {}
	
	public Move(int column, int score, byte piece) {
		this.column = column;
		this.score = score;
		this.piece = piece;
	}
}
