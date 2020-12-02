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
import iteso.utils.KeyHandler;




public class Board  extends JPanel implements Runnable, MouseListener
{

    boolean ingame = true;
    private Dimension d;
    private final int BOARD_WIDTH=720;
    private final int BOARD_HEIGHT=280;
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
    private ImageIcon backgroundd = new ImageIcon("images/backgroudSkin.png");

    //added array lists
    private ArrayList<Bullet> bullets = new ArrayList();

    //added objects
    private Bullet bullet;

    //added booleans
    private boolean canFireNewBullet = true;
    private int newx = 0;
    private BufferedImage img;
    private Thread animator;
    private Player player;
    private Robot robots;
    private ArrayList<Robot> enemyList = new ArrayList<Robot>();
    private KeyHandler controller = new KeyHandler();

 
    public Board()
    {
        this.addKeyListener(controller);
        this.setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        this.setBackground(Color.black);
        setDoubleBuffered(true);
            
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PAINT
    @Override
    public void paint(Graphics g){


        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        //g.fillOval(x,y,r,r);
        
        //draw characters from this line below
        backgroundd.paintIcon(null, g, 0, 0);
        player.draw(g);
    
                Font small = new Font("Helvetica", Font.BOLD, 20);
                FontMetrics metr = this.getFontMetrics(small);
                g.setColor(Color.pink);
                g.setFont(small);
                g.drawString("SCORE: " + player.scoreToString(), 10, d.height-260);
        if(player.isShooting() && canFireNewBullet){
            bullet = new Bullet(player.getXPosition()+45, player.getYPosition()+55, 0, null, true);
            canFireNewBullet = false;
            bullets.add(bullet);
        }
        for(int index = 0; index < bullets.size(); index++){
                bullets.get(index).draw(g);
        }
            if (ingame) {
                
            
                
            
                
            // g.drawImage(img,0,0,200,200 ,null);
            }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// UPDATE GAME STATE

    public void updateGameState(){
        player.move();
        for(int index = 0; index < bullets.size(); index++){
            bullets.get(index).move();
            if (bullet.getXPosition() > (player.getXPosition()+250)){
                canFireNewBullet = true;
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// SETUP GAME
    public final void setupGame(){
        if (level == 1) {
            //aquí pedir nombres y datos para el player
            JOptionPane.showMessageDialog(null, "Welcome to Space Intruders!\n\nTHINGS TO KNOW:\n\n- Use left/right arrow keys to move\n- Press spacebar to shoot\n- The enemies get faster every level"
                    + "\n- BOSS every 3 levels\n- A bonus enemy will appear randomly\n- Shoot it for extra points!\n- Press R to reset high score\n- All pixel art is original\n- PLAY WITH SOUND\n\nHAVE FUN!");
        }
        player = new Player("ARmando", "Gradak", 0, 120, null, controller);
        
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
                    
    }



    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 15;
        long time = System.currentTimeMillis();
            while (true) {//infinite loop
                updateGameState();
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
