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
    boolean pause = false;
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
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Dron> enemyList = new ArrayList<>();
    private ArrayList<Bullet> robotBullets = new ArrayList<>();


    //added objects
    private Bullet bullet;
    private Dron robots = new Dron(0, 0, false, 0);
    private Player player;
    private Bullet robotBullet;

    //added booleans
    private boolean canFireNewBullet = true;
    private boolean newRobotCanFire = true;
    private int newx = 0;
    private BufferedImage img;
    private Thread animator;
    
    
    private KeyHandler controller;

    public void setupBoard(){
        // Sets enemies for normal levels
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            for(int index = 0; index < level * 3; index++){
                robots = new Dron(BOARD_WIDTH+Dron.WIDTH+(Dron.WIDTH*index), d.height-250, false, level);
                enemyList.add(robots);
            }
                
        }
    
        // Gives directions on level 1
        if (level == 1) {
            JOptionPane.showMessageDialog(null, "Welcome to Space Intruders!\n\nTHINGS TO KNOW:\n\n- Use left/right arrow keys to move\n- Press spacebar to shoot\n- The enemies get faster every level"
                    + "\n- BOSS every 3 levels\n- A bonus enemy will appear randomly\n- Shoot it for extra points!\n- Press R to reset high score\n- All pixel art is original\n- PLAY WITH SOUND\n\nHAVE FUN!");
        }
        // Resets all controller movement
        controller.resetController();

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

        setupBoard();
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.requestFocusInWindow();

        if (animator == null || !ingame) {
        animator = new Thread(this);
        animator.start();
        }

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
        //create player bullets
        if(player.isShooting() && canFireNewBullet){
            bullet = new Bullet(player.getXPosition()+45, player.getYPosition()+55, 0, null, true);
            bullet.setBulletGraphic("images/bullet.gif");
            canFireNewBullet = false;
            bullets.add(bullet);
        }
        //BFB shooting
        if(player.isBFBReady() && canFireNewBullet){
            bullet = new Bullet(player.getXPosition()+45, player.getYPosition()+55, 0, null, true);
            bullet.setBulletGraphic("images/bullet.gif");
            canFireNewBullet = false;
            bullets.add(bullet);
        }
        //draw player bullets
        for(int index = 0; index < bullets.size(); index++){
                bullets.get(index).draw(g);
        }
        //draw enemy
        for(int index = 0; index < enemyList.size(); index++){
            if ((enemyList.get(index).getXPosition() < 650) && 
            (enemyList.get(index).getVisible() == false)) {
                enemyList.get(index).setVisibile(true);
            }
            System.out.println("soy el robot "+index+" y estoy en x= "+ enemyList.get(index).getXPosition()+" Y = " +enemyList.get(index).getYPosition());
            enemyList.get(index).draw(g);          
    }
        //create enemy bullets
        if (newRobotCanFire) {
            for (int index = 0; index < enemyList.size(); index++) {
                    if(enemyList.get(index).getXVelocity() < 0){
                        robotBullet = new Bullet(enemyList.get(index).getXPosition()+Dron.WIDTH-25, enemyList.get(index).getYPosition(), 0, null, true);
                    }
                    else{
                        robotBullet = new Bullet(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition(), 0, null, true);
                    }
                    robotBullet.setYVelocity(robotBullet.getXVelocity());
                    robotBullet.setXVelocity(0);
                    robotBullet.setBulletGraphic("images/robotBullet.gif");
                    robotBullets.add(robotBullet);
                    System.out.println("hay esta cantidad de balas de robot"+robotBullets.size());
                
            }
            newRobotCanFire = false;
        }
        // Draws the robots bullets
        for (int index = 0; index < robotBullets.size(); index++) {
            robotBullets.get(index).draw(g);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// UPDATE GAME STATE

    public void updateGameState(){
        if (!pause){
            player.move();
            //move player bullets
            for(int index = 0; index < bullets.size(); index++){
                bullets.get(index).move();
                if (bullet.getXPosition() > (player.getXPosition()+250)){
                    canFireNewBullet = true;
                }
                //check if bullet out of screen limit
                if (bullets.get(index).getXPosition() > BOARD_WIDTH){
                    bullets.remove(index);
                }
                System.out.println(bullets.size());
            }
            //move enemy
            for(int index = 0; index < enemyList.size(); index++){
                
                if (enemyList.get(index).getVisible()){
                    if ((enemyList.get(index).getXPosition() + enemyList.get(index).getXVelocity() < 0) || 
                        (enemyList.get(index).getXPosition() + enemyList.get(index).getXVelocity() > 650)) {
                    enemyList.get(index).setXVelocity(enemyList.get(index).getXVelocity()*(-1));
                    }
                }
                enemyList.get(index).move();
            }
            //move robot bullets
            for(int index = 0; index < robotBullets.size(); index++){
                robotBullets.get(index).move();
                if (robotBullet.getYPosition() > 135){
                    newRobotCanFire = true;
                }
                if (robotBullets.get(index).getYPosition()+robotBullets.get(index).getYVelocity() > BOARD_HEIGHT-130){
                    robotBullets.remove(index);
                }
            }
            if(controller.getKeyStatus(controller.ESCAPE) == true){
                pause = true;
            }
            
        }
        else{
            //agregar opciones de pausa aquí
            System.out.println("estoy en pausa");
            if(controller.getKeyStatus(controller.ESCAPE)){
                pause = false;
            }
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
