package iteso.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.ImageIcon;


import iteso.utils.KeyHandler;

public class Human extends ControlledCharacter {
    private boolean moveLeft, moveRight, moveUp, moveDown; 
    private boolean shoot;
    public int bfbMetter = 0;
    protected static final int BFBREADY = 1000;
    protected  int width = 54;
    protected int height = 86;
    ImageIcon life = new ImageIcon("images/life.gif");


    public Human(){
        super();
    }
    public Human(int xPosition, int yPosition, Color color, KeyHandler control){
        super(xPosition, yPosition, color, control,true);
        moveLeft = false;
        moveDown = false;
        moveRight = false;
        moveUp = false;
    }

    public boolean isShooting(){
        return shoot;
    }

	public void setMoveLeft(boolean state) {
        moveLeft = state;
    }

    public void setMoveRight(boolean state) {
        moveRight = state;
    }

    public void setMoveUp(boolean state) {
        moveUp = state;
    }

    public void setMoveDown(boolean state) {
        moveDown = state;
    }

    public boolean getMoveLeft() {
        return moveLeft;
    }

    public boolean getMoveRight() {
        return moveRight;
    }

    public boolean getMoveUp() {
        return moveUp;
    }

    public boolean getMoveDown() {
        return moveDown;
    }

    public boolean isBFBReady(){
        if (bfbMetter >= BFBREADY){
            bfbMetter -= BFBREADY;
            return true;
        }
        else{
            return false;
        }
    }
    public void startShooting() {
        shoot = true;
    }

    public void stopShooting() {
        shoot = false;
    }

    public void stopMoving() {
        setMoveDown(false);
        setMoveLeft(false);
        setMoveRight(false);
        setMoveUp(false);
    }

    public void drawLife(Graphics g){
        life.paintIcon(null, g, this.getXPosition(), this.getYPosition());

    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return null;
    }
}
