import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;



/**
 * HW09 for Data Structures and Algorithms course CSE22, Gebze Technical University
 * A mid layer for Graph classes to implement some other methods used for Matrix
 * and List graphs. addRandomEdgesToGraph, breadthFirstSearch,
 * getConnectedComponentUndirectedGraph, isBipartiteUndirectedGraph,
 * writeGraphToFile methods are required public methods for this homework.
 * graphCounter, vertexCounter, buildGraphFromTable, indexHandler,
 * createSameGraphType, getAllEdges, getFlag methods are private helpers for
 * those methods
 *
 * @author Deniz Can Erdem Yılmaz
 *         17.05.2017
 */
public abstract class AbstractGraphExtended extends AbstractGraph
{
	/** Constant definition */
	public static final int ZERO = 0;
	/** Constant definition */
	public static final int ONE = 1;
	/** Constant definition */
	public static final int NOT_BOUND = -1;
	/** Constant definition */
	public static final int NEUTRAL = 0;
	/** Constant definition */
	public static final int RED = 1;
	/** Constant definition */
	public static final int BLUE = -1;
	
	
	
	/**
	 * Construct a graph with the specified number of vertices
	 * and the directed flag. If the directed flag is true,
	 * this is a directed graph.
	 *
	 * @param numV     The number of vertices
	 * @param directed The directed flag
	 */
	public AbstractGraphExtended(int numV, boolean directed)
	{
		super(numV, directed);
	}
	
	
	
	/**
	 * Method to add random number of edges to the current graph. Maximum number
	 * of edges that can be added has given as an int parameter
	 *
	 * @param edgeLimit Maximum number of edges can be inserted
	 * @return Returns the number of edges inserted
	 */
	public int addRandomEdgesToGraph(int edgeLimit)
	{
		if (edgeLimit > ZERO)
		{
			Random random = new Random();
			int count = ZERO;
			int edge = ONE;
			
			
			if (edgeLimit != ONE)
				edge = random.nextInt(edgeLimit - ONE) + ONE;
			
			for (int i=0; i<edge; ++i)
			{
				int source = random.nextInt(getNumV());
				int destination = random.nextInt(getNumV());
				
				if (!isEdge(source, destination))
				{
					insert(new Edge(source, destination));
					++count;
				}
			}
			
			return count;
		}
		
		else
			return ZERO;
	}
	
	
	
	/**
	 * Method to search graph in breadth first order. All vertices connected
	 * to given starter index found and their parent index has placed in a
	 * parent table (int array) to return
	 *
	 * @param start Starting vertex (index)
	 * @return int array that contains parent indices
	 */
	public int[] breadthFirstSearch(int start)
	{
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] parent = new int[getNumV()];
		boolean[] identified = new boolean[getNumV()];
		
		
		for (int i = 0; i < parent.length; i++)
			parent[i] = NOT_BOUND;
		queue.offer(start);
		
		while (!queue.isEmpty())
		{
			int current = queue.poll();
			Iterator<Edge> it = edgeIterator(current);
			
			while (it.hasNext())
			{
				Edge edge = it.next();
				int neighbor = edge.getDest();
				
				if (!identified[neighbor])
				{
					identified[neighbor] = true;
					queue.offer(neighbor);
					parent[neighbor] = current;
				}
			}
		}
		
		return parent;
	}
	
	
	
	/**
	 * Finds all connected vertex groups and divides graph into sub graphs
	 * to return them in a graph array. Only works if graph is undirected.
	 * Directed graphs return null. Main graphs and sub graphs are same type
	 * (matrix or list)
	 *
	 * @return Connected components into divided graphs as graphs array
	 */
	public Graph[] getConnectedComponentUndirectedGraph()
	{
		Graph[] result = null;
		
		
		if (!isDirected())
		{
			ArrayList<Integer> starters = graphCounter();
			result = new Graph[starters.size()];
			
			for (int i = 0; i < starters.size(); ++i)
			{
				int[] vertexTable = breadthFirstSearch(starters.get(i));
				result[i] = buildGraphFromTable(vertexTable, starters.get(i));
			}
		}
		
		return result;
	}
	
	
	
	/**
	 * Checks whether the graph is bipartite or not. Works for undirected graphs
	 * only. Returns false for directed graphs.
	 *
	 * @return True if graph is bipartite, false if not or directed
	 */
	public boolean isBipartiteUndirectedGraph()
	{
		int[] flags = new int[getNumV()];
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList<Integer> graphs = null;
		int flag = RED;
		
		
		if (isDirected())
			return false;
		
		
		graphs = graphCounter();
		
		for (int i = 0; i < flags.length; i++)
			flags[i] = NEUTRAL;
		
		
		while (!graphs.isEmpty())
		{
			int starter = graphs.remove(ZERO);
			flags[starter] = RED;
			queue.add(starter);
			
			while (!queue.isEmpty())
			{
				int current = queue.poll();
				Iterator<Edge> it = edgeIterator(current);
				
				while (it.hasNext())
				{
					Edge edge = it.next();
					int neighbor = edge.getDest();
					if (flags[neighbor] == NEUTRAL)
					{
						flags[neighbor] = getFlag(flags[current]);
						queue.add(neighbor);
					}
					
					else if (flags[neighbor] != getFlag(flags[current]))
						return false;
				}
			}
		}
		
		
		return true;
	}
	
	
	
	/**
	 * Writes graph into an output file in example input format
	 *
	 * @param fileName File name to write output in
	 */
	public void writeGraphToFile(String fileName)
	{
		String outputString = getAllEdges();
		
		
		try
		{
			PrintWriter writer = new PrintWriter(fileName);
			writer.print(getNumV());
			writer.print(outputString);
			writer.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Helper method to find how many connected graphs are there in main graph
	 *
	 * @return An arraylist contains smallest indices for connected graphs
	 */
	private ArrayList<Integer> graphCounter()
	{
		int[] searchResult = null;
		ArrayList<Boolean> visited = new ArrayList<Boolean>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		int graphCount = ONE, current = ZERO;
		boolean alone, completed = false;
		
		
		for (int i = 0; i < getNumV(); i++)
			visited.add(false);
		
		while (!completed)
		{
			alone = true;
			searchResult = breadthFirstSearch(current);
			result.add(current);
			
			for (int i = 0; i < searchResult.length; i++)
				if (searchResult[i] != NOT_BOUND)
				{
					visited.set(i, true);
					alone = false;
				}
			
			if (alone)
				visited.set(current, true);
			
			
			current = visited.indexOf(false);
			if (current == -1)
				completed = true;
			else
				++graphCount;
		}
		
		return result;
	}
	
	
	
	/**
	 * Takes the parent table (result of breadthFirstSearch method) to count
	 * how many vertex has parent parent vertex to determine the connected
	 * vertex number
	 *
	 * @param searchTable Parent table returned from BFS method
	 * @return Number of connected vertices in table
	 */
	private int vertexCounter(int[] searchTable)
	{
		int result = ZERO;
		boolean alone = true;
		
		
		for (int i = 0; i < searchTable.length; i++)
			if (searchTable[i] != NOT_BOUND)
			{
				++result;
				alone = false;
			}
		
		if (alone)
			++result;
		
		
		return result;
	}
	
	
	
	/**
	 * Graph builder to create new graph from given search table (BFS method)
	 *
	 * @param table return value of BFS method, search table
	 * @param head  index where search started on search table
	 * @return Graph that constructed from search table, same type as main graph
	 */
	private Graph buildGraphFromTable(int[] table, int head)
	{
		Graph result = null;
		int vertex = vertexCounter(table);
		
		
		if (vertex == ONE)
			result = createSameGraphType(vertex);
		
		else
		{
			result = createSameGraphType(vertex);
			int[] indexTable = new int[getNumV()];
			
			for (int i = 0; i < indexTable.length; i++)
				indexTable[i] = NOT_BOUND;
			
			for (int i = 0; i < table.length; i++)
				for (int j = 0; j < getNumV(); j++)
					if (table[i] != NOT_BOUND && isEdge(i, j))
					{
						result.insert(new Edge(indexHandler(i, indexTable), indexHandler(j, indexTable)));
						/*
						System.out.println("OLD  ||  [" + i + " : " + j + "]");
						System.out.println("NEW  ||  [" + indexHandler(i, indexTable) + " : " + indexHandler(j, indexTable) + "]\n");
						*/
					}
		}
		
		return result;
	}
	
	
	
	/**
	 * Index handler method for buildGraphFromTable method to reduce vertex
	 * indexes to ones that can be inserted to new graph
	 * @param oldIndex Index of vertex in old graph
	 * @param table New index table to fill or find old one
	 * @return New index equivalent of old index
	 */
	private int indexHandler(int oldIndex, int[] table)
	{
		int i;
		
		for (i = 0; table[i] != NOT_BOUND; ++i)
			if (table[i] == oldIndex)
				return i;
		
		table[i] = oldIndex;
		return i;
	}
	
	
	
	/**
	 * Determines the main graph's type and creates new graph with given vertex
	 * number as same type to return
	 * @param vertexNumber Vertex number to give constructor
	 * @return New graph that is same type with main graph
	 */
	private Graph createSameGraphType(int vertexNumber)
	{
		if (this instanceof MatrixGraph)
			return new MatrixGraph(vertexNumber, isDirected());
		
		else if (this instanceof ListGraph)
			return new ListGraph(vertexNumber, isDirected());
		
		else
		{
			System.out.println("Unsupported graph type: " + this.getClass());
			return null;
		}
	}
	
	
	
	/**
	 * Finds all edges inside the graph to format for output file
	 * @return Formatted string equivalent of all edges inside the graph
	 */
	private String getAllEdges()
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < getNumV(); i++)
			for (int j = 0; j < getNumV(); j++)
				if (isEdge(i, j) && i < j)
					builder.append("\n" + i + " " + j);
		
		return builder.toString();
	}
	
	
	
	/**
	 * Bipartite helper method to determine inverse of the given flag
	 * @param flag Flag to take inverse
	 * @return Inverse of the parameter flag
	 */
	private int getFlag(int flag)
	{
		if (flag == RED)
			return BLUE;
		else if (flag == BLUE)
			return RED;
		else
			return NEUTRAL;
	}
}
