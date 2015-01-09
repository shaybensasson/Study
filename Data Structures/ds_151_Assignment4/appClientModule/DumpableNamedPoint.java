/**
 * TODO
 */

/**
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 * NO_SUBMIT
 *
 */
public class DumpableNamedPoint extends NamedPoint {

	public DumpableNamedPoint(int x, int y) {
		super(x,y, "");
	}
	
	public DumpableNamedPoint(int x, int y, String data) {
		super(x,y,data);
	}
	
	@Override
	public String toString() {
		//String s = (String)getData();
		//s = s.equals("") ? s: " " + s;
		//return s + String.format("(%d,%d)", this.getX(), this.getY());
		return String.format("(%d,%d)", this.getX(), this.getY()); //TODO:
	}

}
