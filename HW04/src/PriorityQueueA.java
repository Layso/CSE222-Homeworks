import java.util.LinkedList;
import java.util.ListIterator;


/**
 * HW04 for Gebze Technical Universtiy
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         23.03.2017.
 */



/** First priority queue class */
public class PriorityQueueA<R> extends LinkedList<R>
{
	/** Constant defined variable */
	private final int ZERO = 0;
	
	
	
	/**
	 * No parameter constructor. Calls super class' constructor
	 */
	public PriorityQueueA()
	{
		super();
	}
	
	
	
	/**
	 * Inserts an item to the queue
	 * @param item - Element to insert
	 * @return Returns true
	 */
	public boolean offer(R item)
	{
		addLast(item);
		return true;
	}
	
	
	
	/**
	 * Retrieves and removes the head of this queue
	 * @return Returns removed element
	 */
	public R poll()
	{
		return remove(ZERO);
	}
	
	
	
	/**
	 * Finds the number of elements on queue
	 * @return Returns size of the queue
	 */
	public int size()
	{
		return super.size();
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
		ListIterator it = listIterator();
		R minValue = (R) peek();
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
