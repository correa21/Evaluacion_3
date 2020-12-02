package iteso.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.Visibility;

import javax.swing.ImageIcon;

public class Dron extends Robot {

    private static final int WIDTH = 153;
    private static final int HEIGTH = 210;

    ImageIcon dron = new ImageIcon("images/dron.gif");

    public Dron (int xPosition, int yPosition, boolean visible){


    }
    @Override
    public void draw(Graphics g) {
        if (this.isVisible){
            dron.paintIcon(null, g, this.xPos, this.yPos);
        }
        else{
            g.setColor(color);
            g.fillRect(this.getXPosition(), this.getYPosition(), Dron.WIDTH, Dron.HEIGTH);
        }
        
    }
}
