package JocLaberint;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	private MazePanel panel_maze;
	private playerPanel panel_player;
	private boolean amagar;
	
	public Frame(String title, MazeObstacles mapa, Jugador jugador) {
		
		super(title);
		
		amagar=false;
		
		int ancho=Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto=Toolkit.getDefaultToolkit().getScreenSize().height;	
		setBounds(ancho/4, alto/4, ancho/2, alto/2);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_maze=new MazePanel(mapa, jugador);
		panel_player=new playerPanel(jugador);
				
		add(panel_maze, BorderLayout.CENTER);
		add(panel_player, BorderLayout.WEST);
		
		JPopupMenu emergent=new JPopupMenu();
		JMenuItem amagar_menu=new JMenuItem("Amagar Info.");
		amagar_menu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				amagar=!amagar;
				
				if(amagar) {
					
					remove(panel_player);
					
				}else {
					
					add(panel_player, BorderLayout.WEST);
					
				}
				
				panel_maze.updateUI();
				
			}
		});
		
		emergent.add(amagar_menu);
		
		panel_maze.setComponentPopupMenu(emergent);
				
		setVisible(true);
		
		//panel_maze.printMaze();

		this.setAutoRequestFocus(true);
		
		addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyChar()=='w') {
					jugador.up();
				}else if(e.getKeyChar()=='s') {
					jugador.down();
				}else if(e.getKeyChar()=='d') {
					jugador.right();
				}else if(e.getKeyChar()=='a') {
					jugador.left();
				}else if(e.getKeyCode()==27){ //ESC
					mapa.finish("Surtint del joc");
				}
				
				mapa.setPlayer(jugador.getX(), jugador.getY());

				mapa.print();
				updateMaze(mapa, jugador);
				
				if(!mapa.isPlaying()) {
					
					System.out.println(mapa.getEndMsj());
					JOptionPane.showMessageDialog(null, mapa.getEndMsj(), "Saliste", JOptionPane.INFORMATION_MESSAGE);
					
					if(mapa.getExit()) {
						
						if(jugador.getVides()==3) {
							mapa.setVisibleBombs(true);
							
							System.out.print("\n");
							
							mapa.print();
							updateMaze(mapa, jugador);
						}
						
						System.out.println("Puntuació --> " + (jugador.getVides()==3?":)":jugador.getVides()==2?":|":":("));
						
						JOptionPane.showMessageDialog(null, "Puntuació --> " + (jugador.getVides()==3?":)":jugador.getVides()==2?":|":":("), "Oleeee", JOptionPane.INFORMATION_MESSAGE);						
						
					}
					
					dispose();
					System.exit(0);
					
				}
				
			}
			
			public void keyPressed(KeyEvent e) {}
		});
		
	}
	
	public void setDebug(boolean debug) {
		
		panel_maze.setDebug(debug);
		
	}
	
	public void updateMaze(MazeObstacles mapa, Jugador jugador) {
		
		panel_maze.printMaze(mapa, jugador);
		panel_player.loadInfo(jugador);
		
	}
	
	
	
}

//© Daniel Garcia - @daniieelgs
