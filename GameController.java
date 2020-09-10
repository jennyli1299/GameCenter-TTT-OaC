import java.util.Scanner;
import java.util.Arrays;

public class GameController {

    // Game game;

    public GameController() {}
    
    public static Team makeTeam(String t) {
        Scanner maket = new Scanner(System.in);
        boolean is_int = false;
        int x = 0; //teamcount
        System.out.println("Okay, now how many people are playing on Team " + t + "?"); 
        do {
            if (maket.hasNextInt()) {
                x = maket.nextInt();
                is_int = true;
            }
            else {
                System.out.println("Huh? That's not a number! Please enter how many people are playing on Team" + t + ": ");
                maket.next();
            }
        }
        while (!is_int);
        Player[] plist = new Player[x];
        for (int i = 0; i < x; i++) {
            System.out.print("Perfect. Please enter Player ID for Player " + Integer.toString(i) + " on TEAM " + t + ":\n");
            // maket.nextLine();
            String pid = maket.nextLine();
            System.out.print("Perfect. Please enter first name for Player " + Integer.toString(i) + " on TEAM " + t + ":\n");
            String fn = maket.nextLine();
            System.out.print("Perfect. Please enter last name for Player " + Integer.toString(i) + " on TEAM " + t + ":\n");
            String ln = maket.nextLine();
            plist[i] = new Player(pid, fn, ln);
        }
        Team finret = new Team(t, plist);
        System.out.println("Team Complete!");
        return finret;
    }
    public static void main(String[] args) {
        // GameController gc = new GameController();
        Scanner sgame = new Scanner(System.in);
        System.out.println("Hello there! Welcome to the Game Center!");
        System.out.println("Let's form our 2 teams!");
        System.out.print("Team 1. What would you like your team name to be?\n");
        String tname = sgame.nextLine();
        // System.out.println("Nice!");
        Team T1 = GameController.makeTeam(tname);
        // Team T1 = gc.makeTeam(tname);
        System.out.println("Team 2. What would you like your team name to be?");
        String t2name = sgame.nextLine();
        // System.out.println("Nice!");
        Team T2 = GameController.makeTeam(t2name);
        // Team T2 = gc.makeTeam(t2name);

        Team[] teamarray = new Team[2];
        teamarray[0] = T1;
        teamarray[1] = T2;
        Scoreboard sb = new Scoreboard(teamarray);

        // Board_Games_2P phantomgame;
        String replaychoice = "Yes";
        
        System.out.println("What game would you like to play?\nWe currently offer Tic Tac Toe and Order and Chaos.");
        String choice = sgame.nextLine();
        while (!choice.equals("Tic Tac Toe") && !choice.equals("Order and Chaos")) {
            System.out.println("Sorry, we currently don't offer this game. Please choose either Tic Tac Toe or Order and Chaos.");
            choice = sgame.nextLine();
        }
        if (choice.equals("Tic Tac Toe")) {
            System.out.println("Great choice! What size board would you like to play with?");
            int n = sgame.nextInt();
            TTT game = new TTT(n, T1, T2);
            game.play();
            System.out.println(sb);
        }
        else //if (choice.equals("Order and Chaos")) 
        {
            System.out.println("Fabulous choice! Here we go!");
            OaC game = new OaC(T1, T2);
            game.play();
            System.out.println(sb);
        }

        //REPLAY OPTION
        while (replaychoice.equals("Yes")) {
            Scanner scanw = new Scanner(System.in);
            System.out.println("Would you like to play again? Enter Y or Yes for another round of fun or enter anything else to quit.");
            String re = scanw.nextLine();
            if (re.equals("Y") || re.equals("Yes") || re.equals("y") || re.equals("yes") || re.equals("YES")) {
                System.out.println("Alright! Would you like to play Tic Tac Toe or Order and Chaos?");
                choice = scanw.nextLine();
                while (!choice.equals("Tic Tac Toe") && !choice.equals("Order and Chaos")) {
                    System.out.println("Sorry, we currently don't offer this game. Please choose either Tic Tac Toe or Order and Chaos.");
                    choice = sgame.nextLine();
                }
                if (choice.equals("Tic Tac Toe")) {
                    System.out.println("Great choice! What size board would you like to play with?");
                    int n = sgame.nextInt();
                    TTT game = new TTT(n, T1, T2);
                    game.play();
                    System.out.println(sb);
                }
                else //if (choice.equals("Order and Chaos")) 
                {
                    System.out.println("Fabulous choice! Here we go!");
                    OaC game = new OaC(T1, T2);
                    game.play();
                    System.out.println(sb);
                }
            }
            else {
                replaychoice = re;
                System.out.println("Thanks for playing! I hope you had fun! :)");
                System.out.println(sb);
            }
            // scanw.close();
        }
    }
}