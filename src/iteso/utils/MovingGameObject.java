package iteso.utils;

import java.awt.Color;

public abstract class MovingGameObject extends GameObject implements Moveable{
    private boolean isVisible = false;
    private int xVel;
    private int yVel;

    
    // Constructor for any non controllable object
    protected MovingGameObject(int xPosition, int yPosition, int xVelocity,
                               int yVelocity, Color color, boolean visible){
        super(xPosition, yPosition, color);
        this.xVel = xVelocity;
        this.yVel = yVelocity;
        this.isVisible = visible;
    
    }
    
    // Accessors and mutators for every part of the MovingGameObject constructor
    public int getXVelocity()
    {
        return xVel;
    }
    public int getYVelocity()
    {
        return yVel;
    }
    public void setXVelocity(int xVelocity)
    {
        this.xVel = xVelocity;
    }
    public void setYVelocity(int yVelocity)
    {
        this.yVel = yVelocity;
    }
    public boolean getVisible(){
        return this.isVisible;
    }
    public void setVisibile(boolean state){
        this.isVisible = state;
    }

    @Override
    // Used to move non controllable objects
    public void move()
    {
        this.xPos += xVel;
        this.yPos += yVel;
    }
    
}