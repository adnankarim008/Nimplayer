/*
 Programming and Software Development
 Project C
 Name : Adnan Munshi
 ID : 1139173
 Username : munshi@student.unimelb.edu.au
 */

import java.util.Scanner;
import java.util.NoSuchElementException ;
import java.util.StringTokenizer;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Nimsys {
    // Array to store players' information
    private static NimPlayer[] Nimplayerarray = new NimPlayer[0];
    public static StringTokenizer tokenizer;
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String txt;
        try {
            // Create file for storing the array of objects of NimPlayer
            ObjectInputStream inputStream =
                new ObjectInputStream(new FileInputStream("arrayfile"));
            Nimplayerarray = (NimPlayer[]) inputStream.readObject();
            inputStream.close();
        } catch (FileNotFoundException e) {
            //System.out.println("Cannot find file arrayfile.");            
        } catch (ClassNotFoundException e) {
            //System.out.println("Problems with file input.");           
        } catch (IOException e) {
            //System.out.println("Problems with file input.");
        }

        System.out.print("Welcome to Nim\n\n$");
        do {
            txt = sc.nextLine();
            tokenizer = new StringTokenizer(txt);
            String action = tokenizer.nextToken(" ");

            try {
                if (action.equals("addplayer")) {
                    // add player
                    addPlayer();
                    System.out.print("\n$");
                } else if (action.equals("addaiplayer")) {
                    // add AI player
                    addAIPlayer();
                    System.out.print("\n$");
                } else if (action.equals("removeplayer")) {
                    // remove player by user name or remove all players
                    removePlayer();
                    System.out.print("\n$");
                } else if (action.equals("editplayer")) {
                    // editplayer by username
                    editPlayer();
                    System.out.print("\n$");
                } else if (action.equals("resetstats")) {
                    // Reset player statistics for one or all users.
                    resetStats();
                    System.out.print("\n$");
                } else if (action.equals("displayplayer")) {
                    // Display player details for one or all users.
                    displayPlayer();
                    System.out.print("\n$");
                } else if (action.equals("startgame")) {
                    // Start game based on user names of 2 players.
                    startGame();
                    System.out.print("\n$");
                } else if (action.equals("rankings")) {
                    // Rankings in descending order of score unless mentioned rankings asc
                    Rankings();
                    System.out.print("\n$");
                } else if (action.equals("exit"))
                    continue;
                else
                    throw new Exception("'" + action + "'" + " is not a valid command.");
            } catch (Exception e) {
                String message = e.getMessage();
                System.out.println(message);
                System.out.print("\n$");
            }
        }
        while (!txt.equals("exit"));
        System.out.println();

        try {
            // Update the file upon exiting the system.
            ObjectOutputStream outputStream =
                new ObjectOutputStream(new FileOutputStream("arrayfile"));
            outputStream.writeObject(Nimplayerarray);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            System.exit(0);
        }

    }

    public static Scanner getScanner() {
        return Nimsys.sc;
    }
    
    public static void startGame() {
        try {
            StringTokenizer inputs = new StringTokenizer(tokenizer.nextToken(" "));
            int InitialStones = Integer.parseInt(inputs.nextToken(","));
            int UpperBound = Integer.parseInt(inputs.nextToken(","));
            String player1 = inputs.nextToken(",");
            String player2 = inputs.nextToken(",");
            NimGame.setUpperBound(UpperBound);
            NimGame.setInitialStones(InitialStones);
            if (userCheck(player1) && userCheck(player2))
                NimGame.startGame(Nimplayerarray, player1, player2);
            else
                System.out.println("One of the players does not exist.");
        } catch (NoSuchElementException e) {
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }
    
    public static void addPlayer() {
        try {
            StringTokenizer names = new StringTokenizer(tokenizer.nextToken(" "));
            String userName = names.nextToken(",");
            String familyName = names.nextToken(",");
            String givenName = names.nextToken(",");
            NimPlayer newNimplayer = new NimHumanPlayer(userName, familyName, givenName);
            if (Nimsys.userCheck(userName)) {
                System.out.print("The player already exists.\n");

            } else {
                NimPlayer newarray[] = new NimPlayer[Nimplayerarray.length + 1];

                for (int i = 0; i < Nimplayerarray.length; i++)
                    newarray[i] = Nimplayerarray[i];

                newarray[Nimplayerarray.length] = newNimplayer;
                Nimplayerarray = newarray;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }

    public static void addAIPlayer() {
        try {
            StringTokenizer names = new StringTokenizer(tokenizer.nextToken(" "));
            String userName = names.nextToken(",");
            String familyName = names.nextToken(",");
            String givenName = names.nextToken(",");
            NimPlayer newNimplayer = new NimAIPlayer(userName, familyName, givenName);
            if (Nimsys.userCheck(userName)) {
                System.out.print("The player already exists.\n");

            } else {
                NimPlayer newarray[] = new NimPlayer[Nimplayerarray.length + 1];

                for (int i = 0; i < Nimplayerarray.length; i++)
                    newarray[i] = Nimplayerarray[i];

                newarray[Nimplayerarray.length] = newNimplayer;
                Nimplayerarray = newarray;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }

    public static boolean userCheck(String userName) {
        for (int j = 0; j < Nimplayerarray.length; j++) {
            if (Nimplayerarray[j].getuserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static void removePlayer() {
        if (tokenizer.hasMoreTokens()) {
            String userName = tokenizer.nextToken(" ");
            if (userCheck(userName)) {
                for (int i = 0; i < Nimplayerarray.length; i++)
                    if (Nimplayerarray[i].getuserName().equals(userName)) {
                        for (int j = i; j < Nimplayerarray.length - 1; j++)
                            Nimplayerarray[j] = Nimplayerarray[j + 1];
                    }
                NimPlayer newarray1[] = new NimPlayer[Nimplayerarray.length - 1];
                for (int k = 0; k < newarray1.length; k++) {
                    newarray1[k] = Nimplayerarray[k];
                }
                Nimplayerarray = newarray1;
            } else {
                System.out.println("The player does not exist.");
            }

        } else {
            System.out.println("Are you sure you want to remove all players? (y/n)");
            String response = sc.nextLine();
            if (response.equals("y")) {
                Nimplayerarray = new NimPlayer[0];
            }
        }
    }
    
    public static void Rankings() {
        if (tokenizer.hasMoreTokens()) {
            String ranking = tokenizer.nextToken();
            if (ranking.equals("desc")) {
                arrangeRankings();
                printRankings();
            } else if (ranking.equals("asc")) {
                arrangeRankingsAsc();
                printRankings();
            } else {
                arrangeRankings();
                printRankings();
            }
        } else {
            arrangeRankings();
            printRankings();
        }
    }

    public static void printRankings() {
    	int display_players = 10 ;
        if (Nimplayerarray.length > display_players) {
            for (int i = 0; i < display_players; i++) {
                int q = (int) Math.round(Nimplayerarray[i].getPercent());
                String str1 = Integer.toString(q) + "%";
                String str2;
                int w = (int) Nimplayerarray[i].getgamesPlayed();
                if (w > 10) {
                    str2 = Integer.toString(q) + " " + "games";
                } else {
                    str2 = "0" + Integer.toString(q) + " " + "games";
                }

                String s = Nimplayerarray[i].getgivenName();
                String x = Nimplayerarray[i].getfamilyName();
                System.out.printf("%-5s| %s | %s %s", str1, str2, s, x);
                System.out.println();
            }
        } else {
            for (int i = 0; i < Nimplayerarray.length; i++) {

                int q = (int) Math.round(Nimplayerarray[i].getPercent());
                String str1 = Integer.toString(q) + "%";
                double w = Nimplayerarray[i].getgamesPlayed();
                String s = Nimplayerarray[i].getgivenName();
                String x = Nimplayerarray[i].getfamilyName();
                System.out.printf("%-5s| 0%1.0f games | %s %s", str1, w, s, x);
                System.out.println();
            }
        }
    }
    
    public static void arrangeRankings() {
        NimPlayer newNimplayer = new NimPlayer();
        for (int i = 0; i < Nimplayerarray.length; i++) {
            for (int j = i + 1; j < Nimplayerarray.length; j++) {
                if (Nimplayerarray[i].getPercent() < Nimplayerarray[j].getPercent()) {
                    newNimplayer = Nimplayerarray[i];
                    Nimplayerarray[i] = Nimplayerarray[j];
                    Nimplayerarray[j] = newNimplayer;
                }
            }
        }
        for (int i = 0; i < Nimplayerarray.length; i++) {
            for (int j = i + 1; j < Nimplayerarray.length; j++) {
                if (Nimplayerarray[i].getPercent() == Nimplayerarray[j].getPercent() &&
                    Nimplayerarray[i].getuserName().compareTo(Nimplayerarray[j].getuserName()) > 0) {
                    newNimplayer = Nimplayerarray[i];
                    Nimplayerarray[i] = Nimplayerarray[j];
                    Nimplayerarray[j] = newNimplayer;

                }
            }
        }
    }
    
    public static void arrangeRankingsAsc() {
        NimPlayer newNimplayer = new NimPlayer();
        for (int i = 0; i < Nimplayerarray.length; i++) {
            for (int j = i + 1; j < Nimplayerarray.length; j++) {
                if (Nimplayerarray[i].getPercent() > Nimplayerarray[j].getPercent()) {
                    newNimplayer = Nimplayerarray[i];
                    Nimplayerarray[i] = Nimplayerarray[j];
                    Nimplayerarray[j] = newNimplayer;
                }
            }
        }
        for (int i = 0; i < Nimplayerarray.length; i++) {
            for (int j = i + 1; j < Nimplayerarray.length; j++) {
                if (Nimplayerarray[i].getPercent() == Nimplayerarray[j].getPercent() &&
                    Nimplayerarray[i].getuserName().compareTo(Nimplayerarray[j].getuserName()) > 0) {
                    newNimplayer = Nimplayerarray[i];
                    Nimplayerarray[i] = Nimplayerarray[j];
                    Nimplayerarray[j] = newNimplayer;

                }
            }
        }
    }

    public static void resetStats() {
        if (tokenizer.hasMoreTokens()) {
            String userName = tokenizer.nextToken(" ");
            if (userCheck(userName)) {
                for (int i = 0; i < Nimplayerarray.length; i++)
                    if (Nimplayerarray[i].getuserName().equals(userName)) {
                        Nimplayerarray[i].setpercent(0);
                        Nimplayerarray[i].setgamesPlayed(0);
                        Nimplayerarray[i].setgamesWon(0);
                    }
            } else
                System.out.println("The player does not exist.");
        } else {
            System.out.println("Are you sure you want to reset all player statistics? (y/n)");
            String response = sc.nextLine();
            if (response.equals("y")) {
                for (int i = 0; i < Nimplayerarray.length; i++) {
                    Nimplayerarray[i].setpercent(0);
                    Nimplayerarray[i].setgamesPlayed(0);
                    Nimplayerarray[i].setgamesWon(0);
                }
            }
        }
    }

    public static void displayPlayer() {
        NimPlayer newNimplayer = new NimPlayer();
        if (tokenizer.hasMoreTokens()) {
            String userName = tokenizer.nextToken(" ");
            if (userCheck(userName)) {
                for (int i = 0; i < Nimplayerarray.length; i++)
                    if (Nimplayerarray[i].getuserName().equals(userName)) {
                        System.out.println(Nimplayerarray[i].getuserName() + "," +
                            Nimplayerarray[i].getgivenName() + "," + Nimplayerarray[i].getfamilyName() +
                            "," + (int) Nimplayerarray[i].getgamesPlayed() +
                            " games" + "," + (int) Nimplayerarray[i].getgamesWon() + " wins");
                    }
            } else
                System.out.println("The player does not exist.");
        } else {
            for (int i = 0; i < Nimplayerarray.length; i++) {
                for (int j = i + 1; j < Nimplayerarray.length; j++) {
                    if (Nimplayerarray[i].getuserName().compareTo(Nimplayerarray[j].getuserName()) > 0) {
                        newNimplayer = Nimplayerarray[i];
                        Nimplayerarray[i] = Nimplayerarray[j];
                        Nimplayerarray[j] = newNimplayer;

                    }
                }
            }
            for (int i = 0; i < Nimplayerarray.length; i++) {
                System.out.println(Nimplayerarray[i].getuserName() + "," +
                    Nimplayerarray[i].getgivenName() + "," + Nimplayerarray[i].getfamilyName() +
                    "," + (int) Nimplayerarray[i].getgamesPlayed() +
                    " games" + "," + (int) Nimplayerarray[i].getgamesWon() + " wins");
            }

        }
    }

    public static void editPlayer() {
        try {
            StringTokenizer names = new StringTokenizer(tokenizer.nextToken(" "));
            String userName = names.nextToken(",");
            if (userCheck(userName)) {

                String familyName = names.nextToken(",");
                String givenName = names.nextToken(",");
                for (int i = 0; i < Nimplayerarray.length; i++)
                    if (Nimplayerarray[i].getuserName().equals(userName)) {
                        Nimplayerarray[i].setfamilyName(familyName);
                        Nimplayerarray[i].setgivenName(givenName);
                    }
            } else
                System.out.println("The player does not exist.");
        } catch (NoSuchElementException e) {
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }
}