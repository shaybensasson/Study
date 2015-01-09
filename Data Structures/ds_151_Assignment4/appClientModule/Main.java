import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.concurrent.Callable;

import javax.swing.JFileChooser;

import org.omg.CORBA.portable.ApplicationException;


public class Main {
	static MyInterface studentSolution = new StudentSolution();
	public static void main(String[] args) {
		//testHashtable();
		//testAVL();
		//testComparators();
		
		//testBUG();
		
		//testTestStudentSolution();
		
	}
	
	/**
	 * 
	 */
	private static void testTestStudentSolution() {
		// TODO Auto-generated method stub
		try {
			TestStudentSolution.setUpBeforeClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TestStudentSolution sol = new TestStudentSolution();
		//sol.testSol1_AllRange_DB1();
		//sol.testSol2_AllRange_DB1();
		//sol.testSol3_AllRange_DB1();
		
		//sol.testSol1_AllRange_DB2();
		
		sol.testSol1_Temp_OneMil();
		
		
	}

	/**
	 * 
	 */
	private static void testBUG() {
		loadData("C:\\Users\\Shay\\workspace\\ds_151_Assignment4\\appClientModule\\DB1.txt");
		String[] s = studentSolution.thirdSolution(84, 20, 169, 98);
		System.out.println(Arrays.deepToString(s));
	}
	
	private static void loadData(String fileName) {
		boolean withOutLoading = true;
		BufferedReader input=null;
		try {
    		
    		int returnVal=0;
    		JFileChooser fc=null;
    		File file=null;
    	
    		input = new BufferedReader(new FileReader(fileName));
    		    	
        	if (returnVal == JFileChooser.APPROVE_OPTION || withOutLoading) {
            	if(!withOutLoading) file = fc.getSelectedFile();

                if (!withOutLoading) input = new BufferedReader(new FileReader(file));
                String line = null;
                String objectName;
                int objectX, objectY;
                ArrayList<String> inputPoints = new ArrayList<String>();

                while ((line = input.readLine()) != null) {
                    String[] lineArray = line.split(" ");
                    objectName = lineArray[0];
                    objectX = Integer.parseInt(lineArray[1].split("=")[1]);
                    objectY = Integer.parseInt(lineArray[2].split("=")[1]);
                    inputPoints.add(line);
                    
                    insertDataFromDBFile (objectName, objectX, objectY);

                 }           
            }
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static void insertDataFromDBFile(String objectName, int objectX, int objectY) {
		studentSolution.insertDataFromDBFile(objectName, objectX, objectY);
	}

	/**
	 * 
	 */
	private static void testComparators() {
		AVL<ObjectWithCoordinates> tree = new AVL<ObjectWithCoordinates>();
		DumpableNamedPoint n00 =  new DumpableNamedPoint(0,0,"");
		DumpableNamedPoint n11 =  new DumpableNamedPoint(1,1,"");
		DumpableNamedPoint n22 =  new DumpableNamedPoint(2,2,"");
		DumpableNamedPoint n12 =  new DumpableNamedPoint(1,2,"");
		
		ObjectWithCoordinatesComparator comp = new ObjectWithCoordinatesByXThenYComparator();		
		System.out.println(comp.compare(n00, n11));
		System.out.println(comp.compare(n22, n11));
		System.out.println(comp.compare(n11, n12));
		System.out.println(comp.compare(n11, n11));
		
	}

	/**
	 * 
	 */
	private static void testAVL() {
		// TODO Auto-generated method stub
		//TestAVLRotations();
		//testAVLRandom();
		//testAVLVsplit();
		//testAVLGeneric();
		testRangeOnBigTrees();
		
		
	}
	
	/**
	 * 
	 */
	private static void testRangeOnBigTrees() {
		// TODO Auto-generated method stub
		AVL<DumpableNamedPoint> tree = new AVL<DumpableNamedPoint>();
		
		for (int i=0; i<50; i=i+2) {
			tree.insert(i, new DumpableNamedPoint(i,i+1,""));
		}
		
		TreePrinter.printTree(tree, 10);
		
		//int a=10;int b=30;
		int a=30;int b=42;
		AVLNode<DumpableNamedPoint> nodeVSplit = tree.getRoot().findVSplit(a, b);
		System.out.println(String.format("vSplit (a=%d,b=%d): %s", a,b,nodeVSplit.getData()));
		
		LinkedList<DumpableNamedPoint> m1 = tree.Range(a, b);
		System.out.print(String.format("Range (a=%d,b=%d): ", a, b));
		
		System.out.println(TestSpatialAVL.dumpLinkedList(m1));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
		
	}

	/**
	 * 
	 */
	private static void testAVLVsplit() {
		
		AVL<DumpableNamedPoint> tree = new AVL<DumpableNamedPoint>();
		tree.insert(0, new DumpableNamedPoint(0,0,""));
		tree.insert(0, new DumpableNamedPoint(0,1,""));
		tree.insert(0, new DumpableNamedPoint(0,2,""));
		tree.insert(1, new DumpableNamedPoint(1,0,""));
		tree.insert(1, new DumpableNamedPoint(1,1,""));
		tree.insert(1, new DumpableNamedPoint(1,2,""));
		tree.insert(2, new DumpableNamedPoint(2,0,""));
		tree.insert(2, new DumpableNamedPoint(2,1,""));
		tree.insert(2, new DumpableNamedPoint(2,2,""));
		tree.insert(3, new DumpableNamedPoint(3,0,""));
		tree.insert(3, new DumpableNamedPoint(3,1,""));
		tree.insert(3, new DumpableNamedPoint(3,2,""));
		
		TreePrinter.printTree(tree);
		
		AVLNode<DumpableNamedPoint> root = tree.getRoot();
		//System.out.println(root.findVSplit(0, 3).getData().toString());
		//System.out.println(root.findVSplit(0, 1).getData().toString());
		//System.out.println(root.findVSplit(1, 2).getData().toString());
		//System.out.println(root.findVSplit(5, 8).getData().toString()); //returns last visited node
		//System.out.println(root.findVSplit(2, 2).getData().toString()); //returns last visited node
		//System.out.println(root.findVSplit(0, 0).getData().toString()); //returns last visited node
		System.out.println(root.findVSplit(3, 3).getData().toString()); //returns last visited node
		
		
		
	}
	

	

	/**
	 * 
	 */
	private static void testAVLRandom() {
		AVL<ObjectWithCoordinates> tree = new AVL<ObjectWithCoordinates>();
		int TOTAL = 9; 
		for (int i=0;i<Math.pow(2, 3);i++) {
			int X = (int)(Math.random()*TOTAL); //0..TOTAL-1
			int Y = (int)(Math.random()*TOTAL); //0..TOTAL-1
		
			tree.insert(Y, new DumpableNamedPoint(X,Y,""));
		}
		
		
		TreePrinter.printTree(tree);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
		
	}

	private static void testAVLGeneric() {
		// TODO Auto-generated method stub
		//TestAVLRotations();
		
		Func<ObjectWithCoordinates, Integer> funcX = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getX();
			}
		};
		
		Func<ObjectWithCoordinates, Integer> funcY = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getY();
			}
		};
		
		AVL<ObjectWithCoordinates> tree = new AVL<ObjectWithCoordinates>();
		
		Func<ObjectWithCoordinates, Integer> func = funcX;
		//Func<ObjectWithCoordinates, Integer> func = funcY;
		
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
		
		TreePrinter.printTree(tree);
		
		
		//int a=0;int b=0; 
		//int a=1;int b=3;
		//int a=1;int b=2;
		//int a=3;int b=3;
		//int a=0;int b=3;
		//int a=0;int b=50;
		int a=-1;int b=Integer.MAX_VALUE;
		AVLNode<ObjectWithCoordinates> nodeVSplit = tree.getRoot().findVSplit(a, b);
		System.out.println(String.format("vSplit (a=%d,b=%d): %s", a,b,nodeVSplit.getData()));
		
		LinkedList<ObjectWithCoordinates> m1 = tree.Range(a, b);
		System.out.print(String.format("Range (a=%d,b=%d): ", a, b));
		
		System.out.println(TestSpatialAVL.dumpLinkedList(m1));
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
		
		
	}

	/**
	 * @return
	 */
	private static void TestAVLRotations() {
		Func<ObjectWithCoordinates, Integer> funcX = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getX();
			}
		};
		
		Func<ObjectWithCoordinates, Integer> funcY = new Func<ObjectWithCoordinates, Integer>() {
			
			@Override
			public Integer call(ObjectWithCoordinates target) {
				return target.getY();
			}
		};
		
		Func<ObjectWithCoordinates, Integer> func = funcX;
		
		AVL<ObjectWithCoordinates> tree;
		ObjectWithCoordinates dp;
		
		System.out.println("LR");
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		TreePrinter.printTree(tree);
		System.out.println(TestBase.dumpLinkedList(tree.Range(Integer.MIN_VALUE, Integer.MAX_VALUE)));
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
		
		System.out.println("RR");
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		TreePrinter.printTree(tree);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
		
		System.out.println("RR->LR");
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		TreePrinter.printTree(tree);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
		
		
		System.out.println("LR->RR");
		tree = new AVL<ObjectWithCoordinates>();
		dp = new DumpableNamedPoint(2,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(0,-1,"");
		tree.insert(func.call(dp), dp);
		dp = new DumpableNamedPoint(1,-1,"");
		tree.insert(func.call(dp), dp);
		TreePrinter.printTree(tree);
		
		TestBase.validateAVLHeightPolicyAndHierarchy(tree);
	}

	/**
	 * 
	 */
	private static void testHashtable() {
		// TODO Auto-generated method stub
		int M = 2;
		HashTable h = new HashTable(M);
		h.insert(new NamedPoint(0,0,""));
		
		h.insert(new NamedPoint(0,1,""));
		h.insert(new NamedPoint(0,2,""));
		h.insert(new NamedPoint(1,0,""));
		h.insert(new NamedPoint(1,1,""));
		h.insert(new NamedPoint(1,2,""));
		h.insert(new NamedPoint(2,0,""));
		h.insert(new NamedPoint(2,1,""));
		h.insert(new NamedPoint(2,2,""));
		h.insert(new NamedPoint(3,0,""));
		h.insert(new NamedPoint(3,1,""));
		h.insert(new NamedPoint(3,2,""));
		
		System.out.println(h.search(2, 2));
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}