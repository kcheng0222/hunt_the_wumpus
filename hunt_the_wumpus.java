import java.util.*;

public class WumpusGame{
    
    //Made by Kyle Cheng and Jay Nathan 3/8/2019
    public static void printMap(String[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");       
            }
            System.out.println();
        }
    }
    
    public static String[][] fillMap(String[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                arr[i][j] = "X";       
            }
        }
        return arr;
    }
    
    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
    
    public static boolean nextTo(int a, int b, int c, int d){
        if(((a + 1 == c) && (b == d)) ||((a - 1 == c) && (b == d)) || ((b + 1 == d) && (a == c))||((b - 1 == d) && (a == c))){
            return true;
        }
        return false;
    } 
    public static int[] wrap(int x, int y, String[][] gameMap){
        
        if(x == gameMap.length){
            x = 0;
        }
        if(x == -1){
            x = gameMap.length-1;
        }
        if(y == gameMap[0].length){
            y = 0;
        }
        if(y == -1){
            y = gameMap[0].length-1;
        }
        
        int[] xyCoor = new int[]{x,y};
        return xyCoor;
    }
    
    public static void main(String [] args){
        
        Scanner sc = new Scanner(System.in);
        Random gen = new Random();
        String gameMap[][] = new String[9][9]; //change this if you want to change gameMap size
        String displayMap[][] = new String[9][9];
        
        int[] tempArr = new int[2];
        int gCount = 4;

        gameMap = fillMap(gameMap);
        displayMap = fillMap(displayMap);
        
        int playerX = 0;
        int playerY = 0;
        
        int wumpusX = gen.nextInt(gameMap.length-1) + 1;
        int wumpusY = gen.nextInt(gameMap[0].length-1) + 1;
        
        //wumpusX = 0;
        //wumpusY = 1;
        
        int pitX = gen.nextInt(gameMap.length-1) + 1;
        int pitY = gen.nextInt(gameMap[0].length-1) + 1;
        
        int batX = gen.nextInt(gameMap.length-1) + 1;
        int batY = gen.nextInt(gameMap[0].length-1) + 1;
        
        pitX = gen.nextInt(gameMap.length-1) + 1;
        pitY = gen.nextInt(gameMap[0].length-1) + 1;
        batX = gen.nextInt(gameMap.length-1) + 1;
        batY = gen.nextInt(gameMap[0].length-1) + 1;
        
        while(wumpusX == pitX && wumpusY == pitY){
            pitX = gen.nextInt(gameMap.length-1) + 1;
            pitY = gen.nextInt(gameMap[0].length-1) + 1;
        }
        
        while(wumpusX == batX && wumpusY == batY){
            batX = gen.nextInt(gameMap.length-1) + 1;
            batY = gen.nextInt(gameMap[0].length-1) + 1;
        }
        
        gameMap[playerX][playerY] = "@"; displayMap[playerX][playerY] = "@";
        gameMap[wumpusX][wumpusY] = "#";
        gameMap[batX][batY] = "B";
        printMap(displayMap);
        
        System.out.println("Welcome to wumpus game! Use wasd to move, and 't' to shoot.");
        
        while(true){
            System.out.println("Enter wasd:");
            
            String in = sc.next().substring(0,1);
            
            //shoot time

            if( in.equals("t") && gCount > 0){

                //shoot!
                System.out.println("What direction would you like to throw? Enter wasd:");
                String shootDir = sc.next().substring(0,1);
                int r = 0;
              
                //you used a grenade
                gCount --;

                if(shootDir.equals("w")){
                    if(playerX - 1 == wumpusX && playerY == wumpusY){
                        System.out.println("You shoot the wumpus! Good game.");
                        System.out.println("Here's the map : ");
                        printMap(gameMap);
                        break;
                    }
                    else if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                        gameMap[wumpusX][wumpusY] = "_";
                        r = gen.nextInt(4)+1;
                        
                        if(r == 1){wumpusX ++;}
                        else if(r == 2){wumpusX --;}
                        else if(r == 3){wumpusY --;}
                        else{wumpusY ++;}
                        
                    }
                }
                else if(shootDir.equals("s")){
                    if(playerX + 1 == wumpusX && playerY == wumpusY){
                        System.out.println("You shoot the wumpus! Good game.");
                        System.out.println("Here's the map : ");
                        printMap(gameMap);
                        break;
                    }else if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                        gameMap[wumpusX][wumpusY] = "_";
                        r = gen.nextInt(4) + 1;
                        if(r == 1){wumpusX ++;}
                        else if(r == 2){wumpusX --;}
                        else if(r == 3){wumpusY --;}
                        else{wumpusY ++;}
                        
                    }
                }
                else if(shootDir.equals("a")){
                    if(playerY - 1 == wumpusY && playerX == wumpusX){
                        System.out.println("You shoot the wumpus! Good game.");
                        System.out.println("Here's the map : ");
                        printMap(gameMap);
                        break;
                    }else if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                        gameMap[wumpusX][wumpusY] = "_";
                        r = gen.nextInt(4) + 1;
                        if(r == 1){wumpusX ++;}
                        else if(r == 2){wumpusX --;}
                        else if(r == 3){wumpusY --;}
                        else{wumpusY ++;}
                        
                    }
                }
                else if(shootDir.equals("d")){
                    if(playerY + 1 == wumpusY && playerX == wumpusX){
                        System.out.println("You shoot the wumpus! Good game.");
                        System.out.println("Here's the map : ");
                        printMap(gameMap);
                        break;
                    }else if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                        gameMap[wumpusX][wumpusY] = "_";
                        r = gen.nextInt(4) + 1;
                        if(r == 1){wumpusX ++;}
                        else if(r == 2){wumpusX --;}
                        else if(r == 3){wumpusY --;}
                        else{wumpusY ++;}
                        
                    }
                }
                System.out.println("You missed. Very bad.");
                System.out.println("You have " + gCount + " grenades left...");
                if(gCount < 1){
                    System.out.println("Sorry dummy you ran out of grenades");
                    System.out.println("You loose");

                    System.out.println("Here's the map, loser :");
                    printMap(gameMap);


                    break;
                }
                
                tempArr = wrap(wumpusX,wumpusY,gameMap);
                
                wumpusX = tempArr[0];
                wumpusY = tempArr[1];
                
                gameMap[wumpusX][wumpusY] = "#";

            }else{

                displayMap[playerX][playerY] = "_";

                

                gameMap[playerX][playerY] = "_";
                if(in.equals("w")){
                    playerX -= 1;
                }
                else if(in.equals("s")){
                    playerX += 1;
                }
                else if(in.equals("a")){
                    playerY -= 1;
                }
                else if(in.equals("d")){
                    playerY += 1;
                }

                // player wrap
                tempArr = wrap(playerX,playerY,gameMap);
                
                playerX = tempArr[0];
                playerY = tempArr[1];
                
            }
                        
            gameMap[playerX][playerY] = "@"; displayMap[playerX][playerY] = "@";
            gameMap[batX][batY] = "B";
            gameMap[pitX][pitY] = "P";
            //clearScreen()
            
            if(playerX == batX && playerY == batY){
                // puts player in a random location
                System.out.println("You got carried away to a random location!");
                playerX = gen.nextInt(gameMap.length-1)+1;
                playerY = gen.nextInt(gameMap[0].length-1)+1;
                gameMap[playerX][playerY] = "@"; displayMap[playerX][playerY] = "@";
                printMap(displayMap);
                if(playerX == wumpusX && playerY == wumpusY){
                    System.out.println("The bats dropped you onto the wumpus. oOOOOF.");
                    System.out.println("You loose");
                    System.out.println("Here's the map, loser :");
                    printMap(gameMap);
                    break;
                }
                
                if(playerX == pitX && playerY == pitY){
                    System.out.println("The bats dropped you into the pit. Get dropped.");
                    System.out.println("You loose");
                    System.out.println("Here's the map, loser :");
                    printMap(gameMap);
                    break;
                }
                
            }else if(playerX == wumpusX && playerY == wumpusY){
                // player gets killed if they run into a wumpus(#)
                gameMap[playerX][playerY] = "#";
                printMap(displayMap);
                System.out.println("You ran into wumpus ha :(\nyou died");
                System.out.println("Here's the map, loser :");
                printMap(gameMap);
                break;
            }else if(playerX == pitX && playerY == pitY){
                // player dies if falls into a pit(P)
                gameMap[playerX][playerY] = "P";
                printMap(displayMap);
                System.out.println("You fell into a pit genius :(\nyou died");
                System.out.println("Here's the map, loser :");
                printMap(gameMap);
                break;
            }else{
                //tells player if they are next to a pit(breeze), wumpus(sniff), or bat(flap)
                printMap(displayMap);
                
                if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                    System.out.println("SNIFF");
                }
                
                if(nextTo(playerX, playerY, pitX, pitY)){
                    System.out.println("BREEZE");
                }
                
                if(nextTo(playerX, playerY, batX, batY)){
                    System.out.println("FLAP");
                }
            }
        }
        sc.close();
    }
}
