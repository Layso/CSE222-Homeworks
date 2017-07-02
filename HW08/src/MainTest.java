/**
 * HW08 for Data Structures and Algorithms course CSE22, Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz
 *         06.05.2017
 */
public class MainTest
{
	public static void main(String[] args)
	{
		AVLTree<Integer> tree = new AVLTree<>();
		tree.add(40);
		tree.add(20);
		tree.add(7);
		tree.add(15);
		tree.add(5);
		tree.add(10);
		System.out.println(tree);
		System.out.println("\n\n\n");
		tree.delete(10);
		tree.delete(20);
		System.out.println(tree);
	}
}
