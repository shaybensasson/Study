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
public class TestLinkedList extends TestBase {

	@Test
	public void addFirstSingleItem() {
		//arrange
		LinkedList<Integer> ll = new LinkedList<Integer>();
		String expected = "1";
		
		//act
		ll.addFirst(1);
		
		//assert
		String actual = ll.toString();
		assertTrue(expected.equals(actual));
		assertTrue(ll.getFirst().getData() == 1);
		assertTrue(ll.getLast().getData() == 1);
	}
	
	@Test
	public void addLastSingleItem() {
		//arrange
		LinkedList<Integer> ll = new LinkedList<Integer>();
		String expected = "1";
		
		//act
		ll.addLast(1);
		
		//assert
		String actual = ll.toString();
		assertTrue(expected.equals(actual));
		assertTrue(ll.getFirst().getData() == 1);
		assertTrue(ll.getLast().getData() == 1);
	}
	
	@Test
	public void addTwoItems() {
		//arrange
		LinkedList<Integer> ll = new LinkedList<Integer>();
		String expected = "1->2->3";
		
		//act
		ll.addFirst(2);
		ll.addFirst(1);
		ll.addLast(3);
		
		
		//assert
		String actual = ll.toString();
		assertTrue(expected.equals(actual));
		assertTrue(ll.getFirst().getData() == 1);
		assertTrue(ll.getLast().getData() == 3);
	}
}
