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
public class TestSpatialAVL extends TestBase {

	private final ObjectWithCoordinatesComparator compX = new ObjectWithCoordinatesByXThenYComparator();
	private final ObjectWithCoordinatesComparator compY = new ObjectWithCoordinatesByYThenXComparator();
	
	@Test
	public void testEmptyTree() {
		//arrange
				
		//act
		AVL<ObjectWithCoordinates> tree = new AVL<ObjectWithCoordinates>();
		
		//assert
		assertNull(tree.getRoot());
	}
	
	@Test
	public void testSingleNodeTree() {
		//arrange
		AVL<ObjectWithCoordinates> tree = new AVL<ObjectWithCoordinates>();
		ObjectWithCoordinates expected = new DumpableNamedPoint(1, 2);
		tree.insert(1, expected);
		
		//act
		ObjectWithCoordinates actual = tree.search(1);
		
		//assert
		assertTrue(compX.compare(expected, actual) == 0);
	}
	
	@Test
	public void testRangeIsSortedByXThenY() {
		//arrange
		Func<ObjectWithCoordinates, Integer> funcX = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getX();
			}
		};
		
				
		AVL<ObjectWithCoordinates> tree = new AVL<ObjectWithCoordinates>();
		Func<ObjectWithCoordinates, Integer> func = funcX;
				
		ObjectWithCoordinates dp;
		dp = new DumpableNamedPoint(0,11,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(20,1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(2,3,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(4,5,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(6,6,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(7,8,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(10,9,"");
		tree.insert(func.call(dp), dp);
		
		
		
		String expected = "[(0,11),(2,3),(4,5),(6,6),(7,8),(10,9),(20,1)]"; 
		//act
		String actual = dumpLinkedList(tree.Range(-1, 20));
		
		//assert
		assertEquals(expected, actual);
		
		validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testRangeIsSortedByYThenX() {
		//arrange
		Func<ObjectWithCoordinates, Integer> funcY = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getY();
			}
		};
		
				
		AVL<ObjectWithCoordinates> tree = new AVL<ObjectWithCoordinates>();
		Func<ObjectWithCoordinates, Integer> func = funcY;
				
		ObjectWithCoordinates dp;
		dp = new DumpableNamedPoint(0,11,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(20,1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(2,3,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(4,5,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(6,6,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(7,8,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(10,9,"");
		tree.insert(func.call(dp), dp);
				
		
		String expected = "[(20,1),(2,3),(4,5),(6,6),(7,8),(10,9),(0,11)]"; 
		//act
		String actual = dumpLinkedList(tree.Range(-1, 20));
		
		//assert
		assertEquals(expected, actual);
		
		validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testGetPointsLeftToVSplit_WhenAEqualsInorderRightSubTree() {
		//arrange
		AVL<DumpableNamedPoint> tree = new AVL<DumpableNamedPoint>();
		
		for (int i=0; i<50; i=i+2) {
			tree.insert(i, new DumpableNamedPoint(i,i+1,""));
		}
		
		int a=10;int b=30;
		String expected = "[(10,11),(12,13),(14,15),(16,17),(18,19),(20,21),(22,23),(24,25),(26,27),(28,29),(30,31)]";
		
		//act
		LinkedList<DumpableNamedPoint> m1 = tree.Range(a, b);
		
		//assert
		String actual = dumpLinkedList(m1);
		assertTrue(expected.equals(actual));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
	
	@Test
	public void testGetPointsRightToVSplit_WhenBEqualsInorderLeftSubTree() {
		//arrange
		AVL<DumpableNamedPoint> tree = new AVL<DumpableNamedPoint>();
		
		for (int i=0; i<50; i=i+2) {
			tree.insert(i, new DumpableNamedPoint(i,i+1,""));
		}
		
		int a=30;int b=42;
		String expected = "[(30,31),(32,33),(34,35),(36,37),(38,39),(40,41),(42,43)]";
		
		//act
		LinkedList<DumpableNamedPoint> m1 = tree.Range(a, b);
		
		//assert
		String actual = dumpLinkedList(m1);
		assertTrue(expected.equals(actual));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}
}
