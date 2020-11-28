package iteso.entity;

import java.awt.Graphics;


public class Robot extends Character {
    private String serialId;

    private boolean moveForward;
    private boolean isVisible;

    public Robot() {

    }

    public Robot(int x, int y, int s) {
        super(x, y, s);
        moveForward = true;
        isVisible = false;
    }

    public Robot(String newName, String newSerialId) {
        super(newName);
        this.serialId = newSerialId;
    }

    public String getSerialId() {
        return this.serialId;
    }


}
