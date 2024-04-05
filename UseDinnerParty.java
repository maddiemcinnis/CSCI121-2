//import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class UseDinnerParty {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Wedding Planning");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setSize(300, 300);

            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new FlowLayout());

            String guestsExpected = JOptionPane.showInputDialog(null, "Please enter the number of guests attending this dinner party.");
            int guestsInt = Integer.parseInt(guestsExpected);
            JOptionPane.showMessageDialog(frame, "You have successfully invited " + guestsInt + " people.");

            int dinnerChoiceInt = 4;
            while (dinnerChoiceInt < 1 || dinnerChoiceInt > 3) {
                String dinnerOption = JOptionPane.showInputDialog(frame, "Please enter 1, 2, or 3 to select a dinner option.\n" +
                        "Option #1: Seafood & Fish\n" +
                        "Option #2: Steak\n" +
                        "Option #3: Tofu & Veggies");
                dinnerChoiceInt = Integer.parseInt(dinnerOption);
                if (dinnerChoiceInt < 1 || dinnerChoiceInt > 3) {
                    JOptionPane.showMessageDialog(null, "Please input a valid option.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(frame, "You've selected Option #" + dinnerChoiceInt + ".");

            DinnerParty dp1 = new DinnerParty();
            dp1.setGuestsExpected(guestsInt);
            dp1.setDinnerChoice(dinnerChoiceInt);

            Font font = new Font("Serif", Font.BOLD, 16);
            JLabel invitation = new JLabel ("Please come to my party!");
            invitation.setFont(font);

            invitation.setPreferredSize(new Dimension(300,20));

            panel.add(invitation);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

//        Scanner sc = new Scanner(System.in);

//        Party p1 = new Party();
//        System.out.println("Please enter the number of guests for the party:");
//        int userInput = sc.nextInt();
//        p1.setGuestsExpected(userInput);
//        System.out.println("This party has " + p1.getGuestsExpected() + " guests.");
//        p1.displayInvitation();

//        DinnerParty dp1 = new DinnerParty();
//        System.out.println("Please enter the number of guests for the dinner party:");
//        int dinnerUserInput = sc.nextInt();
//        dp1.setGuestsExpected(dinnerUserInput);
//        System.out.println("Please select dinner choice: 1 for vegetarian option, 2 for meat option.");
//        int dinnerChoice = sc.nextInt();
//        dp1.setDinnerChoice(dinnerChoice);
//        System.out.println("Menu option #" + dinnerChoice + " will be served.");
//        dp1.displayInvitation();
//    }
//}
