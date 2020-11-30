public class Scoreboard {
    private Team[] sb;

    public Scoreboard(Team[] teamlist) {
        sb = teamlist;
    }

    public String toString() {
        String sbd = "Scoreboard: \n";
        for (Team t: sb) {
            sbd = sbd + t.getOfficialTitle() + ": " + Integer.toString(t.getsetTeamScore()) + " wins\n";
        }
        return sbd;
    }
}