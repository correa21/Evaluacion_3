package iteso.game;// package iteso.game;

// import java.util.Random;

// import iteso.calendar.Millis;
// import iteso.entity.Character;
// import iteso.entity.Human;
// import iteso.entity.Player;
// import iteso.entity.Robot;
// import iteso.statistics.Statistics;


// public class Game {
//     private static final int NUM_PLAYER = 6;
//     private static final int MIN_ROBOTS = 100;
//     private static final int MAX_ROBOTS = 1_000_000;
//     private Player[] playersArray = new Player[NUM_PLAYER];
//     private Statistics gameStats = Statistics.getInstance();
//     private long score = 0;
//     private Random random = new Random();

//     public Game(){
//         for (int i = 0; i < NUM_PLAYER; i++){
//             playersArray[i] = new Player();
//         }
//     }
//     public void newPlayer(int playerNumber, String name, String nickName){
//         playersArray[playerNumber] = new Player(name, nickName,0,0,0);
//     }
//     public String getPlayerName(int playerNumber){
//         return playersArray[playerNumber].getName();
//     }
//     public String getPlayerNick(int playerNumber){
//         return playersArray[playerNumber].getNickName();
//     }
//     public long getScore(){
//         return score;
//     }
//     public void showTopScores(){
//         System.out.println(gameStats);
//     }
//     public void play(int plyar_number){
//         int numberOfMobs = (int) (Math.random() * (MAX_ROBOTS - MIN_ROBOTS + 1) + MIN_ROBOTS);
//         System.out.println("numero de mobs:"+numberOfMobs+"\n");
//         Character[] mobsArray = new Character[numberOfMobs*2];
//         Millis start = new Millis();
//         for (int i = 0; i < numberOfMobs ; i+=2){
//             mobsArray[i] = spawnHuman();
//             mobsArray[i+1] = spawnRobot();
//         }
//         Millis end = new Millis();
//         score = end.getTimeStamp() - start.getTimeStamp();
//         gameStats.setHonourTableMember(playersArray[plyar_number], end, score);
//     }

//     private Robot spawnRobot(){
//         return new Robot(generateName(),generateCurpSerialId());
//     }
//     private Human spawnHuman(){
//         return new Human(generateName(),generateCurpSerialId());
//     }
//     private String generateCurpSerialId() {
//         int leftLimit = 48; // numeral '0'
//         int rightLimit = 122; // letter 'z'
//         int targetStringLength = 10;
        
//         String generatedString = random.ints(leftLimit, rightLimit + 1)
//             .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//             .limit(targetStringLength)
//             .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//             .toString();
//         return generatedString;
//     }
//     private String generateName() {
//         int leftLimit = 97; // letter 'a'
//         int rightLimit = 122; // letter 'z'
//         int targetStringLength = 10;

//         String generatedString = random.ints(leftLimit, rightLimit + 1)
//             .limit(targetStringLength)
//             .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//             .toString();
//         return (generatedString);
//     }

// }
