import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer {

    private static final long serialVersionUID = 1L;

    NimHumanPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

    public int removeStone() {
    	Scanner sc = Nimsys.getScanner();
        System.out.println("\n" + getgivenName() + "'s turn - remove how many?");
        int removestone = sc.nextInt();
        return removestone;
    }

}