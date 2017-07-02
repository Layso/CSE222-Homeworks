// 22 February 2017, Wednesday

//Import(s)
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.InputMismatchException;



/**
  * Extended version of user interface with variables
  *
  * @author Deniz Can Erdem Yılmaz - 151044001
  */
public abstract class AbstractUser implements User
{
	// Varaibles that every user object need to have
	private String username;
	private String role;
	private int command;
	private int firstOption;
	private int lastOption;
	private boolean exit;


	// Pre-defined default values user constructor(s)
	protected final String DEFAULT_USERNAME = "";
	protected final String DEFAULT_ROLE = "";
	protected final String COMMA = ",";
	protected final int DEFAULT_COMMAND = -1;
	protected final boolean DEFAULT_EXIT = FALSE;



	/**
	  * Overriding user input handler method
	  */
	@Override
	public void TakeCommand()
	{
		while (GetCommand() == DEFAULT_COMMAND)
		{
			try
			{
				int inp;

				Scanner input = new Scanner(System.in);
				inp = input.nextInt();

				if (inp < firstOption || inp > lastOption)
				{
					System.out.println("Please enter a valid input");
					SetCommand(DEFAULT_COMMAND);
				}

				else
					SetCommand(inp);
			}

			catch (InputMismatchException e)
			{
				System.out.println("Please enter a valid input (integer only)");
			}
		}
	}



	/**
	  * Function to search a string in given csv file with known column number
	  *
	  * @param fileName - Name of the file
	  * @param colNum   - Column number
	  * @param word     - Seeked word
	  *
	  * @return True if word is found, false if not
	  */
	protected boolean SearchCell(String fileName, int colNum, String word)
	{
		String line;
		String[] lineToken;
		BufferedReader readFile;
		boolean wordExists = FALSE;


		try
		{
			readFile = new BufferedReader(new FileReader(fileName));


			while ((line = readFile.readLine()) != null)
			{
				lineToken = line.split(COMMA);
				if (lineToken[colNum].equals(word))
				{
					wordExists = TRUE;
				}
			}
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during usr file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}


		return wordExists;
	}



	/**
	  * Overriding toString method
	  */
	@Override
	public String toString()
	{
		return (role + "," + username);
	}



	/**
	  * Setter(s)
	  */
	public void SetUsername(String param){username = param;}
	public void SetRole(String param){role = param;}
	public void SetCommand(int param){command = param;}
	public void SetExit(boolean param){exit = param;}
	public void SetFirstOption(int param){firstOption = param;}
	public void SetLastOption(int param){lastOption = param;}

	/**
	  * Getter(s)
	  */
	public String GetUsername(){return username;}
	public String GetRole(){return role;}
	public int GetCommand(){return command;}
	public boolean Exit(){return exit;}
	public int GetFirstOption(){return firstOption;}
	public int GetLastOption(){return lastOption;}
}
