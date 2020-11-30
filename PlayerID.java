public class PlayerID {
    private String pID;
    private String first;
    private String last;

    public PlayerID() {
        this.pID = "123456789";
        this.first = "";
        this.last = "";
    }
    
    public PlayerID(String pid, String firstname, String lastname) {
        this.pID = pid;
        this.first = firstname;
        this.last = lastname;
    }

    public PlayerID(String pid) {
        this.pID = pid;
        this.first = "";
        this.last = "";
    }

    public String getPID() {
        return this.pID;
    }

    public String getPName() {
        if (this.last != "") {return this.first + " " + this.last;}
        else {return this.first;}
    }

    public String toString() {
        return "Player ID: " + this.getPID() + ", Name: " + this.getPName();
    }

}