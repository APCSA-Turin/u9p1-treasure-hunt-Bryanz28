package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size; //sets size to size
        grid = new Sprite[size][size]; //makes new 2d array
        for(int i =0;i<grid.length;i++) { //nested for loop that iterates throughout array
            for(int j= 0;j<grid[0].length;j++) {
                grid[i][j] = new Dot(j, size-i-1); //places dot obj at each spot
            }
        }
    }

    public int getSize() { //returns size
        return size;
    }
 
    public Sprite[][] getGrid(){ //returns grid
        return grid;
    }

    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size-s.getY()-1][s.getX()]=s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on what key you press
        if (direction.equals("w")) { //if w key is pressed
            grid[size - s.getY() - 1][s.getX()] = s; //put sprite on the square above it
            grid[size - s.getY() - 1 + 1][s.getX()] = new Dot(s.getX(), s.getY()); //add a dot obj to the old spot on grid
        }
        if (direction.equals("s")) {//if s key is pressed
            grid[size - s.getY() - 1][s.getX()] = s;//put sprite on the square under it
            grid[size - s.getY() - 1 - 1][s.getX()] = new Dot(s.getX(), s.getY());//add a dot obj to the old spot on grid
        }
        if (direction.equals("a")) {//if a key is pressed
            grid[size - s.getY() - 1][s.getX()] = s;//put sprite on the square left to it
            grid[size - s.getY() - 1][s.getX() + 1] = new Dot(s.getX(), s.getY());//add a dot obj to the old spot on grid
        }
        if (direction.equals("d")) {//if d key is pressed
            grid[size - s.getY() - 1][s.getX()] = s;//put sprite on the square right to it
            grid[size - s.getY() - 1][s.getX() - 1] = new Dot(s.getX(), s.getY());//add a dot obj to the old spot on grid
        }
}


    public void display() { //print out the current grid to the screen 
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){ //iterates though grid and gives each obj an emoji
                if(grid[i][j] instanceof Dot){
                    System.out.print("🟩");
                }
                if(grid[i][j] instanceof Enemy){
                    System.out.print("👮");
                }
                if(grid[i][j] instanceof Player){
                    System.out.print("🥷 ");
                }
                if(grid[i][j] instanceof Treasure &&  !(grid[i][j] instanceof Trophy)){
                    System.out.print("💎");
                }
                if(grid[i][j] instanceof Trophy){
                    System.out.print("🚗");
                }
                if(grid[i][j] instanceof Tree){
                    System.out.print("🌳");
                }
            }
            System.out.println(); //prints out a line
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println(" _____                                             _____ \r\n" + //
                        "( ___ )-------------------------------------------( ___ )\r\n" + //
                        " |   |                                             |   | \r\n" + //
                        " |   | __   __            _                     _  |   | \r\n" + //
                        " |   | \\ \\ / /__  _   _  | |    ___  ___  ___  | | |   | \r\n" + //
                        " |   |  \\ V / _ \\| | | | | |   / _ \\/ __|/ _ \\ | | |   | \r\n" + //
                        " |   |   | | (_) | |_| | | |__| (_) \\__ \\  __/ |_| |   | \r\n" + //
                        " |   |   |_|\\___/ \\__,_| |_____\\___/|___/\\___| (_) |   | \r\n" + //
                        " |___|                                             |___| \r\n" + //
                        "(_____)-------------------------------------------(_____)");
    }

    public void win(){ //use this method to display a win 
        System.out.println(" _____                                               _____ \r\n" + //
                        "( ___ )---------------------------------------------( ___ )\r\n" + //
                        " |   |                                               |   | \r\n" + //
                        " |   | __   _____  _   _  __        _____ _   _   _  |   | \r\n" + //
                        " |   | \\ \\ / / _ \\| | | | \\ \\      / /_ _| \\ | | | | |   | \r\n" + //
                        " |   |  \\ V / | | | | | |  \\ \\ /\\ / / | ||  \\| | | | |   | \r\n" + //
                        " |   |   | || |_| | |_| |   \\ V  V /  | || |\\  | |_| |   | \r\n" + //
                        " |   |   |_| \\___/ \\___/     \\_/\\_/  |___|_| \\_| (_) |   | \r\n" + //
                        " |___|                                               |___| \r\n" + //
                        "(_____)---------------------------------------------(_____)");
    }


}