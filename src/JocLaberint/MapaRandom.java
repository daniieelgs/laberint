package JocLaberint;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MapaRandom implements Maze{

	private char[][] laberint;
	private String[] spawnPoints;
	
	private int x, y;
	
	private boolean playing; 
	private boolean exit;
	private String end_msj;
	
	//private int bucle;
	
	final static char MUR='#';
	final static char CAMI=' ';
	final static char SORTIDA='S';
	final static char PAS='+';
	final static char JUGADOR='P';
	final static char SPAWN='·';
	
	public MapaRandom() {
	
		playing=true;
		exit=false;
		
		end_msj="";
		
		RandomMaze();
		
	}
	
	
	public void print() {
						
		//bucle=0;
		
		for (char[] chars : laberint) {
			
			for (char c : chars) {
				
				System.out.print(c + " ");
				
			}
			System.out.print("\n");
		}
		
	
	
	}

	public void setPlayer(int _x, int _y) {

		laberint[y][x]=PAS;
		
		x=_x;
		y=_y;
		
		laberint[y][x]=JUGADOR;
		
	}

	public boolean isWall(int x, int y) {

		if(x<0||y<0||x>=laberint[0].length||y>=laberint.length) return false;
		
		char w=laberint[y][x];
		
		if(w==MUR) return true;
		else return false;
		
	}

	public boolean isExit(int x, int y) {

		if(x<0||y<0||x>=laberint[0].length||y>=laberint.length) return false;
		
		char e=laberint[y][x];
		
		if(e==SORTIDA) return true;
		else return false;
		
	}

	public boolean isRoad(int x, int y) {

		if(x<0||y<0||x>=laberint[0].length||y>=laberint.length) return false;
		
		char r=laberint[y][x];

		if(r==CAMI||r==PAS||r==SPAWN) return true;
		else return false;
		
	}

	public void spawnPlayer(int _x, int _y) throws UnableSpawnPlayerException {

		for (int i=0; i<laberint.length; i++) {
			for (int j=0; j<laberint[i].length; j++) {
				if(laberint[i][j]==JUGADOR) laberint[i][j]=CAMI;
			}
		}
		
		if(isRoad(_x, _y)) {
			
			x=_x;
			y=_y;
			
			laberint[y][x]=JUGADOR;
			
		}else {
			throw new UnableSpawnPlayerException(_x, _y);
		}
		
	}

	public boolean isSpawn(int _x, int _y) {
		if(x<0||y<0||x>=laberint[0].length||y>=laberint.length) return false;
		
		boolean spawn=false;
		
		for (String crd : spawnPoints) {
			
			if((_y + "-" + _x).equals(crd)) spawn=true;
			
		}
		
		return spawn;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void finish(String msj) {
		playing=false;
		end_msj=msj;
	}

	public String getEndMsj() {
		return end_msj;

	}

	public void exit() {
		exit=true;
		finish("ENHORABONA!!!");
	}
	
	public boolean getExit() {
		return exit;
	}

	public int[] getCrd(String id) {
		int[] crd=new int[2];
		
		if(id.length()<0) return null;
		
		int separator=-1;
		
		for (int i=0; i<id.length(); i++) {
			
			if(id.charAt(i)=='-') separator=i;
			
		}
		
		crd[0]=Integer.parseInt(id.substring(0, separator));
		crd[1]=Integer.parseInt(id.substring(separator+1, id.length()));
		
		return crd;
	}

	public String[] getSpawnPoints() {
		return spawnPoints;
	}
	
	private String[] generateSpawnPoint(char[][] _laberint) {
		
		ArrayList<String> ids_spawns=new ArrayList<String>();
			
		for(int i=0; i<_laberint.length; i++) {
			for(int j=0; j<_laberint[i].length; j++) {
				if(_laberint[i][j]==CAMI||_laberint[i][j]==JUGADOR)  
					ids_spawns.add(i + "-" + j);
			}
		}
		
		String[] spawnPoint=new String[ids_spawns.size()];
		
		for(int i=0; i<ids_spawns.size(); i++) {
			spawnPoint[i]=ids_spawns.get(i);
		}
		
		return spawnPoint;
		
	}
	
	private void RandomMaze() {
		
		int length=(int)(Math.random()*50+10);
		
		//length=10;
		
		laberint=new char[length][length];
		
		for(int i=0; i<laberint.length; i++) {
			for(int j=0; j<laberint[i].length; j++) {
				laberint[i][j]=MUR;
			}
		}
		
		
		ArrayDeque<String> stack=new ArrayDeque<String>();
		
		int y=(int)(Math.random()*length);
		int x=(int)(Math.random()*length);
		
		//y=5;
		//x=5;
		
		String id=y + "-" + x;
		laberint[y][x]=CAMI;
		
		stack.add(id);
		
		generateRandomMaze(stack, x, y);
		
		boolean insert_exit=false;
		
		while(!insert_exit) {
			
			int xE=(int)(Math.random()*length);
			int yE=(int)(Math.random()*length);
			
			if(isRoad(xE, yE)) {
				laberint[yE][xE]=SORTIDA;
				insert_exit=true;
			}
			
		}
		
		spawnPoints=generateSpawnPoint(laberint);
		
		
		//scan.close();
				
	}
	
	private void generateRandomMaze(ArrayDeque<String> _stack, int _x, int _y) {
		
		//bucle++;
		
	//	System.out.println(bucle + " --> " + laberint.length);
		
//		print();
		
//		System.out.print("\n");
		
//		scan.nextLine();
		
		boolean moviment=false;
		int contador=0;
		
		int x=_x;
		int y=_y;
		ArrayDeque<String> stack=_stack;
		
		ArrayList<Integer> moviments=new ArrayList<Integer>();
		
		while(!moviment&&contador<4) {
			
			int mov=(int)(Math.random()*4);
			
			boolean registrat=false;
			
			for(int i: moviments) {
				if(mov==i) registrat=true;
			}
			
			if(!registrat) {
				moviments.add(mov);
				contador++;
				
				if(mov==0&&isWall(x, y+1)&&isWall(x, y+2)&&isWall(x+1, y+1)&&isWall(x-1, y+1)) { //S
					y++;
					moviment=true;
				}
				else if(mov==1&&isWall(x+1, y)&&isWall(x+2, y)&&isWall(x+1, y+1)&&isWall(x+1, y-1)) { //D
					x++; 
					moviment=true;
				}
				else if(mov==2&&isWall(x, y-1)&&isWall(x, y-2)&&isWall(x+1, y-1)&&isWall(x-1, y-1)) { //W
					y--;
					moviment=true;
				}
				else if(mov==3&&isWall(x-1, y)&&isWall(x-2, y)&&isWall(x-1, y+1)&&isWall(x-1, y-1)) { //A
					x--;
					moviment=true;
				}
				
			}
			
			
		}
		
		String id;
		
		if(moviment) {
			laberint[y][x]=CAMI;
			id=y + "-" + x;
			stack.add(id);
		}else if(stack.size()>1){
			
			stack.removeLast();
			id=stack.getLast();
			
			int[] crd=getCrd(id);
			
			y=crd[0];
			x=crd[1];
						
		}

		if(stack.size()>1) {
			generateRandomMaze(stack, x, y);
		}
		
		
	}

	public char[][] getMaze() {
		return laberint;
	}


}
