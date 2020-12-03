package iteso.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.Visibility;

import javax.swing.ImageIcon;

public class BigRobot extends Robot {

    public static final int WIDTH = 77;
    public static final int HEIGTH = 105;
    private static final int SPEED = 1;

    ImageIcon brIconL = new ImageIcon("images/dron2.gif");
    ImageIcon brIconR = new ImageIcon("images/dron.gif");
    ImageIcon invisible = new ImageIcon("images/invisible.png");

    public BigRobot(int xPosition, int yPosition, boolean visible, int level) {
        super(xPosition, yPosition, - SPEED * level, 0, visible, WIDTH, HEIGTH);

    }

    @Override
    public void draw(Graphics g) {
        if (this.getVisible()) {
            if (this.getXVelocity() < 0) {
                brIconL.paintIcon(null, g, this.xPos, this.yPos);
            } else {
                brIconR.paintIcon(null, g, this.xPos, this.yPos);
            }

        } else {
            invisible.paintIcon(null, g, this.xPos, this.yPos);
        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle enemyHitBox = new Rectangle(this.getXPosition(), this.getYPosition(), BigRobot.WIDTH,
                BigRobot.HEIGTH);
        return enemyHitBox;
    }
    @Override
    public void move() {
        this.xPos += this.getXVelocity();
    }
}
