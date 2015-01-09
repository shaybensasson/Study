import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

/**
 * TODO
 */

/**
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
public class TestComparators extends TestBase {

	@Test
	public void testKeyComparator() {
		//arrange
		KeyComparator comp = new KeyComparator();

		//act
				
		//assert
		assertTrue(comp.compare(1, 2) < 0);
		assertTrue(comp.compare(2, 1) > 0);
		assertTrue(comp.compare(1, 1) == 0);
	}
	
	@Test
	public void testObjectWithCoordinatesByXComparator() {
		//arrange
		ObjectWithCoordinatesComparator comp = new ObjectWithCoordinatesByXThenYComparator();

		ObjectWithCoordinates h = new DumpableNamedPoint(1, 2);	
		ObjectWithCoordinates l = new DumpableNamedPoint(0, 2);
		
		//assert
		assertTrue(comp.compare(h, l) > 0);
		assertTrue(comp.compare(l, h) < 0);
		assertTrue(comp.compare(h, h) == 0);
	}
	
	@Test
	public void testObjectWithCoordinatesByYComparator() {
		//arrange
		ObjectWithCoordinatesComparator comp = new ObjectWithCoordinatesByYThenXComparator();

		ObjectWithCoordinates h = new DumpableNamedPoint(2, 1);	
		ObjectWithCoordinates l = new DumpableNamedPoint(2, 0);
		
		//assert
		assertTrue(comp.compare(h, l) > 0);
		assertTrue(comp.compare(l, h) < 0);
		assertTrue(comp.compare(h, h) == 0);
	}
	
	
}
