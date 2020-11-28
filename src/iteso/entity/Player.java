package iteso.entity;


public class Player extends Human {
    private String playerName = "";
    private String nickName = "";
    private int bestScore = 0;

    public Player(){
        super();
    }

    public Player(String name, String nickName, int posX, int posY, int speed){
        super(posX, posY, speed);
        this.playerName = name;
        this.nickName = nickName;
        setBestScore(0);
    }

    public String getName(){
        return playerName;
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
