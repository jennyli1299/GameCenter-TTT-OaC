import java.util.Scanner;

public class OaC extends Board_Games_2P {

    // private Board b;
    private Team Order;
    private Team Chaos;
    // private Team[] turn;
    // private int mod;
    // private boolean gameOver;
    // private String playagain;

    /** 
    public OaC(Player[] o, Player[] c) {
        b = new Board(6);
        Order = new Team("Order", o);
        Chaos = new Team("Chaos", c);
        turn = new Team[2];
        turn[0] = Order;
        turn[1] = Chaos;
        mod = -1;
        gameOver = false;
        playagain = "Yes";
    } */
    
    public OaC(Team o, Team c) {
        b = new Board(6);
        Order = o;
        Order.setRole("Order");
        Chaos = c;
        Chaos.setRole("Chaos");
        turn = new Team[2];
        turn[0] = Order;
        turn[1] = Chaos;
        mod = -1;
        gameOver = false;
        playagain = "Yes";
    }

    public void setOrder(String tn) {
        for (Team t: turn) {
            if (t.getOfficialTitle().equals(tn) && t.getTeamName().equals("Chaos")) {
                Team o = Order;
                // Team c = Chaos;
                Order = Chaos;
                Order.setRole("Order");
                Chaos = o;
                Chaos.setRole("Chaos");
                turn[0] = Order;
                turn[1] = Chaos;
            }
        }
    }

    public void play() {
        System.out.println("\nHello! Welcome to Order and Chaos!\nAt the prompt, please enter the numbers corresponding to the row and column on the board you want to pick.\nThen enter your choice of X or O.\nOrder wins if they are able to get 5 X's or 5 O's in a row on the board.\nChaos wins if the board fills up before then.\nGood luck! Have fun!\n");
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the team name of the team that would like to be Order. The remaining team will be Chaos.");
        String wantO = scan.nextLine();
        while (!wantO.equals(Order.getOfficialTitle()) && !wantO.equals(Chaos.getOfficialTitle())) {
            System.out.println("Sorry, that doesn't match any of the team names. Please try again.");
            wantO = scan.nextLine();
        }
        this.setOrder(wantO);

        int r, c;
        System.out.println("Here is the board: \n");
        System.out.println(this.b);
        // System.out.println("PLEASE");
        // int n = ttt.getn();
        String whowin = b.checkBoardWinOaC();
        while (whowin.equals("") && b.howmanyOpen() > 0) {
            mod++;
            Team up = turn[mod%2];
            Player pt = up.nextPlayer();
            System.out.println("Team " + up.getTeamName() + ", Player " + pt.getPID() + ". It is your turn. ");
            int[] pos = takeInput(b, scan);
            r = pos[0];
            c = pos[1];
            System.out.println("What you would like to fill in that cell with?");
            String f = scan.nextLine();
            while (!f.equals("X") && !f.equals("O") && !f.equals("x") && !f.equals("o")) {
                System.out.println("Please enter your choice of X or O: ");
                f = scan.nextLine();
            }
            System.out.println();
            this.b.setBoardPos(f.toUpperCase(), r, c);
            System.out.println("Here is the current board: \n");
            System.out.println(this.b);
            whowin = b.checkBoardWinOaC();
        }
        gameOver = true;
        if (b.howmanyOpen() == 0) {
            Chaos.setWin();
            System.out.println("CHAOS WINS!\n");
            //System.out.println("The score is now " + Chaos.getOfficialTitle() + ":" + Integer.toString(Chaos.getsetTeamScore()) + " " + Order.getOfficialTitle() + ":" + Integer.toString(Order.getsetTeamScore()) + "\n");

        }
        else {
            Order.setWin();
            System.out.println("ORDER WINS!\n");
            //System.out.println("The score is now " + Order.getOfficialTitle() + ":" + Integer.toString(Order.getsetTeamScore()) + " " + Chaos.getOfficialTitle() + ":" + Integer.toString(Chaos.getsetTeamScore()) + "\n");

        }
    }

    public int[] takeInput(Board b, Scanner scan) {
        // Scanner scan = new Scanner(System.in);
        int[] ret = new int[2];
        int n = b.getn();

        System.out.println("Please enter your choice of row: ");
        ret[0] = scan.nextInt();
        while (ret[0] < 0 || ret[0] >= n) {
            System.out.println("Sorry, that is not a valid position. Please choose a valid row from 0 through " + Integer.toString(n-1));
            ret[0] = scan.nextInt();
        }
        System.out.println("Please enter your choice of column: ");
        ret[1] = scan.nextInt();
        while (ret[1] < 0 || ret[1] >= n) {
            System.out.println("Sorry, that is not a valid position. Please choose a valid column from 0 through " + Integer.toString(n-1));
            ret[1] = scan.nextInt();
        }
        while (!b.checkOpen(ret[0],ret[1])) {
            System.out.println("Sorry, that spot is taken. Try another position: ");
            ret = takeInput(b, scan);
        }
        // scan.close();
        return ret;
    }

    public void resetBoard(int n){
        b = new Board(n);
    }

    public void replay(){
        resetBoard(6);
        // System.out.println(ttt);
        mod = -1;
        gameOver = false;
        play();
    }

    public String getReplayChoice() {
        return playagain;
    }
    public void setReplayChoice(String choice) {
        playagain = choice;
    }


    public static void main(String[] args) {
        // System.out.println(Integer.toString(-1%3));
        Player[] tx = new Player[2];
        tx[0] = new Player("x0", "J", "L");
        Player x1 = new Player("x1", "J", "c");
        tx[1] = x1;
        Team X1 = new Team("TX", tx);

        Player[] to = new Player[3];
        to[0] = new Player("o0", "Jo", "Lo");
        Player o1 = new Player("o1", "Jo", "co");
        to[1] = o1;
        Player o2 = new Player("o2", "Co", "co");
        to[2] = o2;
        Team O1 = new Team("TO", to);

        OaC game = new OaC(X1, O1);
        game.play();

        // game.replay();
    }
}