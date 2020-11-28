/**TODO
 * Create screen limits for mobs
 * spawn enemies
 * generate different enemies
 * score display 
 * define score per bot
 * grafics
 * menu
 * logIN
 * pause with ESC
 * shoot dinamics
 * shoot beam
 */


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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


import iteso.entity.*;




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
    private final int BOARD_WIDTH=500;
    private final int BOARD_HEIGHT=500;
    private final int framesPerSecond = 120;

    // Added Counters
    Random r = new Random();
    private int score = 0;
    private int level = 1;
    private int numberOfLives = 3;
    private int highScore;
    private int markerX, markerY;
    private static int bossHealth = 30;

    //added backGround
    private ImageIcon backgroundd = new ImageIcon("images/backgroundSkin.jpg");


    private int newx = 0;
    private BufferedImage img;
    private Thread animator;
    private Player player;
    private Robot robots;
    private ArrayList<Robot> enemyList = new ArrayList<Robot>();

 
    public Board()
    {
        addKeyListener(new TAdapter());
        addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
        if (level == 1) {
            //aquí pedir nombres y datos para el player
            JOptionPane.showMessageDialog(null, "Welcome to Space Intruders!\n\nTHINGS TO KNOW:\n\n- Use left/right arrow keys to move\n- Press spacebar to shoot\n- The enemies get faster every level"
                    + "\n- BOSS every 3 levels\n- A bonus enemy will appear randomly\n- Shoot it for extra points!\n- Press R to reset high score\n- All pixel art is original\n- PLAY WITH SOUND\n\nHAVE FUN!");
        }
        player = new Player("armando", "Gradak", BOARD_WIDTH-(BOARD_WIDTH-60), BOARD_HEIGHT/2, 2);
        
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
    
    @Override
    public void paint(Graphics g){

        backgroundd.paintIcon(null, g, d.width, d.height);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        //g.fillOval(x,y,r,r);
        
        backgroundd.paintIcon(null, g, 0, 0);

        // player movement
        g.setColor(Color.red);
        g.fillRect(player.x, player.y, player.width, player.height);
        if(player.getMoveRight() == true && player.x < (BOARD_WIDTH - player.width)){
            player.x += player.speed;
        }
        if(player.getMoveLeft() == true && player.x > 0){
            player.x -= player.speed;
        }
        if(player.getMoveUp() == true && player.y > 0){
            player.y -= player.speed;
        }
        if(player.getMoveDown() == true && player.y < (BOARD_HEIGHT - (player.height * 3))){
            player.y += player.speed;
        }
                Font small = new Font("Helvetica", Font.BOLD, 14);
                FontMetrics metr = this.getFontMetrics(small);
                g.setColor(Color.black);
                g.setFont(small);
                g.drawString(player.scoreToString(), 10, d.height-60);

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
                    //change a flag and pause the game
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
                repaint();
                try {
                    time += animationDelay;
                    Thread.sleep(Math.max(0,time - System.currentTimeMillis()));
                }catch (InterruptedException e) {
                    System.out.println(e);
                }//end catch
            }//end while loop
    }//end of run

}//end of class
