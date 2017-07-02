import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



/**
 * HW09 for Data Structures and Algorithms course CSE22, Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz
 *         17.05.2017
 */
public class Main
{
	public static String INPUT_FILE1 = "bipartite.txt";
	public static String INPUT_FILE2 = "mixed.txt";
	
	static ArrayList<String> outputFiles = new ArrayList<String>();
	static int testCount = 0;
	
	
	
	public static void main(String[] args)
	{
		try
		{
			System.out.println("***************************************************************************************************************");
			System.out.println("***************************************************************************************************************");
			System.out.println("*****************************************      TEST ONE      **************************************************");
			System.out.println("***************************************************************************************************************");
			System.out.println("***************************************************************************************************************");
			test(INPUT_FILE1);
			System.out.println("\n\n\n");
			System.out.println("***************************************************************************************************************");
			System.out.println("***************************************************************************************************************");
			System.out.println("*****************************************      TEST TWO      **************************************************");
			System.out.println("***************************************************************************************************************");
			System.out.println("***************************************************************************************************************");
			test(INPUT_FILE2);
			
			if (outputFiles.size() > 0)
			{
				System.out.println("\n\n\n");
				System.out.println("Would you like to delete (" + outputFiles.size() + ") output files created after tests?");
				System.out.println("Press ENTER to DELETE all of them (any other input will allow them to exist)");
				Scanner scan = new Scanner(System.in);
				
				if (scan.nextLine().equals(""))
					while (!outputFiles.isEmpty())
					{
						String fileNameToDelete = outputFiles.remove(0);
						System.out.println("Deleting file: " + fileNameToDelete);
						(new File(fileNameToDelete)).delete();
					}
				
				else
				{
					System.out.println("Remaining files:");
					while (!outputFiles.isEmpty())
						System.out.println(outputFiles.remove(0));
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
	
	static void test(String inputFile) throws IOException
	{
		long bool = (System.currentTimeMillis()%2);
		boolean direction;
		
		
		if (bool == 1)
			direction = true;
		else
			direction = false;
		
		
		testList(inputFile, direction);
		System.out.println("\n\n\n");
		testMatrix(inputFile, !direction);
	}
	
	
	
	static void testMatrix(String inputName, boolean directed) throws IOException
	{
		Scanner input = new Scanner(new File(inputName));
		Random random = new Random();
		int addRandom = Math.abs(random.nextInt(5));
		int added = 0;
		int[] search = null;
		String resultStr = null;
		Graph[] connectedGraphs = null;
		
		
		System.out.println("+++++++++++++++++++++++++++++++++++++     TESTING MATRIX     +++++++++++++++++++++++++++++++++++++++++++++++++++");
		MatrixGraph graph = (MatrixGraph) AbstractGraph.createGraph(input, directed, "Matrix");
		System.out.println("Initializing a MatrixGraph\nFrom file: " + inputName + "\nDirection: " + directed);
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.println("Trying to insert max " + addRandom + " edges to MatrixGraph");
		added = graph.addRandomEdgesToGraph(addRandom);
		System.out.println(added + " edges inserted to MatrixGraph");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		connectedGraphs = graph.getConnectedComponentUndirectedGraph();
		if (!graph.isDirected())
		{
			int total = 0;
			System.out.println(connectedGraphs.length + " graphs produced from graph (with random insertions) given on file: " + inputName);
			for (int i = 0; i < connectedGraphs.length; i++)
			{
				String subFileName = "test" + testCount + "_subGraph" + (i + 1) + ".txt";
				total += connectedGraphs[i].getNumV();
				System.out.println("Writing sub-Graph" + (i + 1) + " with " + connectedGraphs[i].getNumV() + " vertex to file: " + subFileName);
				((AbstractGraphExtended) connectedGraphs[i]).writeGraphToFile(subFileName);
				outputFiles.add(subFileName);
			}
			System.out.println("Separated total count: " + total);
			System.out.println("Graph's initial count: " + graph.getNumV());
		}
		else
			System.out.println("getConnectedComponentUndirectedGraph method failed due to directed graph (result: " + connectedGraphs + ")");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.println("Parent table:");
		search = graph.breadthFirstSearch(AbstractGraphExtended.ZERO);
		for (int i = 0; i < search.length; i++)
			System.out.println(i + "  :  " + search[i]);
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		resultStr = (graph.isBipartiteUndirectedGraph()) ? "BIPARTITE" : "NOT BIPARTITE";
		if (!graph.isDirected())
			System.out.println("Current graph constructed from file " + inputName + " and " + added + " new edges added is " + resultStr);
		else
			System.out.println("isBipartiteUndirectedGraph method failed due to directed graph (result: " + resultStr + ")");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		String output = "test" + testCount + "_result.txt";
		graph.writeGraphToFile(output);
		System.out.println("Writing result graph to the file: " + output);
		outputFiles.add(output);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		++testCount;
	}
	
	
	
	static void testList(String inputName, boolean directed) throws IOException
	{
		Scanner input = new Scanner(new File(inputName));
		Random random = new Random();
		int addRandom = Math.abs(random.nextInt(5));
		int added = 0;
		int[] search = null;
		String resultStr = null;
		Graph[] connectedGraphs = null;
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++     TESTING LIST     +++++++++++++++++++++++++++++++++++++++++++++++++++");
		ListGraph graph = (ListGraph) AbstractGraph.createGraph(input, directed, "List");
		System.out.println("Initializing a ListGraph\nFrom file: " + inputName + "\nDirection: " + directed);
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.println("Trying to insert max " + addRandom + " edges to ListGraph");
		added = graph.addRandomEdgesToGraph(addRandom);
		System.out.println(added + " edges inserted to ListGraph");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		
		
		connectedGraphs = graph.getConnectedComponentUndirectedGraph();
		if (!graph.isDirected())
		{
			int total = 0;
			System.out.println(connectedGraphs.length + " graphs produced from graph (with random insertions) given on file: " + inputName);
			for (int i = 0; i < connectedGraphs.length; i++)
			{
				String subFileName = "test" + testCount + "_subGraph" + (i + 1) + ".txt";
				total += connectedGraphs[i].getNumV();
				System.out.println("Writing sub-Graph" + (i + 1) + " with " + connectedGraphs[i].getNumV() + " vertex to file: " + subFileName);
				((AbstractGraphExtended) connectedGraphs[i]).writeGraphToFile(subFileName);
				outputFiles.add(subFileName);
			}
			System.out.println("Separated total count: " + total);
			System.out.println("Graph's initial count: " + graph.getNumV());
		}
		else
			System.out.println("getConnectedComponentUndirectedGraph method failed due to directed graph (result: " + connectedGraphs + ")");
		
		
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.println("Parent table:");
		search = graph.breadthFirstSearch(AbstractGraphExtended.ZERO);
		for (int i = 0; i < search.length; i++)
			System.out.println(i + "  :  " + search[i]);
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		resultStr = (graph.isBipartiteUndirectedGraph()) ? "BIPARTITE" : "NOT BIPARTITE";
		if (!graph.isDirected())
			System.out.println("Current graph constructed from file " + inputName + " and " + added + " new edges added is " + resultStr);
		else
			System.out.println("isBipartiteUndirectedGraph method failed due to directed graph (result: " + resultStr + ")");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		String output = "test" + testCount + "_result.txt";
		graph.writeGraphToFile(output);
		System.out.println("Writing result graph to the file: " + output);
		outputFiles.add(output);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		++testCount;
	}
}
