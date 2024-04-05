import java.util.Scanner;

public class enumGame {
    public enum Game {
        ROCK, PAPER, SCISSORS;
    }
    public static void main(String[] args) {
        System.out.println("Player 1: Rock, Paper, Scissors?");
            Scanner sc1 = new Scanner(System.in);
            String move1 = sc1.nextLine().toUpperCase();
            Game playerMove1 = Game.valueOf(move1);
            System.out.println("Player 1 played: " + playerMove1);

            System.out.println("Player 2: Rock, Paper, Scissors?");
            Scanner sc2 = new Scanner(System.in);
            String move2 = sc2.nextLine().toUpperCase();
            Game playerMove2 = Game.valueOf(move2);
            System.out.println("Player 2 played: " + playerMove2);

        if (playerMove1 == playerMove2) {
            System.out.println("It's a tie!");
        }
        else if (playerMove1 == Game.SCISSORS && playerMove2 == Game.PAPER
        || playerMove1 == Game.ROCK && playerMove2 == Game.SCISSORS || playerMove1 == Game.PAPER && playerMove2 == Game.ROCK) {
            System.out.println("Player 1 wins!");
        }
        else {
            System.out.println("Player 2 wins!");
        }
        }
    }

