import java.util.Scanner;
import java.io.Serializable;

public class NimPlayer implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String givenName;
    private String familyName;
    private double gamesPlayed = 0;
    private double gamesWon = 0;
    private double percent ;
    
    NimPlayer() {

    }
    
    NimPlayer(String userName, String familyName, String givenName) {
        this.userName = userName;
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public double getPercent() {
    	int percentage = 100 ;
        if (gamesPlayed > 0 && gamesWon > 0) {
            percent = (gamesWon / gamesPlayed) * percentage;
            return this.percent;
        }
        return 0;
    }

    public String getuserName() {
        return this.userName;
    }

    public String getgivenName() {
        return this.givenName;
    }

    public String getfamilyName() {
        return this.familyName;
    }

    public double getgamesWon() {
        return this.gamesWon;
    }

    public double getgamesPlayed() {
        return this.gamesPlayed;
    }

    public void setpercent(double percent) {
        this.percent = percent;
    }

    public void setgivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setfamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setgamesPlayed(double gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setgamesWon(double gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int removeStone() {
    	Scanner sc = Nimsys.getScanner();
        System.out.println("\n" + getgivenName() + "'s turn - remove how many?");
        int removestone = sc.nextInt();
        return removestone;
    }

}