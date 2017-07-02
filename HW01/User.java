// 22 February 2017, Wednesday



/**
  * Interface and top of the user class for the library system
  *
  * @author Deniz Can Erdem Yılmaz - 151044001
  */
public interface User
{
	// Pre-defined variables
	boolean TRUE = true;
	boolean FALSE = false;
	int ZERO = 0;
	int ONE = 1;
	int EXIT_CODE = 0;

	// Functions needed to be overrided by sub-classes
	public void PrintMenu();
	public void TakeCommand();
	public void ProcessCommand();
	public boolean Authenticate();
	public boolean Exit();
}
