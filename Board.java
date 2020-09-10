import java.util.Arrays;

public class Board {
    private Tile[][] gameBoard;
    // private boolean[] available;
    private int n;
    private int open;

    public Board(int n) {
        /** 
        hahagameBoard = new String[][] {
            { "   ", "|", "   ", "|", "   " },
            { "---", "+", "---", "+", "---" },
            { "   ", "|", "   ", "|", "   " },
            { "---", "+", "---", "+", "---" },
            { "   ", "|", "   ", "|", "   " }
        }; */
        gameBoard = new Tile[n][n];
        this.n = n;
        open = n*n;
        // available = new boolean[open]; //default: false
        // Arrays.fill(available, Boolean.TRUE);
        // Arrays.fill(gameBoard, new Tile());
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                Tile t = new Tile();
                gameBoard[r][c] = t;
            }
        }
    }

    public void setBoardPos(String piece, int r, int c) {
        // r = r--;
        // c = c--;
        gameBoard[r][c].setTile(piece);
        open--;
    }

    public boolean checkOpen(int r, int c) {
        // r = r--;
        // c = c--;
        return gameBoard[r][c].isEmpty();
    }

    // returns the amount of open tiles left on the board
    public int howmanyOpen() {
        return open;
    }

    public int getn() {
        return n;
    }

    public String checkBoardWinTTT(Team[] teams) { // returns String of winning Team "X" or "O" for TTT or returns empty str "" for no winner
        String winner = "";
        winner = this.checkRowTTT(teams);
        if (!winner.equals("")) return winner;
        winner = this.checkColTTT(teams);
        if (!winner.equals("")) return winner;
        winner = this.checkXTTT(teams);
        return winner;
    }

    private String checkRowTTT(Team[] teams) {
        String winner = "";
        int[] count = new int[teams.length];
        // boolean win = false;
        for (Tile[] row: gameBoard) {
            count = new int[teams.length];
            for (Tile t: row) {
                if (t.isEmpty()) continue;
                else {
                    for (int i = 0; i < teams.length; i++) {
                        if (t.getOwner().equals(teams[i].getTeamName())) count[i]++;
                    }
                }
            }
            for (int i = 0; i < teams.length; i++) {
                if (count[i] == this.getn()) {
                    winner = teams[i].getTeamName();
                }
            }
            if (!winner.equals("")) break;
        }
        // System.out.println(winner);
        return winner;
    }
    private String checkColTTT(Team[] teams) {
        String winner = "";
        // boolean win = true;
        int[] count = new int[teams.length];
        for (int c = 0; c < n; c++) {
            count = new int[teams.length];
            for (int r = 0; r < n; r++) {
                Tile tc = gameBoard[r][c];
                if (tc.isEmpty()) continue;
                else {
                    for (int i = 0; i < teams.length; i++) {
                        if (this.gameBoard[r][c].getOwner().equals(teams[i].getTeamName())) count[i]++;
                    }
                }
            }
            for (int i = 0; i < teams.length; i++) {
                if (count[i] == this.getn()) {
                    winner = teams[i].getTeamName();
                }
            }
            if (!winner.equals("")) break;
        }
        // System.out.println(winner);
        return winner;
    }
    private String checkXTTT(Team[] teams) {
        String winner = "";
        // boolean win = true;
        int[] count = new int[teams.length];
        for (int c = 0; c < n; c++) {
            Tile tx = gameBoard[c][c];
            if (tx.isEmpty()) break;
            else {
                for (int i = 0; i < teams.length; i++) {
                    if (tx.getOwner().equals(teams[i].getTeamName())) count[i]++;
                }
            }
        }
        for (int i = 0; i < teams.length; i++) {
            if (count[i] == this.getn()) {
                winner = teams[i].getTeamName();
            }
        }
        if (!winner.equals("")) return winner;

        count = new int[teams.length];
        for (int c = 0; c < n; c++) {
            Tile tx = gameBoard[c][this.n-1-c];
            if (tx.isEmpty()) break;
            else {
                for (int i = 0; i < teams.length; i++) {
                    if (tx.getOwner().equals(teams[i].getTeamName())) count[i]++;
                }
            }
        }
        for (int i = 0; i < teams.length; i++) {
            if (count[i] == this.getn()) {
                winner = teams[i].getTeamName();
            }
        }
        // System.out.println(winner);
        return winner;
    }


    public String checkBoardWinOaC() {
        String winner = "";
        winner = this.checkROaC();
        if (!winner.equals("")) return winner;
        winner = this.checkCOaC();
        if (!winner.equals("")) return winner;
        winner = this.checkXOaC();
        return winner;
    }

    private String checkROaC() {
        String winner = "";
        int[] count = new int[2];
        for (Tile[] row: gameBoard) {
            count = new int[2];
            // System.out.println(count.toString());
            for (Tile t: row) {
                if (t.getOwner().equals("X")) count[0]++;
                if (t.getOwner().equals("O")) count[1]++;                    

            }
            for (int c: count) {
                if (c == 5 && !(row[0].equals(row[5]))) {
                    winner = "Order";
                }
            }
            if (!winner.equals("")) break;
        }
        // System.out.println(winner);
        return winner;
    }
    private String checkCOaC() {
        String winner = "";
        int[] count = new int[2];
        for (int c = 0; c < n; c++) {
            count = new int[2];
            for (int r = 0; r < n; r++) {
                Tile tc = gameBoard[r][c];
                if (tc.getOwner().equals("X")) count[0]++;
                if (tc.getOwner().equals("O")) count[1]++;   
            }
            for (int i: count) {
                if (i == 5 && !(gameBoard[0][c].equals(gameBoard[5][c]))) {
                    winner = "Order";
                }
            }
            if (!winner.equals("")) break;
        }
        // System.out.println(winner);
        return winner;
    }
    private String checkXOaC() {
        String winner = "";

        // \
        int[] count = new int[2];
        for (int c = 0; c < 6; c++) {
            Tile tx = gameBoard[c][c];
            if (tx.getOwner().equals("X")) count[0]++;
            if (tx.getOwner().equals("O")) count[1]++;   
        }
        for (int i: count) {
            if (i == 5 && !(gameBoard[0][0].equals(gameBoard[5][5])) ) {
                winner = "Order";
            }
        }
        if (!winner.equals("")) return winner;

        // /
        count = new int[2];
        for (int c = 0; c < n; c++) {
            Tile tx = gameBoard[c][this.n-1-c];
                if (tx.getOwner().equals("X")) count[0]++;
                if (tx.getOwner().equals("O")) count[1]++;   
        }
        for (int i: count) {
            if (i == 5 && !(gameBoard[0][5].equals(gameBoard[5][0])) ) {
                winner = "Order";
            }
        }
        if (!winner.equals("")) return winner;

        // the 2 weird 5 length diagonals \
        count = new int[4];
        for (int c = 0; c < n-1; c++) {
            Tile tx1 = gameBoard[c+1][c];
            Tile tx2 = gameBoard[c][c+1];
            if (tx1.getOwner().equals("X")) count[0]++;
            if (tx1.getOwner().equals("O")) count[1]++;   
            if (tx2.getOwner().equals("X")) count[2]++;
            if (tx2.getOwner().equals("O")) count[3]++;
        }
        for (int i: count) {
            if (i == 5) {
                winner = "Order";
            }
        }
        if (!winner.equals("")) return winner;

        // the 2 weird 5 length diagonals /
        count = new int[4];
        for (int c = 0; c < n-1; c++) {
            Tile tx1 = gameBoard[c][this.n-2-c];
            Tile tx2 = gameBoard[c+1][this.n-1-c];
            if (tx1.getOwner().equals("X")) count[0]++;
            if (tx1.getOwner().equals("O")) count[1]++;   
            if (tx2.getOwner().equals("X")) count[2]++;
            if (tx2.getOwner().equals("O")) count[3]++;
        }
        for (int i: count) {
            if (i == 5) {
                winner = "Order";
            }
        }

        return winner;
    }


    public String toString() {
        String bd = "";
        String midrows = "   +";
        for (int i = 0; i < n; i++) {
            midrows = midrows + "---+";
        }
        for (int r = -1; r < n; r++) {
            if (r == -1) {bd = bd + "    ";}
            if (r>=0) {bd = bd + Integer.toString(r) + "  |";}
            for (int c = 0; c < n; c++) {
                if (r == -1) {
                    bd = bd + " " + Integer.toString(c) + "  ";
                }
                else {
                    String f = this.gameBoard[r][c].toString();
                    if (f.equals("")) f = " ";
                    bd = bd + " " + f + " |";
                }
                if (c == n-1) {bd = bd + "\n";}
            }
            bd = bd + midrows + "\n";
        }
        return bd;
    }

    public void printBoard(String[][] board) {
        for (String[] row : board) {
            String str_row = "";
            for (String spot : row) {
                str_row += spot;
            }
            System.out.println(str_row);
        }
        System.out.println();
    }

    /** public void resetBoard() {
        this.gameBoard = new Board();
    } */

    public static void main(String[] args) {
        Board hello = new Board(3);

        System.out.println(hello);
    }
}