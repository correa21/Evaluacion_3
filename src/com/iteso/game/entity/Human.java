package com.iteso.game.entity;

public class Human extends Character {
    private String CURP;

    public Human(String newCURP){
        super();
        this.CURP = newCURP;
    }

    public Human(String newName,String newCURP){
        super(newName);
        this.CURP = newCURP;
    }

    public String getCURP() {
        return this.CURP;
    }

    @Override
    public void Hit() {
        // TODO Auto-generated method stub

    }
}
