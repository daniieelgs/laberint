package JocLaberint;

public interface MazeObstacles extends Maze{
	
	public void breakWall(int x, int y) throws UnableWallException;
	
	public void takePickaxe(int x, int y) throws UnablePickaxeException;
	
	public void exploteBomb(int x, int y) throws UnableBombException;
	
	public void exitMaze(int x, int y) throws UnableExitException;
	
	public boolean isBomb(int x, int y);
	
	public boolean isPickaxe(int x, int y);
	
	public boolean isBorder(int x, int y);

	public void setVisibleBombs(boolean b);

	public boolean getVisibleBombs();
	
	
}
