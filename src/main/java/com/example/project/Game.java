package com.example.project;
import java.util.Scanner;

public class Game{
    // makes the variables
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Tree[] tree;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        // initializzes size
        this.size = size;
        // calls initalize and play
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   // game logic
        public void play(){ //write your game logic here
        // creates the scanner object
        Scanner scanner = new Scanner(System.in);
        // runs the game
        while(true){
        try {
            Thread.sleep(100); // Wait for 1/10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearScreen(); // Clear the screen at the beggining of the while loop

        // displays the grid
        System.out.println(" _____                                                      _____ \r\n" + //
                        "( ___ )----------------------------------------------------( ___ )\r\n" + //
                        " |   |                                                      |   | \r\n" + //
                        " |   |  _____                   _     __  __                |   | \r\n" + //
                        " |   | |  ___|__  _ __ ___  ___| |_  |  \\/  | __ _ _______  |   | \r\n" + //
                        " |   | | |_ / _ \\| '__/ _ \\/ __| __| | |\\/| |/ _` |_  / _ \\ |   | \r\n" + //
                        " |   | |  _| (_) | | |  __/\\__ \\ |_  | |  | | (_| |/ /  __/ |   | \r\n" + //
                        " |   | |_|  \\___/|_|  \\___||___/\\__| |_|  |_|\\__,_/___\\___| |   | \r\n" + //
                        " |___|                                                      |___| \r\n" + //
                        "(_____)----------------------------------------------------(_____)");
        grid.display();
        // displays the hud
        System.out.println(player.getCoords());
        System.out.println(player.getRowCol(size));
        System.out.println("Treasure collected: " + player.getTreasureCount());
        System.out.println("Lives remaining: " + player.getLives());
        // asks the user for a direction to move to
        System.out.println("Enter a direction(w,a,s,d) or q to exit: ");
        // sets the string to direction
        String direction = scanner.nextLine();
        // if the user writes q, then quit the for loop and the game
            if (direction.equals("q")) {
                break;
            }
        // else continue
        else {
                // checks to see if the direction is valid and if it dosent collide with tree 
                if (player.isValid(size, direction)) {
                    // creates a boolean to see if the player can move
                    boolean go = true;

                    // if the player moves up
                    if (direction.equals("w")) {
                        //checks if the uobject up is tree
                        if(grid.getGrid()[size - player.getY() - 1 - 1][player.getX()] instanceof Tree){
                            go=false;
                        }
                        // checks to see if the object up is a trophy
                        if (grid.getGrid()[size - player.getY() - 1 - 1][player.getX()] instanceof Trophy) {
                            // if it is, then interacts with it
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1 - 1][player.getX()]);
                            // if the player doesnt have two treasures, then they wont move up. 
                            if (player.getWin() == false) {
                                go = false;
                            }
                        }
                        // checks to see if the object is a treasure and if so, iteracts with it
                        else if (grid.getGrid()[size - player.getY() - 1 - 1][player.getX()] instanceof Treasure && !(grid.getGrid()[size - player.getY() - 1 - 1][player.getX()] instanceof Trophy)) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1 - 1][player.getX()]);
                        }
                        // checks to see if the object is an enemy and if so, interacts with it
                        else if (grid.getGrid()[size - player.getY() - 1 - 1][player.getX()] instanceof Enemy) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1 - 1][player.getX()]);
                        }
                    }
                    // if the player moves down
                    if (direction.equals("s")) {
                        // checks to see if the object down is a tree
                        if(grid.getGrid()[size - player.getY() - 1 + 1][player.getX()] instanceof Tree){
                            go=false;
                        }
                        // checks to see if the object down is a trophy
                        if (grid.getGrid()[size - player.getY() - 1 + 1][player.getX()] instanceof Trophy) {
                            // iteracts with it
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1 + 1][player.getX()]);
                            // if the player doesnt have two treasures then go is set to false and the player doesnt move down
                            if (player.getWin() == false) {
                                go = false;
                            }
                        }
                        // checks to see if the object down is a treasure and if so interact
                        else if (grid.getGrid()[size - player.getY() - 1 + 1][player.getX()] instanceof Treasure && !(grid.getGrid()[size - player.getY() - 1 + 1][player.getX()] instanceof Trophy)) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1 + 1][player.getX()]);
                        }
                        // checks to see if the object down is an enemy and if so interact
                        else if (grid.getGrid()[size - player.getY() - 1 + 1][player.getX()] instanceof Enemy) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1 + 1][player.getX()]);
                        }
                    }
                    // if the player moves to the left
                    if (direction.equals("a")) {
                        // checks to see if the object left is a tree
                        if (grid.getGrid()[size - player.getY() - 1][player.getX() - 1] instanceof Tree) {
                            go=false;
                        }
                        // checks to see if the object left is a trophy
                        if (grid.getGrid()[size - player.getY() - 1][player.getX() - 1] instanceof Trophy) {
                            // interacts with the trophy
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1][player.getX() - 1]);
                            // if the player does not have two treasures then set go to false and player stays
                            if (player.getWin() == false) {
                                go = false;
                            }
                        }
                        // if the object is a treasure then interact with it
                        else if (grid.getGrid()[size - player.getY() - 1][player.getX() - 1] instanceof Treasure && !(grid.getGrid()[size - player.getY() - 1][player.getX() - 1] instanceof Trophy)) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1][player.getX() - 1]);
                        }
                        // if the object is an enemy then interact with it
                        else if (grid.getGrid()[size - player.getY() - 1][player.getX() - 1] instanceof Enemy) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1][player.getX() - 1]);
                        }
                    }
                    // if the plaayer moves to the right
                    if (direction.equals("d")) {
                        // checks to see if the object to the right is a tree
                        if (grid.getGrid()[size - player.getY() - 1][player.getX() + 1] instanceof Tree) {
                            go=false;
                        }
                        // checks to see if the object to the right is a trophy
                        if (grid.getGrid()[size - player.getY() - 1][player.getX() + 1] instanceof Trophy) {
                            // iterates with it
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1][player.getX() +1]);
                            // if the player doesnt have two treasures then go is false and player doesnt move
                            if (player.getWin() == false) {
                                go = false;
                            }
                        }
                        // checks if the object is a treasure and interacts if so
                        else if (grid.getGrid()[size - player.getY() - 1][player.getX() + 1] instanceof Treasure && !(grid.getGrid()[size - player.getY() - 1][player.getX() + 1] instanceof Trophy)) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1][player.getX() + 1]);
                        }
                        // checks if the object is an enemy and interacts if so
                        else if (grid.getGrid()[size - player.getY() - 1][player.getX() + 1] instanceof Enemy) {
                            player.interact(size, direction, treasures.length, grid.getGrid()[size - player.getY() - 1][player.getX() + 1]);
                        }
                    }
                    //  move the player on its new rowcol if valid
                    if (go) {
                        player.move(direction);
                        grid.placeSprite(player,direction);
                    }
                    // if the player wins then break the loop
                    if (player.getWin()) {
                        break;
                    }
                    // if the player dies then break the loop
                    if (player.getLives() == 0) {
                        break;
                    }
                }
            }
        }
        // clears the screen
        clearScreen(); 
        // if the player wins
        if (player.getWin()) {         
            // prints out the win statement
            grid.win();
            System.out.println();
            System.out.println(
                                "....................................................................................................\r\n" + //
                                "....................................................................................................\r\n" + //
                                "....................................................................................................\r\n" + //
                                "....................................................................................................\r\n" + //
                                "....................................:::--=====**--::................................................\r\n" + //
                                "................................-##%%%%%%%%%%%%%%%%%%%%###*-:.......................................\r\n" + //
                                "...............................:#%%%%%%%%%%#-=#%*===++#%%%%%#*......................................\r\n" + //
                                "..............................:#%%%%@%@%%##**#%##*:::*%%%%%%%#+.....................................\r\n" + //
                                "..............................*%%%%#%@@%%=----:::::::*%%%%%%%%#=....................................\r\n" + //
                                ".......................-+=:..=%%%@%#%@@@@%%%%%%%%%#-::----+%%%%*:...................................\r\n" + //
                                "......................##%%#:=#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#+..*##*:............................\r\n" + //
                                "........................::*#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#*=%##*:............................\r\n" + //
                                ".........................=#%%@@%%%%%%%@@@@@@@@@@@@@@@%%%%%%%%%%%%%#-................................\r\n" + //
                                ".........................+%%%@@%@%%%%@@@%%@@@@@@@@%%%%%%%%%%%%%%%%#*:...............................\r\n" + //
                                "........................=%%%%@@%@%@@%%@@@@@@@@@@@@@@@@%%%%%%%%%%%%%#:...............................\r\n" + //
                                "........................=%%%%%@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%*...............................\r\n" + //
                                "........................-%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%+...............................\r\n" + //
                                "........................-%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%#-...............................\r\n" + //
                                "........................=%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%#:...............................\r\n" + //
                                "........................+%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%@%%#:...............................\r\n" + //
                                "........................*%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%#................................\r\n" + //
                                "........................*%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#................................\r\n" + //
                                "........................#@@@@@%##%%##*+++++*******#####%%%%%%@@@@@%#................................\r\n" + //
                                "#####################***%@@@@%+=---:::........................-%@@%#................................\r\n" + //
                                "%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@%%%%%%%%%%%%%####################@@@@%+++===----:::...................\r\n" + //
                                "%@%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#**################***************\r\n" + //
                                "%%%%%%%@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%###%%%%%%#######################\r\n" + //
                                "%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%%%%%%%%################");
        }
        // if the player loses
        if (player.getLives() == 0 ){
            // prints out a gameover statement
            grid.gameover();
            System.out.println();
            System.out.println(":::#%::::::-@*::::::-@+::::::*@-------@@-------@#======*@====++=@@+++++++@%******#@*****##@@#######@\r\n" + //
                                "::.#%::::::-@*::::::-@+::::::*@-------@@-------@#======*@+===+++@@+++++++@%******#@*****##@@#######@\r\n" + //
                                "%%%@@%%%%%%%@@%%%%%%@@@%%%%%%@@%%%%%@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + //
                                "...#%.....:-@*:::::::@+::::::*@+=-:---@%-------@#-=====*@=======@@+++++++@%++****#@*******@@#######@\r\n" + //
                                "...#%......:@*:.:::::@+:::*@@@@@@@@@*-@@-------@#-=====*@=======@@+++++++@%+*****#@*******@@#######@\r\n" + //
                                "...#%......:@*...::::@+::@@@@@@@@@@@@@@@-------@#-=====*@=======@@+++++++@%++****#@*******@@#######@\r\n" + //
                                "...#%......:@*....:::@+:@@@@@@@@@@@@@@@@-------@#-=====*@=======@@+++++++@%++****#@*******@@#######@\r\n" + //
                                "...#%......:@*....:::@+@@@@@@@@@@@@@@@@@-------@#--====*@=======@@+++++++@%++****#@*******@@#######@\r\n" + //
                                "...#%......:@*.....::@#@@@@@@@@@@@@@@@@@=------@#---===*@=======@@+++++++@%++****#@*******@@#######@\r\n" + //
                                "...#%......:@*......:@@@@@@@@@@@@@@@@@@@@@@@---@#---===*@=======@@+++++++@%+++***#@*******@@#######@\r\n" + //
                                "...#%......:@*...::=@@@@@@@@@@@@@@@@@@@@@@@@+--@#---===*@=======@@+++++++@%+++***#@*******@@@@@@@@@@\r\n" + //
                                "...#%......:@*+%@@@@@@@@@@@@@@@@@@@@@@@@@@@@*--@#---===*@=======@@+++++++@%+++***#@*******@@@@@@@@@@\r\n" + //
                                "...#%.....=%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#--@#---===*@=======@@+++++++@%+++***#@*******@@@@@@@@@@\r\n" + //
                                ":::#%::=@@@@@@@@@@@@@@@@@@@@@@@@@@@-#@@@@@@@*--@#======*@=====+=@@+++++++@%******#@#****##@@@@@@@@@@\r\n" + //
                                "+++%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*****@@@@@@***@%******%@#######@@#######@@##%%%%%@%%%%%%%@@@@@@@@@@\r\n" + //
                                "..:@@@@@@@@@@@@@@@@@@@@@@@@@@#@*-::---@@@@@@+--@#-=-===*@=======@@+++++++@%++****#@*******@@@@@@@@%#\r\n" + //
                                ".#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-::::--@@@@@@#--@#-=====*@=======@@+++++++@%++****#@*******@@@@@@@@@#\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-::::--@@@@@@@--@#-=====*@=======@@+++++++@%++****#@*******@@@@@@@@@@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-::----@@@@@@@=-@#======*@=======@@+++++++@%++****#@*****##@@@@@@@@@@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=-----@@@@@@@+-@#-=====*@====++=@@+++++++@%+*****#@*****##@@#######@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@+---+@@@@@@@+-@#======*@=====+=@@+++++++@%******#@******#@@#######@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--=@@@@@@@@=-@#======*@====++=@@+++++++@%******#@*****##@@#######@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%:@@@@@@@@@--@#======*@+==++++@@+++++++@%******#@#**####@@#######@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=-@#======*@+=+++++@@+++++++@%******#@#**####@@#######@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#=-@#======*@+==++++@@+++++++@%******#@****###@@#######@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@@+*@@@@@@@@@@@@@@@@#===@#======*@+++++++@@++++**+@%******#@#######@@#######@\r\n" + //
                                "@@@@@@@@@@@@@@@@@@@@@@@@+---=@@@@@@@@@@@@@@====@#======#@+++++++@@++++**+@%******#@#######@@#######@");
        }
    }



    // initializes the variables
    public void initialize(){
        // makes a new grid depending size
        grid = new Grid(size);

        // makes a player and places it on grid
        player = new Player(0, 0);
        grid.placeSprite(player);

        //places the trees on the grid
        Tree t1 = new Tree(3,3);
        grid.placeSprite(t1);
        Tree t2 = new Tree(1,0);
        grid.placeSprite(t2);
        Tree t3 = new Tree(2,0);
        grid.placeSprite(t3);
        Tree t4 = new Tree(0,4);
        grid.placeSprite(t4);
        Tree t5 = new Tree(1,5);
        grid.placeSprite(t5);
        Tree t6 = new Tree(1,6);
        grid.placeSprite(t6);
        Tree t7 = new Tree(1,7);
        grid.placeSprite(t7);
        Tree t8 = new Tree(1,8);
        grid.placeSprite(t8);
        Tree t9 = new Tree(1,9);
        grid.placeSprite(t9);
        Tree t10 = new Tree(2,10);
        grid.placeSprite(t10);
        Tree t11 = new Tree(3,11);
        grid.placeSprite(t11);
        Tree t12 = new Tree(4,6);
        grid.placeSprite(t12);
        Tree t13 = new Tree(2,2);
        grid.placeSprite(t13);
        Tree t14 = new Tree(3,0);
        grid.placeSprite(t14);
        Tree t15 = new Tree(0,4);
        grid.placeSprite(t15);
        Tree t16 = new Tree(5,0);
        grid.placeSprite(t16);
        Tree t17 = new Tree(6,0);
        grid.placeSprite(t17);
        Tree t18 = new Tree(7,0);
        grid.placeSprite(t18);
        Tree t19 = new Tree(8,1);
        grid.placeSprite(t19);
        Tree t20 = new Tree(9,2);
        grid.placeSprite(t20);
        Tree t21 = new Tree(10,3);
        grid.placeSprite(t21);
        Tree t22 = new Tree(11,4);
        grid.placeSprite(t22);
        Tree t23 = new Tree(10,6);
        grid.placeSprite(t23);
        Tree t24 = new Tree(5,2);
        grid.placeSprite(t24);
        Tree t25 = new Tree(2,4);
        grid.placeSprite(t25);
        Tree t26 = new Tree(5,5);
        grid.placeSprite(t26);
        Tree t27 = new Tree(5,4);
        grid.placeSprite(t27);
        Tree t28 = new Tree(5,3);
        grid.placeSprite(t28);
        Tree t29 = new Tree(6,6);
        grid.placeSprite(t29);
        Tree t30 = new Tree(6,11);
        grid.placeSprite(t30);
        Tree t31 = new Tree(6,10);
        grid.placeSprite(t31);
        Tree t32 = new Tree(6,9);
        grid.placeSprite(t32);
        Tree t33 = new Tree(8,6);
        grid.placeSprite(t33);
        Tree t34 = new Tree(9,6);
        grid.placeSprite(t34);
        Tree t35 = new Tree(10,6);
        grid.placeSprite(t35);
        Tree t36 = new Tree(7,6);
        grid.placeSprite(t36);
        Tree t37 = new Tree(11,5);
        grid.placeSprite(t37);
        Tree t38 = new Tree(9,7);
        grid.placeSprite(t38);
        Tree t39 = new Tree(9,8);
        grid.placeSprite(t39);
        Tree t40 = new Tree(7,4);
        grid.placeSprite(t40);
        
        

        // creates two enemies and places it on grid
        Enemy enemy = new Enemy(4, 7);
        grid.placeSprite(enemy);

        Enemy enemy2 = new Enemy(9,9);
        grid.placeSprite(enemy2);


        
        // makes an enemy list and puts the enemies in it
        enemies = new Enemy[2];
        enemies[0] = enemy;
        enemies[1] = enemy2;

        // places two treasures on grid
        Treasure treasure = new Treasure(2, 5);
        grid.placeSprite(treasure);

        Treasure treasure2 = new Treasure(6,8);
        grid.placeSprite(treasure2);

        // places the treasures on a list
        treasures = new Treasure[2];
        treasures[0] = treasure;
        treasures[1] = treasure2;

        // places a new trophy on grid
        trophy = new Trophy(11, 11);
        grid.placeSprite(trophy);
    }

    // runner
    public static void main(String[] args) {
        // makes a new game and runs it
        Game game = new Game(12);
    }
}