/**
 * HW04 for Gebze Technical Universtiy
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         22.03.2017.
 */



/* Import(s) */
import java.util.ListIterator;



/** MyQueue class to implement reverse function */
public class MyQueue<R> extends KWLinkedList<R>
{
	/** Constant definitions */
	private final int ZERO = 0;
	
	
	
	/**
	 * Inserts an item to the queue
	 * @param item - Element to insert
	 * @return Returns true
	 */
	public boolean offer(R item)
	{
		ListIterator it = listIterator();
		it.add(item);
		return true;
	}
	
	
	
	/**
	 * Retrieves and removes the head of this queue
	 * @return Returns removed element
	 */
	public R poll()
	{
		R itemToReturn; //Why did R type reasoned for error on line 39?
		ListIterator it = listIterator();
		
		while (it.hasNext())
			it.next();
		
		itemToReturn = (R)it.previous();
		it.remove();
		
		
		return itemToReturn;
	}
	
	
	
	/**
	 * Returns first element to remove with poll operation without removing
	 * @return Head of queue
	 */
	public  R peek()
	{
		return get(ZERO);
	}
	
	
	
	/**
	 * Finds the number of elements on queue
	 * @return Returns size of the queue
	 */
	public int size()
	{
		return super.getSize();
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
	 * Reverses the elements of queue
	 */
	public void reverseQueue()
	{
		ListIterator it = listIterator();
		
		for (int i = 0; i < size()/2; i++)
		{
			R temp1 = (R) it.next();
			
			for (int j = 0; j < size() - 2 - i*2; j++)
				it.next();

			R temp2 = (R) it.next();
			it.set(temp1);
			
			for (int j = 0; j < size() - i*2; j++)
				it.previous();
			
			it.set(temp2);
			it.next();
		}
	}
}
