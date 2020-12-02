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

    protected Character(){

    };

    protected Character(int xPosition, int yPosition, int xVelocity, int yVelocity, Color color, boolean visible){
        super(xPosition, yPosition, xVelocity, yVelocity, null, true);
    }


    protected String getName(){
        return this.name;
    }

    
}
