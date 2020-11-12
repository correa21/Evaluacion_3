package com.iteso.game.entity;

public abstract class Character {
    private static String name;
    private static int hp;
    private static int mp;

    public Character(){

    }

    public Character(String newName){
        this.name = newName;
    }

    public String getName(){
        return this.name;
    }

    public abstract void Hit();
}
