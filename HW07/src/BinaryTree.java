import java.util.ArrayList;



/**
 * BinaryTree class for the CSE222 - Data Structures and Algorithms course on
 * Gebze Technical University. This class written with the help of Wolfgang &
 * Koffman's Data Structures and Algorithms Using Java book to learn internal
 * of binary trees and use whenever needed on homeworks
 *
 * @author Deniz Can Erdem Yılmaz
 *         30.04.2017
 */
public class BinaryTree <R>
{
	/** Reference of root node */
	protected Node<R> root;
	
	
	
	/**
	 * No parametered constructor
	 * Initializes an empty tree
	 */
	public BinaryTree()
	{
		root = null;
	}
	
	
	
	/**
	 * Node<R> type parametered constructor
	 * Initializes tree with root node
	 *
	 * @param rootNode Node to be root
	 */
	public BinaryTree(Node<R> rootNode)
	{
		root = rootNode;
	}
	
	
	
	/**
	 * R and BinaryTree type parametered constructor
	 * Initializes a tree with given item at root and other two trees on
	 * left and right attributes
	 *
	 * @param item  Root data
	 * @param left  Left sub-tree
	 * @param right Right sub-tree
	 */
	public BinaryTree(R item, BinaryTree<R> left, BinaryTree<R> right)
	{
		root = new Node<R>(item);
		
		
		if (left != null)
			root.left = left.root;
		
		if (right != null)
			root.right = right.root;
	}
	
	
	
	/**
	 * Creates a new binary tree from the left child of root to return user
	 *
	 * @return Left child of root as BinaryTree
	 */
	public BinaryTree<R> getLeftSubtree()
	{
		if (root != null && root.left != null)
			return new BinaryTree<R>(root.left);
		else
			return null;
	}
	
	
	
	/**
	 * Creates a new binary tree from the right child of root to return user
	 *
	 * @return Right child of root as BinaryTree
	 */
	public BinaryTree<R> getRightSubtree()
	{
		if (root != null && root.right != null)
			return new BinaryTree<R>(root.right);
		else
			return null;
	}
	
	
	
	/**
	 * Method to check if root has any child
	 *
	 * @return True if root has no child, else false
	 */
	public boolean isLeaf()
	{
		if (root == null)
			System.out.println("NullPointerException on isLeaf() method of BinaryTree");
		
		return (root.right == null && root.left == null);
	}
	
	
	
	/**
	 * toString method override from Object class
	 * Constructs a string representation of tree to print
	 *
	 * @return Tree structure as string representation
	 */
	@Override
	public String toString()
	{
		ArrayList<Node<R>> nodeList = new ArrayList<Node<R>>();
		StringBuilder builder = new StringBuilder();
		
		preOrderHelper(root, 0, builder);
		return builder.toString();
	}
	
	
	
	/**
	 * Helper recursive method for toString to pre order traverse the tree
	 *
	 * @param node    Node to print
	 * @param depth   Depth of the node
	 * @param builder String builder to append node
	 */
	private void preOrderHelper(Node<R> node, int depth, StringBuilder builder)
	{
		for (int i = 0; i < depth; i++)
			builder.append("    ");
		
		if (node == null)
			builder.append("null\n");
		
		else
		{
			builder.append(node.toString());
			builder.append("\n");
			preOrderHelper(node.left, depth + 1, builder);
			preOrderHelper(node.right, depth + 1, builder);
		}
	}
	
	
	
	/**
	 * Inner node class to construct binary tree
	 *
	 * @param <R> Generic type
	 */
	protected static class Node <R>
	{
		/** Data storage field */
		protected R data;
		/** Reference for left child */
		protected Node<R> left;
		/** Reference for right child */
		protected Node<R> right;
		
		
		
		/**
		 * No parameter constructor, calls R type parametered constructor with
		 * null item
		 */
		public Node()
		{
			this(null);
		}
		
		
		
		/**
		 * R type parametered constructor to initialize data with given item
		 *
		 * @param item Data to store
		 */
		public Node(R item)
		{
			data = item;
			left = null;
			right = null;
		}
		
		
		
		/**
		 * toString method override from Object class to print data. Generic
		 * type should also override toString method for better results
		 *
		 * @return String representation of data;
		 */
		@Override
		public String toString()
		{
			return data.toString();
		}
	}
}
