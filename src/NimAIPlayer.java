public class NimAIPlayer extends NimPlayer implements Testable {

    private static final long serialVersionUID = 1L;

    public NimAIPlayer() {
        super();
    }

    public NimAIPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

    public int removeStone() {
        System.out.println("\n" + getgivenName() + "'s turn - remove how many?");
        int series[] = new int[50];
        int removeStone;
        int Upperbound = NimGame.getUpperBound();
        int InitialStones = NimGame.getInitialStones();
        /* 
         Checking if remaining stones is not k(M + 1) + 1, where k is (0,1,2...)
         and removing stones so opponent is left with k(M + 1) + 1
         */
        for (int i = 0; i < 50; i++) {
            series[i] = i * (Upperbound + 1) + 1;
            if (InitialStones < series[i]) {
                removeStone = InitialStones - series[i - 1];
                return removeStone;
            } else if (InitialStones == series[i]) {
                removeStone = 1;
                return removeStone;
            }
        }
        return 1;
    }

    public String advancedMove(boolean[] available, String lastMove) {
        // the implementation of the victory
        // guaranteed strategy designed by you
        String move = "";

        return move;
    }

}