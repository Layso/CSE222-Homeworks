import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;



/**
 * HW05 for Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         03.04.2017.
 */
public class FirstTest
{
	private static String TEST_NAME = "test.txt";
	
	
	
	public static void main(String[] args)
	{
		Scanner fileScanner = null;
		BinaryTree<Integer> binaryTree = new BinaryTree<>();
		BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
		
		
		try
		{
			fileScanner = new Scanner(new File(TEST_NAME));
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Error! File " + TEST_NAME + " couldn't found");
			System.exit(1);
		}
		
		
		while (fileScanner.hasNextInt())
		{
			int next = fileScanner.nextInt();
			
			binaryTree.add(next);
			searchTree.add(next);
		}
		
		
		Iterator it1 = binaryTree.iterator();
		Iterator it2 = searchTree.levelIterator();
		
		System.out.println("Traverse of binary tree with pre-order");
		while (it1.hasNext())
		{
			System.out.println(it1.next());
		}
		
		System.out.println("\n\n");
		System.out.println("Traverse of binary search tree with level-order");
		while (it2.hasNext())
		{
			System.out.println(it2.next());
		}
	}
}
