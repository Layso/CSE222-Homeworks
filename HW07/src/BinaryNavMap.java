/**
 * Created by syucer on 4/24/2017.
 */



import java.util.*;



public class BinaryNavMap <K extends Comparable, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, java.io.Serializable
{
	BinarySearchTree<NavEntry<K, V>> container;
	
	
	
	public BinaryNavMap()
	{
		container = new BinarySearchTree<NavEntry<K, V>>();
	}
	
	
	
	@Override
	public V put(K key, V value)
	{
		if (container.add(new NavEntry<K, V>(key, value)))
			return value;
		
		else
			return null;
	}
	
	
	
	@Override
	public Set<Entry<K, V>> entrySet()
	{
		Set<Entry<K, V>> set = new HashSet<>();
		Iterator it = container.iterator();
		
		while (it.hasNext())
		{
			set.add((Entry) it.next());
		}
		
		return set;
	}
	
	
	
	/**
	 * Returns a key-value mapping associated with the greatest key
	 * strictly less than the given key, or {@code null} if there is
	 * no such key.
	 *
	 * @param key the key
	 * @return an entry with the greatest key less than {@code key},
	 * or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public Entry<K, V> lowerEntry(K key)
	{
		Entry<K, V> entry = null;
		Entry<K, V> next;
		Iterator it = container.iterator();
		
		if (!it.hasNext())
			return null;
		
		else
			entry = firstEntry();
		
		
		while (it.hasNext())
		{
			next = (Entry) it.next();
			if (key.compareTo(next.getKey()) > 0 && entry.getKey().compareTo(next.getKey()) < 0)
				entry = next;
		}
		
		return entry;
	}
	
	
	
	/**
	 * Returns the greatest key strictly less than the given key, or
	 * {@code null} if there is no such key.
	 *
	 * @param key the key
	 * @return the greatest key less than {@code key},
	 * or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public K lowerKey(K key)
	{
		return lowerEntry(key).getKey();
	}
	
	
	
	/**
	 * Returns a key-value mapping associated with the greatest key
	 * less than or equal to the given key, or {@code null} if there
	 * is no such key.
	 *
	 * @param key the key
	 * @return an entry with the greatest key less than or equal to
	 * {@code key}, or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public Entry<K, V> floorEntry(K key)
	{
		Entry<K, V> entry = null;
		Entry<K, V> next;
		Iterator it = container.iterator();
		
		if (!it.hasNext())
			return null;
		
		else
			entry = firstEntry();
		
		
		while (it.hasNext())
		{
			next = (Entry) it.next();
			if (key.compareTo(next.getKey()) >= 0 && entry.getKey().compareTo(next.getKey()) < 0)
				entry = next;
		}
		
		return entry;
	}
	
	
	
	/**
	 * Returns the greatest key less than or equal to the given key,
	 * or {@code null} if there is no such key.
	 *
	 * @param key the key
	 * @return the greatest key less than or equal to {@code key},
	 * or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public K floorKey(K key)
	{
		return floorEntry(key).getKey();
	}
	
	
	
	/**
	 * Returns a key-value mapping associated with the least key
	 * greater than or equal to the given key, or {@code null} if
	 * there is no such key.
	 *
	 * @param key the key
	 * @return an entry with the least key greater than or equal to
	 * {@code key}, or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public Entry<K, V> ceilingEntry(K key)
	{
		Entry<K, V> entry = null;
		Entry<K, V> next;
		Iterator it = container.iterator();
		
		if (!it.hasNext())
			return null;
		
		else
			entry = (Entry) it.next();
		
		
		while (it.hasNext())
		{
			next = (Entry) it.next();
			if (key.compareTo(next.getKey()) <= 0 && entry.getKey().compareTo(next.getKey()) > 0)
				entry = next;
		}
		
		return entry;
	}
	
	
	
	/**
	 * Returns the least key greater than or equal to the given key,
	 * or {@code null} if there is no such key.
	 *
	 * @param key the key
	 * @return the least key greater than or equal to {@code key},
	 * or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public K ceilingKey(K key)
	{
		return ceilingEntry(key).getKey();
	}
	
	
	
	/**
	 * Returns a key-value mapping associated with the least key
	 * strictly greater than the given key, or {@code null} if there
	 * is no such key.
	 *
	 * @param key the key
	 * @return an entry with the least key greater than {@code key},
	 * or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public Entry<K, V> higherEntry(K key)
	{
		Entry<K, V> entry = null;
		Entry<K, V> next;
		Iterator it = container.iterator();
		
		if (!it.hasNext())
			return null;
		
		else
			entry = (Entry) it.next();
		
		
		while (it.hasNext())
		{
			next = (Entry) it.next();
			if (key.compareTo(next.getKey()) < 0 && entry.getKey().compareTo(next.getKey()) > 0)
				entry = next;
		}
		
		return entry;
	}
	
	
	
	/**
	 * Returns the least key strictly greater than the given key, or
	 * {@code null} if there is no such key.
	 *
	 * @param key the key
	 * @return the least key greater than {@code key},
	 * or {@code null} if there is no such key
	 * @throws ClassCastException   if the specified key cannot be compared
	 *                              with the keys currently in the map
	 * @throws NullPointerException if the specified key is null
	 *                              and this map does not permit null keys
	 */
	@Override
	public K higherKey(K key)
	{
		return higherEntry(key).getKey();
	}
	
	
	
	/**
	 * Returns a key-value mapping associated with the least
	 * key in this map, or {@code null} if the map is empty.
	 *
	 * @return an entry with the least key,
	 * or {@code null} if this map is empty
	 */
	@Override
	public Entry<K, V> firstEntry()
	{
		Entry<K, V> entry = null;
		NavEntry<K, V> next;
		Iterator it = container.iterator();
		
		
		if (!it.hasNext())
			return null;
		else
			entry = (Entry)it.next();
		
		while (it.hasNext())
		{
			next = (NavEntry) it.next();
			if (entry.getKey().compareTo(next.getKey()) > 0)
				entry = next;
		}
		
		
		return entry;
	}
	
	
	
	/**
	 * Returns a key-value mapping associated with the greatest
	 * key in this map, or {@code null} if the map is empty.
	 *
	 * @return an entry with the greatest key,
	 * or {@code null} if this map is empty
	 */
	@Override
	public Entry<K, V> lastEntry()
	{
		Entry<K, V> entry = null;
		Entry<K, V> next;
		Iterator it = container.iterator();
		
		
		if (!it.hasNext())
			return null;
		else
			entry = (Entry)it.next();
		
		while (it.hasNext())
		{
			next = (Entry) it.next();
			if (entry.getKey().compareTo(next.getKey()) < 0)
				entry = next;
		}
		
		
		return entry;
	}
	
	
	
	/**
	 * Removes and returns a key-value mapping associated with
	 * the least key in this map, or {@code null} if the map is empty.
	 *
	 * @return the removed first entry of this map,
	 * or {@code null} if this map is empty
	 */
	@Override
	public Entry<K, V> pollFirstEntry()
	{
		Entry<K, V> entry = firstEntry();
		NavEntry<K, V> navEntry = new NavEntry<>(entry.getKey(), entry.getValue());
		
		if (entry == null)
			return null;
		
		else
			return container.delete(navEntry);
	}
	
	
	
	/**
	 * Removes and returns a key-value mapping associated with
	 * the greatest key in this map, or {@code null} if the map is empty.
	 *
	 * @return the removed last entry of this map,
	 * or {@code null} if this map is empty
	 */
	@Override
	public Entry<K, V> pollLastEntry()
	{
		Entry<K, V> entry = lastEntry();
		NavEntry<K, V> navEntry = new NavEntry<>(entry.getKey(), entry.getValue());
		
		if (entry == null)
			return null;
		
		else
			return container.delete(navEntry);
	}
	
	
	
	/**
	 * Returns a reverse order view of the mappings contained in this map.
	 * The descending map is backed by this map, so changes to the map are
	 * reflected in the descending map, and vice-versa.  If either map is
	 * modified while an iteration over a collection view of either map
	 * is in progress (except through the iterator's own {@code remove}
	 * operation), the results of the iteration are undefined.
	 * <p>
	 * <p>The returned map has an ordering equivalent to
	 * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
	 * The expression {@code m.descendingMap().descendingMap()} returns a
	 * view of {@code m} essentially equivalent to {@code m}.
	 *
	 * @return a reverse order view of this map
	 */
	@Override
	public NavigableMap<K, V> descendingMap()
	{
		/* Couldn't implemented due to lack of knowledge how to change binary
		search trees compare method */
		return null;
	}
	
	
	
	/**
	 * Returns a {@link NavigableSet} view of the keys contained in this map.
	 * The set's iterator returns the keys in ascending order.
	 * The set is backed by the map, so changes to the map are reflected in
	 * the set, and vice-versa.  If the map is modified while an iteration
	 * over the set is in progress (except through the iterator's own {@code
	 * remove} operation), the results of the iteration are undefined.  The
	 * set supports element removal, which removes the corresponding mapping
	 * from the map, via the {@code Iterator.remove}, {@code Set.remove},
	 * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
	 * It does not support the {@code add} or {@code addAll} operations.
	 *
	 * @return a navigable set view of the keys in this map
	 */
	@Override
	public NavigableSet<K> navigableKeySet()
	{
		/* Couldn't implemented due to lack of knowledge how to change binary
		search trees compare method */
		return null;
	}
	
	
	
	/**
	 * Returns a reverse order {@link NavigableSet} view of the keys contained in this map.
	 * The set's iterator returns the keys in descending order.
	 * The set is backed by the map, so changes to the map are reflected in
	 * the set, and vice-versa.  If the map is modified while an iteration
	 * over the set is in progress (except through the iterator's own {@code
	 * remove} operation), the results of the iteration are undefined.  The
	 * set supports element removal, which removes the corresponding mapping
	 * from the map, via the {@code Iterator.remove}, {@code Set.remove},
	 * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
	 * It does not support the {@code add} or {@code addAll} operations.
	 *
	 * @return a reverse order navigable set view of the keys in this map
	 */
	@Override
	public NavigableSet<K> descendingKeySet()
	{
		/* Couldn't implemented due to lack of knowledge how to change binary
		search trees compare method */
		return null;
	}
	
	
	
	/**
	 * Returns a view of the portion of this map whose keys range from
	 * {@code fromKey} to {@code toKey}.  If {@code fromKey} and
	 * {@code toKey} are equal, the returned map is empty unless
	 * {@code fromInclusive} and {@code toInclusive} are both true.  The
	 * returned map is backed by this map, so changes in the returned map are
	 * reflected in this map, and vice-versa.  The returned map supports all
	 * optional map operations that this map supports.
	 * <p>
	 * <p>The returned map will throw an {@code IllegalArgumentException}
	 * on an attempt to insert a key outside of its range, or to construct a
	 * submap either of whose endpoints lie outside its range.
	 *
	 * @param fromKey       low endpoint of the keys in the returned map
	 * @param fromInclusive {@code true} if the low endpoint
	 *                      is to be included in the returned view
	 * @param toKey         high endpoint of the keys in the returned map
	 * @param toInclusive   {@code true} if the high endpoint
	 *                      is to be included in the returned view
	 * @return a view of the portion of this map whose keys range from
	 * {@code fromKey} to {@code toKey}
	 * @throws ClassCastException       if {@code fromKey} and {@code toKey}
	 *                                  cannot be compared to one another using this map's comparator
	 *                                  (or, if the map has no comparator, using natural ordering).
	 *                                  Implementations may, but are not required to, throw this
	 *                                  exception if {@code fromKey} or {@code toKey}
	 *                                  cannot be compared to keys currently in the map.
	 * @throws NullPointerException     if {@code fromKey} or {@code toKey}
	 *                                  is null and this map does not permit null keys
	 * @throws IllegalArgumentException if {@code fromKey} is greater than
	 *                                  {@code toKey}; or if this map itself has a restricted
	 *                                  range, and {@code fromKey} or {@code toKey} lies
	 *                                  outside the bounds of the range
	 */
	@Override
	public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)
	{
		NavigableMap<K, V> result = new BinaryNavMap<>();
		Iterator it = container.iterator();
		Entry<K, V> entry = null;
		
		
		if (!it.hasNext())
			return null;
		
		while (it.hasNext())
		{
			entry = (Entry)it.next();
			
			if (entry.getKey().compareTo(fromKey) > 0 && entry.getKey().compareTo(toKey) < 0)
				result.put(entry.getKey(), entry.getValue());
			
			else if (entry.getKey().compareTo(fromKey) == 0 && fromInclusive)
				result.put(entry.getKey(), entry.getValue());
			
			else if (entry.getKey().compareTo(toKey) == 0 && toInclusive)
				result.put(entry.getKey(), entry.getValue());
		}
		
		
		return result;
	}
	
	
	
	/**
	 * Returns a view of the portion of this map whose keys are less than (or
	 * equal to, if {@code inclusive} is true) {@code toKey}.  The returned
	 * map is backed by this map, so changes in the returned map are reflected
	 * in this map, and vice-versa.  The returned map supports all optional
	 * map operations that this map supports.
	 * <p>
	 * <p>The returned map will throw an {@code IllegalArgumentException}
	 * on an attempt to insert a key outside its range.
	 *
	 * @param toKey     high endpoint of the keys in the returned map
	 * @param inclusive {@code true} if the high endpoint
	 *                  is to be included in the returned view
	 * @return a view of the portion of this map whose keys are less than
	 * (or equal to, if {@code inclusive} is true) {@code toKey}
	 * @throws ClassCastException       if {@code toKey} is not compatible
	 *                                  with this map's comparator (or, if the map has no comparator,
	 *                                  if {@code toKey} does not implement {@link Comparable}).
	 *                                  Implementations may, but are not required to, throw this
	 *                                  exception if {@code toKey} cannot be compared to keys
	 *                                  currently in the map.
	 * @throws NullPointerException     if {@code toKey} is null
	 *                                  and this map does not permit null keys
	 * @throws IllegalArgumentException if this map itself has a
	 *                                  restricted range, and {@code toKey} lies outside the
	 *                                  bounds of the range
	 */
	@Override
	public NavigableMap<K, V> headMap(K toKey, boolean inclusive)
	{
		NavigableMap<K, V> result = new BinaryNavMap<>();
		Iterator it = container.iterator();
		Entry<K, V> entry = null;
		
		
		if (!it.hasNext())
			return null;
		
		while (it.hasNext())
		{
			entry = (Entry)it.next();
			
			if (entry.getKey().compareTo(toKey) < 0)
				result.put(entry.getKey(), entry.getValue());
			
			else if (entry.getKey().compareTo(toKey) == 0 && inclusive)
				result.put(entry.getKey(), entry.getValue());
		}
		
		
		return result;
	}
	
	
	
	/**
	 * Returns a view of the portion of this map whose keys are greater than (or
	 * equal to, if {@code inclusive} is true) {@code fromKey}.  The returned
	 * map is backed by this map, so changes in the returned map are reflected
	 * in this map, and vice-versa.  The returned map supports all optional
	 * map operations that this map supports.
	 * <p>
	 * <p>The returned map will throw an {@code IllegalArgumentException}
	 * on an attempt to insert a key outside its range.
	 *
	 * @param fromKey   low endpoint of the keys in the returned map
	 * @param inclusive {@code true} if the low endpoint
	 *                  is to be included in the returned view
	 * @return a view of the portion of this map whose keys are greater than
	 * (or equal to, if {@code inclusive} is true) {@code fromKey}
	 * @throws ClassCastException       if {@code fromKey} is not compatible
	 *                                  with this map's comparator (or, if the map has no comparator,
	 *                                  if {@code fromKey} does not implement {@link Comparable}).
	 *                                  Implementations may, but are not required to, throw this
	 *                                  exception if {@code fromKey} cannot be compared to keys
	 *                                  currently in the map.
	 * @throws NullPointerException     if {@code fromKey} is null
	 *                                  and this map does not permit null keys
	 * @throws IllegalArgumentException if this map itself has a
	 *                                  restricted range, and {@code fromKey} lies outside the
	 *                                  bounds of the range
	 */
	@Override
	public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive)
	{
		NavigableMap<K, V> result = new BinaryNavMap<>();
		Iterator it = container.iterator();
		Entry<K, V> entry = null;
		
		
		if (!it.hasNext())
			return null;
		
		while (it.hasNext())
		{
			entry = (Entry)it.next();
			
			if (entry.getKey().compareTo(fromKey) > 0)
				result.put(entry.getKey(), entry.getValue());
			
			else if (entry.getKey().compareTo(fromKey) == 0 && inclusive)
				result.put(entry.getKey(), entry.getValue());
		}
		
		
		return result;
	}
	
	
	
	/**
	 * Returns the comparator used to order the keys in this map, or
	 * {@code null} if this map uses the {@linkplain Comparable
	 * natural ordering} of its keys.
	 *
	 * @return the comparator used to order the keys in this map,
	 * or {@code null} if this map uses the natural ordering
	 * of its keys
	 */
	@Override
	public Comparator<? super K> comparator()
	{
		return null;
	}
	
	
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>Equivalent to {@code subMap(fromKey, true, toKey, false)}.
	 *
	 * @param fromKey
	 * @param toKey
	 * @throws ClassCastException       {@inheritDoc}
	 * @throws NullPointerException     {@inheritDoc}
	 * @throws IllegalArgumentException {@inheritDoc}
	 */
	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey)
	{
		return subMap(fromKey, true, toKey, false);
	}
	
	
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>Equivalent to {@code headMap(toKey, false)}.
	 *
	 * @param toKey
	 * @throws ClassCastException       {@inheritDoc}
	 * @throws NullPointerException     {@inheritDoc}
	 * @throws IllegalArgumentException {@inheritDoc}
	 */
	@Override
	public SortedMap<K, V> headMap(K toKey)
	{
		return headMap(toKey, false);
	}
	
	
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>Equivalent to {@code tailMap(fromKey, true)}.
	 *
	 * @param fromKey
	 * @throws ClassCastException       {@inheritDoc}
	 * @throws NullPointerException     {@inheritDoc}
	 * @throws IllegalArgumentException {@inheritDoc}
	 */
	@Override
	public SortedMap<K, V> tailMap(K fromKey)
	{
		return tailMap(fromKey, true);
	}
	
	
	
	/**
	 * Returns the first (lowest) key currently in this map.
	 *
	 * @return the first (lowest) key currently in this map
	 * @throws NoSuchElementException if this map is empty
	 */
	@Override
	public K firstKey()
	{
		return firstEntry().getKey();
	}
	
	
	
	/**
	 * Returns the last (highest) key currently in this map.
	 *
	 * @return the last (highest) key currently in this map
	 * @throws NoSuchElementException if this map is empty
	 */
	@Override
	public K lastKey()
	{
		return lastEntry().getKey();
	}
	
	
	
	
	
	class NavEntry <K extends Comparable, V> implements Map.Entry<K, V>, Comparable<NavEntry<K, V>>
	{
		K key;
		V value;
		
		
		
		public NavEntry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		
		
		
		@Override
		public int compareTo(NavEntry<K, V> o)
		{
			return key.compareTo(o.key);
		}
		
		
		
		@Override
		public K getKey()
		{
			return key;
		}
		
		
		
		@Override
		public V getValue()
		{
			return value;
		}
		
		
		
		@Override
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