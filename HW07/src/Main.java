import java.util.NavigableMap;



public class Main
{
	public static void main(String args[])
	{
		final Boolean q1 = Q1Test();
		final Boolean q2 = Q2Test();
		if (q1 == q2 == Boolean.TRUE)
		{
			System.out.println("Your tests is done. Make sure that you test all methods of class!! ");
			return;
		}
	}
	
	
	
	public static Boolean Q1Test()
	{
		String testing = "foca";
		NavigableMap<String, String> turkey = new BinaryNavMap<String, String>();
		turkey.put("uskudar", "istanbul");
		turkey.put("kadıkoy", "istanbul");
		turkey.put("cekirge", "bursa");
		turkey.put("gebze", "kocaeli");
		turkey.put("niksar", "tokat");
		turkey.put("kecıoren", "ankara");
		turkey.put("aksaray", "istanbul");
		turkey.put("foca", "izmir");
		turkey.put("manavgat", "antalya");
		turkey.put("kahta", "adıyaman");
		turkey.put("biga", "canakkale");
		
		
		
		System.out.println("The original set odds is " + turkey);
		NavigableMap<String, String> m = turkey.subMap("uskudar", true, "gebze", false);
		System.out.println("The ordered set m is " + m);
		System.out.println("Set of navigable: " + turkey.entrySet());
		System.out.println();
		System.out.println("The first entry is " + turkey.firstEntry());
		System.out.println("The first key is " + turkey.firstKey());
		System.out.println();
		System.out.println("The last entry is " + turkey.lastEntry());
		System.out.println("The last key is " + turkey.lastKey());
		System.out.println();
		System.out.println("Ceiling entry of "+ testing +" is: " + turkey.ceilingEntry(testing));
		System.out.println("Ceiling key of "+ testing +" is: " + turkey.ceilingKey(testing));
		System.out.println();
		System.out.println("Higher entry of "+ testing +" is: " + turkey.higherEntry(testing));
		System.out.println("Higher key of "+ testing +" is: " + turkey.higherKey(testing));
		System.out.println();
		System.out.println("Floor entry of "+ testing +" is: " + turkey.floorEntry(testing));
		System.out.println("Floor key of "+ testing +" is: " + turkey.floorKey(testing));
		System.out.println();
		System.out.println("Lower entry of "+ testing +" is: " + turkey.lowerEntry(testing));
		System.out.println("Lower key of "+ testing +" is: " + turkey.lowerKey(testing));
		System.out.println();
		System.out.println("Tail map of "+ testing +" is: " + turkey.tailMap(testing));
		System.out.println("Head map of "+ testing +" is: " + turkey.headMap(testing));
		System.out.println();
		System.out.println("Poll first: " + turkey.pollFirstEntry());
		System.out.println("Poll last: " + turkey.pollLastEntry());
		
		//you should write more test function to show your solution
		//your test must contain all methods to get full points!!!
		//you also may need to owerwrite some methods to provide BST RULES

        /* *some links to help you

           https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html
           https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.html

        * */
		return Boolean.TRUE;
		
	}
	
	
	
	public static Boolean Q2Test()
	{
		HashMap<String, String> turkey = new HashTableChaining<String, String>();
		turkey.put("edremit", "balikesir");
		turkey.put("edremit", "van");
		turkey.put("kemalpasa", "bursa");
		turkey.put("kemalpasa", "izmir");
		turkey.put("ortakoy", "istanbul");//we assume a district
		turkey.put("ortakoy", "aksaray");
		turkey.put("ortakoy", "corum");
		turkey.put("kecıoren", "ankara");
		turkey.put("pinarbasi", "kastamonu");
		turkey.put("pinarbasi", "kayseri");
		turkey.put("eregli", "konya");
		turkey.put("eregli", "tekirdag");
		turkey.put("eregli", "zonguldak");
		turkey.put("golbasi", "adıyaman");
		turkey.put("golbasi", "ankara");
		turkey.put("biga", "canakkale");

        /* *test all

            V get(Object key);

            V put(K key, V value);

            V remove(Object key);

            int size();

        * */
		System.out.println(turkey.get("pinarbasi"));
		System.out.println(turkey.put("baglarbasi", "istanbul"));
		System.out.println(turkey.remove("biga"));
		System.out.println(turkey.size());
		return Boolean.TRUE;
	}
	
	
}
