package JocLaberint;

public class UnableSpawnPlayerException extends MazeException{

	private static final long serialVersionUID = 1L;

	public UnableSpawnPlayerException() {
		super("Bad spawn point");
	}
	
	public UnableSpawnPlayerException(int x, int y) {
		super("Bad spawn point on x:" + x + " y:" + y);
	}
	
	public UnableSpawnPlayerException(String msjError) {
		super(msjError);
	}
	
}
