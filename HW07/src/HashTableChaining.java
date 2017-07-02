import java.util.ArrayList;



/**
 * Created by syucer on 4/24/2017.
 */
public class HashTableChaining <K extends Comparable, V> implements HashMap<K, V>
{
	/** The table */
	private HashTableOpen<Entry<K, V>>[] table;
	private int size;
	private int deletedSize;
	private ArrayList<Integer> deletedIndexes;
	private final int INITIAL_CAP = 199;
	
	//Do not forget you can use more class and methods to do this homework,
	// this project gives you an initial classes an methods to see easily
	//....
	//.... other class members
	
	
	
	public HashTableChaining()
	{
		table = new HashTableOpen[INITIAL_CAP];
		
		for (int i = 0; i < table.length; i++)
			table[i] = new HashTableOpen();
			
		size = 0;
		deletedSize = 0;
		deletedIndexes = new ArrayList<Integer>();
	}
	
	
	
	@Override
	public V get(Object key)
	{
		int index = key.hashCode()%table.length;
		Entry<K, V> entry = new Entry<K, V>((K)key, null);
		
		if (index<0)
			index+=table.length;
		
		entry = table[index].remove(entry);
		if (entry == null)
			return null;
		
		table[index].put(entry);
		return entry.getValue();
	}
	
	
	
	@Override
	public V put(K key, V value)
	{
		Entry<K, V> newEntry = new Entry<K, V>(key, value);
		int index = key.hashCode()%table.length;
		
		
		if (index<0)
			index+=table.length;
		
		table[index].put(newEntry);
		
		
		++size;
		return value;
	}
	
	
	
	@Override
	public V remove(Object key)
	{
		int index = key.hashCode()%table.length;
		Entry<K, V> entry = new Entry<K, V>((K)key, null);
		
		if (index<0)
			index+=table.length;
		
		entry = table[index].remove(entry);
		
		if (entry == null)
			return null;
		
		--size;
		return entry.getValue();
	}
	
	
	
	@Override
	public int size()
	{
		return size;
	}
	
	
	
	@Override
	public boolean isEmpty()
	{
		return size()==0;
	}
	
	
	
	private class Entry <K extends Comparable, V> implements Comparable<Entry<K, V>>
	{
		K key;
		V value;
		
		
		
		public Entry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		
		
		
		@Override
		public int compareTo(Entry<K, V> o)
		{
			return key.compareTo(o.key);
		}
		
		
		
		public K getKey()
		{
			return key;
		}
		
		
		
		public V getValue()
		{
			return value;
		}
		
		
		
		public V setValue(V v)
		{
			return value = v;
		}
		
		
		
		@Override
		public String toString()
		{
			return (key + " --> " + value);
		}
	}
}
