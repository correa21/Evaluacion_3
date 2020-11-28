package iteso.entity;

public  class Character {
      private String name;
      public int hp = 10;
      public int x = 0;
      public int y = 0;
      public int speed;
      public int width = 20;
      public int height = 20;

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
