package iteso.entity;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import iteso.utils.Drawable;

public class Dron extends Robot implements Drawable {

    ImageIcon dron = new ImageIcon("images/dron.gif");

    @Override
    public void draw(Graphics g) {
        dron.paintIcon(null, g, this.xPos, this.yPos);
    }
}
