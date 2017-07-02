/* myAbstractCollection.java
 *
 * This class extends the AbstractCollection class to implement a method named
 * appendAnything() which takes another AbstractCollection object and appends
 * its elements to first object
 *  
 * Deniz Can Erdem Yılmaz / 151044001 / Gebze Technical University 
 * 14 March 2017
 */



/* Import(s) */
import java.util.AbstractCollection;
import java.util.Iterator;



/** Class to extend myAbstractCollection */
public abstract class myAbstractCollection extends AbstractCollection
{
	/** Function to append any collection member to this collection
	  * Adds the elements of the other collection's container until the iterator
	  * says there is a next element
	  * @param other - Second collection to append elements from
	  */
	public void appendAnything(myAbstractCollection other)
	{
		Iterator it = iterator();
		
		
		/* Traversing other collection and adding each element here */
		while (it.hasNext())
			this.add(it.next());
	}
}
