/**
 * HW04 for Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         22.03.2017.
 */
 
 

/* Import(s) */
import java.util.EmptyStackException;



/** Stack structure with using linked list's node as a member */
public class StackC<R> implements StackInterface<R>
{
	/** Data field for first node */
	Node<R> head;
	/** Size of stack */
	int listSize;
	
	/** Constant definition */
	private final int ZERO = 0;
	/** Constant definition */
	private final int ONE = 1;
	
	
	
	/**
	 * No parameter constructor
	 */
	public StackC()
	{
		head = null;
		listSize = ZERO;
	}
	
	
	
	/**
	 * One parametered constructor
	 * @param item - Item to start stack with
	 */
	public StackC(R item)
	{
		head = new Node<R>(item);
		listSize = ONE;
	}
	
	
	
	@Override
	public R pop() throws EmptyStackException
	{
		if (size() > ZERO)
		{
			R dataToReturn = head.data;
			head = head.next;
			--listSize;
			return dataToReturn;
		}
		
		else
			throw new EmptyStackException();
	}
	
	
	
	@Override
	public R push(R item)
	{
		Node<R> newItem = new Node<>(item);
		newItem.next = head;
		head = newItem;
		++listSize;
		
		
		return item;
	}
	
	
	
	@Override
	public boolean isEmpty()
	{
		return (size() == ZERO);
	}
	
	
	
	@Override
	public int size()
	{
		return listSize;
	}
	
	
	
	/** Inner node class for linked list like structure */
	private static class Node<R>
	{
		/** Address of next node */
		Node<R> next;
		/** Data that node stores */
		R data;
		
		
		
		/**
		 * No parameter node constructor
		 */
		public Node()
		{
			next = null;
			data = null;
		}
		
		
		
		/**
		 * One parametered node constructor
		 * @param item - Item to start node with
		 */
		public Node(R item)
		{
			next = null;
			data = item;
		}
	}
}