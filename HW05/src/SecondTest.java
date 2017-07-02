import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;



/**
 * HW05 for Gebze Technical University
 *
 * @author Deniz Can Erdem Yılmaz - 151044001
 *         06.04.2017.
 */
public class SecondTest
{
	public static void main(String[] args)
	{
		final String TEST_FILE       = "family.txt";
		final int    CHILD_INDEX     = 0;
		final int    PARENT_INDEX    = 1;
		final int    NICK_INDEX      = 2;
		final int    REQUIRED_LENGTH = 3;
		
		int        lineCount   = 0;
		String     line, root, child, parent, nick;
		String[]   splitArr;
		FamilyTree family;
		Scanner    fileScanner = null;
		
		
		try
		{
			fileScanner = new Scanner(new File(TEST_FILE));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error! File " + TEST_FILE + " couldn't found");
			System.exit(1);
		}
		
		
		root = fileScanner.nextLine();
		family = new FamilyTree(root);
		++lineCount;
		
		while (fileScanner.hasNextLine())
		{
			line = fileScanner.nextLine();
			splitArr = line.split(", ");
			++lineCount;
			
			if (splitArr.length == REQUIRED_LENGTH)
			{
				child = splitArr[CHILD_INDEX];
				parent = splitArr[PARENT_INDEX];
				nick = splitArr[NICK_INDEX];
				family.add(child, parent, nick);
			}
			
			else
			{
				System.out.print("Error! Unrecognizable line on ");
				System.out.println("file: " + TEST_FILE + ", row: " + lineCount);
				System.out.println("\"" + line + "\"");
			}
		}
		
		
		Iterator it = family.iterator();
		System.out.println("Traverse of Family Tree with level-order");
		while (it.hasNext())
		{
			System.out.println(it.next());
		}
	}
}
