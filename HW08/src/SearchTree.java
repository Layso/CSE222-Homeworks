/**
 * SearchTree interface for the CSE222 - Data Structures and Algorithms course
 * on Gebze Technical University. This class written with the help of Wolfgang &
 * Koffman's Data Structures and Algorithms Using Java book to implement search
 * trees and use whenever needed on homeworks
 *
 * @author Deniz Can Erdem Yılmaz
 *         30.04.2017
 */
public interface SearchTree <R>
{
	/**
	 * Inserts item to tree according to search tree rules
	 *
	 * @param item Data to add to tree
	 * @return True if item inserted, false if it's already in tree
	 */
	boolean add(R item);
	
	
	
	/**
	 * Checks whether the target is in tree
	 *
	 * @param target Data to search in tree
	 * @return True if item found, false if not
	 */
	boolean contains(R target);
	
	
	
	/**
	 * Checks whether the target is in tree
	 *
	 * @param target Data to search in tree
	 * @return Reference of data if found, null if not
	 */
	R find(R target);
	
	
	
	/**
	 * Removes target (if found) from tree
	 *
	 * @param target Data to remove from tree
	 * @return Removed item if found, null if not
	 */
	R delete(R target);
	
	
	
	/**
	 * Removes target (if found) from tree
	 *
	 * @param target Data to remove from tree
	 * @return True if found, false if not
	 */
	boolean remove(R target);
}
