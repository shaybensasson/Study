import java.util.Comparator;

/**
 * A comparator for integer keys
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
public class KeyComparator implements Comparator<Integer> {

	/** 
	 * Compares two integers
	 * NOTE: we do not use subtraction so inf and -inf could be used
	 */
	@Override
	public int compare(Integer x, Integer y) {
		if (x<y) return -1;
		if (x>y) return +1;
		return 0;
	}
}
