import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Queue;



/**
 * HW06 for Gebze Technical University
 * HuffmanTree class is a simple text coding/encoding mechanism to represent
 * characters with more efficient way than 8 bits. Each character has a code
 * representation depending their frequency on converted text line.
 *
 * Basic functions and implementations taken from course book
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         20.04.2017.
 */
public class HuffmanTree
{
	BinaryTree<HuffData> huffTree;
	BinaryTree<String>   huffmanCode;
	String               frequencyFile;
	HuffData[]           symbols;
	
	private final int REQUIRED_LENGTH = 2;
	private final int CHAR_INDEX      = 0;
	private final int FREQ_INDEX      = 1;
	
	
	
	/**
	 * Constructor with string parameter
	 * Initializes data members with given file name
	 *
	 * @param fileName File to read character frequencies
	 */
	public HuffmanTree(String fileName)
	{
		huffmanCode = new BinaryTree<>();
		frequencyFile = fileName;
		ReadFile();
	}
	
	
	
	/**
	 * Setter for frequency file name
	 *
	 * @param fileName File name to change
	 */
	public void SetFreqFile(String fileName)
	{
		frequencyFile = fileName;
		/* Calling read file method again to make symbols array ready for building tree */
		ReadFile();
	}
	
	
	
	/**
	 * Builds a binary tree that includes characters with huffman representation
	 * to encode and decode words
	 */
	public void buildTree()
	{
		Queue<BinaryTree<HuffData>> theQueue = new PriorityQueue<BinaryTree<HuffData>>(symbols.length, new CompareHuffmanTrees());
		
		
		for (HuffData nextSymbol : symbols)
		{
			BinaryTree<HuffData> aBinaryTree = new BinaryTree<HuffData>(nextSymbol, null, null);
			theQueue.offer(aBinaryTree);
		}
		
		
		while (theQueue.size() > 1)
		{
			BinaryTree<HuffData> left    = theQueue.poll();
			BinaryTree<HuffData> right   = theQueue.poll();
			double               wl      = left.getData().weight;
			double               wr      = right.getData().weight;
			HuffData             sum     = new HuffData(wl + wr, null);
			BinaryTree<HuffData> newTree = new BinaryTree<>(sum, left, right);
			theQueue.offer(newTree);
		}
		
		huffTree = theQueue.poll();
	}
	
	
	
	/**
	 * Method to decode a message that encoded by huffman tree
	 *
	 * @param codedMessage Message to convert to ascii form
	 * @return ASCII represntation of given message
	 */
	public String decode(String codedMessage)
	{
		StringBuilder        result      = new StringBuilder();
		BinaryTree<HuffData> currentTree = huffTree;
		for (int i = 0; i < codedMessage.length(); i++)
		{
			if (codedMessage.charAt(i) == '1')
			{
				currentTree = currentTree.getRightSubtree();
			}
			else if (codedMessage.charAt(i) == '0')
			{
				currentTree = currentTree.getLeftSubtree();
			}
			
			if (currentTree.isLeaf())
			{
				HuffData theData = currentTree.getData();
				result.append(theData.symbol);
				currentTree = huffTree;
			}
			
			if (codedMessage.charAt(i) == ' ')
			{
				currentTree = huffTree;
			}
		}
		
		return result.toString();
	}
	
	
	
	/**
	 * Method to encode a message
	 *
	 * @param messageToEncode Message to convert to huffman encoded form
	 * @return Huffman representation of the message
	 */
	public String encode(String messageToEncode)
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < messageToEncode.length(); ++i)
		{
			if (messageToEncode.charAt(i) == ' ')
				builder.append("\n");
			
			else
			{
				builder.append(encodeHelper(huffTree, messageToEncode.charAt(i), ""));
				builder.append(" ");
			}
		}
		
		
		return builder.toString();
	}
	
	
	
	/**
	 * Helper recursive method for message encoding
	 *
	 * @param codeTree      Starting position of tree
	 * @param charToConvert Character to convert
	 * @param code          Current representation of character
	 * @return Full representation of character
	 */
	private String encodeHelper(BinaryTree<HuffData> codeTree, char charToConvert, String code)
	{
		if (codeTree.isLeaf())
		{
			if (charToConvert == codeTree.getData().symbol)
				return code;
			
			else
				return null;
		}
		
		
		String leftResult  = encodeHelper(codeTree.getLeftSubtree(), charToConvert, code + "0");
		String rightResult = encodeHelper(codeTree.getRightSubtree(), charToConvert, code + "1");
		
		if (leftResult != null)
			return leftResult;
		
		else if (rightResult != null)
			return rightResult;
		
		else
			return null;
	}
	
	
	
	/**
	 * Opens given frequency file, takes lines and split them into 2 to take
	 * character and its frequency to create huffData array, symbols
	 * Quits from program if any error or bad input occurs
	 */
	private void ReadFile()
	{
		int                  lineCount   = 0;
		int                  freq        = 0;
		String               line, chr   = "";
		String[]             splitArr;
		Scanner              fileScanner = null;
		ArrayList<Character> huffArr     = new ArrayList<>();
		ArrayList<Integer>   freqArr     = new ArrayList<>();
		
		try
		{
			fileScanner = new Scanner(new File(frequencyFile));
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Error! File " + frequencyFile + " couldn't found");
			System.exit(1);
		}
		
		
		while (fileScanner.hasNextLine())
		{
			line = fileScanner.nextLine();
			splitArr = line.split(" ");
			++lineCount;
			
			if (splitArr.length == REQUIRED_LENGTH)
			{
				try
				{
					freq = Integer.parseInt(splitArr[FREQ_INDEX]);
				}
				
				catch (NumberFormatException e)
				{
					System.out.println("Error! Frequency could not resolved");
					System.out.println("file: " + frequencyFile + ", row: " + lineCount);
					System.out.println("\"" + line + "\"");
					System.exit(1);
				}
				
				
				if (splitArr[CHAR_INDEX].length() == 1)
					chr = splitArr[CHAR_INDEX];
				
				else
				{
					System.out.println(splitArr[CHAR_INDEX].length());
					System.out.println("Error! Character could not resolved");
					System.out.println("file: " + frequencyFile + ", row: " + lineCount);
					System.out.println("\"" + line + "\"");
					System.exit(1);
				}
				
				huffArr.add(chr.charAt(0));
				freqArr.add(freq);
			}
			
			else
			{
				System.out.print("Error! Unrecognizable line on ");
				System.out.println("file: " + frequencyFile + ", row: " + lineCount);
				System.out.println("\"" + line + "\"");
				System.exit(1);
			}
		}
		
		symbols = new HuffData[huffArr.size()];
		for (int i = 0; i < huffArr.size(); ++i)
		{
			symbols[i] = new HuffData(freqArr.get(i), huffArr.get(i));
		}
	}
	
	
	
	/**
	 * Inner class for holding both frequency and character in one variable
	 */
	public static class HuffData
	{
		private double    weight;
		private Character symbol;
		
		
		
		public HuffData(double weight, Character symbol)
		{
			this.weight = weight;
			this.symbol = symbol;
		}
		
		
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append(weight);
			builder.append("\n");
			builder.append(symbol);
			builder.append("\n");
			builder.append("\n");
			return builder.toString();
		}
	}
	
	
	
	
	
	/**
	 * Inner class for comparing huffData class
	 */
	private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffData>>
	{
		@Override
		public int compare(BinaryTree<HuffData> treeLeft, BinaryTree<HuffData> treeRight)
		{
			double wLeft  = treeLeft.getData().weight;
			double wRight = treeRight.getData().weight;
			return Double.compare(wLeft, wRight);
		}
	}
}
