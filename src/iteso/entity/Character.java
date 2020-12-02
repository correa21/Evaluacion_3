/**
 * TODO
 * HP methods
 * check functionality of color atribute
 * 
 */


package iteso.entity;

import java.awt.Color;
import iteso.utils.MovingGameObject;

public abstract class Character extends MovingGameObject {
    private String name;
    public int hp = 10;
    public int width ;
    public int height ;


    protected Character(int xPosition, int yPosition, int xVelocity, 
                        int yVelocity, Color color, boolean visible, int width, int height){
        super(xPosition, yPosition, xVelocity, yVelocity, color, visible);
        this.width = width;
        this.height = height;
    }


    protected String getName(){
        return this.name;
    }

    
}
