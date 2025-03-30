package com.example.project;

public class Sprite {
    private int x, y;
    // Sprite constructor 
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int X){
        x=X;
    }
    public void setY(int Y){
        y=Y;
    }

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "("+x+","+y+")"; // concatenates the values of x and y with the parentheses
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "[" + (size-y-1) + "]" + "[" + x + "]";
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
