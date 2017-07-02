// 22 February 2017, Wednesday

// Import(s)
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;



/**
  * Implemented class for library staff class of the library system
  *
  * @author Deniz Can Erdem Yılmaz - 151044001
  */
public class LibraryStaff extends LibraryRoot
{
	private final String NEW_LINE = "\n";
	private final String COMMA = ",";
	private final String STAFF_NAME = "staff";
	private final String USER_NAME = "user";
	private final int FIRST_OPTION = 0;
	private final int LAST_OPTION = 4;
	private final String USER_FILE = "usr.csv";
	private final int USERNAME_INDEX = 0;
	private final int ADD_STAFF_CODE = 1;
	private final int ADD_USER_CODE = 2;
	private final int ADD_BOOK_CODE = 3;
	private final int REMOVE_BOOK_CODE = 4;
	private final String DEFAULT_BOOK_STATUS = "#ready";
	private final String LIBRARY_FILE = "lib.csv";
	private final int BOOK_INDEX = 0;
	private final int USER_INDEX = 0;
	private final int PASS_INDEX = 1;
	private final int ROLE_INDEX = 2;




	/**
	  * No parameter constructor
	  */
	LibraryStaff()
	{
		// Assigning default values to all variables
		SetRole(DEFAULT_ROLE);
		SetCommand(DEFAULT_COMMAND);
		SetUsername(DEFAULT_USERNAME);
		SetExit(DEFAULT_EXIT);
		SetFirstOption(FIRST_OPTION);
		SetLastOption(LAST_OPTION);
	}



	/**
	  * Overriding menu printer method
	  */
	@Override
	public void PrintMenu()
	{
		System.out.println("\n\n");
		System.out.println("Please choose from menu");
		System.out.println("[1] --> Create new staff");
		System.out.println("[2] --> Create new user");
		System.out.println("[3] --> Add new book");
		System.out.println("[4] --> Remove an existing book");
		System.out.println("[0] --> Exit");
	}



	/**
	  * Overriding command processer method
	  */
	@Override
	public void ProcessCommand()
	{
		if (GetCommand() == ADD_STAFF_CODE)
			AddUser(STAFF_NAME);

		else if (GetCommand() == ADD_USER_CODE)
			AddUser(USER_NAME);

		else if (GetCommand() == ADD_BOOK_CODE)
			AddBook();

		else if (GetCommand() == REMOVE_BOOK_CODE)
			RemoveBook();

		else if (GetCommand() == EXIT_CODE)
			SetExit(TRUE);


		SetCommand(DEFAULT_COMMAND);
	}



	/**
	  * Method to add a new book to library
	  */
	private void AddBook()
	{
		String line;
		String bookName;


		System.out.print("Book name: ");
		Scanner input = new Scanner(System.in);
		bookName = input.next();

		System.out.println("Adding book \"" + bookName + "\"");
		line = bookName + COMMA + DEFAULT_BOOK_STATUS + NEW_LINE;

		try
		{
			FileWriter libWriter = new FileWriter(LIBRARY_FILE,TRUE);
			libWriter.append(line);
			libWriter.flush();
			libWriter.close();
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during usr file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}
	}



	/**
	  * Method to remove a book from library
	  */
	private void RemoveBook()
	{
		String line;
		String[] lines;
		String[] lineToken;
		String removeBook;
		int removeIndex = ZERO;
		int fileSize = ZERO;
		int index = ZERO;
		BufferedReader libFile;


		Scanner input = new Scanner(System.in);
		removeBook = input.next();

		if (SearchCell(LIBRARY_FILE, BOOK_INDEX, removeBook))
		{
			try
			{
				libFile = new BufferedReader(new FileReader(LIBRARY_FILE));
				// Finding the size of file and also row number of book to remove
				while ((line = libFile.readLine()) != null)
				{
					lineToken = line.split(COMMA);
					if (lineToken[BOOK_INDEX].equals(removeBook))
					{
						removeIndex = fileSize;
					}
					++fileSize;
				}
				libFile.close();

				lines = new String[fileSize];
				libFile = new BufferedReader(new FileReader(LIBRARY_FILE));
				while ((line = libFile.readLine()) != null)
				{
					lines[index] = line;
					++index;
				}
				libFile.close();


				FileWriter libWriter = new FileWriter(LIBRARY_FILE,FALSE);
				// Shifting lines to delete selected one
				for (int i=removeIndex; i<lines.length-ONE; ++i)
					lines[i] = lines[i+ONE];

				// Writing new records
				for (int i=0; i<lines.length-ONE; ++i)
				{
					libWriter.append(lines[i] + NEW_LINE);
					libWriter.flush();
				}
				libWriter.close();
			}

			catch (IOException e)
			{
				System.out.println("An error has occured with lib file");
				System.out.println("Exiting from system");
				System.exit(-1);
			}

			System.out.println("Book has been deleted from system");
		}

		else
			System.out.println("Book does not exists");
	}



	/**
	  * Authentication method override. Prints root information
	  *
	  * @return True if login succesfull, false if failed
	  */
	public boolean Authenticate()
	{
		BufferedReader usrReader;
		String line;
		String userParam;
		String passParam;
		Scanner userScan;
		Scanner passScan;
		String[] lineTokens;
		boolean retVal = FALSE;


		System.out.print("Username: ");
		userScan = new Scanner(System.in);
		userParam = userScan.next();

		System.out.print("Password: ");
		passScan = new Scanner(System.in);
		passParam = passScan.next();

		try
		{
			usrReader = new BufferedReader(new FileReader(USER_FILE));
			while ((line = usrReader.readLine()) != null)
			{
				lineTokens = line.split(COMMA);

				if (lineTokens[USER_INDEX].equals(userParam))
					if (lineTokens[PASS_INDEX].equals(passParam))
						if (lineTokens[ROLE_INDEX].equals(STAFF_NAME))
						{
							SetUsername(userParam);
							SetUsername(passParam);
							SetRole(STAFF_NAME);
							retVal = TRUE;
						}
			}
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during lib file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}


		if (!retVal)
			System.out.println("User not found");


		return retVal;
	}
}
