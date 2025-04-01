package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;
    //player constructor 
    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount=0;
        numLives=2;
        win=false;
    }


    public int getTreasureCount(){return treasureCount;} //returns treasure count
    public int getLives(){return numLives;} //returns num of lives
    public boolean getWin(){return win;} //returns win

    @Override
    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "Player:"+ super.getRowCol(size); // adds player:
    }
    public String getCoords(){  //returns the coordinates of the sprite ->"(x,y)"
        return "Player:"+ super.getCoords(); // adds Player:
    }
  
    //move method should override parent class, sprite
    //move the (x,y) coordinates of the player (also takes in direction as parameter)
    public void move(String direction) { 
        if(direction.equals("w")){ //checks if direction is w
            super.setY(getY()+1); // increaces y by 1
        }

        if(direction.equals("a")){//checks if direction is a
            super.setX(getX()-1); // decreaces x by 1
        }

        if(direction.equals("s")){//checks if direction is s 
            super.setY(getY()-1); // decreaces y by 1
        }

        if(direction.equals("d")){//checks if direction is d 
            super.setX(getX()+1); // increaces x by 1
        }
    }
    // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
    public void interact(int size, String direction, int numTreasures, Object obj) { 
        if(numTreasures==treasureCount && obj instanceof Trophy ){ //checks if obj is trophy and treasure is all collected
            win=true;
        } 
        else if(obj instanceof Treasure&& !(obj instanceof Trophy)){//checks if obj is treasure
            treasureCount++; //increaces treasure count
        }
        else if(obj instanceof Enemy){ //checks if obj is enemy
            numLives--; //subtract num lives
        }
            
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        if(super.getX()==size-1 && direction.equals("d") ){ //checks for out of bounds collision in right side
            return false;
        }

        if(super.getX()==0 && direction.equals("a") ){//checks for out of bounds collision in left side
            return false;
        }

        if(super.getY()==size-1 && direction.equals("w") ){//checks for out of bounds collision in top side
            return false;
        }

        if(super.getY()==0 && direction.equals("s") ){//checks for out of bounds collision in bottm side
            return false;
        }

        return true;
    }
}



