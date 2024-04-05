import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DemoPizza {
    private static final String QUIT = "X";

    public static void main(String[] args) {
        List<String> toppingsList = new ArrayList<>();
        String input;
        int numToppings = 0;

        // Prompt user for toppings
        while (numToppings < 10) {
            input = JOptionPane.showInputDialog("Enter a topping (or X to finish):");
            if (input == null || input.equalsIgnoreCase(QUIT)) {
                break;
            }
            toppingsList.add(input);
            numToppings++;
        }

        // Convert list to array
        String[] toppings = new String[toppingsList.size()];
        toppings = toppingsList.toArray(toppings);

        // Ask if delivery
        int option = JOptionPane.showConfirmDialog(null, "Would you like your pizza delivered?", "Delivery Option", JOptionPane.YES_NO_OPTION);
        boolean isDelivery = (option == JOptionPane.YES_OPTION);

        if (isDelivery) {
            String deliveryAddress = JOptionPane.showInputDialog("Enter delivery address:");
            DeliveryPizza deliveryPizza = new DeliveryPizza(toppings, deliveryAddress, numToppings);
            JOptionPane.showMessageDialog(null, deliveryPizza.toString(), "Pizza Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Pizza pizza = new Pizza(toppings, numToppings);
            JOptionPane.showMessageDialog(null, pizza.toString(), "Pizza Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
