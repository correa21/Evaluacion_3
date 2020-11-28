package iteso.statistics;

import iteso.calendar.Millis;
import iteso.entity.Player;

public class Statistics {
    private static final int TOPSCORE = 3;
    private static final int TABLE_ELEMENTS = 3;
    private enum Elements{
        PLAYER,
        DATE,
        SCORE
    }
    private static Statistics instance = null;
    private  int numberOfRegisters = 0;

    private Object [][] scoreTable = new Object[TOPSCORE][TABLE_ELEMENTS];

   private Statistics(){
       for (int i = 0; i < TOPSCORE; i++){
           for (int j = 0; j < TABLE_ELEMENTS; j++){
               if(Elements.PLAYER.ordinal() == j){
                   scoreTable[i][j] = new Player();
               }
               if(Elements.DATE.ordinal() == j){
                   scoreTable[i][j] = new Millis(0L);
               }
               if(Elements.SCORE.ordinal() == j){
                   scoreTable[i][j] = 0L;
               }
           }

       }
   }

   public static Statistics getInstance(){
       if(instance == null) instance = new Statistics();
       return instance;
   }

   public long getBestScore(){
       return (long) scoreTable[0][Elements.SCORE.ordinal()];
   }

   public String getBestPlayerNickName(){
       Player p = (Player) scoreTable[0][Elements.PLAYER.ordinal()];
       return p.getNickName();
   }

   public void setBestScoreDate(Millis date){
       scoreTable[0][Elements.DATE.ordinal()] = date;
   }

   public Millis getBestScoreDate(){
       return (Millis) scoreTable[0][Elements.DATE.ordinal()];
   }

   public Object [][] getHonourTable(){
       return scoreTable;
   }
   private long getScoreAt(int n){
       return (long) scoreTable[n][Elements.SCORE.ordinal()];
   }
   private String getPlayerNickAt(int n){
       Player p = (Player) scoreTable[n][Elements.PLAYER.ordinal()];
       return p.getNickName();
   }
   private Millis getScoreDateAt(int n){
       return (Millis) scoreTable[n][Elements.DATE.ordinal()];
   }
    public void setHonourTableMember(Player player, Millis date, long score){


        if(numberOfRegisters < TOPSCORE){
            scoreTable[numberOfRegisters][Elements.SCORE.ordinal()] = score;
            scoreTable[numberOfRegisters][Elements.DATE.ordinal()] = date;
            scoreTable[numberOfRegisters][Elements.PLAYER.ordinal()] = player;
            numberOfRegisters++;
        }
        else{
            if(((long) scoreTable[TOPSCORE-1][Elements.SCORE.ordinal()]) < score){
                scoreTable[TOPSCORE-1][Elements.SCORE.ordinal()] = score;
                scoreTable[TOPSCORE-1][Elements.DATE.ordinal()] = date;
                scoreTable[TOPSCORE-1][Elements.PLAYER.ordinal()] = player;

            }
        }
        bubbleSort();
    }


    public String toString(){
        if (scoreTable[0][0] == null){return "";}
        return ("\t\tTOP SCORES\n SCORE\t\t\tDATE\t\t\tNICKNAME\n" +
                getBestScore() + "\t" + getBestScoreDate() + "\t" + getBestPlayerNickName() + "\n" +
                getScoreAt(1) + "\t" + getScoreDateAt(1) + "\t" + getPlayerNickAt(1) + "\n" +
                getScoreAt(2) + "\t" + getScoreDateAt(2) + "\t" + getPlayerNickAt(2) + "\n");
    }

    private void bubbleSort(){
        int n = TOPSCORE;
        Object[][] temp = new Object[1][3];
        for(int i = n; i > 0; i--){
            for(int j=(n-1); j > 1; j--){
                if(((long) scoreTable[j-1][Elements.SCORE.ordinal()]) < ((long) scoreTable[j][Elements.SCORE.ordinal()])){
                    //swap elements
                    temp[0][Elements.SCORE.ordinal()] = scoreTable[j-1][Elements.SCORE.ordinal()];
                    temp[0][Elements.DATE.ordinal()] = scoreTable[j-1][Elements.DATE.ordinal()];
                    temp[0][Elements.PLAYER.ordinal()] = scoreTable[j-1][Elements.PLAYER.ordinal()];
                    scoreTable[j-1][Elements.SCORE.ordinal()] = scoreTable[j][Elements.SCORE.ordinal()];
                    scoreTable[j-1][Elements.DATE.ordinal()] = scoreTable[j][Elements.DATE.ordinal()];
                    scoreTable[j-1][Elements.PLAYER.ordinal()] = scoreTable[j][Elements.PLAYER.ordinal()];
                    scoreTable[j][Elements.SCORE.ordinal()] = temp[0][Elements.SCORE.ordinal()];
                    scoreTable[j][Elements.DATE.ordinal()] = temp[0][Elements.DATE.ordinal()];
                    scoreTable[j][Elements.PLAYER.ordinal()] = temp[0][Elements.PLAYER.ordinal()];
                }
            }
        }
    }
}
