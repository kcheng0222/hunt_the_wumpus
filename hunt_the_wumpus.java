import java.util.*;
import java.io.*;

public class WumpusGame{
    
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
    public static void main(String [] args){
        
        Scanner sc = new Scanner(System.in);
        Random gen = new Random();
        String gameMap[][] = new String[15][15];      
        
        gameMap = fillMap(gameMap);
        int playerX = 0;
        int playerY = 0;
        
        int wumpusX = gen.nextInt(gameMap.length-1)+1;
        int wumpusY = gen.nextInt(gameMap[0].length-1)+1;
        
        int pitX = gen.nextInt(gameMap.length-1)+1;
        int pitY = gen.nextInt(gameMap[0].length-1)+1;
        
        int batX = gen.nextInt(gameMap.length-1)+1;
        int batY = gen.nextInt(gameMap[0].length-1)+1;
        
        gameMap[playerX][playerY] = "@";
        gameMap[wumpusX][wumpusY] = "#";
        gameMap[batX][batY] = "B";
        printMap(gameMap);
        
        while(true){
            System.out.println("Enter wasd:");
            
            String in = sc.next().substring(0,1);
            
            //shoot time
            
            if(in.equals("t")){
                //shoot!
                System.out.println("What direction would you like to throw? Enter wasd:");
                String shootDir = sc.next().substring(0,1);
                int r = 0;
                if(shootDir.equals("w")){
                    if(playerX - 1 == wumpusX && playerY == wumpusY){
                        System.out.println("You shoot the wumpus! Good game.");
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
                if(shootDir.equals("s")){
                    if(playerX + 1 == wumpusX && playerY == wumpusY){
                        System.out.println("You shoot the wumpus! Good game.");
                        break;
                    }else if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                        gameMap[wumpusX][wumpusY] = "_";
                        r = gen.nextInt(4)+1;
                        if(r == 1){wumpusX ++;}
                        else if(r == 2){wumpusX --;}
                        else if(r == 3){wumpusY --;}
                        else{wumpusY ++;}
                        
                    }
                }
                if(shootDir.equals("a")){
                    if(playerY - 1 == wumpusY && playerX == wumpusX){
                        System.out.println("You shoot the wumpus! Good game.");
                        break;
                    }else if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                        gameMap[wumpusX][wumpusY] = "_";
                        r = gen.nextInt(4)+1;
                        if(r == 1){wumpusX ++;}
                        else if(r == 2){wumpusX --;}
                        else if(r == 3){wumpusY --;}
                        else{wumpusY ++;}
                        
                    }
                }
                if(shootDir.equals("d")){
                    if(playerY + 1 == wumpusY && playerX == wumpusX){
                        System.out.println("You shoot the wumpus! Good game.");
                        break;
                    }else if(nextTo(playerX, playerY, wumpusX, wumpusY)){
                        gameMap[wumpusX][wumpusY] = "_";
                        r = gen.nextInt(4)+1;
                        if(r == 1){wumpusX ++;}
                        else if(r == 2){wumpusX --;}
                        else if(r == 3){wumpusY --;}
                        else{wumpusY ++;}
                        
                    }
                }
                System.out.println("You missed. Very bad.");
                gameMap[wumpusX][wumpusY] = "#";
            }
            else{
                
                gameMap[playerX][playerY] = "_";
                if(in.equals("w")){
                    playerX -= 1;
                }
                if(in.equals("s")){
                    playerX += 1;
                }
                if(in.equals("a")){
                    playerY -= 1;
                }
                if(in.equals("d")){
                    playerY += 1;
                }
                
                if(playerX == gameMap.length){
                    playerX = 0;
                }
                if(playerX == -1){
                    playerX = gameMap.length-1;
                }
                if(playerY == gameMap[0].length){
                    playerY = 0;
                }
                if(playerY == -1){
                    playerY = gameMap[0].length-1;
                }
            }
                        
            gameMap[playerX][playerY] = "@";
            gameMap[batX][batY] = "B";
            gameMap[pitX][pitY] = "P";
            //clearScreen();
            
            if(playerX == batX && playerY == batY){
                // puts player in a random location
                System.out.println("You got carried away to a random location!");
                playerX = gen.nextInt(gameMap.length-1)+1;
                playerY = gen.nextInt(gameMap[0].length-1)+1;
                gameMap[playerX][playerY] = "@";
                printMap(gameMap);
            }else if(playerX == wumpusX && playerY == wumpusY){
                // player gets killed if they run into a wumpus(#)
                gameMap[playerX][playerY] = "#";
                printMap(gameMap);
                System.out.println("You ran into wumpus ha :(\nyou died");
                break;
            }else if(playerX == pitX && playerY == pitY){
                // player dies if falls into a pit(P)
                gameMap[playerX][playerY] = "P";
                printMap(gameMap);
                System.out.println("You fell into a pit genius :(\nyou died");
                break;
            }else{
                //tells player if they are next to a pit(breeze), wumpus(sniff), or bat(flap)
                printMap(gameMap);
                if((playerX + 1 == wumpusX) && (playerY == wumpusY)){
                    System.out.println("SNIFF");
                }
                else if((playerX - 1 == wumpusX) && (playerY == wumpusY)){
                    System.out.println("SNIFF");
                }
                else if((playerY + 1 == wumpusY) && (playerX == wumpusX)){
                    System.out.println("SNIFF");
                }
                else if((playerY - 1 == wumpusY) && (playerX == wumpusX)){
                    System.out.println("SNIFF");
                }
                
                if((playerX + 1 == pitX) && (playerY == pitY)){
                    System.out.println("BREEZE");
                }
                else if((playerX - 1 == pitX) && (playerY == pitY)){
                    System.out.println("BREEZE");
                }
                else if((playerY + 1 == pitY) && (playerX == pitX)){
                    System.out.println("BREEZE");
                }
                else if((playerY - 1 == pitY) && (playerX == pitX)){
                    System.out.println("BREEZE");
                }
                
                if((playerX + 1 == batX) && (playerY == batY)){
                    System.out.println("FLAP");
                }
                else if((playerX - 1 == batX) && (playerY == batY)){
                    System.out.println("FLAP");
                }
                else if((playerY + 1 == batY) && (playerX == batX)){
                    System.out.println("FLAP");
                }
                else if((playerY - 1 == batY) && (playerX == batX)){
                    System.out.println("FLAP");
                }
                
            }
            
            
            
        }
        
    }
}
