import java.util.ArrayList;



/**
 * HW05 for Gebze Technical University
 * A simple queue class for traversing tree with level-order
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         05.04.2017.
 */
public class MyQueue <R>
{
	/** ArrayList as container to store data */
	private ArrayList<R> container;
	
	
	
	/**
	 * No parameter constructor
	 * Initializes the ArrayList component
	 */
	public MyQueue()
	{
		container = new ArrayList<>();
	}
	
	
	
	/**
	 * Inserts new item to the queue
	 *
	 * @param item - Item to insert
	 */
	public void offer(R item)
	{
		container.add(item);
	}
	
	
	
	/**
	 * Removes an element from the start point of queue and returns it
	 *
	 * @return First element of queue
	 */
	public R poll()
	{
		return container.remove(0);
	}
	
	
	
	/**
	 * Returns the element on the first index of arrayList without removing it
	 *
	 * @return First element of queue
	 */
	public R peek()
	{
		return container.get(0);
	}
	
	
	
	/**
	 * Finds the size of the arrayList
	 *
	 * @return The size of queue
	 */
	public int size()
	{
		return container.size();
	}
}
