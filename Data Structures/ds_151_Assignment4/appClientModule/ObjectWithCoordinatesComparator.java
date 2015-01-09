import java.util.Comparator;

/**
 * A skeleton comparator for ObjectWithCoordinates instances
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 */
abstract class ObjectWithCoordinatesComparator implements Comparator<ObjectWithCoordinates> {

	/**
	 * compares two points
	 */
	public abstract int compare(ObjectWithCoordinates a, ObjectWithCoordinates b);
}
