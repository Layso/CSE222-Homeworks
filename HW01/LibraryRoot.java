// 23 February 2017, Thursday

// Import(s)
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;



/**
  * Root user for first time login to library system
  *
  * @author Deniz Can Erdem Yılmaz - 151044001
  */
public class LibraryRoot extends AbstractUser
{
	// Data member(s)
	private boolean canExit;

	// Default values for variables
	private  final String ROOT_USERNAME = "root";
	private  final String ROOT_ROLE = "root";
	private  final boolean ROOT_AUTHENTICATION = TRUE;
	private  final String USER_FILE = "usr.csv";
	private final String COMMA = ",";
	private final String STAFF_NAME = "staff";
	private final String USER_NAME = "user";
	private final int FIRST_OPTION = 0;
	private final int LAST_OPTION = 2;
	private final int USERNAME_INDEX = 0;
	private final String NEW_LINE = "\n";
	private final int ROOT_ADD_STAFF = 1;
	private final int ROOT_ADD_USER = 2;



	/**
	  * No parameter constructor
	  */
	public LibraryRoot()
	{
		// Assigning default values to all variables
		canExit = FALSE;
		SetRole(ROOT_ROLE);
		SetCommand(DEFAULT_COMMAND);
		SetUsername(ROOT_USERNAME);
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
		System.out.println("[0] --> Exit");
	}



	/**
	  * Overriding command processer method
	  */
	@Override
	public void ProcessCommand()
	{
		if (GetCommand() == ROOT_ADD_STAFF)
		{
			AddUser(STAFF_NAME);
			canExit = TRUE;
		}

		else if (GetCommand() == ROOT_ADD_USER)
			AddUser(USER_NAME);

		else if (GetCommand() == EXIT_CODE)
		{
			if (canExit)
			{
				SetExit(TRUE);

			}

			else
				System.out.println("You must at least add 1 staff member" +
				"before quitting the system");
		}

		SetCommand(DEFAULT_COMMAND);
	}



	/**
	  * Authentication method override. Prints root information
	  *
	  * @return Returns true automatically as root user
	  */
	@Override
	public boolean Authenticate()
	{
		// Printing root information text
		System.out.println("Root access granted");
		System.out.println("Please sign up a new library staff before logging" +
						   " out to use system properly hereafter");
		return TRUE;
	}



	/**
	  * Protected user adder method. Will be used for sub-classes
	  *
	  * @param userType - Role of user to save in folder
	  */
	protected void AddUser(String userType)
	{
		String newUsername;
		String newPassword;
		String usr;
		boolean loop = TRUE;

		// Taking username input and checking if there is an account exists
		// with same name. Takes another input until a unique one will entered
		do
		{
			System.out.print("\nNew Username: ");
			Scanner userInp = new Scanner(System.in);
			newUsername = userInp.next();

			if (SearchCell(USER_FILE, USERNAME_INDEX, newUsername))
				System.out.println("This username already exists");
			else
				loop = !loop;

		} while (loop);

		System.out.print("New Password: ");
		Scanner userInp = new Scanner(System.in);
		newPassword = userInp.next();


		usr = newUsername + COMMA + newPassword + COMMA + userType + NEW_LINE;
		System.out.println("\nAdding new " + userType + " with informations:");
		System.out.println(usr);


		try
		{
			FileWriter userWriter = new FileWriter(USER_FILE,TRUE);
			userWriter.append(usr);
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
}
