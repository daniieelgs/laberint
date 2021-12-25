package JocLaberint;

public class UnableBombException extends MazeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableBombException() {
		super("Not bomb aviable");
	}
	
	public UnableBombException(int x, int y) {
		super("Not bomb aviable on x:" + x + " y:" + y);
	}
	
	public UnableBombException(String msjError) {
		super(msjError);
	}
	
}
