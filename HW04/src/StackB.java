/**
 * HW04 for Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         22.03.2017.
 */



/* Import(s) */
import java.util.ArrayList;
import java.util.EmptyStackException;



/** Stack structure with using array list as a member */
public class StackB<R> implements StackInterface<R>
{
	/** Container to store elements */
	ArrayList<R> container;
	
	/** Constant definition */
	private final int ZERO = 0;
	/** Constant definition */
	private final int ONE = 1;
	
	
	
	/**
	 * No parameter constructor
	 */
	public StackB()
	{
		container  = new ArrayList<R>();
	}
	
	
	
	/**
	 * One parameterd constructor to start stack with an element
	 * @param item - Element to start stack with
	 */
	public StackB(R item)
	{
		container = new ArrayList<R>();
		container.add(item);
	}
	
	
	
	@Override
	public R pop() throws EmptyStackException
	{
		if (size() > ZERO)
			return container.remove(container.size()-ONE);
		
		else
			throw new EmptyStackException();
	}
	
	
	
	@Override
	public R push(R item)
	{
		container.add(item);
		return item;
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
}
