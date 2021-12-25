package JocLaberint;

public class MazeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MazeException() {
		super("Fatal Error on the Maze");
	}
	
	public MazeException(String msjError) {
		
		super(msjError);
		
	}
	
}
