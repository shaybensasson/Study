/**
 * An ObjectWithCoordinate implementation
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
public class NamedPoint implements ObjectWithCoordinates {

	private int x;
	private int y;
	private String name;

	public NamedPoint(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		
	}
	/* (non-Javadoc)
	 * @see ObjectWithCoordinates#getX()
	 */
	@Override
	public int getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see ObjectWithCoordinates#getY()
	 */
	@Override
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see ObjectWithCoordinates#getData()
	 */
	@Override
	public Object getData() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("%s X=%d Y=%d", name, x, y);
	}

}
