import java.util.ArrayList;
import java.util.Iterator;



/**
 * BinarySearchTree class for the CSE222 - Data Structures and Algorithms course
 * on Gebze Technical University. This class written with the help of Wolfgang &
 * Koffman's Data Structures and Algorithms Using Java book to learn internal
 * of binary search trees and use whenever needed on homeworks
 *
 * @author Deniz Can Erdem Yılmaz
 *         30.04.2017
 */
public class BinarySearchTree <R extends Comparable> extends BinaryTree<R> implements SearchTree<R>, Iterable
{
	/** Return value of insertion */
	protected boolean addReturn;
	/** Return value of removal */
	protected R deleteReturn;
	
	
	
	@Override
	public boolean add(R item)
	{
		root = add(root, item);
		return addReturn;
	}
	
	
	
	@Override
	public boolean contains(R target)
	{
		return (find(root, target) != null);
	}
	
	
	
	@Override
	public R find(R target)
	{
		return find(root, target);
	}
	
	
	
	@Override
	public R delete(R target)
	{
		root = delete(root, target);
		return deleteReturn;
	}
	
	
	
	@Override
	public boolean remove(R target)
	{
		return (delete(target) != null);
	}
	
	
	
	/**
	 * Recursive helper for find method
	 *
	 * @param localRoot Currently searched node
	 * @param target    Item that looked for
	 * @return Target itself if found, null if not
	 */
	private R find(Node<R> localRoot, R target)
	{
		int compResult = target.compareTo(localRoot.data);
		
		
		if (localRoot == null)
			return null;
		
		if (compResult == 0)
			return localRoot.data;
		
		else if (compResult < 0)
			return find(localRoot.left, target);
		
		else
			return find(localRoot.right, target);
	}
	
	
	
	/**
	 * Recursive helper for insertion
	 *
	 * @param localRoot Node that currently tried to insert
	 * @param item      Data to insert
	 * @return Node of item if inserted, null if not (already contains)
	 */
	private Node<R> add(Node<R> localRoot, R item)
	{
		if (localRoot == null)
		{
			addReturn = true;
			return new Node<R>(item);
		}
		
		if (item.compareTo(localRoot.data) == 0)
		{
			addReturn = false;
			return localRoot;
		}
		
		else if (item.compareTo(localRoot.data) < 0)
		{
			localRoot.left = add(localRoot.left, item);
			return localRoot;
		}
		
		else
		{
			localRoot.right = add(localRoot.right, item);
			return localRoot;
		}
	}
	
	
	
	/**
	 * Recursive helper for removal
	 *
	 * @param localRoot Current node while trying to reach target
	 * @param item      Data to remove
	 * @return Node of data if deleted, null if not (couldn't found in tree)
	 */
	private Node<R> delete(Node<R> localRoot, R item)
	{
		if (localRoot == null)
		{
			deleteReturn = null;
			return localRoot;
		}
		
		
		if (item.compareTo(localRoot.data) < 0)
		{
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		}
		
		else if (item.compareTo(localRoot.data) > 0)
		{
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		}
		
		else
		{
			deleteReturn = localRoot.data;
			
			if (localRoot.left == null)
				return localRoot.right;
			
			else if (localRoot.right == null)
				return localRoot.left;
			
			else
			{
				if (localRoot.left.right == null)
				{
					localRoot.data = localRoot.left.data;
					localRoot.left = localRoot.left.left;
					return localRoot;
				}
				
				else
				{
					localRoot.data = findLargestChild(localRoot.left);
					return localRoot;
				}
			}
		}
	}
	
	
	
	/**
	 * Helper for recursive removal helper method to find the largest right
	 * child of a node
	 *
	 * @param parent Parent node
	 * @return Data of largest subchild
	 */
	private R findLargestChild(Node<R> parent)
	{
		if (parent.right.right == null)
		{
			R returnValue = parent.right.data;
			parent.right = parent.right.left;
			return returnValue;
		}
		
		else
			return findLargestChild(parent.right);
	}
	
	
	
	@Override
	public Iterator iterator()
	{
		return new treeIterator();
	}
	
	
	public class treeIterator implements Iterator
	{
		ArrayList<Node<R>> queue;
		
		public treeIterator()
		{
			queue = new ArrayList<>();
			queue.add(root);
		}
		
		@Override
		public boolean hasNext()
		{
			return !queue.isEmpty();
		}
		
		
		
		@Override
		public Object next()
		{
			if (!queue.isEmpty())
			{
				Node<R> node = queue.remove(0);
				
				if (node.left != null)
					queue.add(node.left);
	
				if (node.right != null)
					queue.add(node.right);
	
				
				return node.data;
			}
			
			else
				return null;
		}
		
		
		
		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
