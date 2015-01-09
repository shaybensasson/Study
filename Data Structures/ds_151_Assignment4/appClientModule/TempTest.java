import java.util.Iterator;
import java.util.LinkedList;

/**
 * TODO
 */


/**
 * @author Shay Ben-Sasson
 *
 */
public class TempTest {

	/**
	 * 
	 */
	public TempTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<ObjectWithCoordinates> linkedList = new LinkedList<ObjectWithCoordinates>();
		linkedList.add(new NamedPoint(1, 2, ""));
		linkedList.add(new NamedPoint(3, 4, ""));
		linkedList.add(new NamedPoint(5, 6, ""));
		
		for (Iterator<ObjectWithCoordinates> iterator = linkedList.iterator(); iterator.hasNext();) {
    		ObjectWithCoordinates owc = iterator.next();
        	//Bla Bla Blat
    		
    		System.out.println(owc);
        		
        }

	}

}
