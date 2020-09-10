import java.util.Scanner;

public class TTT extends Board_Games_2P {

    // private Board b;
    private Team X;
    private Team O;
    // private Team[] turn;
    // private int mod;
    // private boolean gameOver;
    // private String playagain;

    /** 
    public TTT(int boardsize, Player[] Xp, Player[] Op) {
        b = new Board(boardsize);
        X = new Team("X", Xp);
        O = new Team("O", Op);
        turn = new Team[2];
        turn[0] = X;
        turn[1] = O;
        mod = -1;
        gameOver = false;
        playagain = "Yes";
    } */

    public TTT(int boardsize, Team Xt, Team Ot) {
        b = new Board(boardsize);
        X = Xt;
        X.setRole("X");
        O = Ot;
        O.setRole("O");
        turn = new Team[2];
        turn[0] = X;
        turn[1] = O;
        mod = -1;
        gameOver = false;
        playagain = "Yes";
    }

    public void play() {
        System.out.println("\nHello! Welcome to TicTacToe!\nAt the prompt, please enter the numbers corresponding to the row and column on the board you want to pick.\nFirst team to get " + Integer.toString(b.getn()) + " in a row WINS! Have fun!\n");
        
        int r, c;
        System.out.println("Here is the board: \n");
        System.out.println(this.b);
        // System.out.println("PLEASE");
        // int n = b.getn();
        Scanner scan = new Scanner(System.in);
        String whowin = b.checkBoardWinTTT(turn);
        while (whowin.equals("") && b.howmanyOpen() > 0) {
            mod++;
            Team up = turn[mod%2];
            Player pt = up.nextPlayer();
            System.out.println("Team " + up.getOfficialTitle() + ", a.k.a. team " + up.getTeamName() + ", Player " + pt.getPID() + ". It is your turn. ");
            int[] pos = takeInput(b, scan);
            r = pos[0];
            c = pos[1];
            System.out.println();
            this.b.setBoardPos(up.getTeamName(), r, c);
            System.out.println("Here is the current board: \n");
            System.out.println(this.b);
            whowin = b.checkBoardWinTTT(turn);
        }
        gameOver = true;
        if (b.howmanyOpen() == 0) {
            System.out.println("It's a TIE!\n");
        }
        else {
            if (whowin.equals("O")) {
                O.setWin();
                System.out.println("CONGRATULATIONS TEAM O HAS WON!"); // The score is now " + O.getOfficialTitle() + ":" + Integer.toString(O.getsetTeamScore()) + " " + X.getOfficialTitle() + ":" + Integer.toString(X.getsetTeamScore()) + "\n");
            }
            else if (whowin.equals("X")) {
                X.setWin();
                System.out.println("CONGRATULATIONS TEAM X HAS WON!"); // The score is now " + X.getOfficialTitle() + ":" + Integer.toString(X.getsetTeamScore()) + " " + O.getOfficialTitle() + ":" + Integer.toString(O.getsetTeamScore()) + "\n");

            }
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
        Scanner snew = new Scanner(System.in);
        System.out.println("Let's Replay! What size nxn board would you like to play with?");
        int n = snew.nextInt();
        resetBoard(n);
        // System.out.println(b);
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

        TTT game = new TTT(4, X1, O1);
        game.play();

        game.replay();
    }

}