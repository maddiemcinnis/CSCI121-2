import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

public class ShoppingCart extends JFrame {
    private static JFrame frame;
    private static JPanel welcomePanel;
    private static JPanel orderItems;
    private static JLabel youSelected;
    private static JPanel finishOrder;
    private static JPanel shoppingCart;
    private static List<CartItem> selectedItems = new ArrayList<>(); // to keep track of items & quantities
    private static List<JComboBox<Integer>> quantityComboBoxes = new ArrayList<>(); // to keep track of quantities
    private static JTextField totalField = new JTextField();

    public static class CartItem { // for later to organize shopping cart
        private String itemName;
        private int quantity;
        public CartItem(String itemName, int quantity) {
            this.itemName = itemName;
            this.quantity = quantity;
        }
        public String getItemName() {
            return itemName;
        }
        public int getQuantity() {
            return quantity;
        }
    }

    public static class Items { // my items for the store
        JCheckBox clementines = new JCheckBox("Clementines (QTY 2 lbs), $5");
        JCheckBox mango = new JCheckBox("Mango, $2.50 each");
        JCheckBox ramen = new JCheckBox("Ramen, $0.99");
        JCheckBox fruitSnacks = new JCheckBox("Welch's Fruit Snacks (QTY 40), $8.99");
        JCheckBox sumo = new JCheckBox("Sumo Orange, $2.99");
        JCheckBox tea = new JCheckBox("Mao Jian Tea, (QTY 18), $14");
    }

    public static void GUI() {
        frame = new JFrame("Grocery Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 350);

        welcomePanel = new JPanel(); // welcome!
        welcomePanel.setBackground(Color.lightGray);
        welcomePanel.setPreferredSize(new Dimension(800, 50));
        frame.add(welcomePanel, BorderLayout.NORTH);
        JLabel welcomeMessage = new JLabel("Welcome to our grocery store!");
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 20));
        welcomePanel.add(welcomeMessage);
        welcomePanel.setVisible(true);

        orderItems = new JPanel(); // prompts user with list of options for them to select
        orderItems.setLayout(new GridLayout(0, 1));
        orderItems.setBackground(Color.white);
        orderItems.setPreferredSize(new Dimension(800, 300));
        frame.add(orderItems, BorderLayout.CENTER);
        JLabel info = new JLabel("Here's what we have in stock right now:");
        info.setFont(new Font("Serif", Font.PLAIN, 18));
        orderItems.add(info);
        JLabel itemLabel = new JLabel("(Click on any of the items you are looking to check out and then hit NEXT to continue with your order.)");
        itemLabel.setFont(new Font("Serif", Font.ITALIC, 16));
        orderItems.add(itemLabel);
        orderItems.setVisible(true);

        JButton next1 = new JButton("NEXT"); // next button to continue to next step and panel in program
        orderItems.add(next1);
        next1.setEnabled(false);
        ActionListener next1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(orderItems);
                frame.getContentPane().remove(welcomePanel);
                frame.getContentPane().add(finishOrder, BorderLayout.CENTER);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        };
        next1.addActionListener(next1Listener);

        Items items = new Items(); // make it so that the NEXT button is not enabled until an item is selected for order
        orderItems.add(items.clementines);
        items.clementines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleItemSelection(items.clementines, items.clementines.getText());
                next1.setEnabled(true);
            }
        });
        orderItems.add(items.mango);
        items.mango.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleItemSelection(items.mango, items.mango.getText());
                next1.setEnabled(true);
            }
        });
        orderItems.add(items.ramen);
        items.ramen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleItemSelection(items.ramen, items.ramen.getText());
                next1.setEnabled(true);
            }
        });
        orderItems.add(items.fruitSnacks);
        items.fruitSnacks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleItemSelection(items.fruitSnacks, items.fruitSnacks.getText());
                next1.setEnabled(true);
            }
        });
        orderItems.add(items.sumo);
        items.sumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleItemSelection(items.sumo, items.sumo.getText());
                next1.setEnabled(true);
            }
        });
        orderItems.add(items.tea);
        items.tea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleItemSelection(items.tea, items.tea.getText());
                next1.setEnabled(true);
            }
        });

        finishOrder = new JPanel(); // next panel; user is prompted to selected quantities for their items
        finishOrder.setLayout(new GridLayout(12, 1));
        finishOrder.setBackground(Color.white);
        finishOrder.setPreferredSize(new Dimension(800, 300));
        finishOrder.setVisible(true);

        youSelected = new JLabel("You selected the following items:"); // provides summary
        youSelected.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel youSelected2 = new JLabel("Please enter the quantities for each.");
        youSelected2.setFont(new Font("Serif", Font.ITALIC, 16));
        finishOrder.add(youSelected, BorderLayout.NORTH);
        finishOrder.add(youSelected2);

        shoppingCart = new JPanel();
        shoppingCart.setLayout(new BorderLayout());
        shoppingCart.setBackground(Color.white);
        shoppingCart.setPreferredSize(new Dimension(800, 300));
        shoppingCart.setVisible(false);

        JButton next2 = new JButton("NEXT"); // next button
        finishOrder.add(next2);
        next2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(finishOrder);
                frame.getContentPane().add(shoppingCart, BorderLayout.CENTER);
                displayCart();
                shoppingCart.setVisible(true);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        JButton goBack = new JButton("BACK"); // now implementing a back button for user to add or unselect items
        finishOrder.add(goBack);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(finishOrder);
                frame.getContentPane().add(orderItems, BorderLayout.CENTER);
                orderItems.setVisible(true);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        JLabel saveDeals = new JLabel("PLUS! CHECK OUT TODAY'S DEALS!!"); // adding in deals for the user to be aware about
        saveDeals.setFont(new Font("Serif", Font.BOLD, 16));
        finishOrder.add(saveDeals);
        JLabel deals = new JLabel("Buy 5+ mangoes, get each for $2; Buy 3+ ramen, save 24 cents each; Buy 3+ sumo oranges, get each for $2.75");
        deals.setFont(new Font("Serif", Font.ITALIC, 14));
        finishOrder.add(deals);

        frame.pack();
        frame.setVisible(true);
    }

    private static void handleItemSelection(JCheckBox checkBox, String itemName) {
        if (checkBox.isSelected()) {
            JComboBox<Integer> quantityComboBox = new JComboBox<>();
            for (int i = 1; i <= 10; i++) { // allow user to only select up to 10 of each item
                quantityComboBox.addItem(i);
            }
            quantityComboBoxes.add(quantityComboBox);

            JPanel itemPanel = new JPanel();
            itemPanel.add(new JLabel(itemName));
            itemPanel.add(quantityComboBox);

            finishOrder.add(itemPanel);
            finishOrder.revalidate();
            finishOrder.repaint();
        } else {
            for (int i = 0; i < quantityComboBoxes.size(); i++) {
                JComboBox<Integer> comboBox = quantityComboBoxes.get(i);
                if (((JLabel)((JPanel)comboBox.getParent()).getComponent(0)).getText().equals(itemName)) {
                    quantityComboBoxes.remove(i);
                    finishOrder.remove(i);
                    finishOrder.revalidate();
                    finishOrder.repaint();
                    break;
                }
            }
        }
    }

    private static void displayCart() {
        selectedItems.clear(); // clear previous selections
        for (int i = 0; i < quantityComboBoxes.size(); i++) {
            JComboBox<Integer> comboBox = quantityComboBoxes.get(i);
            String itemName = ((JLabel)((JPanel)comboBox.getParent()).getComponent(0)).getText();
            int quantity = (Integer)comboBox.getSelectedItem();
            selectedItems.add(new CartItem(itemName, quantity));
        }

        shoppingCart.removeAll();
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new GridLayout(8, 1));
        cartPanel.setBackground(Color.white);

        JLabel orderSummary = new JLabel("Here is the summary of your order: "); // provides summary of order
        orderSummary.setFont(new Font("Serif", Font.BOLD, 16));
        cartPanel.add(orderSummary);

        for (CartItem item : selectedItems) {
            JLabel cartItemLabel = new JLabel(item.getItemName() + " (x" + item.getQuantity() + ")"); // with the quantity chosen by user
            cartItemLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            cartPanel.add(cartItemLabel);
        }

        JButton goBack2 = new JButton("BACK"); // back button
            cartPanel.add(goBack2);
            goBack2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().remove(shoppingCart);
                    frame.getContentPane().add(finishOrder, BorderLayout.CENTER);
                    frame.getContentPane().revalidate();
                    frame.getContentPane().repaint();
                }
            });

        JButton next3 = new JButton("PLACE ORDER"); // place your order
        cartPanel.add(next3);
        next3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Would you like to place your online order?", "ORDER CONFIRMATION", JOptionPane.YES_NO_OPTION); // confirmation box
                if (option == JOptionPane.YES_OPTION) { // if yes, prompt user to enter their name
                    String orderName = JOptionPane.showInputDialog(frame, "Please enter your name for your order", "Name for Order"); // prompt user for name
                    if (!orderName.isEmpty()) { // as long as name box is not empty, propose a thank you message
                        JOptionPane.showMessageDialog(frame, "Thank you for your order, " + orderName + ".");
                    }
                }
            }
        });

        shoppingCart.add(cartPanel, BorderLayout.CENTER);
        updateTotal();
        shoppingCart.revalidate();
        shoppingCart.repaint();
    }
    private static void updateTotal() { // update total in proper format throughout program to give accurate and corresponding total
        double total = 0;
        for (int i = 0; i < selectedItems.size(); i++) {
            CartItem item = selectedItems.get(i);
            JComboBox<Integer> comboBox = quantityComboBoxes.get(i);
            int quantity = (int) comboBox.getSelectedItem();
            total += calculateItemTotal(item, quantity);
        }
        totalField.setText("Total: $" + String.format("%.2f", total));
        totalField.setFont(new Font("Serif", Font.BOLD, 20));
        shoppingCart.add(totalField, BorderLayout.SOUTH);
    }

    private static double calculateItemTotal(CartItem item, int quantity) { // cost for each item, including the deals
        if (item.getItemName().contains("Clementines")) {
            return quantity * 5; // Price of clementines
        } else if (item.getItemName().contains("Mango")) {
            if (quantity >= 5) {
                return 2.00 * quantity; // Buy 5+ mangoes, and get each for $2
            } else return quantity * 2.50; // Price of mango
        } else if (item.getItemName().contains("Ramen")) {
            if (quantity >= 3) {
                return 0.75 * quantity;
            } else return quantity * 0.99; // Price of ramen
        } else if (item.getItemName().contains("Fruit Snacks")) {
            return quantity * 8.99; // Price of fruit snacks
        } else if (item.getItemName().contains("Sumo Orange")) {
            if (quantity >= 3) {
                return 2.75 * quantity;
            } else return quantity * 2.99; // Price of sumo orange
        } else if (item.getItemName().contains("Mao Jian Tea")) {
            return quantity * 14; // Price of Mao Jian Tea
        }
        return 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> GUI());
    }
}
