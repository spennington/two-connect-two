/**
 * @author Steven Pennington and Julie Sparrow
 *
 */
public class Move implements Cloneable {
	public int column;
	public int score;
	public byte piece;
	
	public Move() {}
	
	public Move(int column, int score, byte piece) {
		this.column = column;
		this.score = score;
		this.piece = piece;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Move clone() {
		return new Move(this.column, this.score, this.piece);
	}
}
