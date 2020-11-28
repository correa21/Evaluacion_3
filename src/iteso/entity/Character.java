package iteso.entity;

public  class Character {
      private String name;
      public int hp;
      public int x;
      public int y;
      public int speed;

    public Character(){

    };

    public Character(int x,int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Character(String newName){
        this.name = newName;
    }

    public String getName(){
        return this.name;
    }

    
}
