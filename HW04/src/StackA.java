/**
 * HW04 for Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         21.03.2017.
 */



/* Import(s) */
import java.util.ArrayList;
import java.util.EmptyStackException;



/** Stack structure with using array list as super class */
public class StackA<R> extends ArrayList<R> implements StackInterface<R>
{
	/** Constant definition */
	private final int ZERO = 0;
	/** Constant definition */
	private final int ONE = 1;
	
	
	
	/**
	 * No parameter constructor
	 */
	public StackA()
	{
		super();
	}
	
	
	
	/**
	 * One parameter constructor to start with an element
	 * @param item - element to add to stack
	 */
	public StackA(R item)
	{
		super();
		add(item);
	}
	
	
	
	@Override
	public R pop() throws EmptyStackException
	{
		if (size() > ZERO)
			return remove(size()-ONE);
		
		else
			throw new EmptyStackException();
	}
	
	
	
	@Override
	public R push(R item)
	{
		add(item);
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
		return super.size();
	}
}
