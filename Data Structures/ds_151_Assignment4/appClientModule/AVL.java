import java.util.Comparator;

/**
 * An infrastructure AVL tree implementation
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 * @param <T>
 */
public class AVL<T> {
	
	private AVLNode<T> root;
	
	private int count;
		
	private static final Comparator<Integer> keyComparator = new KeyComparator();
			
	/**
	 * AVL tree insertion ensuring tree is balanced using rotations
	 * O(logn)
	 * @param key
	 * @param data
	 */
	public void insert(int key, T data){
		count++;
		if (root == null)
			root = new AVLNode<T>(this, key, data);
		else
			insert(root, key, data);
	}

	/**
	 * AVL tree insertion helper
	 * inserting key/data under 'node'
	 */
	private void insert(AVLNode<T> node, int key, T data) {
		boolean hadChildrenBeforeInsert = !node.isLeaf();
		
		boolean createdNode = false;
		int c = keyComparator.compare(key, node.getKey());
		if (c == 0)
			return;
		
		AVLNode<T> newNode = new AVLNode<T>(this, key, data);
		
		//BST insertion logic
		if (c < 0)
			if (node.getLeftChild() == null) {
				node.setLeftChild(newNode);
				createdNode = true; 
				}
			else
				insert(node.getLeftChild(), key, data);
		else if (node.getRightChild() == null) {
			node.setRightChild(newNode);
			createdNode = true;
		}
		else
			insert(node.getRightChild(), key, data);
		
		if (createdNode) {
			
			//attach to parent
			newNode.setFather(node);
			
			if (!hadChildrenBeforeInsert) { 
				//height changed due to insertion
				node.setHeight(2); //we've added a lonely child to a parent
				
				//ensure parent node is balanced
				BalanceNode(node.getFather());
			}
		}
		
		
	}

	/**
	 * Balancing a given node using rotations
	 */
	private void BalanceNode(AVLNode<T> node) {
		if (node == null) return;
		
		//sets node height to be max(l,r)+1
		node.updateHeight();
		
		if (node.isBalanced()) {
			//looking for the first unbalanced node
			BalanceNode(node.getFather());
		}
		else {
			//perform appropriate rotations
			AVLNode<T> z = node;
			AVLNode<T> y = z.getHighestChild();
			AVLNode<T> x = y.getHighestChild();
			
			if (x == y.getRightChild() && y == z.getRightChild()) {
				//Single Left-Right Rotation
				LR(z);
			}
			else {
				if (x == y.getLeftChild() && y == z.getLeftChild()) {
					//Single Right-Right Rotation
					RR(z);
				}
				else
					//Double Rotation
					if (x == y.getRightChild() && y == z.getLeftChild()) {
						
						LR(y);
						RR(z);
					}
					else {
						RR(y);
						LR(z);
					}
			}
			
		}
		
	}

	/**
	 * Single Left-Right Rotation
	 * @param z
	 */
	private void LR(AVLNode<T> z) {
		AVLNode<T> y = z.getRightChild();
		@SuppressWarnings("unused")
		AVLNode<T> x = y.getRightChild();
		
		AVLNode<T> ancestor = z.getFather();
		if (ancestor != null)
		{
			if (z == ancestor.getLeftChild())
				ancestor.setLeftChild(y);
			else
				ancestor.setRightChild(y);
		}
		else //we are changing the root
		{
			root = y;
		}
		
		AVLNode<T> T2 = y.getLeftChild();
		
		y.setFather(z.getFather());
		
		y.setLeftChild(z);
		z.setFather(y);
		
		z.setRightChild(T2);
		
		if (T2 != null)
			T2.setFather(z);
		
		//Update Heights
		z.updateHeight();
		y.updateHeight();
	}
	
	/**
	 * Single Right-Right Rotation
	 * @param z
	 */
	private void RR(AVLNode<T> z) {
		AVLNode<T> y = z.getLeftChild();
		@SuppressWarnings("unused")
		AVLNode<T> x = y.getLeftChild();
		
		AVLNode<T> ancestor = z.getFather();
		if (ancestor != null)
		{
			if (z == ancestor.getRightChild())
				ancestor.setRightChild(y);
			else
				ancestor.setLeftChild(y);
		}
		else //we are changing the root
		{
			root = y;
		}
		
		AVLNode<T> T2 = y.getRightChild();
		
		y.setFather(z.getFather());
		
		y.setRightChild(z);
		z.setFather(y);
		
		z.setLeftChild(T2);
		if (T2 != null)
			T2.setFather(z);
		
		//Update Heights
		z.updateHeight();
		y.updateHeight();
		
	}

	/**
	 * finds a key in the structure
	 * O(logn)
	 * @param key
	 * @return
	 */
	public T search(int key){
		return search(root, key);
	}

	/**
	 * finds a key in the structure, looking in node and its descendants
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	private T search(AVLNode<T> node, int key) {
		T res = null;
		
		int c = keyComparator.compare(key, node.getKey());
		if (c == 0)
			res = node.getData();
		else if (c < 0)
		{
			if (node.getLeftChild() != null) 
				res = search(node.getLeftChild(), key);
		}
		else {
			if (node.getRightChild() != null) 
				res = search(node.getRightChild(), key);
		}
		return res;
	}

	/**
	 * returns the root node
	 * @return
	 */
	public AVLNode<T> getRoot(){
		return this.root;
	}
	
	/*
	 * assume a<b
	 * Returns a sorted linked list of the object with keys in range [a,b]
	 * O(logn+m), where m are the nodes that satisfy the range query
	 */
	public LinkedList<T> Range(int a, int b) {
		LinkedList<T> linkedList = new LinkedList<>();
		if (getRoot() != null) {

			//O(logn)
			AVLNode<T> vsplitNode = getRoot().findVSplit(a, b);
			if (vsplitNode != null) { // node not in tree or not in range

				//O(logn+m)
				getPointsInRange(a, b, vsplitNode, linkedList);
			}
		}
		
		return linkedList;
	}
	
	/**
	 * Returns a sorted linked list of the object with keys in range [a,b], given a vsplit node
	 * 
	 * @param a
	 * @param b
	 * @param nodeVsplit
	 * @param linkedList
	 */
	private void getPointsInRange(int a, int b, 
			AVLNode<T> nodeVsplit, LinkedList<T> linkedList) {
		if (nodeVsplit.isLeaf())
		{
			if (isInRange(nodeVsplit.getKey(), a, b))
			{
				//report point
				linkedList.addLast(nodeVsplit.getData());
			}
		}
		else
		{
			AVLNode<T> leftNode = nodeVsplit.getLeftChild();
			if (leftNode != null)
			{
				//go left
				getPointsLeftToVSplit(a,b,leftNode, linkedList);
			}
						
			//report current node
			if (isInRange(nodeVsplit.getKey(), a, b))
			{
				//report point
				linkedList.addLast(nodeVsplit.getData());
			}
						
								
			AVLNode<T> rightNode = nodeVsplit.getRightChild();
			if (rightNode != null)
			{
				//go right
				getPointsRightToVSplit(a,b,rightNode, linkedList);
			}
			
		}
	}
		
		
	/**
	 * Returns a sorted linked list of the object with keys in range [a,b], traversing left to a vsplit node
	 * 
	 * @param a
	 * @param b
	 * @param node
	 * @param linkedList
	 */
	private void getPointsLeftToVSplit(int a, int b,
			AVLNode<T> node,
			LinkedList<T> linkedList) {
		
		if (node.isLeaf()) {
			//report node
			if (isInRange(node.getKey(), a, b))
			{
				//report point
				linkedList.addLast(node.getData());
			}
		}
		else {
			if (keyComparator.compare(a, node.getKey()) < 0) {
				//a<v
													
				if (node.getLeftChild() != null)
					getPointsLeftToVSplit(a, b, node.getLeftChild(), linkedList);
				
				//report node
				if (isInRange(node.getKey(), a, b))
				{
					//report point
					linkedList.addLast(node.getData());
				}
				
				//reporting all in range right subtree linkedList in an inorder fashion
				if (node.getRightChild() != null)
					getSubtreePointsInRange(node.getRightChild(), a, b, linkedList);
				
			}
			else if (keyComparator.compare(a, node.getKey()) > 0) {
				//v<a
				if (node.getRightChild() != null)
					getPointsLeftToVSplit(a, b, node.getRightChild(), linkedList);
			}
			else { //v==a
				//report point
				linkedList.addLast(node.getData());
								
				//reporting all in range right subtree linkedList in an inorder fashion
				if (node.getRightChild() != null)
					getSubtreePointsInRange(node.getRightChild(), a, b, linkedList);
			}
		}
	}
	

	/**
	 * Returns a sorted linked list of the object with keys in range [a,b], traversing right to a vsplit node
	 * 
	 * @param a
	 * @param b
	 * @param node
	 * @param linkedList
	 */
	private void getPointsRightToVSplit(int a, int b,
			AVLNode<T> node,
			LinkedList<T> linkedList) {
		
		if (node.isLeaf()) {
			//report node
			if (isInRange(node.getKey(), a, b))
			{
				//report point
				linkedList.addLast(node.getData());
			}
		}
		else {
		
			if (keyComparator.compare(b, node.getKey()) > 0) {
				//v<b
								
				//reporting all in range left subtree linkedList in an inorder fashion
				if (node.getLeftChild() != null)
					getSubtreePointsInRange(node.getLeftChild(), a, b, linkedList);
									
				//report node
				if (isInRange(node.getKey(), a, b))
				{
					//report point
					linkedList.addLast(node.getData());
				}
				
				if (node.getRightChild() != null)
					getPointsRightToVSplit(a, b, node.getRightChild(), linkedList);
			}
			else if (keyComparator.compare(b, node.getKey()) < 0) { //b<v
				if (node.getLeftChild() != null)
					getPointsRightToVSplit(a, b, node.getLeftChild(), linkedList);
			}
			else { //v==b
				//reporting all in range left subtree linkedList in an inorder fashion
				if (node.getLeftChild() != null)
					getSubtreePointsInRange(node.getLeftChild(), a, b, linkedList);
				
				//report point
				linkedList.addLast(node.getData());
			}
		}
	}
	
	/**
	 * fetches all T datas in the node subTree (including) that are in range [a,b] in an inorder fashion
	 * 
	 * O(logn)
	 * 
	 * @param a low range 1d coordinate
	 * @param b high range 1d coordinate
	 * @param linkedList the out result array
	 * @param size initial size
	 * @return size of linkedList we actually used out of allocated linkedList array
	 */
	void getSubtreePointsInRange(AVLNode<T> node, int a, int b, LinkedList<T> linkedList) {
		if (node.isLeaf()) { 
			if (this.isInRange(node.getKey(), a, b))
				//add date
				linkedList.addLast(node.getData());
			
		}
		else {
			if (node.getLeftChild() != null) //traverse left
			   getSubtreePointsInRange(node.getLeftChild(), a, b, linkedList);
			
			if (this.isInRange(node.getKey(), a, b)) {
				//report node
				linkedList.addLast(node.getData());
			}
			
			if (node.getRightChild() != null) //traverse right
				getSubtreePointsInRange(node.getRightChild(), a, b, linkedList);
		}
	}


	/**
	 * checks if a key is in range a<=key<=b
	 * O(1)
	 * 
	 * @param data
	 * @param a
	 * @param b
	 * @return
	 */
	protected boolean isInRange(int key, int a, int b) { 
		return (a<=key && key<=b); 		
	}

	
	/**
	 * the total nodes in the structure
	 * @return
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * comparator for the keys
	 * 
	 * @param a
	 * @param key
	 * @return
	 */
	public Comparator<Integer> getComparator() {
		return keyComparator;
	}

}
