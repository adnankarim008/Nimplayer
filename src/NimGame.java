import java.util.Scanner;


public class NimGame {
    private static NimPlayer Player1;
    private static NimPlayer Player2;
    private static int InitialStones;
    private static int UpperBound;

    public static void startGame(NimPlayer[] Nimplayerarray, String player1, String player2) {
        Scanner sc = Nimsys.getScanner();
        int currentPlayer = 1;
        int removestone;
        String discard;

        // Loop through Nimplayerarray to find player 1.
        for (int m = 0; m < Nimplayerarray.length; m++)
            if (Nimplayerarray[m].getuserName().equals(player1)) {
                Player1 = Nimplayerarray[m];
                break;
            }

        // Loop through Nimplayerarray to find player 2.
        for (int n = 0; n < Nimplayerarray.length; n++)
            if (Nimplayerarray[n].getuserName().equals(player2)) {
                Player2 = Nimplayerarray[n];
                break;
            }

        System.out.println("\nInitial stone count: " + InitialStones + "\nMaximum stone removal: " +
            UpperBound + "\nPlayer 1: " + Player1.getgivenName() + " " + Player1.getfamilyName() +
            "\nPlayer 2: " + Player2.getgivenName() + " " + Player2.getfamilyName());
        int count = 0;
        while (InitialStones > 0) {
            System.out.print("\n" + InitialStones + " stones left:");
            String str = " *";

            for (int j = 0; j < InitialStones; j++) {
                System.out.print(str);
            }

            // Alternating player turns
            if (count % 2 == 0) {
                removestone = Player1.removeStone();
                currentPlayer = 1;
            } else {
                removestone = Player2.removeStone();
                currentPlayer = 2;
            }

            count++;

            try {
                if (removestone < 1 || removestone > UpperBound)
                    throw new Exception("\nInvalid move. You must remove between 1 and " +                            UpperBound + " stones.");
            } catch (Exception e) {
                String message = e.getMessage();
                System.out.println(message);
                count--;
                continue;
            }

            InitialStones = InitialStones - removestone;
        }



        if (currentPlayer == 1) {
            System.out.println("\nGame Over");
            System.out.println(Player2.getgivenName() + " " + Player2.getfamilyName() + " wins!");
            Player1.setgamesPlayed(Player1.getgamesPlayed() + 1);
            Player2.setgamesPlayed(Player2.getgamesPlayed() + 1);
            Player2.setgamesWon(Player2.getgamesWon() + 1);
        } else {
            System.out.println("\nGame Over");
            System.out.println(Player1.getgivenName() + " " + Player1.getfamilyName() + " wins!");
            Player1.setgamesPlayed(Player1.getgamesPlayed() + 1);
            Player2.setgamesPlayed(Player2.getgamesPlayed() + 1);
            Player1.setgamesWon(Player1.getgamesWon() + 1);
        }

        if (sc.hasNextLine())
            /* 
            The scanner takes unwanted spaces after this method
            for which this is used to discard any unwanted inputs.
            */
            discard = sc.nextLine();
    }

    public static int getInitialStones() {
        return NimGame.InitialStones;
    }

    public static void setInitialStones(int InitialStones) {
        NimGame.InitialStones = InitialStones;
    }

    public static int getUpperBound() {
        return NimGame.UpperBound;
    }

    public static void setUpperBound(int UpperBound) {
        NimGame.UpperBound = UpperBound;
    }

}