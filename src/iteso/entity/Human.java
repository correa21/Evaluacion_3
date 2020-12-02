package iteso.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import iteso.utils.KeyHandler;

public class Human extends ControlledCharacter {
    private boolean moveLeft, moveRight, moveUp, moveDown; 
    private boolean shoot;
    private boolean pause;
    public int bfbMetter = 0;
    protected static final int BFBREADY = 1000;
    protected  int width = 150;
    protected int height = 120;
    private boolean bfb = false;

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

    public void setPause(boolean state){
        pause = state;
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
        return bfb;
    }
    public void startShooting() {
        shoot = true;
    }

    public void stopShooting() {
        shoot = false;
    }
    public void shootBFB(){
        if (bfbMetter >= BFBREADY){
            bfb = true;
            bfbMetter -= BFBREADY;
        }
        else{
            bfb = false;
        }
            
    }

    public void stopMoving() {
        setMoveDown(false);
        setMoveLeft(false);
        setMoveRight(false);
        setMoveUp(false);
    }


    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return null;
    }
}
