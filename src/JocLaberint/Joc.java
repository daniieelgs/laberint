package JocLaberint;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class Joc {
	
	private static Jugador player;
	private static Scanner scan;
	private static Mapa laberint;
	private static boolean random, gui, debug;
	private static int limit;
	private static Frame marc;
	
	public static void main(String[] args) {
				
		random=false;
		limit=-1;
		
		try {
			
			ayuda();
			
			laberint=new Mapa();
						
			laberint.setVisibleBombs(false);
			
			//laberint.RandomMaze();
			
			player=new Jugador(laberint);
			
			player.printInfo();
			
			laberint.spawnPlayer(player.getX(), player.getY());
			
			laberint.print();
			
			start();
			
		} catch (UnableSpawnPlayerException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void start() { //COMENÇA EL JOC
		
		scan=new Scanner(System.in);
		
		while(laberint.isPlaying()) {
			
			String option=scan.next();
			
			if(option.equalsIgnoreCase("info")) {
				
				player.printInfo();
				
			}else if(option.equalsIgnoreCase("reset")) reset();
				
			else if(option.equalsIgnoreCase("exit")) exit();
			
			else if(option.equalsIgnoreCase("obstacles")) {
				
				option=scan.next();
				
				if(option.equalsIgnoreCase("true")) {
					laberint.setObstacles(true);
				}else if(option.equalsIgnoreCase("false")) {
					laberint.setObstacles(false);
				}				

				int x=player.getX();
				int y=player.getY();
				int vides=player.getVides();
				int pics=player.getPics();
				player=new Jugador(laberint, x, y, vides, pics);
				
			}
			
			else if(option.equalsIgnoreCase("bombes")) {
				
				option=scan.next();
				
				if(option.equalsIgnoreCase("true")) {
						laberint.setVisibleBombs(true);
				}else if(option.equalsIgnoreCase("false")) {
						laberint.setVisibleBombs(false);
				}
				
			}
			
			else if(option.equalsIgnoreCase("random")) {
				option=scan.next();
				
				if(option.equalsIgnoreCase("limit")) {
					
					String l=scan.next();
					
					if(l.matches("[+-]?\\d*(\\.\\d+)?"))
						limit=Integer.parseInt(l);
				
					System.out.println("limit: " + limit);
					
				}else if(option.equalsIgnoreCase("true")) {
						
						//if(!random) {

						try {
							random=true;
							laberint=new Mapa();
							laberint.RandomMaze(limit);
							player=new Jugador(laberint);
							laberint.spawnPlayer(player.getX(), player.getY());
							player.setPicos(0);
						} catch (UnableSpawnPlayerException e) {
							e.printStackTrace();
						}
						//}
				}else if(option.equalsIgnoreCase("false")) {
					
					if(random) {
						try {
							random=false;
							laberint=new Mapa();
							player=new Jugador(laberint);
							laberint.spawnPlayer(player.getX(), player.getY());
						} catch (UnableSpawnPlayerException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			else if(option.equalsIgnoreCase("solve")) {
				if(solve()) {
					laberint.finish("Camí resolt :)");
					System.out.print("\n");
				}else {
					System.err.println("El laberint no te solució");
				}
			}
			
			else if(option.equalsIgnoreCase("gui")) {
				
				gui=!gui;
				
				if(gui) {
					marc=new Frame("Laberint v5.0", laberint, player);
					marc.setAlwaysOnTop(true);
					marc.setAlwaysOnTop(false);
				}
				
				else if(marc!=null) marc.dispose();
				
			}
			
			else if(option.equalsIgnoreCase("debug")) {
				
				debug=!debug;
				
				if(marc!=null) marc.setDebug(debug);
				
			}
			
			else if(option.equals("GOD")) {
				god();
			}
			
			else if(option.equals("MAKER")) {
				System.out.println("CREADOR: Daniel Garcia - @daniieelgs");
				JOptionPane.showMessageDialog(null, "Daniel Garcia\n@daniieelgs", "Creador", JOptionPane.WARNING_MESSAGE);
			}
			
			else if(option.length()==1){
			
				char m=option.toUpperCase().charAt(0);
				
				switch (m) {
				
					case 'W': //PUJAR
						player.up();
						break;
					
					case 'S': //BAIXAR
						player.down();
						break;
						
					case 'A': //ESQUERRA
						player.left();
						break;
					
					case 'D': //DRETA
						player.right();
						break;
					
					case '?': //AYUDA
						ayuda();
						break;
				default:
					System.err.println("Opció no vàlida");
				}
				
			}else {
				System.err.println("Command no vàlid");
			}
			
			laberint.setPlayer(player.getX(), player.getY());

			laberint.print();
			if(gui) marc.updateMaze(laberint, player);
			
		}
				
		System.out.println(laberint.getEndMsj());
		
		if(laberint.getExit()) {
			
			System.out.println("Puntuació --> " + (player.getVides()==3?":)":player.getVides()==2?":|":":("));
			
			if(player.getVides()==3) {
				laberint.setVisibleBombs(true);
				
				System.out.print("\n");
				
				laberint.print();
				if(gui) marc.updateMaze(laberint, player);
			}
			
		}
		
		scan.close();
		
	}
	
	private static boolean solve() {
		
		boolean exit=false;
		
		try {
			
			int x, y;
			
			if(!random) {
				laberint=new Mapa();
				
				laberint.setObstacles(false);
				
				x=player.getX();
				y=player.getY();
				
				
				if(laberint.isSpawn(x, y)) 
					player=new Jugador(laberint, x, y);
				else 
					player=new Jugador(laberint);
				
				
				laberint.spawnPlayer(player.getX(), player.getY());
			}
			
			x=player.getX();
			y=player.getY();
			
			
			System.out.println("RESOLUCIÓ AUTOMÀTICA: PULSA ENTER PER AVANÇAR");
			
			
			
			Set<String> zones_visitades=new HashSet<String>();
			
			ArrayDeque<String> cami=new ArrayDeque<String>(); 			
			
			String posicio=(y + "-" + x);
			
			zones_visitades.add(posicio);
			
			cami.add(posicio);
			
									
			while(!exit) {
											
				if((laberint.isRoad(x+1, y)||laberint.isExit(x+1, y))&&!visited(x+1, y, zones_visitades)) {
					x+=1;
					posicio=(y + "-" + x);
					cami.addLast(posicio);
				}
				else if((laberint.isRoad(x, y+1)||laberint.isExit(x, y+1))&&!visited(x, y+1, zones_visitades)) {
					y+=1;
					posicio=(y + "-" + x);
					cami.addLast(posicio);
				}
				else if((laberint.isRoad(x-1, y)||laberint.isExit(x-1, y))&&!visited(x-1, y, zones_visitades)) {
					x-=1;
					posicio=(y + "-" + x);
					cami.addLast(posicio);
				}
				else if((laberint.isRoad(x, y-1)||laberint.isExit(x, y-1))&&!visited(x, y-1, zones_visitades)) { //ASEGURAR QUE SI ES UN COORDENADR NEGATIVA NO DONI ERROR NOMES FALSE
					y-=1;
					posicio=(y + "-" + x);
					cami.addLast(posicio);
				}
				else { 
				
					if(cami.size()>1) {
												
						cami.removeLast();
						
						String id=cami.getLast();
						
						int[] crd=laberint.getCrd(id);
						
						x=crd[1];
						y=crd[0];
						
					}else {
						
						break;
						
					}
					
				}								
				
				if(laberint.isExit(x, y)) {
					exit=true;
				}
				
				zones_visitades.add(posicio);
				
				
			}
			
			
			if(exit) {
				for (String ids : cami) {
					
					scan.nextLine();
					
					int[] crd=laberint.getCrd(ids);
										
					laberint.setPlayer(crd[1], crd[0]);
					laberint.print();
					if(gui) marc.updateMaze(laberint, player);
														
				}
			}
			
		} catch (MazeException e) {
			e.printStackTrace();
		}		
		
		return exit;
	
	}
	
	private static boolean visited(int x, int y, Collection<String> zones_visitades) {
		
		String posicio=(y + "-" + x);
		
		boolean visited=false;
				
		Iterator<String> it=zones_visitades.iterator();
		
		while(!visited&&it.hasNext()) {
			
			String id=it.next();
			
			if(id.equals(posicio))
				visited=true;
		}
		
		/*for (String id : zones_visitades) {
			if(id.equals(posicio)) {
				visited=true;
				break;
			}
		}	*/	
		
		return visited;
		
	}
	
	private static void exit() { //SURT DEL JOC
		System.out.println("Sortint del joc");
		if(gui) marc.dispose();
		System.exit(0);
	}
	
	private static void reset() { //RESETEJA LA PARTIDA
		
		try {
			
			laberint=new Mapa();
			laberint.setVisibleBombs(false);
			if(random) {
				laberint.RandomMaze(limit);
			}
			player=new Jugador(laberint);
			
			laberint.spawnPlayer(player.getX(), player.getY());
		} catch (UnableSpawnPlayerException e) {
			e.printStackTrace();
		}
	}
	
	private static void ayuda() { //IMPRIMEIX LA INFORMACIÓ BÀSICA DEL JOC
		
		System.out.println("\nCreat per: Daniel Garcia");
		System.out.println("\nTutorial:");
		System.out.println("	P --> Jugador");
		System.out.println("	S --> Sortida");
		System.out.println("	@ --> Bombes. Tingues compte que et poden matar");
		System.out.println("	^ --> Pic. Et permeten eliminar una muralla");
		System.out.println("	# --> Muralla");

		
		System.out.println("\nComands:");
		System.out.println("	'W': Pujar");
		System.out.println("	'S': Baixar");
		System.out.println("	'D': Dreta");
		System.out.println("	'A': Esquerra");
		System.out.println("	'info': Imprimeix informació del jugador");
		System.out.println("	'reset': Torna a començar la partida");
		System.out.println("	'exit': Surt del joc");
		System.out.println("	'solve': Resoleix el joc");
		System.out.println("	'?': Imprimeix aquesta ajuda");
		
		System.out.println("\nBona sort :)");

		System.out.println("\n");

	}
	
	private static void god() {
		
		System.out.println("\nAYUDA AVANÇADA:");
		System.out.println("	'obstacles': true/false Activa o desactiva les bombes i pics");
		System.out.println("	'bombes': true/false Visibilitza les bombes en el mapa");
		System.out.println("	'random': true/false Carrega un mapa generat aleatoriament");
		System.out.println("		'limit': 0-50 Configura les dimensions dels mapes aleatoris. -1 Per a que sigui aleatori");
		System.out.println("	'gui': Carrega la interfaz gráfica del joc");
		System.out.println("		Click dret sobre el laberint: Amaga la informació de vides i pics del jugador");
		System.out.println("	'debug': Activa o desactive el mode debug en la gui");
		System.out.println("	'MAKER': Creador del joc");
		System.out.println("	'GOD': Imprimeix aquesta guia\n");
		
	}

}

// © Daniel Garcia - @daniieelgs
