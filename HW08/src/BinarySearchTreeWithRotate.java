/**
 * HW08 for Data Structures and Algorithms course CSE22, Gebze Technical University
 * Sequel structure of binary search tree. Left and right rotation methods has
 * implement to ease implementation of self balanced binary search trees.
 * Algorithm for implementations taken from Data Structures: Abstraction and
 * Design Using Java by Elliot B. Koffman & Paul A T. Wolfgang
 *
 * @author Deniz Can Erdem Yılmaz
 *         06.05.2017
 */
public class BinarySearchTreeWithRotate <R extends Comparable> extends BinarySearchTree<R>
{
	/**
	 * Method to rotate given node (respected as root) to right. Left of the
	 * local root becomes new root. Local root becomes right of new root.
	 *
	 * @param root Local root, node to rotate right
	 * @return Returns the new root
	 */
	protected Node<R> rotateRight(Node<R> root)
	{
		Node<R> temp = root.left;
		root.left = temp.right;
		temp.right = root;
		return temp;
	}
	
	
	
	/**
	 * Method to rotate given node (respected as root) to left. Right of the
	 * local root becomes new root. Local root becomes left of new root.
	 *
	 * @param root Local root, node to rotate left
	 * @return Returns the new root
	 */
	protected Node<R> rotateLeft(Node<R> root)
	{
		Node<R> temp = root.right;
		root.right = temp.left;
		temp.left = root;
		return temp;
	}
}
