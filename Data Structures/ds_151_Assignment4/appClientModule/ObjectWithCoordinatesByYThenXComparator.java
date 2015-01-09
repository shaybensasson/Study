/**
 * A comparator for ObjectWithCoordinates instances that compares by y then by x
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
class ObjectWithCoordinatesByYThenXComparator extends ObjectWithCoordinatesComparator {

	/** 
	 * Compares two points by their y coordinates and then by x
	 * NOTE: we do not use subtraction so inf and -inf could be used
	 */
	@Override
	public int compare(ObjectWithCoordinates p1, ObjectWithCoordinates p2) {
		if (p1.getY() < p2.getY()) return -1;
		if (p1.getY() > p2.getY()) return +1;
		if (p1.getX() < p2.getX()) return -1;
		if (p1.getX() > p2.getX()) return +1;
		return 0;
	}
}
