package iteso.entity;

import java.awt.Graphics;

import iteso.utils.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Player extends Human implements Drawable {
    private String playerName = "";
    private String nickName = "";
    private int bestScore = 0;
    ImageIcon mainCharacter = new ImageIcon("images/MC.gif");

    public Player() {
        super();
    }

    public Player(String name, String nickName, int posX, int posY, int speed) {
        super(posX, posY, speed);
        this.playerName = name;
        this.nickName = nickName;
        setBestScore(0);
    }

    public String getName() {
        return playerName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public String scoreToString() {
        return String.valueOf(this.bestScore);
    }

    @Override
    public void draw(Graphics g) {
        mainCharacter.paintIcon(null, g, this.x, this.y);
    }
}
