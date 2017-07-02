public class Main
{
	public static void main(String args[])
	{
		LibrarySystem library = new LibrarySystem();
		
		while (library.GetLoop())
		{

			library.InitializeUser();

			if (library.GetLoop())
				library.StartSystem();
		}
	}
}
