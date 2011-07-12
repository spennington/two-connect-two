/**
 * @author Steven Pennington and Julie Sparrow
 *
 */
public class Move implements Cloneable {
	public int column;
	public int score;
	public byte piece;
	public int depth;
	public boolean isWin;
	
	public Move() {}
	
	public Move(int column, int score, byte piece) {
		this(column, score, piece, 0, false);
	}
	
	public Move(int column, int score, byte piece, int depth, boolean isWin) {
		this.column = column;
		this.score = score;
		this.piece = piece;
		this.depth = depth;
		this.isWin = isWin;
	}
	
	public void copy(Move move) {
		this.column = move.column;
		this.score = move.score;
		this.piece = move.piece;
		this.depth = move.depth;
		this.isWin = move.isWin;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Move clone() {
		return new Move(this.column, this.score, this.piece, this.depth, this.isWin);
	}
	
}
