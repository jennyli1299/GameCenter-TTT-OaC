import java.util.Arrays;

public class Team {
    private String teamName;
    private String official;
    private Player[] team_players;
    // private boolean turn; //might not even need
    private int teamscore;
    private int size;
    private int moves;
    private Player activePlayer;

    public Team(String Tname, Player[] plist) {
        // turn = false;
        teamName = "";
        official = Tname;
        this.team_players = plist;
        size = team_players.length;
        teamscore = 0;
        moves = 0;
        activePlayer = team_players[moves%size];
    }

    public void setTeam(Player[] plist) {
        // turn = false;
        this.team_players = plist;
        size = team_players.length;
        teamscore = 0;
        moves = -1;
        activePlayer = team_players[moves%size];
    }

    public void setRole(String role) {
        this.teamName = role;
    }

    public Player nextPlayer() {
        activePlayer = team_players[moves%size];
        moves++;
        return activePlayer;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String getOfficialTitle() {
        return this.official;
    }

    public int getsetTeamScore() {
        int retscore = 0;
        for (Player p: this.team_players) {
            retscore += p.getScore();
        }
        this.teamscore = retscore;
        return teamscore;
    }
    public void setWin() {
        this.activePlayer.setWin();
    }
    public void resetScore() {
        for (Player p: this.team_players) {
            p.resetScore();
        }
        getsetTeamScore();
    }

    // public void setTurn() {
    //     this.turn = true;
    //     activePlayer.setTurn();
    // }
    // public void setTurnOver() {
    //     this.turn = false;
    //     activePlayer.setTurnOver();
    // }
}