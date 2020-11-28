package iteso.entity;

public class Robot extends Character{
    private String serialId;

    private boolean moveForward;
    
    public Robot(int x, int y, int s){
        super(x,y,s);
        moveForward = false;
    }

    public Robot(String newName,String newSerialId){
        super(newName);
        this.serialId = newSerialId;
    }

    public String getSerialId() {
        return this.serialId;
    }
}
