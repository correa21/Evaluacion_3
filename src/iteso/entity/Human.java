package iteso.entity;

public class Human extends Character {
    private boolean moveLeft, moveRight, moveUp, moveDown, shoot;
    Boolean isMan = true;
    public Human(){

    }

    public Human(int x, int y, int s){
        super(x,y,s);
        moveLeft = false;
        moveDown = false;
        moveRight = false;
        moveUp = false;
    }

    public Human(String newName,String newCURP){
        super(newName);
        //
    }

    
    public void setMoveLeft(boolean state){
        moveLeft = state;
    }

    public void setMoveRight(boolean state){
        moveRight = state;
    }

    public void setMoveUp(boolean state){
        moveUp = state;
    }

    public void setMoveDown(boolean state){
        moveDown = state;
    }

    public boolean getMoveLeft(){
       return moveLeft;
    }

    public boolean getMoveRight(){
        return moveRight;
    }

    public boolean getMoveUp(){
        return moveUp;
    }

    public boolean getMoveDown(){
        return moveDown;
    }

    public void startShooting(){
        shoot = true;
    }
    public void stopShooting(){
        shoot = false;
    }

    public void stopMoving(){
        setMoveDown(false);
        setMoveLeft(false);
        setMoveRight(false);
        setMoveUp(false);
    }
}
