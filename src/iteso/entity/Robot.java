package iteso.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Robot extends Character {
    private String serialId;

    private boolean moveForward;

    public Robot() {

    }

    public Robot(int xPosition, int yPosition, int xVelocity, int yVelocity, boolean visible, int width, int heigth) {
        super(xPosition, yPosition, xVelocity, yVelocity, null, visible, width, heigth);
        moveForward = true;
        isVisible = false;
    }


    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return null;
    }


}
