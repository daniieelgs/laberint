package JocLaberint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MazePanel extends JPanel{
		

	private static final long serialVersionUID = 1L;
	private MazeObstacles mapa;
	private Jugador jugador;
	private int vides, pics;
	private boolean debug;
	
	public MazePanel(MazeObstacles _mapa, Jugador _jugador) {
		
				
		mapa=_mapa;
		vides=_jugador.getVidas();
		pics=_jugador.getPics();
		jugador=_jugador;
		
		debug=false;
		
		repaint();
	}
	
    protected void paintComponent(Graphics g) {
        
    	super.paintComponent(g);
            	
    	Graphics2D g2 = (Graphics2D) g;
    	
    	
    	char[][] laberint=mapa.getMaze();
    	
    	int length=laberint.length;
    	
    	int widht=this.getWidth();
    	int height=this.getHeight();
    	
    	//System.out.println(widht + "X" + height + " --> " + length);
    	
    	//int scale=widht>height?(int)(height/length):(int)(widht/length);
    	    	
    	int scale=0, initY=0, initX=0;
    	
    	if(widht>height) {
    		scale=(int)(height/length);
    		initX=(widht/2)-((scale*length)/2);
    	}else {
    		scale=(int)(widht/length);
    		initY=(height/2)-((scale*length)/2);
    	}
    	 
    	/*if(initX==0) initX=scale/2;
    	if(initY==0) initY=scale/2;*/
    	    	
    	//System.out.println("init: " + initX + "," + initY);
    	
    	
    	for(int i=0; i<laberint.length; i++) {
    		
    		for(int j=0; j<laberint[i].length; j++) {
    			
    			int x1, y1, x2, y2;
    			boolean first=false, half=false;;
    			
    			g2.setColor(Color.YELLOW.darker());
    			g2.setStroke(new BasicStroke(1));
    			
    			x1=initX + scale*j;
    			x2=x1 + scale;
    			y1=initY + scale*i;
    			y2=y1;
    			
    			if(debug) {
        			g2.drawLine(x1, y1, x2, y2);
        			
        			x2=x1;
        			y2=y1 + scale;
        			
        			g2.drawLine(x1, y1, x2, y2);
        			
        			g2.setFont(new Font("Arial", Font.PLAIN, 15));
        			g2.drawString(String.valueOf(laberint[i][j]), x1 + (scale/2), y1 + (scale/2));
    			}
    			
    			g2.setFont(new Font("Arial", Font.BOLD, 24));
    			
    			if(!mapa.isWall(j, i)) {
    				
    				BufferedImage imagen = null;
    				
    				if(laberint[i][j]==Mapa.PAS) {
    					
    					/*try {
							imagen=ImageIO.read(MazePanel.class.getResource("Images/pass.png")); //INEFICIENT --> LAGEJA EL JOC
						} catch (IOException e) {
							e.printStackTrace();
						}*/
    					
    					g2.drawImage(imagen, x1 + (scale/2)/2, y1 + (scale/2)/2, scale/2, scale/2, null);
    				}else if(laberint[i][j]==Mapa.JUGADOR) {
    					
    					try {
    						
    						if(vides!=jugador.getVidas()) {
    							imagen=ImageIO.read(MazePanel.class.getResource("Images/jugador_mal.png"));
    							vides=jugador.getVidas();
    						}else if(jugador.getPics()>pics) {
    							imagen=ImageIO.read(MazePanel.class.getResource("Images/jugador_be.png"));
    							pics=jugador.getPics();
    						}
    						else imagen=ImageIO.read(MazePanel.class.getResource("Images/jugador.png"));
    						
						} catch (IOException e) {
							e.printStackTrace();
						}
    					
    					g2.drawImage(imagen, x1, y1, scale, scale, null);
    					
    					
    				}else if(laberint[i][j]==Mapa.SORTIDA) {
    					
    					try {
							imagen=ImageIO.read(MazePanel.class.getResource("Images/sortida.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
    					
    					g2.drawImage(imagen, x1, y1, scale, scale, null);
    					
    				}else if(laberint[i][j]==Mapa.PIC) {
    					
    					try {
							imagen=ImageIO.read(MazePanel.class.getResource("Images/pic.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
    					
    					g2.drawImage(imagen, x1 + (scale/2)/2, y1 + (scale/2)/2, scale/2, scale/2, null);
    					
    				}else if(mapa.isBomb(j, i) && mapa.getVisibleBombs()) {
    					
    					try {
							imagen=ImageIO.read(MazePanel.class.getResource("Images/bomba.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
    					
    					g2.drawImage(imagen, x1 + (scale/2)/2, y1 + (scale/2)/2, scale/2, scale/2, null);
    					
    				}
    			}
    			    			
    			if(mapa.isWall(j, i)) {
    				
        			g2.setColor(Color.BLACK);
    				g2.setStroke(new BasicStroke(2));
    				
    				//HORIZONTALES
    				
        		//	if(((mapa.isWall(j-1, i) || j-1<0) || (!mapa.isWall(j, i+1) && !mapa.isWall(j, i-1))) && (mapa.isWall(j+1,i) || j+1==length)) {
        				
        				if(j==0) first=true;
        				
        				if(j==length-1 || (j+1<length && !mapa.isWall(j+1, i))) half=true;
        				    				
        				/*if((mapa.isWall(j+1,i) || j+1==length) && !(mapa.isWall(j-1, i) || j-1<0)) {
        					
        					x1=initX + scale*j + scale/2;
        					x2=x1 + scale/2;
        					
        				}else{

        				}*/
        				
        			if((mapa.isWall(j-1, i)) || (mapa.isWall(j+1,i) || j+1==length) || (!mapa.isWall(j, i+1) && !mapa.isWall(j, i-1))) {
            				
        				if((mapa.isWall(j+1,i) || j+1==length) && !(mapa.isWall(j-1, i) || j-1<0)) {
            					
            				x1=initX + scale*j + scale/2;
            				x2=x1 + scale/2;
            					
            			}else {
            				x1=initX + ((first?scale/2:scale*j));
                			x2=x1 + ((half?scale/2:scale));
            			}
            				
            			y1=initY + scale/2 + scale*i;
            			y2=y1;
            				
            			g.drawLine(x1, y1, x2, y2);
        			}
        				
        			//}
    				
    				//VERTICALES
    				
    				if(mapa.isWall(j, i-1)) {
    					
    					x1=initX + scale/2 + scale*j;
    					x2=x1;
    					
    					y1=initY + scale/2 + scale*(i-1);
    					y2=y1 + scale;
    					
    					g2.drawLine(x1, y1, x2, y2);
    					
    				}
    				
    			}
    			
    		}
    		
    		if(debug) {
    			g2.setColor(Color.YELLOW.darker());
    			g2.setStroke(new BasicStroke(1));
    			
    			int x1=initX + scale*laberint.length;
    			int x2=x1;
    			int y1=initY + scale*i;
    			int y2=y1 + scale;
    			
    			g2.drawLine(x1, y1, x2, y2);
    		}
    		
    	}
    	
    }
    
    public void setDebug(boolean _debug) {
    	
    	debug=_debug;
    	
    }
    
    public void printMaze(MazeObstacles _mapa, Jugador _jugador) {
    	
    	mapa=_mapa;
    	
    	jugador=_jugador;
    	
		repaint();
    	
    }
    
}

//© Daniel Garcia - @daniieelgs
