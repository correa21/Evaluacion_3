package iteso.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.Visibility;

import javax.swing.ImageIcon;

public class Dron extends Robot {

    public static final int WIDTH = 153;
    public static final int HEIGTH = 210;

    ImageIcon dronIcon = new ImageIcon("images/dron.gif");

    public Dron (int xPosition, int yPosition, boolean visible, int level){
        super(xPosition, yPosition, level, level, visible, WIDTH, HEIGTH);

    }
    @Override
    public void draw(Graphics g) {
        if (this.isVisible){
            dronIcon.paintIcon(null, g, this.xPos, this.yPos);
        }
        else{
            g.setColor(color);
            g.fillRect(this.getXPosition(), this.getYPosition(), Dron.WIDTH, Dron.HEIGTH);
        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle enemyHitBox = new Rectangle(this.getXPosition(), this.getYPosition(), Dron.WIDTH, Dron.HEIGTH);
        return enemyHitBox;
    }
}
