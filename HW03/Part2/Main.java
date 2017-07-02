/* Main.java
 *
 * This is the main class to test reverseToString method of LinkedList
 *
 * Deniz Can Erdem Yılmaz / 151044001 / Gebze Technical University 
 * 14 March 2017
 */
 
 

public class Main
{
	public static void main(String args[])
	{
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		
		System.out.println("Normal toString():");
		System.out.println(list.toString());
		System.out.println("");
		System.out.println("Reverse toString():");
		System.out.println(list.reverseToString());
	}
}

/* End of Main.java */
