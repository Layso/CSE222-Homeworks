import java.util.LinkedList;
import java.util.ListIterator;



/**
 * HW04 for Gebze Technical Universtiy
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         25.03.2017.
 */



/** Priority queue class for HW04 */
public class PriorityQueueB<R>
{
	/** Container to store data inside */
	private LinkedList<R> container;
	
	/** Constant defined variable */
	private final int ZERO = 0;
	
	
	
	/**
	 * No parameter constructor. Creates a new instance for container
	 */
	public PriorityQueueB()
	{
		container = new LinkedList<R>();
	}
	
	
	
	/**
	 * Inserts an item to the queue
	 * @param item - Element to insert
	 * @return Returns true
	 */
	public boolean offer(R item)
	{
		container.addLast(item);
		return true;
	}
	
	
	
	/**
	 * Retrieves and removes the head of this queue
	 * @return Returns removed element
	 */
	public R poll()
	{
		return container.remove(ZERO);
	}
	
	
	
	/**
	 * Finds the number of elements on queue
	 * @return Returns size of the queue
	 */
	public int size()
	{
		return container.size();
	}
	
	
	
	/**
	 * Checks whether the queue is empty or not
	 * @return Returns true if list is empty, else false
	 */
	public boolean isEmpty()
	{
		return (size() == ZERO);
	}
	
	
	
	/**
	 * Finds the minimum value of container and deletes it. Instance type must
	 * extend the Comparable class to use this method
	 * @param <R> Type of the instance
	 */
	public <R extends Comparable<R>> void deleteMin()
	{
		ListIterator it = container.listIterator();
		R minValue = (R) container.peek();
		boolean erased = false;
		
		
		while (it.hasNext())
		{
			R check =(R) it.next();
			if (check.compareTo(minValue) < ZERO)
			{
				minValue = check;
			}
		}
		
		while (it.hasPrevious())
		{
			if (it.previous().equals(minValue) && !erased)
			{
				it.remove();
				erased = true;
			}
		}
	}
}
