import java.util.Scanner;
public class PlayerID {
    private String pID;
    private String first;
    // private String MI;
    private String last;

    public PlayerID() {
        this.pID = "123456789";
        this.first = "";
        // this.MI = "";
        this.last = "";
    }
    
    public PlayerID(String pid, String firstname, String lastname) {
        this.pID = pid;
        this.first = firstname;
        // this.MI = "";
        this.last = lastname;
    }

    public PlayerID(String pid) {
        this.pID = pid;
        this.first = "";
        // this.MI = "";
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

    public static void main(String[] args) {
        Scanner scanw = new Scanner(System.in);
        System.out.println("What would you like your player id to be?");
        String id = scanw.nextLine();
        System.out.println("What is your first name?");
        String fn = scanw.nextLine();
        System.out.println("What is your last name?");
        String ln = scanw.nextLine();
        PlayerID me = new PlayerID(id,fn,ln);
        System.out.println("Your player profile is as follows: ");
        System.out.println(me);
        scanw.close();
    }

}