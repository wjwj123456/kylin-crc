package blservice;

/**
 * all kinds of commond
 * 
 * @author song
 */
public interface Command {

	/**
	 * execute commond
	 */
	public int execute();

	/**
	 * undo command
	 */
	public void undo();
}
