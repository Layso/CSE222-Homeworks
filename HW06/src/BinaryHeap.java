/**
 * HW06 for Gebze Technical University
 * BinaryHeap class extends binary tree to store data's inside and implements
 * queue interface to have a priority queue like structure
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         20.04.2017.
 */
public class BinaryHeap <R> extends BinaryTree<R> implements Queue<R>
{
	
	@Override
	public void add(R item)
	{
		offer(item);
	}
	
	
	
	@Override
	public boolean offer(R item)
	{
		return false;
	}
	
	
	
	@Override
	public R poll()
	{
		return null;
	}
	
	
	
	@Override
	public R peek()
	{
		return null;
	}
}
