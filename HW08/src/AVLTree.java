/**
 * HW08 for Data Structures and Algorithms course CSE22, Gebze Technical University
 * An AVL Tree, self balanced, tree example for HW08
 * Algorithm for implementations taken from Data Structures: Abstraction and
 * Design Using Java by Elliot B. Koffman & Paul A T. Wolfgang
 *
 * @author Deniz Can Erdem Yılmaz
 *         06.05.2017
 */
public class AVLTree <R extends Comparable> extends BinarySearchTreeWithRotate<R>
{
	/** Flag to indicate height increase on the route */
	private boolean increase;
	/** Flag to indicate a member has deleted */
	private boolean deleted;
	
	
	
	/**
	 * Add function override to convert insertion operation to a self balancing
	 * one
	 *
	 * @param item Item to insert
	 * @return True if insertion is successful, false if not
	 */
	@Override
	public boolean add(R item)
	{
		increase = false;
		root = add((AVLNode<R>) root, item);
		return addReturn;
	}
	
	
	
	/**
	 * Delete function override to convert delete operation to a self balancing
	 * one
	 *
	 * @param target Element to delete
	 * @return Deleted element if tree contains it, null if not
	 */
	@Override
	public R delete(R target)
	{
		deleted = false;
		root = delete((AVLNode<R>) root, target);
		return deleteReturn;
	}
	
	
	
	/**
	 * Recursive delete method. Checks the balance of nodes to call rebalance
	 * methods if needed. Node name picked for a little Star Wars reference
	 *
	 * @param force Local root node
	 * @param item  Element to remove from tree
	 * @return Returns the local root after changes
	 */
	private AVLNode<R> delete(AVLNode<R> force, R item)
	{
		if (force == null)
		{
			deleted = false;
			deleteReturn = null;
			return force;
		}
		
		
		if (item.compareTo(force.data) < 0)
		{
			force.left = delete((AVLNode<R>) force.left, item);
			
			if (deleted)
			{
				incrementBalance(force);
				
				if (force.right != null)
					deleted = false;
				
				if (force.balance > AVLNode.RIGHT_HEAVY)
					force = bringBalanceRight(force);
			}
			
			return force;
		}
		
		else if (item.compareTo(force.data) > 0)
		{
			force.right = delete((AVLNode<R>) force.right, item);
			
			if (deleted)
			{
				decrementBalance(force);
				
				if (force.left != null)
					deleted = false;
				
				if (force.balance < AVLNode.LEFT_HEAVY)
					force = bringBalanceLeft(force);
			}
			
			return force;
		}
		
		else
		{
			deleteReturn = force.data;
			deleted = true;
			
			if (force.left == null)
			{
				return (AVLNode<R>) force.right;
			}
			
			else if (force.right == null)
			{
				return (AVLNode<R>) force.left;
			}
			
			else
			{
				if (force.left.right == null)
				{
					force.data = force.left.data;
					force.left = force.left.left;
					incrementBalance(force);
					deleted = false;
					return force;
				}
				
				else
				{
					force.data = findLargestChild(force.left);
					return force;
				}
			}
		}
	}
	
	
	
	/**
	 * Recursive add method. Checks the balance of nodes to call rebalance
	 * methods if needed. Node name picked for a little Star Wars reference
	 *
	 * @param force Local root node
	 * @param item  Element to insert to tree
	 * @return Returns local root after changes
	 */
	private AVLNode<R> add(AVLNode<R> force, R item)
	{
		if (force == null)
		{
			addReturn = true;
			increase = true;
			return new AVLNode<R>(item);
		}
		
		if (item.compareTo(force.data) == 0)
		{
			increase = false;
			addReturn = false;
			return force;
		}
		
		else if (item.compareTo(force.data) < 0)
		{
			force.left = add((AVLNode<R>) force.left, item);
			if (increase)
			{
				decrementBalance(force);
				if (force.balance < AVLNode.LEFT_HEAVY)
				{
					increase = false;
					return bringBalanceLeft(force);
				}
			}
			
			return force;
		}
		
		else
		{
			force.right = add((AVLNode<R>) force.right, item);
			if (increase)
			{
				incrementBalance(force);
				if (force.balance > AVLNode.RIGHT_HEAVY)
				{
					increase = false;
					return bringBalanceRight(force);
				}
			}
			
			return force;
		}
	}
	
	
	
	/**
	 * Method to rebalance the tree if any unbalanced node has occurred. Checks
	 * the right child to see if source of unbalance is the right-right or right
	 * -left situation. Method name picked for a little Star Wars reference
	 *
	 * @param localRoot Node that is unbalanced and needs to rebalance
	 * @return Balanced form of the given node structure
	 */
	private AVLNode<R> bringBalanceRight(AVLNode<R> localRoot)
	{
		AVLNode<R> rightChild = (AVLNode<R>) localRoot.right;
		
		if (rightChild.balance < AVLNode.BALANCED)
		{
			AVLNode<R> rightLeftChild = (AVLNode<R>) rightChild.left;
			if (rightLeftChild.balance > AVLNode.BALANCED)
			{
				rightChild.balance = AVLNode.BALANCED;
				rightLeftChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.RIGHT_HEAVY;
			}
			
			else
			{
				if (rightChild.right == null)
					rightChild.balance = 0;
				
				else
					rightChild.balance = AVLNode.LEFT_HEAVY;
				
				rightLeftChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED;
			}
			localRoot.right = rotateRight(rightChild);
		}
		
		else
		{
			rightChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		}
		
		return (AVLNode<R>) rotateLeft(localRoot);
	}
	
	
	
	/**
	 * Method to rebalance the tree if any unbalanced node has occurred. Checks
	 * the left child to see if source of unbalance is the left-left or left
	 * -right situation. Method name picked for a little Star Wars reference
	 *
	 * @param localRoot Node that is unbalanced and needs to rebalance
	 * @return Balanced form of the given node structure
	 */
	private AVLNode<R> bringBalanceLeft(AVLNode<R> localRoot)
	{
		AVLNode<R> leftChild = (AVLNode<R>) localRoot.left;
		
		if (leftChild.balance > AVLNode.BALANCED)
		{
			AVLNode<R> leftRightChild = (AVLNode<R>) leftChild.right;
			if (leftRightChild.balance > AVLNode.BALANCED)
			{
				leftChild.balance = AVLNode.BALANCED;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.RIGHT_HEAVY;
			}
			
			else
			{
				if (leftChild.left == null)
					leftChild.balance = 0;
				
				else
					leftChild.balance = AVLNode.LEFT_HEAVY;
				
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED;
			}
			localRoot.left = rotateLeft(leftChild);
		}
		
		else
		{
			leftChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		}
		
		return (AVLNode<R>) rotateRight(localRoot);
	}
	
	
	
	/**
	 * Method to decrement 1 the balance value of a node
	 *
	 * @param node Node to decrement balance
	 */
	private void decrementBalance(AVLNode<R> node)
	{
		--node.balance;
		if (node.balance == AVLNode.BALANCED)
			increase = false;
	}
	
	
	
	/**
	 * Method to increment 1 the balance value of a node
	 *
	 * @param node
	 */
	private void incrementBalance(AVLNode<R> node)
	{
		++node.balance;
		if (node.balance == AVLNode.BALANCED)
			increase = false;
	}
	
	
	
	/** New node specialized for AVLTree class for balance calculations */
	private static class AVLNode <R> extends Node<R>
	{
		/** Constant definition */
		public static final int LEFT_HEAVY = -1;
		/** Constant definition */
		public static final int RIGHT_HEAVY = 1;
		/** Constant definition */
		public static final int BALANCED = 0;
		
		/** Field to store balance of node */
		public int balance;
		
		
		
		/**
		 * Constructor for new data fields
		 *
		 * @param item Data to store in node
		 */
		public AVLNode(R item)
		{
			super(item);
			balance = BALANCED;
		}
		
		
		
		/**
		 * toString override from super class to insert balance
		 *
		 * @return String representation of node
		 */
		@Override
		public String toString()
		{
			return super.toString();
			//return balance + ": " + super.toString();
		}
	}
}
