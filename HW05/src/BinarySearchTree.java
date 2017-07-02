import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * HW05 for Gebze Technical University
 * Binary Search Tree class to implement another iterator that traverses the
 * tree with level order. Overrides add(R item) function from super class
 * BinaryTree to add items with order. Includes levelOrderIterator class for
 * level order traverse
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         05.04.2017.
 */
public class BinarySearchTree <R extends Comparable> extends BinaryTree<R>
{
	/**
	 * No parameter constructor
	 * Calls super class' constructor
	 */
	public BinarySearchTree()
	{
		super();
	}
	
	
	
	/**
	 * R type parametered constructor
	 * Constructor to initialize tree with a root and calls super class'
	 * constructor
	 *
	 * @param item - Data to hold on root
	 */
	public BinarySearchTree(R item)
	{
		super(item);
	}
	
	
	
	/**
	 * Function to add new elements to tree as ordered
	 *
	 * @param item - Data to add to tree
	 */
	@Override
	public void add(R item)
	{
		/* Creates root if tree is empty */
		if (root == null)
			root = new Node<>(item);
		
		/* Calls helper function to add element in order */
		else
			addToSearchTree(root, item);
		
		/* Size incrementation */
		++totalElement;
	}
	
	
	
	/**
	 * add(R item) function helper for recursive insertion
	 *
	 * @param head - Start point of recursion
	 * @param item - Item to insert tree
	 */
	private void addToSearchTree(Node<R> head, R item)
	{
		/* If item is lesser than the current node's data continue with right */
		if (head.GetData().compareTo(item) < 0)
		{
			/* If current node's right is null, insert item */
			if (head.right == null)
				head.right = new Node<>(item);
			
			/* Else continue to traverse */
			else
				addToSearchTree(head.right, item);
		}
		
		/* If item is lesser than the node's data continue with left */
		else
		{
			/* If current node's left is null, insert item */
			if (head.left == null)
				head.left = new Node<>(item);
			
			/* Else continue to traverse */
			else
				addToSearchTree(head.left, item);
		}
	}
	
	
	
	/**
	 * Creates a new iterator that traverses the tree with level order
	 *
	 * @return An iterator to traverse tree level-ordered
	 */
	public Iterator levelIterator()
	{
		return new levelOrderIterator();
	}
	
	
	
	/**
	 * Nested iterator class to iterate the BinarySearchTree structure with
	 * level-ordered traversal
	 */
	private class levelOrderIterator implements Iterator
	{
		/** Data member as queue to supply level-order traversal */
		MyQueue<Node<R>> treeQueue;
		
		
		
		/**
		 * No parameter constructor
		 * Initializes the queue and inserts root node
		 */
		public levelOrderIterator()
		{
			treeQueue = new MyQueue<>();
			
			if (root != null)
				treeQueue.offer(root);
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
		
		
		
		/**
		 * next() override from Iterator interface
		 * Traverses the tree as level-ordered
		 *
		 * @return Next element on tree
		 * @throws NoSuchElementException if queue is empty
		 */
		@Override
		public Object next() throws NoSuchElementException
		{
			/* Controlling if queue is empty */
			if (treeQueue.size() == 0)
			{
				throw new NoSuchElementException();
			}
			
			
			/* Continuing the traversal */
			Node<R> current = treeQueue.poll();
			
			if (current.left != null)
				treeQueue.offer(current.left);
			
			if (current.right != null)
				treeQueue.offer(current.right);
			
			
			/* Returning the current node's data */
			return current.GetData();
		}
		
		
		
		/**
		 * hasNext() override from Iterator interface
		 * Controls the queue to determine if iterator has more elements to
		 * traverse the tree
		 *
		 * @return True if there are more elements, false if not
		 */
		@Override
		public boolean hasNext()
		{
			return treeQueue.size() != 0;
		}
	}
}
