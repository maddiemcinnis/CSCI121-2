import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalGuessingGameGUI extends JFrame {

    private TreeGame game;
    private JPanel panel;
    private JLabel questionLabel;
    private JButton yesButton;
    private JButton noButton;

    public AnimalGuessingGameGUI() {
        game = new TreeGame();
        game.load();

        setTitle("20 Questions Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 300);
        setLocationRelativeTo(null); // center  window

        startPanel(); // initialize start panel
    }

    private void startPanel() { // panel for everything
        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(new Dimension(500, 300));
        panel.setLayout(new BorderLayout());

        questionLabel = new JLabel(game.getCurrentQuestion());
        panel.add(questionLabel, BorderLayout.CENTER);
        questionLabel.setFont(new Font("Serif", Font.BOLD, 20));

        yesButton = new JButton("Yes"); // yes button when clicked will correlate with true and update question
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.answerQuestion(true);
                updateQuestion();
            }
        });
        panel.add(yesButton, BorderLayout.WEST); // add to panel

        noButton = new JButton("No"); // no button when clicked will correlate with false and update question
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.answerQuestion(false);
                updateQuestion();
            }
        });
        panel.add(noButton, BorderLayout.EAST); // add to panel
        add(panel);
        setVisible(true);
    }

    private void updateQuestion() {
        if (game.isFinished()) {
            String result; // know when game ended
            if (game.getGuessResult()) {
                result = "I knew it!"; // if got it, say "i knew it!"
            } else {
                result = "I give up... What were you thinking of?"; // asks user for answer
                String correctAnswer = JOptionPane.showInputDialog(this, result); // input dialogue for user to put their answer
                if (correctAnswer != null && !correctAnswer.isEmpty()) { // if user provides this info, ask for a defining question
                    String definingQuestion = JOptionPane.showInputDialog(this, "Please provide a yes/no question that distinguishes your answer");
                    if (definingQuestion != null && !definingQuestion.isEmpty()) { // if user provides this info, ask if it is y/n for their animal in mind
                        String answerToQuestion = JOptionPane.showInputDialog(this, "For " + correctAnswer + ", is the answer yes or no?");
                        if (answerToQuestion != null && !answerToQuestion.isEmpty()) {
                            game.updateTree(definingQuestion, answerToQuestion.equalsIgnoreCase("yes"), correctAnswer); // if user says yes, left node of defining question will become correct answer
                        }
                    }
                    int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION); // prompt the user to play again after
                    if (option == JOptionPane.YES_OPTION) {
                        game.load();
                        questionLabel.setText(game.getCurrentQuestion());
                        return; // Return to avoid further execution of the method
                    }
                }
            }
        }
        questionLabel.setText(game.getCurrentQuestion()); // update question label
    }

    public static void main(String[] args) {
       new AnimalGuessingGameGUI();
    }
}
