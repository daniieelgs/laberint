package JocLaberint;

public class UnableExitException extends MazeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableExitException() {
		super("Not exit aviable");
	}
	
	public UnableExitException(int x, int y) {
		super("Not exit aviable on x:" + x + " y:" + y);
	}
	
	public UnableExitException(String msjError) {
		super(msjError);
	}
	
}
