/* Main.java
 *
 * This is the main function to test recycler single linked list
 *
 * Deniz Can Erdem Yılmaz / 151044001 / Gebze Technical University 
 * 14 March 2017
 */



public class Main
{
	public static void main(String args[])
	{
		ReUseableLinkedList<Integer> list = new ReUseableLinkedList<Integer>();
		
		
		/* Adding and removing integers to test list */
		for (int i=0; i<100; ++i)
			list.add(i);
		
		list.print();
		System.out.println("\n");
		
		for (int i=0; i<50; ++i)
			list.remove();
		
		System.out.println("\n");
		list.print();
		
		for (int i=0; i<100; ++i)
			list.add(i);
		
		System.out.println("\n");
		list.print();
	}
}

/* End of Main.java */
