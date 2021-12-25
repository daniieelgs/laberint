package JocLaberint;

public interface Maze {

	public void print();
	
	public void setPlayer(int x, int y);
	
	public boolean isWall(int x, int y);
	
	public boolean isExit(int x, int y);

	public boolean isRoad(int x, int y);
	
	public boolean isSpawn(int x, int y);
		
	public void spawnPlayer(int x, int y) throws UnableSpawnPlayerException;
	
	public boolean isPlaying();
	
	public void finish(String msj);
	
	public String getEndMsj();
	
	public void exit();
	
	public boolean getExit();
	
	public int[] getCrd(String id);
	
	public String[] getSpawnPoints();
	
	public char[][] getMaze();
	
}
