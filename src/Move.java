/**
 * @author Steven Pennington and Julie Sparrow
 *
 */
public class Move implements Cloneable {
	public int column;
	public int score;
	public byte piece;
	public boolean isWin = false;
	
	public Move() {}
	
	public Move(int column, int score, byte piece) {
		this(column, score, piece, false);
	}
	
	public Move(int column, int score, byte piece, boolean isWin) {
		this.column = column;
		this.score = score;
		this.piece = piece;
		this.isWin = isWin;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Move clone() {
		return new Move(this.column, this.score, this.piece);
	}
}
