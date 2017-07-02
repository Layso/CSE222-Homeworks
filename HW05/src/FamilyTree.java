import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * HW05 for Gebze Technical University
 * Family Tree class to hold a family tree
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         06.04.2017.
 */
public class FamilyTree extends BinaryTree<String>
{
	/** Constant definition */
	private final String ibnPrefix    = "ibn-";
	/** Constant definition */
	private final String ebuPrefix    = "ebu-";
	/** Constant definition */
	private final int    prefixLength = 4;
	/** Constant definition */
	private final int    ZERO         = 0;
	
	
	
	/**
	 * One parametered constructor to initialize tree with the root
	 *
	 * @param grandGrandGrandDad - Root of tree
	 */
	public FamilyTree(String grandGrandGrandDad)
	{
		super(grandGrandGrandDad);
	}
	
	
	
	/**
	 * Function to insert new person to the family tree
	 *
	 * @param child  - Person to insert
	 * @param parent - Parent of that person
	 * @param nick   - Nick (in Arabic culture) of parent
	 * @throws UnsupportedOperationException - If insertion is unresolved
	 */
	public void add(String child, String parent, String nick) throws UnsupportedOperationException
	{
		String prefix = nick.substring(ZERO, prefixLength);
		
		if (prefix.equals(ibnPrefix))
			ibnInsertion(child, parent, nick.substring(prefixLength));
		
		else
			ebuInsertion(child, parent, nick.substring(prefixLength));
	}
	
	
	
	/**
	 * add(String, String, String) function helper method for nicks with
	 * ibn prefix
	 *
	 * @param child       - Person to add
	 * @param parent      - Parent of that person
	 * @param grandParent - Parent of the parent
	 */
	private void ibnInsertion(String child, String parent, String grandParent)
	{
		boolean result      = false;
		boolean childExists = false;
		
		
		Iterator it = new FamilyIterator();
		while (it.hasNext() && !result)
		{
			Node<String> person = (Node<String>) it.next();
			
			/* If grandParent has found */
			if (person.GetData().equals(grandParent))
			{
				/* If grandParent is parent of parent parameter */
				Node<String> parentNode = ChildOf(person, parent);
				if (parentNode != null)
				{
					/* Insert child to most right of parent's left */
					if (parentNode.left == null)
					{
						parentNode.left = new Node<String>(child);
						result = true;
					}
					
					else
					{
						parentNode = parentNode.left;
						while (parentNode.right != null)
						{
							if (parentNode.equals(child))
								childExists = true;
							
							parentNode = parentNode.right;
						}
						
						if (!childExists)
						{
							parentNode.right = new Node<String>(child);
							result = true;
						}
					}
				}
			}
		}
		
		if (!result)
		{
			System.out.println("Error! Unresolved operation while:");
			System.out.println("Inserting " + child + " to " + parent + "-ebu-" + grandParent);
			throw new UnsupportedOperationException();
		}
	}
	
	
	
	/**
	 * add(String, String, String) function helper method for nicks with
	 * ebu prefix
	 *
	 * @param child   - Person to add
	 * @param parent  - Parent of that person
	 * @param sibling - Sibling of the person
	 */
	private void ebuInsertion(String child, String parent, String sibling)
	{
		boolean result     = false;
		boolean hasSibling = false;
		
		
		Iterator it = new FamilyIterator();
		while (it.hasNext() && !result)
		{
			Node<String> person = (Node<String>) it.next();
			
			/* If parent has found */
			if (person.GetData().equals(parent))
			{
				/* If child is equals to sibling insert directly */
				if (sibling.equals(child))
				{
					/* Insert child to most right of parent's left */
					if (person.left == null)
						person.left = new Node<String>(child);
					
					else
					{
						person = person.left;
						while (person.right != null)
							person = person.right;
						
						person.right = new Node<String>(child);
						result = true;
					}
					
					result = true;
				}
				
				/* Else search if parent has the sibling */
				else
				{
					if (person.left != null)
					{
						person = person.left;
						
						if (person.GetData().equals(sibling))
							hasSibling = true;
						
						
						while (person.right != null)
						{
							if (person.GetData().equals(sibling))
								hasSibling = true;
							
							person = person.right;
						}
						
						if (hasSibling)
						{
							person.right = new Node<String>(child);
							result = true;
						}
					}
				}
			}
		}
		
		if (!result)
		{
			System.out.println("Error! Unresolved operation while:");
			System.out.println("Inserting " + child + " to " + parent + "-ibn-" + sibling);
			throw new UnsupportedOperationException();
		}
	}
	
	
	
	/**
	 * A control function to find if given parent node has the child node named
	 * as the parameter
	 *
	 * @param parent - Node to search underneath
	 * @param child  - Seeked person
	 * @return Null if child does not exists, Node reference if it exists
	 */
	private Node<String> ChildOf(Node<String> parent, String child)
	{
		Node<String> temp   = parent.left;
		Node<String> result = null;
		
		if (parent == null)
			return result;
		
		
		while (temp != null)
		{
			if (temp.GetData().equals(child))
			{
				result = temp;
			}
			
			temp = temp.right;
		}
		
		return result;
	}
	
	
	
	/**
	 * add(String item) function override to disable it
	 *
	 * @param item - Data to add to tree
	 */
	@Override
	public void add(String item)
	{
		throw new UnsupportedOperationException();
	}
	
	
	
	/**
	 * Nested iterator class to iterate the Family Tree structure with
	 * level-ordered traversal
	 */
	private class FamilyIterator implements Iterator
	{
		/** Data member as queue to supply level-order traversal */
		MyQueue<Node<String>> familyQueue;
		
		
		
		/**
		 * No parameter constructor
		 * Initializes the queue and inserts root node
		 */
		public FamilyIterator()
		{
			familyQueue = new MyQueue<>();
			
			if (root != null)
				familyQueue.offer(root);
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
		public Node<String> next() throws NoSuchElementException
		{
			/* Controlling if queue is empty */
			if (familyQueue.size() == 0)
			{
				throw new NoSuchElementException();
			}
			
			
			/* Continuing the traversal */
			Node<String> current = familyQueue.poll();
			
			if (current.left != null)
				familyQueue.offer(current.left);
			
			if (current.right != null)
				familyQueue.offer(current.right);
			
			
			/* Returning the current node's data */
			return current;
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
			return familyQueue.size() != 0;
		}
	}
}
