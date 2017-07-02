/**
 * Internship application project for 32 Bit Bilgisayar software company
 *
 * @author Deniz Can Erdem Yılmaz
 *         20.04.2017
 */
public interface Queue<R>
{
	public boolean offer(R item);
	public R poll();
	public R peek();
}
