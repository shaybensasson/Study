import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.junit.*;

import static org.junit.Assert.*;

public class TestBase {
	static boolean stringArrayEquals(String[] a, String[] b) {
		if (a == b)
			return true;

		if (a == null || b == null || a.length != b.length)
			return false;

		for (int i = 0; i < a.length; i++) {
			if (!a[i].equals(b[i]))
				return false;
		}

		return true;
	}
	
	protected static ObjectWithCoordinates[] readObjectWithCoordinatesFile(String fileName) {
		Vector<ObjectWithCoordinates> points = new Vector<ObjectWithCoordinates>();
		BufferedReader input;
		FileReader fileReader;

		try {
			fileReader = new FileReader(fileName);
			input = new BufferedReader(fileReader);
			String line = null;
			String objectName;
			int objectX, objectY;

			 while ((line = input.readLine()) != null) {
                 String[] lineArray = line.split(" ");
                 objectName = lineArray[0];
                 objectX = Integer.parseInt(lineArray[1].split("=")[1]);
                 objectY = Integer.parseInt(lineArray[2].split("=")[1]);
                 
                 //insertDataFromDBFile (objectName, objectX, objectY);
                 points.add(new DumpableNamedPoint(objectX, objectY, objectName));

              }    
			input.close();
			fileReader.close();
		} catch (IOException ioe) {
			// ioe.printStackTrace();
			fail(ioe.toString());
		} catch (Exception e) {
			// e.printStackTrace();
			fail(e.toString());
		}

		ObjectWithCoordinates[] result = new ObjectWithCoordinates[points.size()];
		points.toArray(result);
		return result;
	}
	
	protected static <T> void validateAVLHeightPolicyAndHierarchy(AVL<T> t) {
		if (t.getRoot() != null)
		{
			validateAVLHeightPolicyAndHierarchy(t.getRoot());
		} 
		
	}
	
	private static <T> void validateAVLHeightPolicyAndHierarchy(AVLNode<T> node) {
		assertTrue(
				String.format("The node '%s' is not balanced!", 
						node.getData(), node.isBalanced())
				,node.isBalanced());
		
		
		//Traverse in order
		if (node.getLeftChild() != null) {

			assertTrue(
					String.format("The node '%s' height [%d] is not less than 1 or 2 from it's parent '%s'[%d]!", 
							node.getLeftChild().getData(), node.getLeftChild().getHeight(), node.getData(), node.getHeight())
							,((node.getHeight() == node.getLeftChild().getHeight()+1) || (node.getHeight() == node.getLeftChild().getHeight()+2)) 
					);
			
			if (node.getLeftChild().getFather() != node)
				fail(String.format("lc '%s' expected parent '%s', actual parent '%s'.", node.getLeftChild(), node, node.getLeftChild().getFather()));
			
			validateAVLHeightPolicyAndHierarchy(node.getLeftChild());
		}
		
		if (node.getRightChild() != null) {
			
			assertTrue(
					String.format("The node '%s' height [%d] is not less than 1 or 2 from it's parent '%s'[%d]!", 
							node.getRightChild().getData(), node.getRightChild().getHeight(), node.getData(), node.getHeight())
							,((node.getHeight() == node.getRightChild().getHeight()+1) || (node.getHeight() == node.getRightChild().getHeight()+2))
					);
			
			if (node.getRightChild().getFather() != node)
				fail(String.format("rc '%s' expected parent '%s', actual parent '%s'.", node.getRightChild(), node, node.getRightChild().getFather()));
			
			validateAVLHeightPolicyAndHierarchy(node.getRightChild());
		}
	}
	
	public static <T> String dumpLinkedList(LinkedList<T> linkedList) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
			
		Link<T> tmp;
		
		tmp = linkedList.getFirst();
		while (tmp != null) {
			if (tmp != linkedList.getFirst())
				sb.append(",");
				
			T p = tmp.getData();
			sb.append(p);
			tmp = tmp.getNext();
		}
		
		sb.append("]");
		
		return sb.toString();
	}
	
	
	
	@Before
	public void setUpBeforeMethod() throws Exception {

	}

	@After
	public void tearDownAfterMethod() throws Exception {
	}
	
	public TestBase() {
		super();
	}

}