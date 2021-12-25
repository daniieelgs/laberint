package JocLaberint;

public class UnableWallException extends MazeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableWallException() {
		super("Not wall aviable");
	}
	
	public UnableWallException(int x, int y) {
		super("Not wall aviable on x:" + x + " y:" + y);
	}
	
	public UnableWallException(String msjError) {
		super(msjError);
	}
	
}
