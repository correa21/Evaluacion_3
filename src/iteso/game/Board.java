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
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import iteso.entity.Bullet;
import iteso.entity.Dron;
import iteso.entity.Human;
import iteso.entity.Player;
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
    private Human singleLife;
    private int highScore;
    private int markerX, markerY;
    private static int bossHealth = 30;

    //added backGround
    private ImageIcon backgroundd = new ImageIcon("images/backgroudSkin.png");

    //added array lists
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Dron> enemyList = new ArrayList<>();
    private ArrayList<Bullet> robotBullets = new ArrayList<>();
    private ArrayList<Human> lifeList = new ArrayList<>();


    //added objects
    private Bullet bullet;
    private Dron robots = new Dron(0, 0, false, 0);
    private Player player;
    private Bullet robotBullet;

    //added booleans
    private boolean canFireNewBullet = true;
    private boolean newRobotCanFire = true;
    private boolean hitMarker = false;
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
            /**AQUI AGREGAR 
             * LA ETAPA DE LOGIN 
             * PARA CREAR EL PLAYER 
             * DESPUES
            */
        }
        // Resets all controller movement
        controller.resetController();

        player = new Player("ARmando", "Gradak", 0, 120, null, controller);

        // Sets the life counter Ships
        for (int column = 0; column < numberOfLives; column++) {
            singleLife = new Human(600 - (column * 30), BOARD_HEIGHT-270, null, null);
            lifeList.add(singleLife);
        }
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

        //draw characters from this line below
        backgroundd.paintIcon(null, g, 0, 0);
        if (bullet != null) {
            if (hitMarker) {
                g.setColor(Color.pink);
                if (level != 3 && level != 6 && level != 9 && level != 12) {
                    g.drawString("+ 100", markerX + 20, markerY -= 1);
                } else {
                    g.drawString("- 1", markerX + 75, markerY += 1);
                }
            }
        }

        player.draw(g);

        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.pink);
        g.setFont(small);
        g.drawString("SCORE: " + player.scoreToString(), 10, d.height - 260);

        for (int index = 0; index < lifeList.size(); index++) {
            lifeList.get(index).drawLife(g);
        }
        //create player bullets
        if (player.isShooting() && canFireNewBullet) {
            bullet = new Bullet(player.getXPosition() + 45, player.getYPosition() + 55, 0, null, true);
            bullet.setBulletGraphic("images/bullet.gif");
            canFireNewBullet = false;
            bullets.add(bullet);
        }
        //BFB shooting
        if (controller.getKeyStatus(KeyHandler.BFB) && canFireNewBullet) {
            if (player.isBFBReady()) {
                bullet = new Bullet(player.getXPosition() + 45, player.getYPosition() + 55, 0, null, true);
                bullet.setBulletGraphic("images/bfb.gif");
                canFireNewBullet = false;
                bullets.add(bullet);
            }

        }
        //draw player bullets
        for (int index = 0; index < bullets.size(); index++) {
            bullets.get(index).draw(g);
        }
        //draw enemy
        for (int index = 0; index < enemyList.size(); index++) {
            if ((enemyList.get(index).getXPosition() < 650) &&
                    (enemyList.get(index).getVisible() == false)) {
                enemyList.get(index).setVisibile(true);
            }
            enemyList.get(index).draw(g);
        }
        //create enemy bullets
        if (newRobotCanFire) {
            for (int index = 0; index < enemyList.size(); index++) {
                if (enemyList.get(index).getXVelocity() < 0) {
                    robotBullet = new Bullet(enemyList.get(index).getXPosition() + Dron.WIDTH - 25, enemyList.get(index).getYPosition(), 0, null, true);
                } else {
                    robotBullet = new Bullet(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition(), 0, null, true);
                }
                robotBullet.setYVelocity(robotBullet.getXVelocity());
                robotBullet.setXVelocity(0);
                robotBullet.setBulletGraphic("images/robotBullet.gif");
                robotBullets.add(robotBullet);
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
                //check if bullet is far enough
                if (bullet.getXPosition() > (player.getXPosition()+250)){
                    canFireNewBullet = true;
                }
                // Checks for collisions with normal enemies
                for (int enemyIndex = 0; enemyIndex < enemyList.size(); enemyIndex++) {
                    System.out.println("enemigo "+enemyIndex+" bala "+index);
                    if ((bullets.isEmpty() != true) && (bullets.get(index).isColliding(enemyList.get(enemyIndex)))) {
                        bullets.remove(index);
                        canFireNewBullet = true;
                        // Updates score for normal levels
                        if (level != 3 && level != 6 && level != 9 && level != 12) {
                            player.setBestScore(player.getBestScore()+100);
                            player.bfbMetter += 10000;
                            hitMarker = true;
                            markerX = enemyList.get(index).getXPosition(); // Gets positions that the "+ 100" spawns off of
                            markerY = enemyList.get(index).getYPosition();
                            enemyList.remove(enemyIndex);

                        }
                    }else{
                        break;
                    }
                }
                //check if there still bullets after collision
                if(bullets.isEmpty()){
                    canFireNewBullet = true;
                    break;
                }
                //check if bullet out of screen limit
                if (bullets.get(index).getXPosition() > BOARD_WIDTH){
                    bullets.remove(index);
                    canFireNewBullet = true;
                }
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
            // Checks for beam and player collisions
            for (int index = 0; index < robotBullets.size(); index++) {
                if (robotBullets.get(index).isColliding(player)) {
                    robotBullets.remove(index);
                    lifeList.remove(lifeList.size() - 1); // Removes life if hit by bullet
                }
            }
            //Updates the life counter display 
            if ((player.isColliding)&& !lifeList.isEmpty()) {
                int index = lifeList.size() - 1;
                lifeList.remove(index);
            } 
            // Ends game if player runs out of lives
            else if (lifeList.isEmpty()) {
                // Gives the player an option to play again or exit
                int answer = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "You lost the game with " + player.getBestScore() + " points", 0);
                // If they choose to play again, this resets every element in the game
                if (answer == 0) {
                    lifeList.clear();
                    enemyList.clear();
                    robotBullets.clear();
                    level = 1;
                    numberOfLives = 3;
                    canFireNewBullet = true;
                    newRobotCanFire = true;
                    setupBoard();
                }
                // If they choose not to play again, it closes the game
                if (answer == 1) {
                    System.exit(0);
                }
            }

            if(controller.getKeyStatus(controller.ESCAPE) == true){
                pause = true;
            }
            
        }
        else{
            //agregar opciones de pausa aquí
            String[] buttons = {"Resume","Restart","Exit"};
            int obtionSelect = JOptionPane.showOptionDialog(null,"PAUSE","",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
            // If they choose to play again, this resets every element in the game

            switch (obtionSelect) {
                case 0 -> {
                    System.out.println("Reanudar");
                    controller.setKeyStatus(controller.ESCAPE,false);
                    pause = false;
                    //JOptionPane.getRootFrame().dispose();
                }
                case 1 -> {
                    lifeList.clear();
                    enemyList.clear();
                    robotBullets.clear();
                    level = 1;
                    numberOfLives = 3;
                    canFireNewBullet = true;
                    newRobotCanFire = true;
                    setupBoard();
                }
                case 2 -> System.exit(0);
            }

            //if(controller.getKeyStatus(controller.ESCAPE)){
            //    pause = false;
            //}
        }
    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 20;
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
