import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pizza {
    private String[] toppings;
    protected double price;
    private String description;

    public Pizza(String[] toppings, int numberToppings) {
        this.toppings = new String[numberToppings];
        for (int i = 0; i < numberToppings; i++) {
            this.toppings[i] = toppings[i];
        }
        this.price = 14 + (2 * numberToppings);
        generateDescription(); // Generate the description
    }

    private void generateDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("Toppings: ");
        for (int i = 0; i < toppings.length; i++) {
            builder.append(toppings[i]);
            if (i < toppings.length - 1) {
                builder.append(", ");
            }
        }
        description = builder.toString();
    }

    public String toString() {
        return description + "\n" + "Price: $" + price;
    }
}
