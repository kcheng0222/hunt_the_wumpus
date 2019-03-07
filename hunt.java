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
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
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
                if(shootDir.equals("w")){
                    if(playerX - 1 == wumpusX && playerY == wumpusY){
                        System.out.println("You shoot the wumpus! Good game.");
                        break;
                    }
                }
                if(shootDir.equals("s")){
                    if(playerX + 1 == wumpusX && playerY == wumpusY){
                        System.out.println("You shoot the wumpus! Good game.");
                        break;
                    }
                }
                if(shootDir.equals("a")){
                    if(playerY - 1 == wumpusY && playerX == wumpusX){
                        System.out.println("You shoot the wumpus! Good game.");
                        break;
                    }
                }
                if(shootDir.equals("d")){
                    if(playerY + 1 == wumpusY && playerX == wumpusX){
                        System.out.println("You shoot the wumpus! Good game.");
                        break;
                    }
                }
                System.out.println("You missed. Very bad.");
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
            //clearScreen();
            
            if(playerX == batX && playerY == batY){
                System.out.println("You got carried away to a random location!");
                playerX = gen.nextInt(gameMap.length-1)+1;
                playerY = gen.nextInt(gameMap[0].length-1)+1;
                gameMap[playerX][playerY] = "@";
                printMap(gameMap);
            }else if(playerX == wumpusX && playerY == wumpusY){
                gameMap[playerX][playerY] = "#";
                printMap(gameMap);
                System.out.println("You ran into wumpus :(\nyou died");
                break;
            }else{
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
