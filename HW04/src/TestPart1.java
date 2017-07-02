/**
 * HW04 for Gebze Technical Universtiy
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         21.03.2017.
 */



/* Import(s) */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;



/** Testing of HW4, Q1
 *
 * Tests each Stack class with adding and removing elements
 * Finds the size of each line in test file and writes the size of lines in
 * front of same line. There is different timer for each operation of each
 * stack to determine the result and see which one was faster
 */
public class TestPart1
{
	/** Constant defined variables */
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
		double timer3 = ZERO;
		double timer4 = ZERO;
		double start = ZERO;
		double end = ZERO;
		int type = ZERO;
		
		/* Preparing output file */
		File outputFile = new File("testResult_1.csv");
		outputFile.delete();
		FileWriter outputWriter = null;
		Scanner fileScanner = null;
		
		
		
		try
		{
			outputWriter = new FileWriter(outputFile,true);
		}
		
		catch (IOException e)
		{
			System.out.println("ERROR while opening file: testResult_1.csv");
			System.exit(ERROR_CODE);
		}
		
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
			StackA firstStack;
			StackB secondStack;
			StackC thirdStack;
			StackD fourthStack;
			
			StringBuilder builder1 = new StringBuilder();
			StringBuilder builder2 = new StringBuilder();
			StringBuilder builder3 = new StringBuilder();
			StringBuilder builder4 = new StringBuilder();
			
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
				firstStack = new StackA<Integer>();
				end = System.currentTimeMillis();
				timer1 += (end-start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				secondStack = new StackB<Integer>();
				end = System.currentTimeMillis();
				timer2 += (end-start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				thirdStack = new StackC<Integer>();
				end = System.currentTimeMillis();
				timer3 += (end-start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				fourthStack = new StackD<Integer>();
				end = System.currentTimeMillis();
				timer4 += (end-start)/TIME_DIVIDER;
				
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
					firstStack.push(intArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					secondStack.push(intArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					thirdStack.push(intArray[i]);
					end = System.currentTimeMillis();
					timer3 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					fourthStack.push(intArray[i]);
					end = System.currentTimeMillis();
					timer4 += (end-start)/TIME_DIVIDER;
				}
			}
			
			else if (type == TYPE_DOUBLE)
			{
				start = System.currentTimeMillis();
				firstStack = new StackA<Double>();
				end = System.currentTimeMillis();
				timer1 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				secondStack = new StackB<Double>();
				end = System.currentTimeMillis();
				timer2 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				thirdStack = new StackC<Double>();
				end = System.currentTimeMillis();
				timer3 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				fourthStack = new StackD<Double>();
				end = System.currentTimeMillis();
				timer4 += (end - start)/TIME_DIVIDER;
				
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
					firstStack.push(doubleArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					secondStack.push(doubleArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					thirdStack.push(doubleArray[i]);
					end = System.currentTimeMillis();
					timer3 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					fourthStack.push(doubleArray[i]);
					end = System.currentTimeMillis();
					timer4 += (end-start)/TIME_DIVIDER;
				}
			}
			
			else if (type == TYPE_CHAR)
			{
				start = System.currentTimeMillis();
				firstStack = new StackA<Character>();
				end = System.currentTimeMillis();
				timer1 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				secondStack = new StackB<Character>();
				end = System.currentTimeMillis();
				timer2 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				thirdStack = new StackC<Character>();
				end = System.currentTimeMillis();
				timer3 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				fourthStack = new StackD<Character>();
				end = System.currentTimeMillis();
				timer4 += (end - start)/TIME_DIVIDER;
				
				charArray = new char[arr.length];
				for (int i = 0; i < charArray.length; i++)
				{
					charArray[i] = arr[i].charAt(ZERO);
					
					start = System.currentTimeMillis();
					firstStack.push(charArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					secondStack.push(charArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					thirdStack.push(charArray[i]);
					end = System.currentTimeMillis();
					timer3 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					fourthStack.push(charArray[i]);
					end = System.currentTimeMillis();
					timer4 += (end-start)/TIME_DIVIDER;
				}
			}
			
			else
			{
				start = System.currentTimeMillis();
				firstStack = new StackA<String>();
				end = System.currentTimeMillis();
				timer1 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				secondStack = new StackB<String>();
				end = System.currentTimeMillis();
				timer2 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				thirdStack = new StackC<String>();
				end = System.currentTimeMillis();
				timer3 += (end - start)/TIME_DIVIDER;
				
				start = System.currentTimeMillis();
				fourthStack = new StackD<String>();
				end = System.currentTimeMillis();
				timer4 += (end - start)/TIME_DIVIDER;
				
				
				strArray = new String[arr.length];
				for (int i = 0; i < strArray.length; i++)
				{
					strArray[i] = arr[i];
					
					start = System.currentTimeMillis();
					firstStack.push(strArray[i]);
					end = System.currentTimeMillis();
					timer1 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					secondStack.push(strArray[i]);
					end = System.currentTimeMillis();
					timer2 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					thirdStack.push(strArray[i]);
					end = System.currentTimeMillis();
					timer3 += (end-start)/TIME_DIVIDER;
					
					start = System.currentTimeMillis();
					fourthStack.push(strArray[i]);
					end = System.currentTimeMillis();
					timer4 += (end-start)/TIME_DIVIDER;
				}
			}
			
			
			start = System.currentTimeMillis();
			builder1.append(firstStack.size());
			while (firstStack.size() > ZERO)
			{
				builder1.append(",");
				builder1.append(firstStack.pop());
			}
			end = System.currentTimeMillis();
			timer1 += (end-start)/TIME_DIVIDER;
			
			
			
			start = System.currentTimeMillis();
			builder2.append(secondStack.size());
			while (secondStack.size() > ZERO)
			{
				builder2.append(",");
				builder2.append(secondStack.pop());
			}
			end = System.currentTimeMillis();
			timer2 += (end-start)/TIME_DIVIDER;
			
			
			start = System.currentTimeMillis();
			builder3.append(thirdStack.size());
			while (thirdStack.size() > ZERO)
			{
				builder3.append(",");
				builder3.append(thirdStack.pop());
			}
			end = System.currentTimeMillis();
			timer3 += (end-start)/TIME_DIVIDER;
			
			
			start = System.currentTimeMillis();
			builder4.append(fourthStack.size());
			while (fourthStack.size() > ZERO)
			{
				builder4.append(",");
				builder4.append(fourthStack.pop());
			}
			end = System.currentTimeMillis();
			timer4 += (end-start)/TIME_DIVIDER;
			
			
			
			try
			{
				outputWriter.write(builder1+"\n");
				outputWriter.write(builder2+"\n");
				outputWriter.write(builder3+"\n");
				outputWriter.write(builder4+"\n");
				outputWriter.write("\n");
			}
			
			catch (IOException e)
			{
				System.out.println("ERROR while writing file: testResult_1.csv");
				System.exit(ERROR_CODE);
			}
		}
	
		try
		{
			outputWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("ERROR while closing file: testResult_1.csv");
			System.exit(ERROR_CODE);
		}
		
		System.out.printf("Time of StackA: %.10f\n", timer1);
		System.out.printf("Time of StackB: %.10f\n", timer2);
		System.out.printf("Time of StackC: %.10f\n", timer3);
		System.out.printf("Time of StackD: %.10f\n", timer4);
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