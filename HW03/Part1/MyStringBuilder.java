/* MyStringBuilder.java
 *
 * This is a string builder class. Takes string inputs with append() function
 * concates all strings together and returns them with 3 different toString()
 * methods with different implementation types
 *
 * Deniz Can Erdem Yılmaz / 151044001 / Gebze Technical University 
 * 14 March 2017
 */



/** Class to concate strings */
public class MyStringBuilder
{
	/** Data member, holds strings with a single linked list */
	LinkedList<String> content;
	
	
	
	/** No parameter constructor
	  * Creates an instance of LinkedList to set it ready for an appendation
	  */
	public MyStringBuilder()
	{
		content = new LinkedList<String>();
	}
	
	
	
	/** String parameter constructor
	  * Creates a LinkedList and adds first element given with parameter
	  * @param data - String to append
	  */
	public MyStringBuilder(String data)
	{
		content = new LinkedList<String>();
		content.add(data);
	}
	
	
	
	/** Append function
	  * Appends given string at the end of the list
	  * @param data - String to append
	  */
	public void append(Object data)
	{
		content.add(data.toString());
	}
	
	
	
	/** First toString function
	  * Makes a string includes all appended strings and returns it. Implemented
	  * with the getter and index
	  * @returns All data together as a single string
	  */
	public String toString1()
	{
		String returnValue = new String();
		
		
		/* Taking each element to the return string */
		for (int i=0; i<content.size(); ++i)
			returnValue += content.get(i);
		
		
		return returnValue;
	}
	
	
	
	/** Second toString function
	  * Makes a string includes all appended strings and returns it. Implemented
	  * with the iterator
	  * @returns All data together as a single string
	  */
	public String toString2()
	{
		String returnValue = new String();
		LinkedList.MyIterator it = content.new MyIterator();
		
		
		/* Traversing through list and taking each element inside string */
		while(it.hasNext())
			returnValue += it.next();
		
		return returnValue;
	}
	
	
	
	/** Third toString function
	  * Makes a string includes all appended strings and returns it. Implemented
	  * with the LinkedList's own toString method
	  * @returns All data together as a single string
	  */
	public String toString3()
	{
		return content.toString();
	}
}

/* End of MyStringBuilder.java */
