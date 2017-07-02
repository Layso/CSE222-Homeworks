/* ReUseableLinkedList.java
 *
 * This is a simple single linked list class with a little bit difference from
 * known linked list. This one holds deleted nodes as another linked list
 * inside itself to reuse them instead of creating new nodes on element addition
 * operations to prevent garbage collecter work less 
 *
 * Deniz Can Erdem Yılmaz / 151044001 / Gebze Technical University 
 * 14 March 2017
 */



/* Import(s) */
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;
import java.util.Iterator;



/** ReUseableLinkedList class to hold datas of a string builder which will be implemented
  * later
  */
public class ReUseableLinkedList<R> implements Iterable<R>
{
	/** Starting address of linked list */
	private Node<R> head;
	/** Last address of linked list */
	private Node<R> tail;
	/** Starting address of reusable node list */
	private Node<R> headRecycle;
	/** Last address of reusable node list */
	private Node<R> tailRecycle;
	/** Size of linked list */
	private int size;
	/** Size of reusable node list */
	private int sizeRecycle;
	
	
	
	/** Constant definitions */
	private final int ZERO = 0;
	private final int ONE = 1;
	private final int TWO = 2;
	private final int DOUBLE = TWO;
	private final int HALF = TWO;
	private final int INITIAL_SIZE = ZERO;
	
	
	
	/** No parameter constructor
	  * Gives data members their initial value
	  */
	public ReUseableLinkedList()
	{
		head = null;
		tail = null;
		headRecycle = null;
		tailRecycle = null;
		size = INITIAL_SIZE;
		sizeRecycle = INITIAL_SIZE;
	}
	
	
	
	/** Adds a new item to end of the list
	  * @param item - Data to add
	  */
	public void add(R item)
	{
		Node<R> newNode;
		
		
		/* Doing recycl if it is possible */
		if (sizeRecycle == ZERO)
			newNode = new Node<R>(item);
		
		else
		{
			newNode = headRecycle;
			headRecycle = headRecycle.next;
			newNode.next = null;
			newNode.data = item;
			--sizeRecycle;
		}
		
		
		/* Adding new element */
		if (size == INITIAL_SIZE)
		{
			head = newNode;
			tail = head;
		}
		
		else
		{
			tail.next = newNode;
			tail = tail.next;
		}
		
		++size;
	}
	
	
	
	/** Removes the last element inserted to the list */
	public void remove()
	{		
		Node<R> traverseNode = head;
		
		
		/* Traversing through the previous node of tail */
		for (int i=0; i<size-TWO; ++i)
			traverseNode = traverseNode.next;
		
		/* Setting it as new last element */
		addToRecycle(tail);
		tail = traverseNode;
		tail.next = null;
		--size;
	}
	
	
	@Override
	/** Iterator override */
	public Iterator<R> iterator()
	{
		return new MyIterator();
	}
	
	
	
	/** Getter function, returns the data at given index 
	  * @param index - Index of the wanted element
	  * @return If index is legal returns element else
	  */
	public R get(int index) throws IndexOutOfBoundsException
	{
		Node<R> traverseNode = head;
		
		
		/* Controlling the index */
		if (index < ZERO || index >= size)
			throw new IndexOutOfBoundsException();
		
		/* Traversing through the index */
		for (int i=0; i<index; ++i)
			traverseNode = traverseNode.next;
		
		
		return traverseNode.data;
	}
	
	
	
	/** Size getter 
	  * @return Size of the linked list
	  */
	public int size()
	{
		return size;
	}
	
	
	
	/** Prints list content to screen */
	public void print()
	{
		Node<R> node = head;
		while (node != null)
		{
			System.out.print(node.data);
			if (node.next != null)
				System.out.print("  ==>  ");
			node = node.next;
		}
		
		System.out.print("\n");
	}
	
	
	
	@Override
	/** Override of toString method */
	public String toString()
	{
		String converted = new String();
		Node<R> node = head;
		
		
		/* Traversing through each element */
		while (node != null)
		{
			converted += node.data.toString();
			if (node.next != null)
				converted += "   ==>   ";
			node = node.next;
		}
		
		return converted;
	}
	
	
	
	/** Adds a new item to end of the list
	  * @param item - Data to add
	  */
	public void addToRecycle(Node<R> garbage)
	{
		garbage.next = null;
		
		if (sizeRecycle == INITIAL_SIZE)
		{
			headRecycle = garbage;
			tailRecycle = headRecycle;
		}
		
		else
		{
			tailRecycle.next = garbage;
			tailRecycle = tailRecycle.next;
		}
		
		++sizeRecycle;
	}
	
	
	
	/** Inner node class to store data and construct list structure */
	private class Node<R>
	{
		/** Container */
		R data;
		/** Referance to next data */
		Node<R> next;
		
		
		
		/** No parameter constructor
		  * Assigns each variable as null
		  */
		private Node()
		{
			data = null;
			next = null;
		}
	
	
		
		/** R parameter constructor
		  * @param item - Creates node's container with given data
		  */
		private Node(R item)
		{
			data = item;
			next = null;
		}
	
	
		
		/** R and Node parameter constructor
		  * @param item - Creates node's container with given data
		  * @param ref  - Sets next node as given parameter
		  */
		private Node(R item, Node<R> ref)
		{
			data = item;
			next = ref;
		}
	}
	
	
	
	/** Inner iterator class to iterate list */
	public class MyIterator implements Iterator<R>
	{
	
		/** Cursor for iterator */
		private int pointer;
		/** Pointed element of the list */
		private Node<R> pointedNode;
		
		
				
		/** No parameter constructor */
		public MyIterator()
		{
			pointer = ZERO;
			pointedNode = ReUseableLinkedList.this.head;
		}
		
		
		
		@Override
		/** hasNext override to indicate if list has more elements to return
		  * @return True if there are more elements, else returns false
		  */
		public boolean hasNext()
		{
			return (pointer < ReUseableLinkedList.this.size);
		}
		
		
		
		@Override
		/** next override to return next value of the list */
		public R next() throws NoSuchElementException
		{
			if(hasNext())
			{
				R currentData = pointedNode.data;
				pointedNode = pointedNode.next;
				++pointer;
				return currentData;
			}
			
			throw new NoSuchElementException();
		}

		
		
		@Override
		/** remove override to throw exception */
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}
	}	
}

/* End of ReUseableLinkedList.java */
