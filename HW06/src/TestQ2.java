import java.util.Scanner;


/**
 * HW06 test of Q2 for Gebze Technical University, CSE 222 course
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         21.04.2017.
 */
public class TestQ2
{
	static final String FREQ_FILE = "freq.txt";
	
	
	
	public static void main(String[] args)
	{
		String input = " ";
		Scanner scan;
		
		HuffmanTree huffman = new HuffmanTree(FREQ_FILE);
		huffman.SetFreqFile(FREQ_FILE);
		huffman.buildTree();
	
		while (input.charAt(0) != '4')
		{
			System.out.println("[1] to encode message");
			System.out.println("[2] to decode message");
			System.out.println("[3] to change frequency file");
			System.out.println("[4] to exit");
			scan = new Scanner(System.in);
			input = scan.next();
			
			if (input.charAt(0) == '1')
			{
				System.out.println("Enter the sentence to code");
				Scanner sentence = new Scanner(System.in);
				String strSentence = sentence.nextLine();
				System.out.println(huffman.encode(strSentence));
			}
			
			else if (input.charAt(0) == '2')
			{
				System.out.println("Enter the code");
				Scanner code = new Scanner(System.in);
				String strCode = code.nextLine();
				System.out.println(huffman.decode(strCode));
			}
			
			else if (input.charAt(0) == '3')
			{
				System.out.println("Enter new file name");
				Scanner fileIn = new Scanner(System.in);
				String fileName = fileIn.nextLine();
				huffman.SetFreqFile(fileName);
				huffman.buildTree();
			}
			
			System.out.println("\n");
		}
	}
}
