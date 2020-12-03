package iteso.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Robot extends Character {
    private String serialId;

    private boolean shoot;


    public Robot(int xPosition, int yPosition, int xVelocity, int yVelocity, boolean visible, int width, int heigth) {
        super(xPosition, yPosition, xVelocity, yVelocity, null, visible, width, heigth);
    }

    @Override
    public void draw(Graphics g) {
        //do nothing

    }

    @Override
    public Rectangle getBounds() {
        //do nothing
        return null;
    }




}
