package JocLaberint;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class playerPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private mouse_event event;
	private JPanel panelInfo;
	
	public playerPanel(Jugador jugador) {		
						
		event=new mouse_event();
		panelInfo=new JPanel(new GridLayout(3,6));
						
		loadInfo(jugador);
				
		add(panelInfo);
	}
	
	public void loadInfo(Jugador jugador) {
		
		//panelInfo=new JPanel(new GridLayout(3,6));
		
		panelInfo.removeAll();
		
		JLabel vides=new JLabel("Vides: ");
		
		panelInfo.add(vides);
		
		vides.setFont(new Font("Verdana", Font.BOLD, 12));
		
		vides.addMouseListener(event);
		
		int vides_jugador=jugador.getVidas();
		
		for(int i=1; i<6; i++) {
			
			JLabel vida_icon=new JLabel();
			BufferedImage icon=null;
			
			vida_icon.setOpaque(true);
						
			if(vides_jugador>0) {
				
				try {
					icon=ImageIO.read(MazePanel.class.getResource("Images/vida.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				vida_icon.setIcon(new ImageIcon(icon.getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
				
				vides_jugador--;
			}
			
			panelInfo.add(vida_icon);
			
		}
		
		for(int i=0; i<6; i++) {
			panelInfo.add(new JLabel());
		}
		
		JLabel pics=new JLabel("Pics: ");
		panelInfo.add(pics);
		
		pics.setFont(new Font("Verdana", Font.BOLD, 12));
		
		pics.addMouseListener(event);
		
		int pics_jugador=jugador.getPicos();
		
		for(int i=1; i<6; i++) {
			
			JLabel pic_icon=new JLabel();
			pic_icon.setOpaque(true);
			BufferedImage icon=null;
			
			if(pics_jugador>0) {
				
				try {
					icon=ImageIO.read(MazePanel.class.getResource("Images/pic_agafat.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				pic_icon.setIcon(new ImageIcon(icon.getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
				
				pics_jugador--;
			}
			
			panelInfo.add(pic_icon);
			
		}
		
		this.updateUI();
		
	}
	
	private class mouse_event extends MouseAdapter{
		
		public void mouseEntered(MouseEvent e) {
			
			JLabel l=(JLabel) e.getSource();
			
			l.setForeground(Color.GREEN.darker());
			
		}
		
		public void mouseExited(MouseEvent e) {
			
			JLabel l=(JLabel) e.getSource();

			l.setForeground(Color.BLACK);

		}
		
	}
	
}
