import java.util.Comparator;

/**
 * 
 */

/**
 * @author steve
 *
 */
public class MaxComparator implements Comparator<Move> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Move m1, Move m2) {
		//if(m1.isWin && m2.isWin && m1.score > 1 && m2.score > 1) {
			//Red win, so we want the lowest depth
		//	return m2.depth - m1.depth;
		//} else if(m1.isWin && m2.isWin && m1.score < 1 && m2.score < 1) {
			//Blue win, so we want the highest depth
		//	return m1.depth - m2.depth;
		//} else 
		if(m1.score != m2.score) {
			return m1.score > m2.score ? 1 : -1;
		} else {
			return 0;
		}
	}

}
