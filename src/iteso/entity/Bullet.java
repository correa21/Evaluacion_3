/**
 * TODO
 * add enum for bullet type hitbox
 * 
 */

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
    private ImageIcon bullett;
    private boolean isBFB = false;
    private ImageIcon invisible = new ImageIcon("images/invisible.png");

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
    public void setBulletGraphic(String path){
        this.bullett = new ImageIcon(path);
    }
    // Used to draw the bullet
    @Override
    public void draw(Graphics g) {
        if (this.getVisible()){
            bullett.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        }
        else{
            invisible.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        }
    }

    @Override
    public Rectangle getBounds() {
        if(!this.getVisible()){
            Rectangle bulletHitbox = new Rectangle(0, 0, 0, 0);
            return bulletHitbox;
        }
        else if (isBFB){
            Rectangle bulletHitbox = new Rectangle(xPos, yPos, 203, 45);
            return bulletHitbox;
        }
        else{
            Rectangle bulletHitbox = new Rectangle(xPos, yPos, 101, 22);
            return bulletHitbox;
        }
    }


}
