import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * HW04 for Gebze Technical Universtiy
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         25.03.2017.
 */



/** Testing of HW4, Q2
 *
 * Tests reversed functions for queue structure. Takes elements from test file
 * to 2 queue instances and reverses them with different timers to find which
 * methods works faster. Writes result file the input in reversed row and cols
 */
public class TestPart3
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
	
	
	
	public static void main(String[] args)
	{
		double timer1 = ZERO;
		double timer2 = ZERO;
		double start = ZERO;
		double end = ZERO;
		int type = ZERO;
		
		File outputFile = new File("testResult_3.csv");
		outputFile.delete();
		
		FileWriter       outputWriter   = null;
		Scanner          fileScanner    = null;
		PriorityQueueA queueA;
		PriorityQueueB queueB;
		
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
				start = System.currentTimeMillis();
				queueA = new PriorityQueueA<Integer>();
				end = System.currentTimeMillis();
				timer1 += (end-start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				queueB = new PriorityQueueB<Integer>();
				end = System.currentTimeMillis();
				timer2 += (end-start)/TIME_DIVIDER;
				
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
					
					
					start = System.currentTimeMillis();
					queueA.offer(intArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					queueB.offer(intArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
				}
			}
			
			else if (type == TYPE_DOUBLE)
			{
				start = System.currentTimeMillis();
				queueA = new PriorityQueueA<Double>();
				end = System.currentTimeMillis();
				timer1 += (end-start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				queueB = new PriorityQueueB<Double>();
				end = System.currentTimeMillis();
				timer2 += (end-start)/TIME_DIVIDER;
				
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
					
					
					start = System.currentTimeMillis();
					queueA.offer(doubleArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					queueB.offer(doubleArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
				}
			}
			
			else if (type == TYPE_CHAR)
			{
				start = System.currentTimeMillis();
				queueA = new PriorityQueueA<Character>();
				end = System.currentTimeMillis();
				timer1 += (end-start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				queueB = new PriorityQueueB<Character>();
				end = System.currentTimeMillis();
				timer2 += (end-start)/TIME_DIVIDER;
				
				charArray = new char[arr.length];
				for (int i = 0; i < charArray.length; i++)
				{
					charArray[i] = arr[i].charAt(ZERO);
					
					start = System.currentTimeMillis();
					queueA.offer(charArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					queueB.offer(charArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
				}
			}
			
			else
			{
				start = System.currentTimeMillis();
				queueA = new PriorityQueueA<String>();
				end = System.currentTimeMillis();
				timer1 += (end-start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				queueB = new PriorityQueueB<String>();
				end = System.currentTimeMillis();
				timer2 += (end-start)/TIME_DIVIDER;
				
				strArray = new String[arr.length];
				for (int i = 0; i < strArray.length; i++)
				{
					strArray[i] = arr[i];
					
					start = System.currentTimeMillis();
					queueA.offer(strArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					queueB.offer(strArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
				}
			}
			
			
			while (!queueA.isEmpty() && !queueB.isEmpty())
			{
				StringBuilder builder1 = new StringBuilder();
				StringBuilder builder2 = new StringBuilder();
				
				
				while (!queueA.isEmpty() && !queueB.isEmpty())
				{
					start = System.currentTimeMillis();
					builder1.append(queueA.poll());
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					builder1.append(",");
					
					start = System.currentTimeMillis();
					builder2.append(queueB.poll());
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
					builder2.append(",");
				}
				
				try
				{
					outputWriter = new FileWriter(outputFile,true);
				}
				
				catch (IOException e)
				{
					System.out.println("ERROR while opening file: testResult_3.csv");
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
					System.out.println("ERROR while writing file: testResult_3.csv");
					System.exit(ERROR_CODE);
				}
			}
		}
		
		System.out.printf("PriorityQueueA time: %.10f\n", timer1);
		System.out.printf("PriorityQueueB time: %.10f\n", timer2);
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
}
