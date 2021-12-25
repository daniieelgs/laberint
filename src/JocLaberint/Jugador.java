package JocLaberint;

import java.util.Random;

public class Jugador {

	private int vidas, picos;
	private int x, y; //POSICIÓ DEL JUGADOR EN EL MAPA
		
	private MazeObstacles laberint; //OBJECTE LABERINT
	
	public Jugador(MazeObstacles _laberint) {
		
		vidas=3;
		picos=1;
		
		laberint=_laberint;
		
		generatePosition();
						
		
	}
	
	public Jugador (MazeObstacles _laberint, int _x, int _y) {
		vidas=3;
		picos=1;
		
		laberint=_laberint;
		
		x=_x;
		y=_y;
	}
	
	public Jugador (MazeObstacles _laberint, int _x, int _y, int _vides, int _pics) {
		vidas=_vides;
		picos=_pics;
		
		laberint=_laberint;
		
		x=_x;
		y=_y;
	}
	
	
	private void generatePosition() { //GENERA UNA POSICION ALEATORIA PER EL JUGADOR
									
		//POSIBLE POSICIÓ ON GENERAR AL JUGADOR
			
		String[] spawnPoints=laberint.getSpawnPoints();
		
		Random rand=new Random();
			
		int rand_num=rand.nextInt(spawnPoints.length);
			
		int[] crd=laberint.getCrd(spawnPoints[rand_num]);
			
		
		x=crd[1];
		y=crd[0];

		
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void up(){ //INTENTA PUJAR EL JUGADOR
		
		if(validatePosition(x, y-1)) y-=1;
		
	}
	
	public void down(){ //INTENTA BAIXAR EL JUGADOR
		
		if(validatePosition(x, y+1)) y+=1;
		
	}
	
	public void left(){ //INTENTA GIRAR A L'ESQUERRA EL JUGADOR
		
		if(validatePosition(x-1, y)) x-=1;
		
	}
	
	public void right(){ //INTENTA GIRAR A LA DRETA EL JUGADOR
		
		if(validatePosition(x+1, y)) x+=1;
		
	}
	
	private boolean validatePosition(int x, int y){ //S'ENCARREGA DE COMPROBAR SI ES POT COLOCAR EL JUGADOR EN LES NOVES COORDENADES (x, y) I DESENCADENA L'ACCIÓ
		
		boolean position=true; // 'true' SI ES POT EFECTUAR EL MOVIMENT | 'false' SI NO ES POT EFECTUAR EL MOVIMENT. ES QUEDRÀ QUIET
						
		if(x<0||y<0) position=false;
		
		else{
						
			try {
													
				if(laberint.isBomb(x, y)) { //SI CAU EN UNA BOMBA
						
					laberint.exploteBomb(x, y);
					vidas--;
							
					System.out.println("BOOOOOOOOM!!!");
					printVides();		
					
					if(vidas==0) {
						laberint.finish("Has mort :("); //SI ES QUEDA SENSE VIDES, FINALITZA LA PARTIDA
						printInfo();
					}
						
				}else if(laberint.isPickaxe(x, y)) { //SI AGAFA UN PIC
					
					laberint.takePickaxe(x, y);
					picos++;
					printPics();
						
				}else if(laberint.isWall(x, y)) { //SI INTENTA TRAVESAR UN MUR
											
					if(picos>0) { //SI TE PICS INTENTARÀ TRENCARLO
							
						laberint.breakWall(x, y);
						picos--;
						printPics();
							
					}else {
						position=false; //SI NO TE PICS, EL JUGADOR NO ES MOURÀ DE POSICIÓ
					}
						
				}else if(laberint.isBorder(x, y)) {
					position=false;
				}else if(laberint.isExit(x, y)) { //SI TROBA LA SORTIDA
						
					laberint.exitMaze(x, y);
						
				}
				
			}catch (MazeException e) {
				position=false; //SI HI HA ALGUN ERROR A L'HORA D'INTENTAR MOURE AL JUGADOR, AQUEST ES QUEDARÀ QUIET
				e.printStackTrace();
			}
		}
				
		return position;
		
	}
	
	public void printInfo() { //IMPRIMEIX TOTA LA INFORMACIÓ DEL JUGADOR
		
		System.out.println("Jugador:");
		printVides();
		printPics();
		
	}
	
	public int getVides() {
		return vidas;
	}
	
	public int getPics() {
		return picos;
	}
	
	private void printVides() { //IMPRIMEIX LES VIDES
		System.out.println("	Vides: " + vidas);
	}
	
	private void printPics() { //IMPRIMEIX EL NOMBRE DE PICS
		System.out.println("	Pics: " + picos);
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getPicos() {
		return picos;
	}

	public void setPicos(int picos) {
		this.picos = picos;
	}
		
}

//© Daniel Garcia - @daniieelgs
