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
public class TestGenericAVL extends TestBase {

	@Test
	public void testEmptyTree() {
		// arrange

		// act
		AVL<Integer> tree = new AVL<Integer>();

		// assert
		assertNull(tree.getRoot());
	}

	@Test
	public void testSingleNodeTree() {
		// arrange
		AVL<Integer> tree = new AVL<Integer>();
		int expected = -1;
		tree.insert(1, expected);

		// act
		Integer actual = tree.search(1);

		// assert
		assertEquals((int) expected, (int) actual);
	}
	
	@Test
	public void testRotations_LR() {
		//arrange
		Func<ObjectWithCoordinates, Integer> func = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getX();
			}
		};
		
		String expected = "[(0,-1),(1,-1),(2,-1)]";
		
		//act
		AVL<ObjectWithCoordinates> tree;
		ObjectWithCoordinates dp;
				
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		String actual = dumpLinkedList(tree.Range(Integer.MIN_VALUE, Integer.MAX_VALUE));
		//assert
		
		assertTrue(expected.equals(actual));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRotations_RR() {
		//arrange
		Func<ObjectWithCoordinates, Integer> func = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getX();
			}
		};
		
		String expected = "[(0,-1),(1,-1),(2,-1)]";
		
		//act
		AVL<ObjectWithCoordinates> tree;
		ObjectWithCoordinates dp;
				
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		String actual = dumpLinkedList(tree.Range(Integer.MIN_VALUE, Integer.MAX_VALUE));
		//assert
		
		assertTrue(expected.equals(actual));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRotations_RRLR() {
		//arrange
		Func<ObjectWithCoordinates, Integer> func = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getX();
			}
		};
		
		String expected = "[(0,-1),(1,-1),(2,-1)]";
		
		//act
		AVL<ObjectWithCoordinates> tree;
		ObjectWithCoordinates dp;
				
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		String actual = dumpLinkedList(tree.Range(Integer.MIN_VALUE, Integer.MAX_VALUE));
		//assert
		
		assertTrue(expected.equals(actual));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRotations_LRRR() {
		//arrange
		Func<ObjectWithCoordinates, Integer> func = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getX();
			}
		};
		
		String expected = "[(0,-1),(1,-1),(2,-1)]";
		
		//act
		AVL<ObjectWithCoordinates> tree;
		ObjectWithCoordinates dp;
				
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		String actual = dumpLinkedList(tree.Range(Integer.MIN_VALUE, Integer.MAX_VALUE));
		//assert
		
		assertTrue(expected.equals(actual));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	
	@Test
	public void testRange_rangeExists() {
		//arrange
		AVL<Integer> tree = new AVL<Integer>();
		
		for (int i=1; i<=30; i++) {
			tree.insert(i, i);
		}
		
		int a=14;int b=16;
		String expected = "[14,15,16]";
		
		//act
		LinkedList<Integer> m1 = tree.Range(a, b);
		
		//assert
		String actual = dumpLinkedList(m1);
		assertTrue(expected.equals(actual));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRange_rangePointsExistAndNotExist() {
		//arrange
		AVL<Integer> tree = new AVL<Integer>();
		
		for (int i=0; i<30; i=i+2) {
			tree.insert(i, i);
		}
		
		int a=15;int b=18;
		String expected = "[16,18]";
		
		//act
		LinkedList<Integer> m1 = tree.Range(a, b);
		
		//assert
		String actual = dumpLinkedList(m1);
		assertEquals(expected, actual);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRange_singleRangePointExists() {
		//arrange
		AVL<Integer> tree = new AVL<Integer>();
		
		for (int i=0; i<30; i=i+2) {
			tree.insert(i, i);
		}
		
		int a=18;int b=18;
		String expected = "[18]";
		
		//act
		LinkedList<Integer> m1 = tree.Range(a, b);
		
		//assert
		String actual = dumpLinkedList(m1);
		assertEquals(expected, actual);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRange_singleRangePointDoesNotExist() {
		//arrange
		AVL<Integer> tree = new AVL<Integer>();
		
		for (int i=0; i<30; i=i+2) {
			tree.insert(i, i);
		}
		
		int a=15;int b=15;
		int expected = 0;
		
		//act
		LinkedList<Integer> m1 = tree.Range(a, b);
		
		//assert
		int actual = m1.getCount();
		assertEquals(expected, actual);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRange_outOfRange() {
		//arrange
		AVL<Integer> tree = new AVL<Integer>();
		
		for (int i=0; i<30; i=i+2) {
			tree.insert(i, i);
		}
		
		int a=-10;int b=-20;
		int expected = 0;
		
		//act
		LinkedList<Integer> m1 = tree.Range(a, b);
		
		//assert
		int actual = m1.getCount();
		assertEquals(expected, actual);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
}
