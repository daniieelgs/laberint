package JocLaberint;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class Mapa implements MazeObstacles{

	private char[][] laberint; //CONTÉ EL MAPA DEL LABERINT
	private String[] spawnPoints;
	private int x,y; //POSICIÓ DEL JUGADOR
	private boolean playing; //INFROMA DE SI EL JOC HA ACABAT O CONTINUA
	private boolean exit;
	private String end_msj; //MISSATGE QUE SORTIRÀ AL FINALITZAR EL JOC
	
	private ArrayList<String> picsMap;
	private ArrayList<String> bombsMap;
	private boolean visibleBombs;

	final static char BORDER='#'; //&
	final static char MUR='#';
	final static char CAMI=' ';
	final static char BOMBA='@';
	final static char PIC='^';
	final static char SORTIDA='S';
	final static char PAS='+';
	final static char JUGADOR='P';
	final static char SPAWN='·';
	
	//Scanner scan=new Scanner(System.in);
		
	public Mapa(char[][] _laberint, String[] _spawnPoints){ //CONSTRUCTOR QUE PERMET REBRE UN MAPA ESPECÍFIC
		
		playing=true;
		exit=false;
		
		end_msj="";
		visibleBombs=true;
		
		laberint=_laberint;
		spawnPoints=_spawnPoints;
		
		_laberint=null;
						
		/*for (String i : _spawnPoints) {
			int[] crd=getCrd(i);
			laberint[crd[0]][crd[1]]=SPAWN;
		}*/
		
		makeObstaclesMap();
						
	}
	
	public Mapa(char[][] _laberint) {
		this(_laberint, generateSpawnPoint(_laberint));
	}
	
	public Mapa() { //CONSTRUCTOR QUE APLICARÀ EL MAPA PER DEFECTE
		
		this(generateDefaultMaze(), generateDefaultSpawnPoint());
				
	}
	
	private static char[][] generateDefaultMaze(){ //GENERA EL MAPA

		/*
				# # # # # # # # # # 
				· · # #         # # 
				# · · · #   #   # # 
				# · # · #   #   # # 
				# · # · ·   #     # 
				· · · · · # # #   S 		1
				^ # · # · · # # # # 
				# · · # # # #     # 
				# # # # # ^       # 
				# # # # # # # # # #
		*/
		
		/*char[][] mapa= {
			{MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR},
			{CAMI,CAMI,MUR,MUR,CAMI,CAMI,BOMBA,CAMI,MUR,MUR},
			{MUR,CAMI,CAMI,CAMI,MUR,CAMI,MUR,CAMI,MUR,MUR},
			{MUR,CAMI,MUR,CAMI,MUR,CAMI,MUR,CAMI,MUR,MUR},
			{MUR,CAMI,MUR,CAMI,CAMI,BOMBA,MUR,CAMI,BOMBA,MUR},				//1
			{CAMI,CAMI,CAMI,BOMBA,CAMI,MUR,MUR,MUR,CAMI,SORTIDA},
			{PIC,MUR,CAMI,MUR,CAMI,CAMI,MUR,MUR,MUR,MUR},
			{MUR,CAMI,CAMI,MUR,MUR,MUR,MUR,CAMI,CAMI,MUR},
			{MUR,MUR,MUR,MUR,MUR,PIC,CAMI,CAMI,CAMI,MUR},
			{MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR}
		};*/
		
		/*
				& & & & & & & & & & & & 
				& # # # # # # # # # # & 
				& ^ · # · #   ^ @ # # & 
				& # · · · #   #     # & 
				& # · # · # · # #   # & 
				& # · # · · · #     # & 
				& · · # · · # #       S 		2
				& ^ # # # · · # # # # & 
				& #     # # # #   # # & 
				& # ^ # # # ^     # # & 
				& # # # # # # # # # # & 
				& & & & & & & & & & & & 
			*/
		
		/*char[][] mapa= {
				{BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER},
				{BORDER,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,BORDER},
				{BORDER,PIC,CAMI,MUR,CAMI,MUR,CAMI,PIC,BOMBA,MUR,MUR,BORDER},
				{BORDER,MUR,CAMI,CAMI,CAMI,MUR,BOMBA,MUR,CAMI,CAMI,MUR,BORDER},
				{BORDER,MUR,CAMI,MUR,CAMI,MUR,CAMI,MUR,MUR,CAMI,MUR,BORDER},
				{BORDER,MUR,CAMI,MUR,CAMI,CAMI,CAMI,MUR,BOMBA,BOMBA,MUR,BORDER},			//2
				{BORDER,CAMI,CAMI,MUR,MUR,CAMI,MUR,MUR,BOMBA,CAMI,CAMI,SORTIDA},
				{BORDER,PIC,MUR,MUR,MUR,CAMI,CAMI,MUR,MUR,MUR,MUR,BORDER},
				{BORDER,MUR,BOMBA,BOMBA,MUR,MUR,MUR,MUR,CAMI,MUR,MUR,BORDER},
				{BORDER,MUR,PIC,MUR,MUR,MUR,PIC,CAMI,CAMI,MUR,MUR,BORDER},
				{BORDER,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,BORDER},
				{BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER}
		};*/
		
		char[][] mapa= {
				{BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER},
				{BORDER,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,BORDER},
				{BORDER,PIC,CAMI,MUR,CAMI,MUR,CAMI,PIC,CAMI,MUR,MUR,BORDER},
				{BORDER,MUR,CAMI,CAMI,CAMI,MUR,BOMBA,MUR,PIC,BOMBA,MUR,BORDER},
				{BORDER,MUR,CAMI,MUR,CAMI,MUR,CAMI,MUR,MUR,BOMBA,MUR,BORDER},
				{BORDER,MUR,CAMI,MUR,CAMI,CAMI,CAMI,MUR,BOMBA,CAMI,MUR,BORDER},			//2
				{BORDER,CAMI,CAMI,MUR,MUR,CAMI,MUR,MUR,BOMBA,CAMI,CAMI,SORTIDA},
				{BORDER,PIC,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,BORDER},
				{BORDER,MUR,BOMBA,BOMBA,MUR,MUR,MUR,MUR,CAMI,MUR,MUR,BORDER},
				{BORDER,MUR,PIC,MUR,MUR,BOMBA,PIC,CAMI,CAMI,MUR,MUR,BORDER},
				{BORDER,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,BORDER},
				{BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER,BORDER}
		};
		
		/*char[][] mapa= {
				{MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR},
				{PIC,CAMI,MUR,MUR,CAMI,CAMI,PIC,CAMI,MUR,MUR},
				{MUR,CAMI,CAMI,CAMI,MUR,CAMI,MUR,CAMI,CAMI,MUR},
				{MUR,CAMI,MUR,CAMI,MUR,CAMI,MUR,MUR,CAMI,MUR},
				{MUR,CAMI,MUR,CAMI,CAMI,BOMBA,MUR,BOMBA,BOMBA,MUR},			//2.1
				{CAMI,CAMI,MUR,CAMI,CAMI,MUR,MUR,BOMBA,CAMI,SORTIDA},
				{PIC,MUR,MUR,MUR,CAMI,CAMI,MUR,MUR,MUR,MUR},
				{MUR,PIC,BOMBA,MUR,MUR,MUR,MUR,CAMI,MUR,MUR},
				{MUR,MUR,MUR,MUR,MUR,PIC,CAMI,CAMI,MUR,MUR},
				{MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR,MUR}
		};*/
		
		
		return mapa;
				
	}
	
	private static String[] generateDefaultSpawnPoint() {
		
		//int[] spawnPoint= {10, 11, 21, 31, 41, 51, 50, 52, 53, 62, 72, 71, 22, 23, 33, 43, 44, 54, 64, 65}; //1
		
		String[] spawnPoint= {"2-2", "2-4", "3-2", "3-3", "4-2", "5-2", "6-2", "6-1", "5-4", "4-4", "3-4", "6-5", "5-6", "4-6"}; //2
		
		//String[] spawnPoint= {"1-1", "1-3", "2-1", "2-2", "3-1", "4-1", "5-1", "5-0", "4-3", "3-3", "2-3", "5-4", "6-5", "4-4", "4-5", "3-5"}; //2
		
		return spawnPoint;
		
	}
	
	private static String[] generateSpawnPoint(char[][] _laberint) {
		
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
	
	private void makeObstaclesMap() {
		
		picsMap=new ArrayList<String>();
	
		for(int i=0; i<laberint.length; i++) {
			for(int j=0; j<laberint[i].length; j++) {
				if(laberint[i][j]==PIC) {
					picsMap.add(i + "-" + j);
				}
			}
		}
				
		bombsMap=new ArrayList<String>();
		
		for(int i=0; i<laberint.length; i++) {
			for(int j=0; j<laberint[i].length; j++) {
				if(laberint[i][j]==BOMBA) {
					bombsMap.add(i + "-" + j);
				}
			}
		}
				
	}
	
	public void print() { //IMPRIMEIX EL MAPA
				
		for (char[] chars : laberint) {
			
			for (char c : chars) {
				
				System.out.print(c + " ");
				
			}
			System.out.print("\n");
		}
		
	}
	
	public void setPlayer(int _x, int _y) { //POSICIONA AL JUGADOR | _x I _y NOVES COORDENADES | x I y ANTIGUES COORDENADES
		
		
		if(isExit(_x, _y)) {
			
			laberint[y][x]=PAS;
			laberint[_y][_x]=SORTIDA;
		}
		
		else {
			
			laberint[y][x]=PAS;
			
			x=_x;
			y=_y;
			
			
			laberint[y][x]=JUGADOR;
		}
		
		
	}
	
	public void spawnPlayer(int _x, int _y) throws UnableSpawnPlayerException{
		
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
	
	public void breakWall(int x, int y) throws UnableWallException{ //TRENCA UN MUR
				
		//char wall=laberint[y][x];
		if(isWall(x,y)) laberint[y][x]=CAMI;
		else throw new UnableWallException(x, y); //LLENÇA UNA EXCEPCIÓ SI INTENTAT TRENCAR UN MUR DE COORDENADES 'x' I 'y' QUE NO EXISTEIX
		
	}
	
	public void takePickaxe(int x, int y) throws UnablePickaxeException{ //AGAFA EL PIC DE LES COORDENADES 'x' I 'y'
				
		//char wall=laberint[y][x];
		
		if(isPickaxe(x, y)) {
			laberint[y][x]=CAMI;
			picsMap.remove((Object)(y + "-" + x));
		}
		else throw new UnablePickaxeException(x, y); //LLENÇA UNA EXCEPCIÓ SI NO INTENTA AGAFAR UN PIC DE COORDENADES 'x' I 'y' QUE NO EXISTEIX
		
	}
	
	public void exploteBomb(int x, int y) throws UnableBombException { //EXPLOTA LA BOMBA DE LES COORDENADES 'x' I 'y'
		
		//char bomb=laberint[y][x];
		
		if(isBomb(x, y)) {
			laberint[y][x]=CAMI;
			bombsMap.remove((Object)(y + "-" + x));
		}
		else throw new UnableBombException(x, y); //LLENÇA UNA EXCEPCIÓ EN EL CAS D'INTENTAT EXPLOTAR UNA BOMBA DE COORDENADES 'x' I 'y' QUE NO EXISTEIX
		
	}
	
	public void exitMaze(int x, int y) throws UnableExitException { //SURT DEL LABERINT EN LES COORDENADES 'x' I 'y'
				
		//char exit=laberint[y][x];
		
		if(isExit(x, y)) {
			exit();
		}
		else throw new UnableExitException(x,y); //LLENÇA UNA EXCEPCIÓ SI EN LES COORDENADES 'x' I 'y' NO HI HA UNA SORTIDA
		
	}
	
	public char[][] getMaze() {
		return laberint;
	}
	
	public boolean isPlaying() { //RETORNA 'false' SI HA ACABAT LA PARTIDA O 'true' SI CONTINUA
		return playing;
	}
	
	public void finish(String msj) { //FINALITZA LA PARTIDA
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
	
	public boolean isBomb(int x, int y) {

		if((x<0||y<0)&&(x>laberint[0].length||y>laberint.length)) return false;
		
		char b=laberint[y][x];
		
		if(visibleBombs&&b==BOMBA) return true;
		else if(!visibleBombs) {
			
			boolean isBomb=false;
			
			for (String ids_bombs : bombsMap) {
				int[] crd=getCrd(ids_bombs);
				if(x==crd[1]&&y==crd[0]) isBomb=true;
			}
			
			return isBomb;
			
		}
		else return false;
		
	}

	public boolean isPickaxe(int x, int y) {

		if(x<0||y<0||x>=laberint[0].length||y>=laberint.length) return false;
		
		char p=laberint[y][x];
		
		if(p==PIC) return true;
		else return false;
		
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
	
	public boolean isBorder(int x, int y) {

		if(x<0||y<0||x>=laberint[0].length||y>=laberint.length) return false;
		
		char b=laberint[y][x];
				
		if(b==BORDER) return true;
		else return false;
		
	}
	
	public boolean isSpawn(int x, int y) {

		if(x<0||y<0||x>=laberint[0].length||y>=laberint.length) return false;
		
		boolean spawn=false;
		
		for (String crd : spawnPoints) {
			
			if((y + "-" + x).equals(crd)) spawn=true;
			
		}
		
		return spawn;
	}
	
	public String[] getSpawnPoints() {
		return spawnPoints;
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
	
	public void setObstacles(boolean obstacles) {
	
		if(obstacles) {
			
			for (String ids_pics : picsMap) {
				
				int[] crd=getCrd(ids_pics);
				
				if(laberint[crd[0]][crd[1]]!=JUGADOR) {
					laberint[crd[0]][crd[1]]=PIC;
				}
				
			}
						
		}else {
			
			for (String ids_pics : picsMap) {
				int[] crd=getCrd(ids_pics);
				
				laberint[crd[0]][crd[1]]=CAMI;
			}
			
		}			
		
		setVisibleBombs(obstacles);
		visibleBombs=!obstacles;
		
	}
	
	public void setVisibleBombs(boolean _visibleBombs) {
		
		if(_visibleBombs) {
			
			for (String ids_bombes : bombsMap) {
				
				int[] crd=getCrd(ids_bombes);
				
				if(laberint[crd[0]][crd[1]]!=JUGADOR) {
					laberint[crd[0]][crd[1]]=BOMBA;
				}				
			}
			
		}else {
			
			for (String ids_bombes : bombsMap) {
				int[] crd=getCrd(ids_bombes);
				
				laberint[crd[0]][crd[1]]=CAMI;
			}
			
		}
		
		visibleBombs=_visibleBombs;
	}
	
	public void RandomMaze(int limit) {
				
		int length=limit;
		
		if(limit==-1) 
			length=(int)(Math.random()*40+10);
		
		else if(limit>50) length=50;
		else if(limit<10) length=10;
		
		
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
		
		bombsMap=new ArrayList<String>();
		picsMap=new ArrayList<String>();
		
		
		//scan.close();
				
	}
	
	private void generateRandomMaze(ArrayDeque<String> _stack, int _x, int _y) {
		
		boolean moviment=false;
		int contador=0;
		
		int x=_x;
		int y=_y;
		ArrayDeque<String> stack=_stack;
		
		ArrayList<Integer> moviments=new ArrayList<Integer>();
		
		while(!moviment&&contador<4) {
			
			int mov=(int)(Math.random()*4);
			
			boolean registrat=false;
			
			Iterator<Integer> it=moviments.iterator();
			
			while(!registrat&&it.hasNext()) {
				
				int i=it.next();
				
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

	public boolean getVisibleBombs() {
		return visibleBombs;
	}
	
}

//© Daniel Garcia - @daniieelgs