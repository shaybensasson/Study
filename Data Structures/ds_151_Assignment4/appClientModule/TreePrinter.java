import java.util.Stack;

/**
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 * NO_SUBMIT
 */
class TreePrinter {

	public static <T>  void printTree(AVL<T> tree)
	{
		printTree(tree.getRoot(), 0);
	}
	
	public static <T>  void printTree(AVL<T> tree, int treePrintHeightAddition)
	{
		printTree(tree.getRoot(), treePrintHeightAddition);
	}
	
	public static <T>  void printTree(AVLNode<T> root, int treePrintHeightAddition)
	{
		Stack<AVLNode<T>> globalStack = new Stack<AVLNode<T>>();
		globalStack.push(root);	
		int emptyLeaf = (int)Math.pow(height(root)+treePrintHeightAddition, 2);
		boolean isRowEmpty = false;
		System.out.println("****......................................................****");
		while(isRowEmpty==false)
		{
			Stack<AVLNode<T>> localStack = new Stack<AVLNode<T>>();
			isRowEmpty = true;
			for(int j=0; j<emptyLeaf; j++)
				System.out.print(' ');
			while(globalStack.isEmpty()==false)
			{
				AVLNode<T> temp = globalStack.pop();
				if(temp != null)
				{
					System.out.print(String.format("%s^%d", temp.getData().toString(), temp.getHeight()));
					localStack.push(temp.getLeftChild());
					localStack.push(temp.getRightChild());
					if(temp.getLeftChild() != null ||temp.getRightChild() != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<emptyLeaf*2-2; j++)
					System.out.print(' ');
			}
			System.out.println();
			emptyLeaf /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		}
	System.out.println("****......................................................****");
	}
	

	public static <T> int height(AVLNode<T> root) {
		if (root == null) return -1;
		return heightRec(root);
	}
	
	public static <T> int heightRec(AVLNode<T> node) {
		int lh = -1;
		int rh = -1;
		if (node.getLeftChild() != null)
			lh = heightRec(node.getLeftChild());
		if (node.getRightChild() != null)
			rh = heightRec(node.getRightChild());
		return Math.max(lh, rh) + 1;
	}
	
	

}
