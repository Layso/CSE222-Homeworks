/**
 * HW04 for Gebze Technical Universtiy
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *      22.03.2017.
 */



/* Import(s) */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;



/** Testing of HW4, Q2
 *
 * Tests reversed functions for queue structure. Takes elements from test file
 * to 2 queue instances and reverses them with different timers to find which
 * methods works faster. Writes result file the input in reversed row and cols
 */
public class TestPart2
{
	private static final String TEST_FILE = "test.csv";
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final double TIME_DIVIDER = 1000.0;
	private static final int ERROR_CODE = 1;
	private static final int TYPE_INT = 0;
	private static final int TYPE_DOUBLE = 1;
	private static final int TYPE_CHAR = 2;
	private static final int TYPE_STRING = 3;
	
	public static void main(String args[])
	{
		double timer1 = ZERO;
		double timer2 = ZERO;
		double start = ZERO;
		double end = ZERO;
		int type = ZERO;
		
		File outputFile = new File("testResult_2.csv");
		outputFile.delete();
		
		FileWriter outputWriter = null;
		Scanner fileScanner = null;
		MyQueue<MyQueue> queueOfQueues1 = new MyQueue<MyQueue>();
		MyQueue<MyQueue> queueOfQueues2 = new MyQueue<MyQueue>();
		
		
		
		try
		{
			fileScanner = new Scanner(new File(TEST_FILE));
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR while opening file: " + TEST_FILE);
			System.exit(ERROR_CODE);
		}
		
		
		while (fileScanner.hasNextLine())
		{
			MyQueue queue1;
			MyQueue queue2;
			
			int[] intArray;
			double[] doubleArray;
			char[] charArray;
			String[] strArray;
			
			String line = fileScanner.nextLine();
			String[] arr = line.split(",");
			
			switch (TypeDetect(arr[ZERO]))
			{
				case 0: type = TYPE_INT; break;
				case 1: type = TYPE_DOUBLE; break;
				case 2: type = TYPE_CHAR; break;
				case 3: type = TYPE_STRING; break;
				default:
					System.out.println("ERROR: On function TypeDetect()");
					System.exit(ERROR_CODE);
			}
			
			if (type == TYPE_INT)
			{
				queue1 = new MyQueue<Integer>();
				queue2 = new MyQueue<Integer>();
				
				intArray = new int[arr.length];
				for (int i = 0; i < intArray.length; i++)
				{
					try
					{
						intArray[i] = Integer.parseInt(arr[i]);
					} catch (InputMismatchException e)
					{
						System.out.println("ERROR! Input mismatch error");
						System.exit(ERROR_CODE);
					} catch (NumberFormatException e)
					{
						System.out.println("ERROR! Number format error");
						System.exit(ERROR_CODE);
					} catch (Exception e)
					{
						System.out.println("ERROR! Unexpected error");
						System.exit(ERROR_CODE);
					}
					
					
					queue1.offer(intArray[i]);
					queue2.offer(intArray[i]);
				}
			}
			
			else if (type == TYPE_DOUBLE)
			{
				queue1 = new MyQueue<Double>();
				queue2 = new MyQueue<Double>();
				
				doubleArray = new double[arr.length];
				for (int i = 0; i < doubleArray.length; i++)
				{
					try
					{
						doubleArray[i] = Double.parseDouble(arr[i]);
					} catch (InputMismatchException e)
					{
						System.out.println("ERROR! Input mismatch error");
						System.exit(ERROR_CODE);
					} catch (NumberFormatException e)
					{
						System.out.println("ERROR! Number format error");
						System.exit(ERROR_CODE);
					} catch (Exception e)
					{
						System.out.println("ERROR! Unexpected error");
						System.exit(ERROR_CODE);
					}
					
					
					queue1.offer(doubleArray[i]);
					queue2.offer(doubleArray[i]);
				}
			}
			
			else if (type == TYPE_CHAR)
			{
				queue1 = new MyQueue<Character>();
				queue2 = new MyQueue<Character>();
				
				charArray = new char[arr.length];
				for (int i = 0; i < charArray.length; i++)
				{
					charArray[i] = arr[i].charAt(ZERO);
					queue1.offer(charArray[i]);
					queue2.offer(charArray[i]);
				}
			}
			
			else
			{
				queue1 = new MyQueue<String>();
				queue2 = new MyQueue<String>();
				
				strArray = new String[arr.length];
				for (int i = 0; i < strArray.length; i++)
				{
					strArray[i] = arr[i];
					queue1.offer(strArray[i]);
					queue2.offer(strArray[i]);
				}
			}
			
			
			queueOfQueues1.offer(queue1);
			queueOfQueues2.offer(queue2);
		}
		
		

		start = System.currentTimeMillis();
		queueOfQueues1.reverseQueue();
		end = System.currentTimeMillis();
		timer1 += (end-start)/TIME_DIVIDER;
		
		start = System.currentTimeMillis();
		reverseQueue(queueOfQueues2);
		end = System.currentTimeMillis();
		timer2 += (end-start)/TIME_DIVIDER;
		
		while (!queueOfQueues1.isEmpty() && !queueOfQueues2.isEmpty())
		{
			MyQueue queue1 = queueOfQueues1.poll();
			start = System.currentTimeMillis();
			queue1.reverseQueue();
			end = System.currentTimeMillis();
			timer1 += (end-start)/TIME_DIVIDER;
			
			MyQueue queue2 = queueOfQueues2.poll();
			start = System.currentTimeMillis();
			reverseQueue(queue2);
			end = System.currentTimeMillis();
			timer1 += (end-start)/TIME_DIVIDER;
			
			StringBuilder builder1 = new StringBuilder();
			StringBuilder builder2 = new StringBuilder();
			
			
			while (!queue1.isEmpty() && !queue2.isEmpty())
			{
				builder1.append(queue1.poll());
				builder1.append(",");
				
				builder2.append(queue2.poll());
				builder2.append(",");
			}
			
			try
			{
				outputWriter = new FileWriter(outputFile,true);
			}
			
			catch (IOException e)
			{
				System.out.println("ERROR while opening file: testResult_2.csv");
				System.exit(ERROR_CODE);
			}
			
			
			builder1.append("\n");
			builder2.append("\n");
			try
			{
				outputWriter.write(builder1.toString());
				outputWriter.write(builder2.toString());
				outputWriter.write("\n");
				outputWriter.close();
			} catch (IOException e)
			{
				System.out.println("ERROR while writing file: testResult_2.csv");
				System.exit(ERROR_CODE);
			}
		}
		
		System.out.printf("Normal reverse method   : %.10f\n", timer1);
		System.out.printf("Recursive reverse method: %.10f\n", timer2);
	}
	
	
	
	static int TypeDetect(String item)
	{
		Scanner detect = new Scanner(item);
		
		if (detect.hasNextInt())
			return TYPE_INT;
		
		else if (detect.hasNextDouble())
			return TYPE_DOUBLE;
		
		else if (detect.hasNext())
		{
			if (((String) item).length() == ONE)
				return TYPE_CHAR;
			else
				return TYPE_STRING;
		}
		
		else
			return ERROR_CODE;
	}
	
	
	
	static <R> void reverseQueue(MyQueue<R> que)
	{
		if (!que.isEmpty())
		{
			R item = que.poll();
			reverseQueue(que);
			que.offer(item);
		}
	}
}