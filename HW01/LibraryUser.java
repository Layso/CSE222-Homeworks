// 26 February 2017, Sunday

// Import(s)
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;



/**
  * Normal user for librarys system
  *
  * @author Deniz Can Erdem Yılmaz - 151044001
  */
public class LibraryUser extends AbstractUser
{


	private final int BORROW_CODE = 1;
	private final int RETURN_CODE = 2;
	private final String LIBRARY_FILE = "lib.csv";
	private final String USER_FILE = "usr.csv";
	private final int BOOK_INDEX = 0;
	private final int STATUS_INDEX = 1;
	private final String COMMA = ",";
	private final String NEW_LINE = "\n";
	private final String READY = "#ready";
	private final int FIRST_OPTION = 0;
	private final int LAST_OPTION = 4;
	private final int USER_INDEX = 0;
	private final int PASS_INDEX = 1;
	private final int ROLE_INDEX = 2;
	private final String USER_NAME = "user";
	private final String STAFF_NAME = "staff";



	/**
	  * No parameter constructor
	  */
	LibraryUser()
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
		System.out.println("[1] --> Borrow book");
		System.out.println("[2] --> Return book");
		System.out.println("[0] --> Exit");
	}



	/**
	  * Overriding command processer method
	  */
	@Override
	public void ProcessCommand()
	{
		if (GetCommand() == BORROW_CODE)
			BorrowBook();

		else if (GetCommand() == RETURN_CODE)
			ReturnBook();

		else if (GetCommand() == EXIT_CODE)
			SetExit(TRUE);


		SetCommand(DEFAULT_COMMAND);
	}



	/**
	  * Method to start borrowing process
	  */
	private void BorrowBook()
	{
		String bookName;


		System.out.print("\nBook name: ");
		Scanner input = new Scanner(System.in);
		bookName = input.next();

		if (SearchCell(LIBRARY_FILE, BOOK_INDEX, bookName))
		{
			if (IsAvailable(bookName))
				ReserveBook(bookName);

			else
				System.out.println("Book is not avaliable at the moment");
		}

		else
			System.out.println("Book does not exists");
	}



	/**
	  * Method to start returning process
	  */
	private void ReturnBook()
	{
		String bookName;


		System.out.print("\nBook name: ");
		Scanner input = new Scanner(System.in);
		bookName = input.next();

		if (SearchCell(LIBRARY_FILE, BOOK_INDEX, bookName))
		{
			if (IsTaken(bookName))
				ChangeStatusBook(bookName);

			else
				System.out.println("You do not have the book");
		}

		else
			System.out.println("Book does not exists");
	}



	/**
	  * Borrowing process helper function
	  *
	  * @param Name of book to borrow
	  */
	private void ReserveBook(String bookName)
	{
		BufferedReader libReader;
		String line;
		String[] lineTokens;
		String[] books;
		int fileSize = ZERO;
		int reserveIndex = ZERO;
		int index = ZERO;


		try
		{
			// Finding index of book and size of the file
			libReader = new BufferedReader(new FileReader(LIBRARY_FILE));
			while ((line = libReader.readLine()) != null)
			{
				lineTokens = line.split(COMMA);
				if (lineTokens[BOOK_INDEX].equals(bookName))
					if (lineTokens[STATUS_INDEX].equals(READY));
						reserveIndex = fileSize;

				++fileSize;
			}
			libReader.close();

			// Reading all books to an array
			books = new String[fileSize];
			libReader = new BufferedReader(new FileReader(LIBRARY_FILE));
			while ((line = libReader.readLine()) != null)
			{
				books[index] = line;
				++index;
			}
			libReader.close();

			// Editing records
			if (((books[reserveIndex].split(COMMA))[STATUS_INDEX]).equals(GetUsername()))
				System.out.println("You already have the book");

			else
			{
				books[reserveIndex] = bookName + COMMA + GetUsername();

				// Writing new records
				FileWriter libWriter = new FileWriter(LIBRARY_FILE,FALSE);
				for (int i=0; i<books.length; ++i)
				{
					libWriter.append(books[i] + NEW_LINE);
					libWriter.flush();
				}
				libWriter.close();

				System.out.println("Book is now registered to your account. Have fun!");
			}
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during lib file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}
	}



	/**
	  * Returning process helper function
	  *
	  * @param Name of the book
	  */
	private void ChangeStatusBook(String bookName)
	{
		BufferedReader libReader;
		String line;
		String[] lineTokens;
		String[] books;
		int fileSize = ZERO;
		int returnIndex = ZERO;
		int index = ZERO;


		try
		{
			// Finding index of book and size of the file
			libReader = new BufferedReader(new FileReader(LIBRARY_FILE));
			while ((line = libReader.readLine()) != null)
			{
				lineTokens = line.split(COMMA);
				if (lineTokens[BOOK_INDEX].equals(bookName))
					if (lineTokens[STATUS_INDEX].equals(GetUsername()));
						returnIndex = fileSize;

				++fileSize;
			}
			libReader.close();

			// Reading all books to an array
			books = new String[fileSize];
			libReader = new BufferedReader(new FileReader(LIBRARY_FILE));
			while ((line = libReader.readLine()) != null)
			{
				books[index] = line;
				++index;
			}
			libReader.close();

			// Editing records
			books[returnIndex] = bookName + COMMA + READY;

			// Writing new records
			FileWriter libWriter = new FileWriter(LIBRARY_FILE,FALSE);
			for (int i=0; i<books.length; ++i)
			{
				libWriter.append(books[i] + NEW_LINE);
				libWriter.flush();
			}
			libWriter.close();
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during lib file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}


		System.out.println("Book has been returned to library. Thank you!");
	}



	/**
	  * Checks if book is available to borrow
	  *
	  * @param Name of the book
	  * @return True if file is ready to borrow, false if taken
	  */
	private boolean IsAvailable(String bookName)
	{
		BufferedReader libReader;
		String line;
		String[] lineTokens;
		boolean retVal = FALSE;


		try
		{
			libReader = new BufferedReader(new FileReader(LIBRARY_FILE));
			while ((line = libReader.readLine()) != null)
			{
				lineTokens = line.split(COMMA);
				if (lineTokens[BOOK_INDEX].equals(bookName))
					if (lineTokens[STATUS_INDEX].equals(READY))
						retVal = TRUE;
			}

			libReader.close();
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during lib file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}

		return retVal;
	}



	/**
	  * Checks if book tried to return is borrowed lately
	  *
	  * @param Name of the book
	  * @return True if book is taken, false if ready to borrow
	  */
	private boolean IsTaken(String bookName)
	{
		BufferedReader libReader;
		String line;
		String[] lineTokens;
		boolean retVal = FALSE;


		try
		{
			libReader = new BufferedReader(new FileReader(LIBRARY_FILE));
			while ((line = libReader.readLine()) != null)
			{
				lineTokens = line.split(COMMA);
				if (lineTokens[BOOK_INDEX].equals(bookName))
					if (lineTokens[STATUS_INDEX].equals(GetUsername()))
						retVal = TRUE;
			}
			libReader.close();
		}

		catch (IOException e)
		{
			System.out.println("An error has occured during lib file opening");
			System.out.println("Exiting from system");
			System.exit(-1);
		}

		return retVal;
	}



	/**
	  * Authentication method override. Prints root information
	  *
	  * @return True if login succesfull, false if failed
	  */
	@Override
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
						if (lineTokens[ROLE_INDEX].equals(USER_NAME))
						{
							SetUsername(userParam);
							SetUsername(passParam);
							SetRole(USER_NAME);
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
