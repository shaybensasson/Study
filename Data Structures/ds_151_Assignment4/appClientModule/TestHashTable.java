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
public class TestHashTable extends TestBase {

	@Test
	public void testEmptyHashtableSearchReturnsNull() {
		//arrange
		int M = 2;
		HashTable h = new HashTable(M);
				
		//act
		ObjectWithCoordinates owc = h.search(2, 2);
		
		//assert
		assertNull(owc);
	}
	
	@Test
	public void singleItem() {
		//arrange
		int M = 2;
		HashTable h = new HashTable(M);
		ObjectWithCoordinates expected = new DumpableNamedPoint(1, 1);
		h.insert(expected);
		
		//act
		ObjectWithCoordinates actual = h.search(1, 1);
		
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void multipleItemsOnSingleSlot() {
		//arrange
		int M = 1;
		HashTable h = new HashTable(M);
		ObjectWithCoordinates expected1 = new DumpableNamedPoint(1, 1);
		h.insert(expected1);
		ObjectWithCoordinates expected2 = new DumpableNamedPoint(2, 2);
		h.insert(expected2);
		
		//act
		ObjectWithCoordinates actual1 = h.search(1, 1);
		ObjectWithCoordinates actual2 = h.search(2, 2);
		
		//assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void multipleItemsOnSameSlot() {
		//arrange
		int M = 10;
		HashTable h = new HashTable(M);
		ObjectWithCoordinates expected1 = new DumpableNamedPoint(1, 2);
		h.insert(expected1);
		ObjectWithCoordinates expected2 = new DumpableNamedPoint(2, 1);
		h.insert(expected2);
		
		//act
		ObjectWithCoordinates actual1 = h.search(1, 2);
		ObjectWithCoordinates actual2 = h.search(2, 1);
		
		//assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void sameHashButNonExistantItemReturnsNull() {
		//arrange
		int M = 10;
		HashTable h = new HashTable(M);
		ObjectWithCoordinates expected1 = new DumpableNamedPoint(1, 2);
		h.insert(expected1);
		ObjectWithCoordinates expected2 = new DumpableNamedPoint(2, 1);
				
		//act
		ObjectWithCoordinates actual = h.search(2, 1);
		
		//assert
		assertNull(actual);
	}
	
	@Test
	public void multipleItemsOnDifferentSlots() {
		//arrange
		int M = 10;
		HashTable h = new HashTable(M);
		ObjectWithCoordinates expected1 = new DumpableNamedPoint(1, 1);
		h.insert(expected1);
		ObjectWithCoordinates expected2 = new DumpableNamedPoint(2, 2);
		h.insert(expected2);
		ObjectWithCoordinates expected3 = new DumpableNamedPoint(3, 3);
		h.insert(expected3);
		
		//act
		ObjectWithCoordinates actual1 = h.search(1, 1);
		ObjectWithCoordinates actual2 = h.search(2, 2);
		ObjectWithCoordinates actual3 = h.search(3, 3);
		
		//assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
	}
	
	@Test
	public void multipleItemsWithNegativeValues() {
		//arrange
		int M = 10;
		HashTable h = new HashTable(M);
		ObjectWithCoordinates expected1 = new DumpableNamedPoint(-1, -1);
		h.insert(expected1);
		ObjectWithCoordinates expected2 = new DumpableNamedPoint(-2, 2);
		h.insert(expected2);
		ObjectWithCoordinates expected3 = new DumpableNamedPoint(-3, -3);
		h.insert(expected3);
		
		//act
		ObjectWithCoordinates actual1 = h.search(-1, -1);
		ObjectWithCoordinates actual2 = h.search(-2, 2);
		ObjectWithCoordinates actual21 = h.search(-2, -2);
		ObjectWithCoordinates actual22 = h.search(2, 2);
		ObjectWithCoordinates actual23 = h.search(2, -2);
		ObjectWithCoordinates actual3 = h.search(-3, -3);
		ObjectWithCoordinates actual31 = h.search(3, 3);
		
		//assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertNull(actual21);
		assertNull(actual22);
		assertNull(actual23);
		assertNull(actual31);
	}


}
