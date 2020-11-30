public class Player {
    private PlayerID pID;
    private int score;
    private boolean turn;

    // CONSTRUCTOR(s)
    public Player(String pid, String fn, String ln) {
        this.pID = new PlayerID(pid,fn,ln);
        this.score = 0;
        this.turn = false;
    }

    // PlayerID and PlayerName [INSTANCE]
    public String getPID() {
        return this.pID.getPID();
    }
    public String getPName() {
        return this.pID.getPName();
    }

    // Accessor methods dealing with an Instance of a Player's SCORE
    public int getScore() {
        return score;
    }
    private void addScore() {
        score++;
    }
    public void setWin() {
        this.addScore();
    }
    public void resetScore() {
        this.score = 0;
    }

    // Accessor methods dealing with an Instance of a Player's TURN
    public void setTurn() {
        this.turn = true;
    }
    public void setTurnOver() {
        this.turn = false;
    }

    public String toString() {
        String ret = this.pID.toString() + " Score: " + Integer.toString(score);
        if (turn) { ret = ret + " It is your turn!"; }
        else { ret = ret + " It is NOT your turn!"; }
        return ret;
    }
}