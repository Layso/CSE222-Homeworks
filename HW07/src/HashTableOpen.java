import java.util.ArrayList;



/**
 * Open hash table class to use in chaining
 *
 * @author Deniz Can Erdem Yılmaz
 *         30.04.2017
 */
public class HashTableOpen <R extends Comparable>
{
	private Comparable[] table;
	private int deletedItems;
	private int size;
	private ArrayList<Integer> deletedIndexes;
	private final double THRESH_HOLD = 0.75;
	private final int INITIAL_CAP = 101;
	
	
	
	public HashTableOpen()
	{
		table = (R[]) (new Comparable[INITIAL_CAP]);
		deletedItems = 0;
		size = 0;
	}
	
	
	
	public R put(R item)
	{
		int index = item.hashCode()%table.length;
		
		
		if (table[index] == null)
			table[index] = item;
		
		else
		{
			while (table[index%table.length] != null)
				++index;
			
			table[index] = item;
		}
		
		++size;
		
		double loadFactor = (size + deletedItems)/table.length;
		if (loadFactor > THRESH_HOLD)
			rehash();
		
		return item;
	}
	
	
	
	public R remove(Object item)
	{
		int index = item.hashCode()%table.length;
		
		
		if (index < 0)
			index += table.length;
		
		if (table[index] == null)
			return null;
		
		while (table[index] != null && table[index].compareTo(item) != 0)
			++index;
		
		deletedIndexes.add(index);
		deletedItems++;
		size--;
		
		return (R) item;
	}
	
	
	
	public int size()
	{
		return size;
	}
	
	
	
	private void rehash()
	{
		int newCap = table.length*2;
		Comparable[] oldTable = table;
		table = (R[]) (new Comparable[newCap]);
		
		
		for (int i = 0; i < oldTable.length; ++i)
			if (oldTable[i] != null && !deletedIndexes.contains(i))
				put((R) oldTable[i]);
		
		deletedIndexes.clear();
		deletedItems = 0;
	}
}
