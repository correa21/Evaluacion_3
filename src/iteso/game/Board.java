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




public class Board  extends JPanel implements Runnable
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
    private KeyHandler controller;

    public void setupBoard(){
        // Sets enemies for normal levels
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            // Six rows
            for (int row = 0; row < 6; row++) {
                // 5 columns
                for (int column = 0; column < 5; column++) {
                    robots = new Robot((20 + (row * 100)), (20 + (column * 60)), level, 0, column, null, 40, 40); // Enemy speed will increase each level
                    enemyList.add(enemy);
                }
            }
        }
        // Sets enemy for boss levels
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            AudioPlayer.player.start(bossSoundAudio); // Plays boss roar
            enemy = new Enemy(20, 20, 3, 0, 100, null, 150, 150);
            enemyList.add(enemy);
        }
        // Gives directions on level 1
        if (level == 1) {
            JOptionPane.showMessageDialog(null, "Welcome to Space Intruders!\n\nTHINGS TO KNOW:\n\n- Use left/right arrow keys to move\n- Press spacebar to shoot\n- The enemies get faster every level"
                    + "\n- BOSS every 3 levels\n- A bonus enemy will appear randomly\n- Shoot it for extra points!\n- Press R to reset high score\n- All pixel art is original\n- PLAY WITH SOUND\n\nHAVE FUN!");
        }
        // Resets all controller movement
        controller.resetController();

        // Sets the player's ship values   
        playerShip = new Ship(375, 730, null, controller);

        // Sets the life counter Ships
        for (int column = 0; column < numberOfLives; column++) {
            singleLife = new Ship(48 + (column * 20), 10, Color.WHITE, null);
            lifeList.add(singleLife);
        }

        // Sets the values for 3 rows and 3 columns of shields
        for (int row = 0;
                row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                shield = new Shield(100 + (column * 250), 650 - (row * 10), 70, 10, Color.RED);
                shieldList.add(shield);
            }
        }
        player = new Player("ARmando", "Gradak", 0, 120, null, controller);
    }

    public Board()
    {
        
        //set the Panel defaults
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);

        //register controller as key listener
        this.controller = new KeyHandler();
        addKeyListener(controller);

        if (animator == null || !ingame) {
        animator = new Thread(this);
        animator.start();
        }

        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.requestFocusInWindow();
    }

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
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// UPDATE GAME STATE

    public void updateGameState(){
        if (ingame){
            player.move();
            for(int index = 0; index < bullets.size(); index++){
                bullets.get(index).move();
                if (bullet.getXPosition() > (player.getXPosition()+250)){
                    canFireNewBullet = true;
                }
                //check if bullet out of screen limit
                if (bullet.getXPosition() > WIDTH){
                    bullets.remove(index);
                }

            }
        }
        else{
            //agregar opciones de pausa aquí

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
