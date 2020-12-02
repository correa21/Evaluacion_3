package iteso.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

import iteso.utils.MovingGameObject;


public class Bullet extends MovingGameObject {

    // My player shoots bullets!
    private int diameter;
    private int xVelocity = 10;
    private ImageIcon BFB = new ImageIcon("images/bfb.gif");
    private ImageIcon bullet = new ImageIcon("images/bullet.gif");
    private boolean isBFB = false;

    // Constructor for bullet
    public Bullet(int xPosition, int yPosition, int diameter, Color color, boolean visible) {
        super(xPosition, yPosition, 0, 0, color, visible);
        this.diameter = diameter;
        this.setXVelocity(xVelocity);
        this.setYVelocity(0);

    }

    // Gets the diameter of the bullet
    public int getDiameter() {
        return diameter;
    }

    // Used to draw the bullet
    @Override
    public void draw(Graphics g) {
        if (isVisible && isBFB){
            // g.setColor(color);
            // g.fillRect(this.getXPosition(), this.getYPosition(), 7, 15);
            BFB.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        }
        else if (isVisible){
            bullet.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bulletHitbox = new Rectangle(xPos, yPos, 7, 15);
        return bulletHitbox;
    }


}
