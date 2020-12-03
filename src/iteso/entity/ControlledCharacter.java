package iteso.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import iteso.utils.GameObject;
import iteso.utils.KeyHandler;
import iteso.utils.Moveable;

public class ControlledCharacter extends GameObject implements Moveable {

    KeyHandler control;
    
    public ControlledCharacter(){
        super();
    }

    // Constructor for any controllable object
    public ControlledCharacter(int xPosition, int yPosition, Color color, KeyHandler control, boolean visible){
        super(xPosition, yPosition, color);
        this.control = control;
    }

    @Override
    public void draw(Graphics g) {
        // added to clear an error

    }

    @Override
    public void move() {
        // added to clear an error

    }

    @Override
    public Rectangle getBounds() {
        // added to clear an error
        return null;
    }

    
}
