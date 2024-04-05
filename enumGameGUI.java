import javax.swing.*;

public class enumGameGUI {
    public enum Game {
        ROCK, PAPER, SCISSORS;
    }

    public void gameGUI() {
        JFrame frame = new JFrame("Rock, Paper, Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        String move1 = JOptionPane.showInputDialog(frame, "Player 1: Rock, Paper, Scissors?").toUpperCase();
        Game playerMove1 = Game.valueOf(move1);

        String move2 = JOptionPane.showInputDialog(frame, "Player 2: Rock, Paper, Scissors?").toUpperCase();
        Game playerMove2 = Game.valueOf(move2);

        JOptionPane.showMessageDialog(frame, "Player 1 played: " + playerMove1 + "\nPlayer 2 played: " + playerMove2);
//        JOptionPane.showMessageDialog(frame, "Player 2 played: " + playerMove2);

        if (playerMove1 == playerMove2) {
            JOptionPane.showMessageDialog(frame, "It's a tie!");
        } else if ((playerMove1 == Game.SCISSORS && playerMove2 == Game.PAPER) ||
                (playerMove1 == Game.ROCK && playerMove2 == Game.SCISSORS) ||
                (playerMove1 == Game.PAPER && playerMove2 == Game.ROCK)) {
            JOptionPane.showMessageDialog(frame, "Player 1 wins!");
        } else {
            JOptionPane.showMessageDialog(frame, "Player 2 wins!");
        }
    }

    public static void main(String[] args) {
        enumGameGUI game = new enumGameGUI();
        game.gameGUI();
    }
}
