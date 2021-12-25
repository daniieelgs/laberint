package JocLaberint;

public class UnablePickaxeException extends MazeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnablePickaxeException() {
		super("Not pickaxe aviable");
	}
	
	public UnablePickaxeException(int x, int y) {
		super("Not pickaxe aviable on x:" + x + " y:" + y);
	}
	
	public UnablePickaxeException(String msjError) {
		super(msjError);
	}
	
}
