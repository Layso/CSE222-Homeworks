// 23 February 2017, Thursday

// Import(s)
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;



/**
  * LibrarySystem class simulates a very simple library
  *
  * @author Deniz Can Erdem Yılmaz - 151044001
  */
public class LibrarySystem
{
	// Data members
	private User systemUser;
	private boolean loop;
	private boolean rootNeeded;
	private int command;
	private String username;
	private String password;

	// Constant definitions
	private final String USER_FILE = "usr.csv";
	private final String LIBRARY_FILE = "lib.csv";
	private final String USER_FILE_HEADER = "Username,Password,Role\n";
	private final String LIBRARY_FILE_HEADER = "Book,Status\n";
	private final String STAFF_NAME = "staff";
	private final String USER_NAME = "user";
	private final String ROOT_NAME = "root";
	private final String COMMA = ",";
	private final int FIRST_OPTION = 0;
	private final int LAST_OPTION = 2;
	private final int STAFF_IN = 1;
	private final int USER_IN = 2;
	private final int EXIT_CODE = 0;
	private final int ZERO = 0;
	private final int REQUIRED_LINE_NUMBER = 2;
	private final int ROLE_INDEX = 2;
	private final int DEFAULT_COMMAND = -1;
	private final boolean TRUE = true;
	private final boolean FALSE = false;



	/**
	  * No parameter constructor
	  */
	public LibrarySystem()
	{
		loop = TRUE;
		rootNeeded = FALSE;
		command = DEFAULT_COMMAND;


		// Checking non existing files and creating if necessary
		if (!DoesFileExists(USER_FILE))
		{
			if (DoesFileExists(LIBRARY_FILE))
			{
				System.out.print("User file couldn't found -- > ");
				rootNeeded = TRUE;
				CreateUsr();
			}

			else
			{
				System.out.print("First time login detected -- > ");
				rootNeeded = TRUE;
				CreateUsr();
				CreateLib();
			}
		}

		else
		{
			if (!DoesFileExists(LIBRARY_FILE))
			{
				System.out.println("Creating new library record");
				System.out.print("Library file couldn't found");
				CreateLib();
			}

			else if (!DoesStaffExists())
			{
				System.out.print("User file is empty -- > ");
				rootNeeded = TRUE;
			}

			else
				System.out.println("All systems ready");
		}
	}



	/**
	  * Makes system run with using User's methods
	  */
	public void StartSystem()
	{
		while (!systemUser.Authenticate());

		while (!systemUser.Exit())
		{
			systemUser.PrintMenu();
			systemUser.TakeCommand();
			systemUser.ProcessCommand();
		}


		command = DEFAULT_COMMAND;
	}



	/**
	  * Initializes User with wanted type of User sub-classes for system
	  */
	public void InitializeUser()
	{
		String userRole;
		String[] tokens;


		if (rootNeeded)
		{
			systemUser = new LibraryRoot();
			rootNeeded = FALSE;
		}

		else
		{
			PrintMenu();
			TakeCommand();

			if (command == STAFF_IN)
				systemUser = new LibraryStaff();

			else if (command == USER_IN)
				systemUser = new LibraryUser();

			else
				loop = FALSE;
		}
	}



	/**
	  * Prints menu for user
	  */
	public void PrintMenu()
	{
		System.out.println("\n\n");
		System.out.println("Welcome to Layso's library!");
		System.out.println("Please choose from menu");
		System.out.println("[1] --> Log-in as staff");
		System.out.println("[2] --> Log-in as user");
		System.out.println("[0] --> Exit");
	}



	/**
	  * Takes user input and handles errors
	  */
	public void TakeCommand()
	{
		while (command == DEFAULT_COMMAND)
		{
			try
			{
				int inp;

				Scanner input = new Scanner(System.in);
				inp = input.nextInt();
				if (inp < FIRST_OPTION || inp > LAST_OPTION)
				{
					System.out.println("Please enter a valid input");
					command = DEFAULT_COMMAND;
				}

				else
					command = inp;
			}

			catch (InputMismatchException e)
			{
				command = DEFAULT_COMMAND;
				System.out.println("Please enter a valid input (integer only)");
			}
		}
	}



	/**
	  * Constructor helper function, creates usr.csv if file does not exists
	  */
	private void CreateUsr()
	{
		try
		{
			// Creating a new user csv file with header info
			FileWriter userWriter = new FileWriter(USER_FILE);
			userWriter.append(USER_FILE_HEADER);
			userWriter.flush();
			userWriter.close();
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during usr file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}
	}



	/**
	  * Constrcutor helper function, creates lib.csb if file does not exists
	  */
	private void CreateLib()
	{
		try
		{
			// Creating a new library csv file with header info
			FileWriter librWriter = new FileWriter(LIBRARY_FILE);
			librWriter.append(LIBRARY_FILE_HEADER);
			librWriter.flush();
			librWriter.close();
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during lib file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}
	}



	/**
	  * Helper function to check existance of a file
	  *
	  * @param Name of the file
	  * @return True if given file exists, false if don't
	  */
	private boolean DoesFileExists(String fileName)
	{
		File file = new File(fileName);
		return (file.exists() && !file.isDirectory());
	}



	/**
	  * Checks if usr.csv includes any staff register
	  *
	  * @return True if staff register found, false if not
	  */
	private boolean DoesStaffExists()
	{
		String line;
		String[] lineToken;
		BufferedReader userRead;
		boolean staffExists = FALSE;


		try
		{
			userRead = new BufferedReader(new FileReader(USER_FILE));

			// Searching records to see if there is any staff
			while ((line = userRead.readLine()) != null)
			{
				lineToken = line.split(COMMA);
				if (lineToken[ROLE_INDEX].equals(STAFF_NAME))
					staffExists = TRUE;

			}
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during usr file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}


		return staffExists;
	}



	/**
	  * loop variable setter
	  *
	  * @param Function parameter to change loop value
	  */
	void SetLoop(boolean param){loop = param;}



	/**
	  * loop variable getter
	  *
	  * @return Value of loop
	  */
	boolean GetLoop(){return loop;}
}
