package iteso.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import iteso.entity.Player;




public class Board  extends JPanel implements Runnable, MouseListener
{
/**KEY DEFINITIONS */
    private static final int UPKEY      = 38;
    private static final int DOWNKEY    = 40;
    private static final int LEFTKEY    = 37;
    private static final int RIGHTKEY   = 39;
    private static final int SPACEBAR   = 32;
    private static final int ESCAPEKEY  = 27;

    boolean ingame = true;
    private Dimension d;
    int BOARD_WIDTH=500;
    int BOARD_HEIGHT=500;
    int newx = 0;
    BufferedImage img;
    String message = "Click Board to Start";
    private Thread animator;
    Player player;

 
    public Board()
    {
          addKeyListener(new TAdapter());
         addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
        player = new Player("armando", "Gradak", BOARD_WIDTH/2, BOARD_HEIGHT-60, 5);
       
           /*         
             try {
                img = ImageIO.read(this.getClass().getResource("mount.jpg"));
            } catch (IOException e) {
                 System.out.println("Image could not be read");
            // System.exit(1);
            }
            */
            if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
            }
                    
  
        setDoubleBuffered(true);
    }
    
    public void paint(Graphics g){
        super.paint(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        //g.fillOval(x,y,r,r);
        
        // player movement
        g.setColor(Color.red);
        g.fillRect(player.x, player.y, 20, 20);
        if(player.getMoveRight() == true){
            player.x += player.speed;
        }
        if(player.getMoveLeft() == true){
            player.x -= player.speed;
        }

                Font small = new Font("Helvetica", Font.BOLD, 14);
                FontMetrics metr = this.getFontMetrics(small);
                g.setColor(Color.black);
            g.setFont(small);
                g.drawString(message, 10, d.height-60);

            if (ingame) {
                
            
                
            
                
            // g.drawImage(img,0,0,200,200 ,null);
            }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if(key==RIGHTKEY){
                player.setMoveRight(false);
            }
            if(key==LEFTKEY){
                player.setMoveLeft(false);
            }
            if(key==UPKEY){
                player.setMoveUp(false);
            }
            if(key==DOWNKEY){
                player.setMoveDown(false);
            }
            if(key==SPACEBAR){
                player.stopShooting();
            }  
        }

        public void keyPressed(KeyEvent e) {
        //System.out.println( e.getKeyCode());
        // message = "Key Pressed: " + e.getKeyCode();
            int key = e.getKeyCode();
                if(key==RIGHTKEY){
                    player.setMoveRight(true);
                }
                if(key==LEFTKEY){
                    player.setMoveLeft(true);
                }
                if(key==UPKEY){
                    player.setMoveUp(true);
                }
                if(key==DOWNKEY){
                    player.setMoveDown(true);
                }
                if(key==SPACEBAR){
                    player.startShooting();
                }
                if(key==ESCAPEKEY){
                    player.setMoveLeft(true);
                }
        }
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
    //
    }

    public void mouseEntered(MouseEvent e) {
    //
    }

    public void mouseExited(MouseEvent e) {
    //
    }

    public void mouseClicked(MouseEvent e) {
    //
    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 15;
        long time = System.currentTimeMillis();
            while (true) {//infinite loop
                // spriteManager.update();
                repaint();
                try {
                    time += animationDelay;
                    Thread.sleep(Math.max(0,time - 
                    System.currentTimeMillis()));
                }catch (InterruptedException e) {
                    System.out.println(e);
                }//end catch
            }//end while loop
    }//end of run

}//end of class
