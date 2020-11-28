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

    public String getCURP() {
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
