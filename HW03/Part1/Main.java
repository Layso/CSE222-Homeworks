/* Main.java
 *
 * This is a main file to test all toString methods of the MyStringBuilder class
 * to compare their performance
 *
 * Deniz Can Erdem Yılmaz / 151044001 / Gebze Technical University 
 * 14 March 2017
 */



/* Impoert(s) */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Main
{
	/** Constant definitions */
	private static final String INPUT_FILE_NAME = "numbers.txt";
	private static final String OUTPUT_FILE_NAME_1 = "result1.txt";
	private static final String OUTPUT_FILE_NAME_2 = "result2.txt";
	private static final String OUTPUT_FILE_NAME_3 = "result3.txt";
	private static final int NUMBER_OF_INTEGERS = 100000;
	private static final int MAX_INTEGER = 100;
	private static final int EXIT_ERROR_CODE = 1;
	private static final double TIME_DIVIDER = 1000.0;
	
	
	public static void main(String args[])
	{
		MyStringBuilder builder = new MyStringBuilder();
		File inputFile = new File(INPUT_FILE_NAME);
		Scanner input = null;
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		long timeStart;
		long timeEnd;
		double first;
		double second;
		double third;
		
		
		/* Rewriting the file with random numbers */
		randomNumberWriter();
		
		/* Reading numbers from file */
		try	
		{
			input = new Scanner(inputFile);
			for (int i=0; i<NUMBER_OF_INTEGERS; ++i)
			{
				int number = input.nextInt();
				builder.append(number + " ");
			}
			input.close();
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Problem with opening file: " + INPUT_FILE_NAME);
			System.exit(EXIT_ERROR_CODE);
		}
		
		
		/* Testing first toString implemented with getter and index */
		System.out.println("Test starts for toString implemented with getter");
		timeStart = System.currentTimeMillis();
		builder.toString1();
		timeEnd = System.currentTimeMillis();
		System.out.println("Execution has been completed\n");
		first = (timeEnd - timeStart)/TIME_DIVIDER;
		
		/* Testing second toString implemented with iterator */
		System.out.println("Test starts for toString implemented with iterator");
		timeStart = System.currentTimeMillis();
		builder.toString2();
		timeEnd = System.currentTimeMillis();
		System.out.println("Execution has been completed\n");
		second = (timeEnd - timeStart)/TIME_DIVIDER;
		
		/* Testing second toString implemented with LinkedList's toString() */
		System.out.println("Test starts for toString implemented with LinkedList's toString");
		timeStart = System.currentTimeMillis();
		builder.toString3();
		timeEnd = System.currentTimeMillis();
		System.out.println("Execution has been completed\n\n");
		third = (timeEnd - timeStart)/TIME_DIVIDER;
		
		
		/* Writing results to log files */
		try
		{
			fileWriter = new FileWriter(OUTPUT_FILE_NAME_1);
			writer = new BufferedWriter(fileWriter);
			writer.write(first + " second(s). (Implemented with getter & index)");
			writer.flush();
			
			fileWriter = new FileWriter(OUTPUT_FILE_NAME_2);
			writer = new BufferedWriter(fileWriter);
			writer.write(second + " second(s). (Implemented with iterator)");
			writer.flush();
			
			fileWriter = new FileWriter(OUTPUT_FILE_NAME_3);
			writer = new BufferedWriter(fileWriter);
			writer.write(third + " second(s). (Implemented with LinkedList's toString())");
			writer.flush();
			
			writer.close();
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Problem with opening log files");
			System.exit(EXIT_ERROR_CODE);
		}
		
		catch (IOException e)
		{
			System.out.println("Problem with writing log files");
			System.exit(EXIT_ERROR_CODE);
		}
		
		
		/* Printing the result */
		System.out.println("First toString(): " + first + " seconds (getter)");
		System.out.println("Second toString(): " + second + " seconds (iterator)");
		System.out.println("Third toString(): " + third + " seconds (LinkedList toString())");
	}
	
	
	
	/** A function to write 100.000 random numbers to numbers.txt to test 
	  * toString methods of the StringBuilder
	  */
	static void randomNumberWriter()
	{
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		
		
		/* Opens file and writes random numbers until loop reaches wanted count */
		try	
		{
			fileWriter = new FileWriter(INPUT_FILE_NAME);
			writer = new BufferedWriter(fileWriter);
			
			for (int i=0; i<NUMBER_OF_INTEGERS; ++i)
			{
				Random rand = new Random();
				int  random = rand.nextInt(MAX_INTEGER);
				writer.write(Integer.toString(random) + " ");
				writer.flush();
			}
			writer.close();
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Problem with opening log files");
			System.exit(EXIT_ERROR_CODE);
		}
		
		catch (IOException e)
		{
			System.out.println("Problem with writing log files");
			System.exit(EXIT_ERROR_CODE);
		}
	}
}

/* End of Main.java */
