import java.util.Iterator;
import java.util.LinkedList;

/**
 * An implementation of A hashtable implemented using chaining
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 */
public class HashTable {
	
    /*
     * the size of the table
     */
	public final int M;
    
    private LinkedList<ObjectWithCoordinates>[] table;

    @SuppressWarnings("unchecked")
	public HashTable(int M)
    {
        this.M = M;
        table = new LinkedList[M];
    }
    
    /**
     * the hash function
     * 
     * @param x
     * @param y
     * @return
     */
    private int h(int x, int y)
    {
    	/**
    	 * NOTE: here we support negative coordinates, because some time the GUI returns negative items,
    	 * which is probably a bug, but we support it in any way.
    	 */
    	return Math.abs(x+y) % M;
    }

    /**
     * looks for a given point in the structure
     * 
     * average: O(1) - according to 'simple uniform hashing' assumption
     * 
     * @param x
     * @param y
     * @return
     */
    public ObjectWithCoordinates search(int x, int y) {
        int hash = h(x,y);
        LinkedList<ObjectWithCoordinates> linkedList = table[hash];

        if (linkedList != null) {
	        //average: O(1), worst: O(n)
        	for (Iterator<ObjectWithCoordinates> iterator = linkedList.iterator(); iterator.hasNext();) {
        		ObjectWithCoordinates owc = iterator.next();
	        	if ((owc.getX() == x) && (owc.getY() == y))
	        		return owc;
	        }
        }
        
        return null;
    }

    /**
     * inserts a given point into the structure
     * 
     * O(1)
     * 
     * @param owc
     */
    public void insert(ObjectWithCoordinates object){
        int hash = h(object.getX(), object.getY());
        
        LinkedList<ObjectWithCoordinates> linkedList = table[hash];
        if (linkedList == null)
        {
            linkedList = new LinkedList<ObjectWithCoordinates>();
            table[hash] = linkedList;
        }
        
        //O(1)
    	linkedList.addFirst(object);
    }
    
	
}
