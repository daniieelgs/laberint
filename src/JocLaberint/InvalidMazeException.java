package JocLaberint;

public class InvalidMazeException extends MazeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMazeException() {
		super("Invalid Maze");
	}
	
	public InvalidMazeException(int lenghtX, int lenghtY) {
		super(lenghtX + "x" + lenghtY + " Invalid Maze. Has to be 10x10");
	}

	public InvalidMazeException(String msjError) {
		super(msjError);
	}
	
}
