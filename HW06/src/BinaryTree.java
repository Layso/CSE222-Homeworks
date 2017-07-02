import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;



/**
 * HW05 for Gebze Technical University
 * Binary Tree class to implement Iterable interface and implement a pre order
 * traversing iterator for tree. Includes nested Node and TreeIterator classes
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         03.04.2017.
 */
public class BinaryTree <R> implements Iterable<R>
{
	/** Starting point of tree nodes, head */
	protected Node<R> root;
	/** Height of the tree */
	protected int     level;
	/** Number of elements that current level holds */
	protected int     levelSize;
	/** Total number of elements that current level can hold */
	protected int     levelCap;
	/** Total number of elements that tree holds currently */
	protected int     totalElement;
	
	
	
	/**
	 * No parameter constructor
	 * Initializes the data members with empty tree values
	 */
	public BinaryTree()
	{
		root = null;
		level = 1;
		levelSize = 0;
		totalElement = 0;
		levelCap = (int) Math.pow(2, level - 1);
	}
	
	
	
	/**
	 * R type parametered constructor
	 * Initializes data members and the tree with root
	 *
	 * @param item - Data to hold on root
	 */
	public BinaryTree(R item)
	{
		root = new Node<R>(item);
		level = 1;
		levelSize = 1;
		totalElement = 1;
		levelCap = (int) Math.pow(2, level - 1);
	}
	
	
	
	public BinaryTree(Node<R> node)
	{
		root = node;
	}
	
	
	
	public BinaryTree(R item, BinaryTree<R> left, BinaryTree<R> right)
	{
		root = new Node<R>(item);
		
		if (left == null)
			root.left = null;
		
		else
			root.left = left.root;
		
		
		if (right == null)
			root.right = null;
		
		else
			root.right = right.root;
	}
	
	
	
	public BinaryTree<R> getRightSubtree()
	{
		if (root.right != null)
		{
			BinaryTree<R> left   = new BinaryTree<>(root.right.left);
			BinaryTree<R> right  = new BinaryTree<>(root.right.right);
			BinaryTree<R> result = new BinaryTree<>((R) root.right.GetData(), left, right);
			return result;
		}
		
		else
			return null;
	}
	
	
	
	public BinaryTree<R> getLeftSubtree()
	{
		if (root.left != null)
		{
			BinaryTree<R> left   = new BinaryTree<>(root.left.left);
			BinaryTree<R> right  = new BinaryTree<>(root.left.right);
			BinaryTree<R> result = new BinaryTree<>((R) root.left.GetData(), left, right);
			return result;
		}
		
		else
			return null;
	}
	
	
	
	public boolean isLeaf()
	{
		return (getLeftSubtree() == null && getRightSubtree() == null);
	}
	
	
	
	/**
	 * Function to add new elements to the tree
	 *
	 * @param item - Data to add to tree
	 */
	public void add(R item)
	{
		/* Checking if all leaves are full to do changes */
		if (levelSize == levelCap)
		{
			++level;
			levelCap = (int) Math.pow(2, level - 1);
			levelSize = 0;
		}
		
		
		/* First element (root) insertion */
		if (root == null)
		{
			root = new Node<R>(item);
			++levelSize;
		}
		
		/* Rest of the element insertions */
		else
		{
			AddToTree(root, level - 2, levelSize, new Node<R>(item));
		}
		
		/* Size incrementation */
		++totalElement;
	}
	
	
	
	public R getData()
	{
		return root.GetData();
	}
	
	
	
	/**
	 * totalElement getter
	 *
	 * @return The total number of elements on tree currently
	 */
	public int size()
	{
		return totalElement;
	}
	
	
	
	/**
	 * iterator function override from Iterable interface
	 * Creates a new iterator bond to the tree. Iterator traverses nodes in pre
	 * order
	 *
	 * @return An iterator to traverse tree pre-ordered
	 */
	@Override
	public Iterator iterator()
	{
		return new TreeIterator();
	}
	
	
	
	/**
	 * add(R item) function helper for the recursive insertion operation
	 *
	 * @param head     - Currently standing node on tree
	 * @param step     - Needed number of steps to go down
	 * @param sizeLeft - Size left on the deepest node level
	 * @param item     - Element to insert into tree
	 */
	private void AddToTree(Node<R> head, int step, int sizeLeft, Node<R> item)
	{
		/* If all steps have passed, insert the element to head as child */
		if (step == 0)
		{
			/* To the right node */
			if (sizeLeft%2 == 1)
			{
				head.right = item;
			}

			/* To the left node */
			else
			{
				head.left = item;
			}
			
			/* Increasing the number of elements on current level due to
			   insertion */
			++levelSize;
		}
		
		
		/* Calculating the location of new place and doing a recursive call */
		else if (Math.pow(2, step - 1) >= sizeLeft)
		{
			AddToTree(head.left, step - 1, sizeLeft, item);
		}
		
		else
		{
			AddToTree(head.right, step - 1, (int) (sizeLeft - Math.pow(2, step - 1)), item);
		}
	}
	
	
	
	/**
	 * Nested node class to store data inside the tree class. Each node has 2
	 * child part as left and right
	 *
	 * @param <R> Type of BinaryTree
	 */
	protected class Node <R>
	{
		/** Data of the node */
		private   R    data;
		/** Address of left child */
		protected Node left;
		/** Address of right child */
		protected Node right;
		
		
		
		/**
		 * R type parametered constructor
		 * Forces user to give data while initializing new node, empty nodes can
		 * not be instantiated due to loss of no parameter constructor
		 *
		 * @param item - Data to store
		 */
		public Node(R item)
		{
			left = null;
			right = null;
			data = item;
		}
		
		
		
		/**
		 * Getter to reach the data of the node
		 *
		 * @return Data that node holds
		 */
		public R GetData()
		{
			return data;
		}
		
		
		
		/**
		 * Setter to change data of the node if necessary. Remove function is
		 * disabled on BinaryTree class. Users have this function to change
		 * a node
		 *
		 * @param item - New data to change the old one
		 */
		public void SetData(R item)
		{
			data = item;
		}
		
		
		
		@Override
		public String toString()
		{
			return data.toString();
		}
	}
	
	
	
	
	
	/**
	 * Nested iterator class to iterate the BinaryTree structure with
	 * pre-ordered traversal
	 */
	private class TreeIterator implements Iterator
	{
		/** Data member as stack to supply pre-order traversal */
		Stack<Node<R>> treeStack;
		
		
		
		/**
		 * No parameter constructor
		 * Initializes a new iterator and pushes the root to the stack to
		 * traverse the tree. If tree is empty (root is null) only initializes
		 * the stack
		 */
		public TreeIterator()
		{
			treeStack = new Stack<Node<R>>();
			
			if (root != null)
				treeStack.push(root);
		}
		
		
		
		/**
		 * hasNext() override from Iterator interface
		 * Controls the stack to determine if iterator has more elements to
		 * traverse the tree
		 *
		 * @return True if there are more elements, false if not
		 */
		@Override
		public boolean hasNext()
		{
			return !treeStack.empty();
		}
		
		
		
		/**
		 * next() override from Iterator interface
		 * Traverses the tree as pre-ordered
		 *
		 * @return Next element on tree
		 * @throws NoSuchElementException if stack is empty
		 */
		@Override
		public Object next() throws NoSuchElementException
		{
			/* Controlling stack to prevent run-time errors */
			if (treeStack.empty())
			{
				throw new NoSuchElementException();
			}
			
			
			/* Taking next node from stack */
			Node<R> current = treeStack.pop();
			
			/* Pushing stack in right-left order to process left tree on next
			   pop attemp and right node when the left is done */
			if (current.right != null)
			{
				treeStack.push(current.right);
			}
			if (current.left != null)
			{
				treeStack.push(current.left);
			}
			
			/* Returning the current node's data */
			return current.GetData();
		}
		
		
		
		/**
		 * remove() function override from Iterator interface
		 * This function is unsupported
		 *
		 * @throws UnsupportedOperationException - If called
		 */
		@Override
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}
	}
}