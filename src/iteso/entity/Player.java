package iteso.entity;

import java.awt.Graphics;

import iteso.utils.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Player extends Human {
    private String playerName = "";
    private String nickName = "";
    private int bestScore = 0;
    private static final int LEFTBOUND = 0;
    private static final int RIGHTBOUND = 720 - (150);
    private static final int SPEED = 3;
    private static final int JUMPHEIGHT = 1;
    ImageIcon mainCharacter = new ImageIcon("images/MC2.gif");

    public Player(){
        super();
    }

    public Player(String name, String nickName, int xPosition, int yPosition, Color color, KeyHandler control) {
        super (xPosition,  yPosition,  color,  control);
        this.playerName = name;
        this.nickName = nickName;
        setBestScore(0);
    }

    public String getName() {
        return playerName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public String scoreToString() {
        return String.valueOf(this.bestScore);
    }

    // Draw player controlled ship
    @Override
    public void draw(Graphics g) {
        if (isVisible){
            mainCharacter.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        }
        

    }

    // Gets the hit box for all ship objects
    @Override
    public Rectangle getBounds() {
        Rectangle playerHitBox = new Rectangle(this.getXPosition(), this.getYPosition(), this.width, this.height);
        return playerHitBox;
    }

    // Used to move all ship objects
    @Override
    public void move() {
        // Left arrow key press
        if (control.getKeyStatus(control.LEFT) && (xPos > LEFTBOUND)) {
            xPos -= SPEED;
        }
        else{
            stopMoving();
        }
        // Right arrow key press
        if (control.getKeyStatus(control.RIGHT) && (xPos < RIGHTBOUND)) {
            xPos += SPEED;
        }
        else{
            stopMoving();
        }
        //Up arrow key press 
        // if (control.getKeyStatus(control.JUMP)){
        //     while (this.yPos < (this.height*2)){
        //         this.yPos -= JUMPHEIGHT;
        //     }
        // }else{
        //     while(this.yPos > height){
        //         this.yPos += JUMPHEIGHT;
        //     }
        // }
        if (control.getKeyStatus(control.SHOOT)){
            startShooting();
        }
        else{
            stopShooting();
        }
        if (control.getKeyStatus(control.BFB) && (bfbMetter >= BFBREADY)){
            shootBFB();
        }
    }
}
