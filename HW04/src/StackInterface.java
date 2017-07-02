/**
 * HW04 for Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         21.03.2017.
 */



import java.util.EmptyStackException;



/** Stack interface to derive different stacks with same operations */
public interface StackInterface <R>
{
	/**
	 * Returns the first element on stack end removes it
	 * @return Returns removed element
	 * @throws EmptyStackException
	 */
	R pop();
	
	/**
	 * Adds new element on the top of stack
	 * @param item - Item to insert
	 * @return Returns the successfully inserted element
	 */
	R push(R item);
	
	/**
	 * Checks if stack has eny element to return
	 * @return Returns true if stack is empty, else false;
	 */
	boolean isEmpty();
	
	/**
	 * Finds the number of elements that stack have
	 * @return Returns the number of elements that stack have
	 */
	int size();
}
