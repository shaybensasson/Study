/**
 * implementing MyInterface for GUI usage: executing range queries in various ways
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
public class StudentSolution  implements MyInterface{

	/**
	 * AVL tree that is sorted by X coordinates, used in 1st and 2nd solutions 
	 */
	private AVL<NamedPoint> treeX;
	/**
	 * AVL tree that is sorted by Y coordinates, used in 1st and 2nd solutions
	 */
	private AVL<NamedPoint> treeY;
	
	/**
	 * AVL tree that is sorted by X coordinates, used in 3rd solution
	 */
	private AVL<PointFor3rdSolution> treeXFor3rd;
	/**
	 * AVL tree that is sorted by Y coordinates, used in 3rd solution
	 */
	private AVL<PointFor3rdSolution> treeYFor3rd;
	
	/**
	 * counts the keys added inserted from file
	 */
	private int counter = 0;
	/**
	 * A structure that aids merging of queries on X and Y sorted trees, used in 3rd solution
	 */
	private int[] merger;
	
	/**
	 * a timestamp used to distinguish sequential iterations data,used in 3rd solution
	 */
	private int mergerTimeStamp = 0;
	
	public StudentSolution() {
		treeX = new AVL<NamedPoint>();
		treeY = new AVL<NamedPoint>();
		
		treeXFor3rd = new AVL<PointFor3rdSolution>();
		treeYFor3rd = new AVL<PointFor3rdSolution>();
	}
	
	/**
	 * inserts data into the structures
	 */
	@Override
	public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
		NamedPoint np = new NamedPoint(objectX, objectY, objectName);
		treeX.insert(objectX, np);
		treeY.insert(objectY, np);
		
		PointFor3rdSolution p = new PointFor3rdSolution(objectX, objectY, objectName, counter);
		treeXFor3rd.insert(objectX, p);
		treeYFor3rd.insert(objectY, p);
		
		counter++;
	}
	
	/**
	 * performs 2d range query
	 * time complexity:
	 * 	average: O(m1+m2+logn)
	 * 	worst: O(m1*m2+logn)
	 * space complexity: O(m1+m2)
	 */
	@Override
	public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX,
			int rightBottomY) {
		//perform range queries on both trees
		LinkedList<NamedPoint> m1 = treeX.Range(leftTopX, rightBottomX);
		LinkedList<NamedPoint> m2 = treeY.Range(leftTopY, rightBottomY);
		Link<NamedPoint> tmp;
				
		if (m1.getCount() == 0 || m2.getCount() == 0)
			return new String[0];
		
		//insert the first query result into a hashtable
		HashTable h = new HashTable(m1.getCount());
		tmp = m1.getFirst();
		while (tmp != null) {
			h.insert(tmp.getData());
			tmp = tmp.getNext();
		}
				
		//merge both queries results into a single array
		ObjectWithCoordinates[] merged = new ObjectWithCoordinates[Math.min(m1.getCount(), m2.getCount())]; 
		int items = 0;
		tmp = m2.getFirst();
		while (tmp != null) {
			ObjectWithCoordinates owc = tmp.getData();
			//check if m2 data exists in the hashtable (m1 keys were stored there)
			if (h.search(owc.getX(), owc.getY()) != null) {
				merged[items] = owc;
				items++;
			}
			tmp = tmp.getNext();
		}
		
		if (items == 0)
			return new String[0];
		
		//truncate the result
		String[] res = new String[items];
		for (int i=0;i<items; i++) {
			res[i] = merged[i].toString();
		}
		return res;
	}

	/**
	 * performs 2d range query
	 * time complexity:
	 * 	O(min(m1,m2)*log(max(m1,m2))+logn)
	 * space complexity: O(m1+m2)
	 */
	@Override
	public String[] secondSolution(int leftTopX, int leftTopY,
			int rightBottomX, int rightBottomY) {
		//perform range queries on both trees
		LinkedList<NamedPoint> m1 = treeX.Range(leftTopX, rightBottomX);
		LinkedList<NamedPoint> m2 = treeY.Range(leftTopY, rightBottomY);
		Link<NamedPoint> tmp;
		
		if (m1.getCount() == 0 || m2.getCount() == 0)
			return new String[0];
		
		//determine results sizes and choose a comparator accordingly
		ObjectWithCoordinatesComparator comp;
		LinkedList<NamedPoint> min, max;
		if (m1.getCount() < m2.getCount()) {
			min = m1; max = m2;
			comp = new ObjectWithCoordinatesByYThenXComparator();
		}
		else {
			min = m2; max = m1;
			comp = new ObjectWithCoordinatesByXThenYComparator();
		}
		
		//O(max)
		ObjectWithCoordinates[] maxArr = convertLinkedListToArray(max);
		
		//merge both queries results into a single array
		ObjectWithCoordinates[] merged = new ObjectWithCoordinates[Math.min(m1.getCount(), m2.getCount())]; 
		int items = 0;
		tmp = min.getFirst();
		//O(min)
		while (tmp != null) {
			ObjectWithCoordinates owc = tmp.getData();
			//binary search 'min' data in 'max'
			//O(log(max))
			if (containsBinarySearch(maxArr, owc, comp)) {
				merged[items] = owc;
				items++;
			}
			tmp = tmp.getNext();
		}
		
		if (items == 0)
			return new String[0];
		
		//truncate the result
		String[] res = new String[items];
		for (int i=0;i<items; i++) {
			res[i] = merged[i].toString();
		}
		return res;
	}
	
	/**
	 * converts a linkedList to an array
	 * @param linkedList
	 * @return
	 */
	private static ObjectWithCoordinates[] convertLinkedListToArray(
			LinkedList<NamedPoint> linkedList) {
		ObjectWithCoordinates[] arr = new ObjectWithCoordinates[linkedList.getCount()];
	    
	    int i = 0;
	    Link<NamedPoint> tmp = linkedList.getFirst();
	    while (tmp != null) {
			arr[i] = tmp.getData();
			i++;
			tmp = tmp.getNext();
		}
	    
	    return arr;
	}
	
	//O(logn)
	private static boolean containsBinarySearch(ObjectWithCoordinates[] arr, ObjectWithCoordinates item, 
			ObjectWithCoordinatesComparator comp) {
	    int low = 0;
	    int high = arr.length-1;

	    while(low <= high) {
	      int middle = (low+high) /2; 
	      int c = comp.compare(item, arr[middle]); 
	      if (c > 0){
	        low = middle +1;
	      } else if (c < 0){
	        high = middle -1;
	      } else { // The element has been found
	        return true; 
	      }
	    }
	    return false;
	}
	
	boolean first3rdSolutionCall = true;
	
	/**
	 * performs 2d range query
	 * time complexity:
	 * 	O(m1+m2+logn)
	 * space complexity: O(n)
	 */
	@Override
	public String[] thirdSolution(int leftTopX, int leftTopY, int rightBottomX,
			int rightBottomY) {
		
		if (first3rdSolutionCall) { //first call
			//allocate an array in the size of the items
			//O(n)
			merger = new int[counter]; 
			
			first3rdSolutionCall = false;
		}
		
		//increase time stamp, so our data won't conflict with other executions
		mergerTimeStamp++;
				
		//perform range queries on both trees
		LinkedList<PointFor3rdSolution> m1 = treeXFor3rd.Range(leftTopX, rightBottomX);
		LinkedList<PointFor3rdSolution> m2 = treeYFor3rd.Range(leftTopY, rightBottomY);
		Link<PointFor3rdSolution> tmp;
		
		if (m1.getCount() == 0 || m2.getCount() == 0)
			return new String[0];
		
		//go thru each point in m1 and flag it using the time stamp 
		// on merger[i], where i is the point identifier/orderOfInsert
		tmp = m1.getFirst();
		while (tmp != null) {
			PointFor3rdSolution p = tmp.getData();
			merger[p.getOrderOfInsert()] = mergerTimeStamp;
			tmp = tmp.getNext();
		}
				
		//merge both queries results into a single array
		ObjectWithCoordinates[] merged = new ObjectWithCoordinates[Math.min(m1.getCount(), m2.getCount())]; 
		int items = 0;
		tmp = m2.getFirst();
		while (tmp != null) {
			PointFor3rdSolution p = tmp.getData();
			//if this point was flagged earlier, hence, it's part of the intersection
			if (merger[p.getOrderOfInsert()] == mergerTimeStamp) {
				merged[items] = p;
				items++;
			}
			
			tmp = tmp.getNext();
		}
		
		if (items == 0)
			return new String[0];
		
		//truncate the result
		String[] res = new String[items];
		for (int i=0;i<items; i++) {
			res[i] = merged[i].toString();
		}
		return res;
	}
}
