/**
 * An infrastructure AVL tree node implementation
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 * @param <T>
 */
public class AVLNode<T> {

	private int key;
	private T data;
	private AVLNode<T> leftChild;
	private AVLNode<T> rightChild;
	private AVLNode<T> father;
	private int height;
	/**
	 * owner tree
	 */
	private AVL<T> tree;
		
	public AVLNode(AVL<T> tree, int key, T data) {
		this.tree = tree;
		this.key = key;
		this.data = data;
		
		this.leftChild = null;
		this.rightChild = null;
		this.father = null;
		
		this.height = 1;
	}
	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public AVLNode<T> getLeftChild(){
		return leftChild;
	}
	
	public void setLeftChild(AVLNode<T> value){
		leftChild = value;
	}
	
	public AVLNode<T> getRightChild(){
		return rightChild;
	}
	
	public void setRightChild(AVLNode<T> value){
		rightChild = value;
	}
	
	public AVLNode<T> getFather(){
		return father;
	}
	
	public void setFather(AVLNode<T> value){
		father = value;
	}
	
	public int getKey(){
		return key;
	}
	
	public T getData(){
		return data;
	}
	
	@Override
	public String toString() {
		
		String s = "";
		
		if (getLeftChild() != null){
			s+="(";
			s+=getLeftChild().toString();
			s+=")";
		}
		s+=getKey();
		
		if (getRightChild() != null){
			s+="(";
			s+=getRightChild().toString();
			s+=")";
		}
		
		return s;
	}


	/**
	 * checking if the node follows the AVL rule
	 */
	public boolean isBalanced() {
		int left = 0;  
		int right = 0;
		
		if (leftChild != null)
			left = leftChild.height;
		
		if (rightChild != null)
			right = rightChild.height;
		return Math.abs(left-right) <= 1;
	}


	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}


	/**
	 * sets node height to be max(l,r)+1
	 */
	public void updateHeight() {
		int left = 0;  
		int right = 0;
		
		if (leftChild != null)
			left = leftChild.height;
		
		if (rightChild != null)
			right = rightChild.height;
		
		height = Math.max(left, right)+1;
		
	}


	/**
	 * gets the highest child among the node children
	 * @return
	 */
	public AVLNode<T> getHighestChild() {
		if (leftChild == null) return rightChild;
		if (rightChild == null) return leftChild;
		
		if (leftChild.height >= rightChild.height)
			return leftChild;
		
		return rightChild;
	}
	
	/**
	 * finds vSplit/LeastCommonAncestor
	 * assumes a<b
	 * if a or b are not items of the tree, looks for closer items (in range)
	 * 
	 *  O(logn)	
	 * @param a
	 * @param b
	 * @return the Least Common Ancestor Node a.k.a vSplit Node
	 */
	public AVLNode<T> findVSplit(int a, int b) {
		if (this.isLeaf()) {
			//We traversed all tree and did not find vSplit/LeastCommonAncestor
			//this is the closest
			return this;
		}
	    
		if (tree.getComparator().compare(b, getKey()) < 0) { 
	    	//b<data, hence both nodes are on the left
	        if (leftChild == null) return null;
	        
	        //traverse left
	    	return leftChild.findVSplit(a, b);    
	    } else { //data<b
		    if (tree.getComparator().compare(a, getKey()) > 0) {
		    	//a>data, hence both nodes are on the right
		    	if (rightChild == null) return null;

		    	//traverse right
		    	return rightChild.findVSplit(a, b);    
		    } else { 
		        // the nodes are on separate branches, this is the vSplit
		        return this;        
		    }
	    }
	}
}
