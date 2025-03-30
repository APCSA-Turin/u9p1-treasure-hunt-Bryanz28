package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size];
        for(int i =0;i<grid.length;i++) {
            for(int j= 0;j<grid[0].length;j++) {
                grid[i][j] = new Dot(j, size-i-1);
            }
        }
    }

    public int getSize() {
        return size;
    }
 
    public Sprite[][] getGrid(){
        return grid;
    }

    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size-s.getY()][s.getX()]=s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        if(direction.equals("s")){
            grid[size-1-s.getY()][s.getX()] = s;
            grid[size-1-1-s.getY()][s.getX()] = new Dot(s.getX(), s.getY());
        } 
        
        if (direction.equals("w")){
            grid[size-s.getY()-1][s.getX()] = s;
            grid[size-1+1-s.getY()][s.getX()] = new Dot(s.getX(), s.getY());
        } 
        
        if (direction.equals("d")){
            grid[size-s.getY()-1][s.getX()] = s;
            grid[size-1-s.getY()][s.getX()-1] = new Dot(s.getX(), s.getY());
        } 
        
        if (direction.equals("a")){
            grid[size-s.getY()-1][s.getX()] = s;
            grid[size-1+1-s.getY()][s.getX()+1] = new Dot(s.getX(), s.getY());
        }
}


    public void display() { //print out the current grid to the screen 
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] instanceof Dot){
                    System.out.print("🟫");
                }
                if(grid[i][j] instanceof Enemy){
                    System.out.print("🌋");
                }
                if(grid[i][j] instanceof Player){
                    System.out.print("👨‍🚀");
                }
                if(grid[i][j] instanceof Treasure){
                    System.out.print("🪨");
                }
                if(grid[i][j] instanceof Trophy){
                    System.out.print("🚀");
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss

    }

    public void win(){ //use this method to display a win 

    }


}