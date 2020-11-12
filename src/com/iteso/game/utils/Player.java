package com.iteso.game.utils;

public class Player{
    private String name = "";
    private String nickName = "";
    private int bestScore = 0;

    public Player(){

    }

    public Player(String name, String nickName){
        this.name = name;
        this.nickName = nickName;
        setBestScore(0);
    }

    public String getName(){
        return name;
    }
    public String getNickName(){
        return nickName;
    }
    public int getBestScore(){
        return bestScore;
    }
    public void setBestScore(int bestScore){
        this.bestScore = bestScore;
    }
}
