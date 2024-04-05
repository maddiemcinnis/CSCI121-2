import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PizzaMaker {
    private static JFrame frame;
    private static JPanel welcomePanel;
    private static JPanel sizePanel; // please choose a pizza size
    private static JPanel allergyPanel; // asks customer if they want allergy alternative ingredients (ie. gluten-free crust, no cheese, dairy free cheese, etc.)
    private static JPanel defaultPanel; // pizza has default toppings (sauce & cheese); this panel provides user with option to not have these toppings
    private static JPanel toppingPanel; // has customer choose 1 main/free topping (all pizzas come with cheese unless provided notice) like pepperoni, extra cheese, tomatoes, etc..
    private static JPanel addToppingPanel; // asks customer to choose up to 3 toppings from list; $0.50 each OR deal of $1.25 for 3 toppings
    private static JPanel totalOrderPanel; // allow user to put in quantity, "place order", and calculate the total cost
    private static double sizePrice = 0;
    private static double totalPrice = 0;

    public static void GUI() {
        frame = new JFrame("BigY's Pizza Online Order"); // Use the class-level frame variable
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 300);
        frame.pack();

        // welcome panel
        welcomePanel = new JPanel();
        frame.add(welcomePanel);
        welcomePanel.setLayout(new FlowLayout());
        welcomePanel.setSize(500,300);
        welcomePanel.setVisible(true);

        JButton makeOrder = new JButton("Click to begin."); // button to begin
        makeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePanel.setVisible(false);
                sizePanel.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        });
        welcomePanel.setLayout(new GridLayout(2, 1));
        welcomePanel.add(new JLabel("Welcome to BigY Pizza! Are you ready to make your order?"));
        welcomePanel.add(makeOrder);

        // size panel
        sizePanel = new JPanel();
        frame.add(sizePanel);
        sizePanel.setLayout(new FlowLayout());
        sizePanel.setSize(500,300);
        sizePanel.setVisible(true);

        JRadioButton smallPizza = new JRadioButton("Small ($7)");
        JRadioButton medium = new JRadioButton("Medium ($10)");
        JRadioButton large = new JRadioButton("Large ($12)");
        JRadioButton extraLarge = new JRadioButton("Extra Large ($14)");

        ButtonGroup sizeOptions = new ButtonGroup(); // only select 1 of 4 options
        sizeOptions.add(smallPizza);
        sizeOptions.add(medium);
        sizeOptions.add(large);
        sizeOptions.add(extraLarge);

        JButton next1 = new JButton("Proceed to alert us of any allergies or accomodations we can make.");
        next1.setEnabled(false); // disable button until one of the sizes are selected

        smallPizza.addActionListener(new ActionListener() { // size price & enable button
            @Override
            public void actionPerformed(ActionEvent e) {
                if (smallPizza.isSelected()) {
                    sizePrice = 7;
                    next1.setEnabled(true);
                }
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (medium.isSelected()) {
                    sizePrice = 10;
                    next1.setEnabled(true);
                }
            }
        });
        large.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (large.isSelected()) {
                    sizePrice = 12;
                    next1.setEnabled(true);
                }
            }
        });
        extraLarge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (extraLarge.isSelected()) {
                    sizePrice = 14;
                    next1.setEnabled(true);
                }
            }
        });

        next1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizePanel.setVisible(false);
                allergyPanel.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        });

        sizePanel.setLayout(new GridLayout(6,1));
        sizePanel.add(new JLabel("Please select pizza size:"));
        sizePanel.add(smallPizza);
        sizePanel.add(medium);
        sizePanel.add(large);
        sizePanel.add(extraLarge);
        sizePanel.add(next1);

        // allergy
        allergyPanel = new JPanel(new GridLayout(5,1));
        frame.add(allergyPanel);
        allergyPanel.setLayout(new FlowLayout());
        allergyPanel.setSize(600,300);
        allergyPanel.setVisible(false);

        JRadioButton glutenFree = new JRadioButton("Gluten free crust ($4)");
        JRadioButton alternative = new JRadioButton("Vegan cheese alternative ($3)");

        JButton next2 = new JButton("Proceed to default pizza options.");

        JLabel total = new JLabel("Your total is: $ " + totalPrice); // add any of the selections to total price
        next2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double allergyPrice = 0;
                if (glutenFree.isSelected()) allergyPrice += 4;
                if (alternative.isSelected()) allergyPrice += 3;
                totalPrice = sizePrice + allergyPrice;
                total.setText("Your total is: $ " + totalPrice);
                allergyPanel.setVisible(false);
                defaultPanel.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        });

        allergyPanel.setLayout(new GridLayout(5,1));
        allergyPanel.add(new JLabel("Please select any relevant allergies/alternatives OR proceed if not applicable."));
        allergyPanel.add(glutenFree);
        allergyPanel.add(alternative);
        allergyPanel.add(new JLabel("[Note] Continue to get the option for no cheese."));
        allergyPanel.add(next2);

        // default panel
        defaultPanel = new JPanel(new GridLayout(5, 1));
        frame.add(defaultPanel);
        defaultPanel.setLayout(new FlowLayout());
        defaultPanel.setSize(600,300);
        defaultPanel.setVisible(false);

        JRadioButton extraCheese = new JRadioButton("Extra cheese");
        JRadioButton noCheese = new JRadioButton("No cheese/dairy");
        JRadioButton extraSauce = new JRadioButton("Extra sauce");
        JRadioButton noSauce = new JRadioButton("No sauce");

        ButtonGroup cheeseOptions = new ButtonGroup(); // only choose one cheese opt.
        cheeseOptions.add(extraCheese);
        cheeseOptions.add(noCheese);

        ButtonGroup sauceOptions = new ButtonGroup(); // only choose one sauce opt.
        sauceOptions.add(extraSauce);
        sauceOptions.add(noSauce);

        JButton next3 = new JButton("Proceed to free toppings.");
        next3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total.setText("Your total is: $ " + totalPrice); // keep updating total for later
                defaultPanel.setVisible(false);
                toppingPanel.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        });

        defaultPanel.setLayout(new GridLayout(8,1));
        defaultPanel.add(new JLabel("Change the default settings of your pizza OR proceed if not applicable."));
        defaultPanel.add(new JLabel(" Change sauce options?"));
        defaultPanel.add(noSauce);
        defaultPanel.add(extraSauce);
        defaultPanel.add(new JLabel("Change cheese options?"));
        defaultPanel.add(noCheese);
        defaultPanel.add(extraCheese);
        defaultPanel.add(next3);

        // topping panel
        toppingPanel = new JPanel();
        frame.add(toppingPanel);
        toppingPanel.setLayout(new FlowLayout());
        toppingPanel.setSize(600,300);
        toppingPanel.setVisible(false);

        JRadioButton pepperoni = new JRadioButton("Pepperoni");
        JRadioButton tomato = new JRadioButton("Tomato");
        JRadioButton mushroom = new JRadioButton("Mushroom");
        JRadioButton pineapple = new JRadioButton("Pineapple");
        JRadioButton sausage = new JRadioButton("Sausage");
        JRadioButton olive = new JRadioButton("Olive");
        JRadioButton cheese = new JRadioButton("None/Plain Cheese");

        ButtonGroup freeTopping = new ButtonGroup(); // can only choose 1 free topping
        freeTopping.add(pepperoni);
        freeTopping.add(tomato);
        freeTopping.add(mushroom);
        freeTopping.add(pineapple);
        freeTopping.add(sausage);
        freeTopping.add(olive);
        freeTopping.add(cheese);

        JButton next4 = new JButton("Proceed to additional toppings."); // mandatory; button is disabled until one is selected
        next4.setEnabled(false);

        pepperoni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pepperoni.isSelected()) {
                    next4.setEnabled(true);
                }
            }
        });
        tomato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tomato.isSelected()) {
                    next4.setEnabled(true);
                }
            }
        });
        mushroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mushroom.isSelected()) {
                    next4.setEnabled(true);
                }
            }
        });

        next4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total.setText("Your total is: $ " + totalPrice);
                toppingPanel.setVisible(false);
                addToppingPanel.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        });
        pineapple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pineapple.isSelected()) {
                    next4.setEnabled(true);
                }
            }
        });
        sausage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sausage.isSelected()) {
                    next4.setEnabled(true);
                }
            }
        });
        olive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (olive.isSelected()) {
                    next4.setEnabled(true);
                }
            }
        });
        cheese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cheese.isSelected()) {
                    next4.setEnabled(true);
                }
            }
        });

        toppingPanel.setLayout(new GridLayout(9,1));
        toppingPanel.add(new JLabel("Choose 1 free topping."));
        toppingPanel.add(pepperoni);
        toppingPanel.add(tomato);
        toppingPanel.add(mushroom);
        toppingPanel.add(pineapple);
        toppingPanel.add(sausage);
        toppingPanel.add(olive);
        toppingPanel.add(cheese);
        toppingPanel.add(next4);

//        // additional toppings
        addToppingPanel = new JPanel();
        frame.add(addToppingPanel);
        addToppingPanel.setLayout(new FlowLayout());
        addToppingPanel.setSize(900,300);
        addToppingPanel.setVisible(false);

        JCheckBox basil = new JCheckBox("Basil");
        JCheckBox meatball = new JCheckBox("Meatball");
        JCheckBox onion = new JCheckBox("Onions");
        JCheckBox chicken = new JCheckBox("Chicken");

        JCheckBox[] toppings = {basil, meatball, onion, chicken}; // create a "list" (?) for i to iterate through

        ActionListener toppingsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int toppingsChecked = 0; // add to this counter
                for (JCheckBox i : toppings) {
                    if (i.isSelected()) {
                        toppingsChecked++;
                    }
                }
                if (toppingsChecked > 3) { // if counter is greater than 3, show error message
                    JOptionPane.showMessageDialog(frame, "Please only select up to 3 additional toppings.", "Error", JOptionPane.ERROR_MESSAGE);
                    ((JCheckBox) e.getSource()).setSelected(false);
                   }
                    }
                };

        basil.addActionListener(toppingsListener);
        meatball.addActionListener(toppingsListener);
        onion.addActionListener(toppingsListener);
        chicken.addActionListener(toppingsListener);

        JButton next5 = new JButton("Proceed to check out.");
        next5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double additionalToppingsPrice = 0; // math for toppings
                int toppingsChecked = 0;
                for (JCheckBox i : toppings) {
                    if (i.isSelected()) {
                        toppingsChecked++;
                    }
                    if (toppingsChecked == 3) {
                        additionalToppingsPrice = 1.25;
                    } else {
                        additionalToppingsPrice = toppingsChecked * 0.50;
                    }
                }
//
                totalPrice += additionalToppingsPrice;
                total.setText("Your total is: $ " + totalPrice);
                addToppingPanel.setVisible(false);
                totalOrderPanel.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        });

        addToppingPanel.add(new JLabel("Choose up to 3 extra toppings for +$0.50 each or get a deal of +$1.25 for 3 OR proceed."), BorderLayout.NORTH);
        addToppingPanel.setLayout(new GridLayout(10,2));
        addToppingPanel.add(meatball);
        addToppingPanel.add(chicken);
        addToppingPanel.add(basil);
        addToppingPanel.add(onion);
        addToppingPanel.add(next5);

        // total order panel
        totalOrderPanel = new JPanel(new GridLayout(5,1));
        frame.add(totalOrderPanel);
        totalOrderPanel.setLayout(new FlowLayout());
        totalOrderPanel.setSize(600,300);
        totalOrderPanel.setVisible(false);

        JButton placeOrder = new JButton("Place Order");
        placeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderName = JOptionPane.showInputDialog(frame, "Please enter your name for your order", "Name for Order"); // have person input name
                if (orderName != null && !orderName.isEmpty()) { // if person cancels instead of submitting name, don't show following message
                    JOptionPane.showMessageDialog(frame, "Thank you for your order, " + orderName + "! Your order will be ready soon.");
                }
            }
        });
        JButton cancelOrder = new JButton("Cancel Order"); // if order cancels, go back to welcome panel
        cancelOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                welcomePanel.setVisible(true);
                sizePanel.setVisible(false);
                allergyPanel.setVisible(false);
                defaultPanel.setVisible(false);
                toppingPanel.setVisible(false);
                addToppingPanel.setVisible(false);
                totalOrderPanel.setVisible(false);
                frame.revalidate();
                frame.repaint();
                } else {
                }
            }
        });

        totalOrderPanel.add(total);
        totalOrderPanel.add(new JLabel("Ready to place your order?"));
        totalOrderPanel.add(placeOrder);
        totalOrderPanel.add(cancelOrder);

        frame.pack();
        frame.setVisible(true);
    }

    // call
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> GUI());
    }}

