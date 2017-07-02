/**
 * HW04 for Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         23.03.2017.
 */



/** Stack structure with using queue as a member */
public class StackD<R> implements StackInterface<R>
{
	/** Container to store elements */
	MyQueue<R> container;
	
	/** Constant definition */
	private final int ZERO = 0;
	/** Constant definition */
	private final int ONE = 1;
	
	
	
	/**
	 * No parameter constructor
	 */
	public StackD()
	{
		container = new MyQueue();
	}
	
	
	
	/**
	 * One parametered constructor
	 * @param item - Element to start stack with
	 */
	public StackD(R item)
	{
		container = new MyQueue();
		container.offer(item);
	}
	
	
	
	@Override
	public boolean isEmpty()
	{
		return (container.size() == ZERO);
	}
	
	
	
	@Override
	public int size()
	{
		return container.size();
	}
	
	
	
	@Override
	public R pop()
	{
		R itemToReturn;
		
		
		for (int i = 0; i < size()-ONE; i++)
			container.offer(container.poll());
		
		itemToReturn = container.poll();
		
		
		return itemToReturn;
	}
	
	
	
	@Override
	public R push(R item)
	{
		container.offer(item);
		return item;
	}
}

