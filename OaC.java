import java.util.Scanner;

public class OaC extends Board_Games_2P {

    private Team Order;
    private Team Chaos;
    
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
        return ret;
    }

    public void resetBoard(int n){
        b = new Board(n);
    }

    public void replay(){
        resetBoard(6);
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
}