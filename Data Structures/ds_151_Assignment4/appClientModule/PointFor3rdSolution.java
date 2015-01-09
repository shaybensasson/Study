/**
 * An ObjectWithCoordinates implementation for 3rd Solution question
 * holds additional information about the order of insert of the point
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
public class PointFor3rdSolution extends NamedPoint {

	private int orderOfInsert;

	public PointFor3rdSolution(int x, int y, String name, int orderOfInsert) {
		super(x,y,name);
		this.orderOfInsert = orderOfInsert; 
	}
	
	public int getOrderOfInsert() {
		return orderOfInsert;
	}
}
