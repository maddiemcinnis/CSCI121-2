import java.util.Scanner;

public class Money {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter money amount."); // allow user to input amount

//        boolean looping = true;
//        while(looping) {
//            if (sc.hasNextDouble())

        String money = sc.nextLine();

        // provides flexibility for user to input their money with or without a $

        if (money.contains("$")) {
            if (money.charAt(0) == '$') {
                money = money.substring(1); // update money to get rid of $
                System.out.println("You entered $" + money);
            } else {
                System.out.println("Invalid format. Try one of the following formats: XX.XX or $XX.XX"); // doesn't allow improper format ex. 4.$50 or letters
                return; // stop executing
            }
        //}
        } else {
            System.out.println("You entered $" + money);
            }

        // convert string into a double to do the conversions
        double amount;
        try {
            amount = Double.parseDouble(money);
        } catch (NumberFormatException e) {
            System.out.println("Something went wrong with the conversion. Try one of the following formats: XX.XX or $XX.XX");
            return; // stop executing
        }

        // multiply the amount by 100 to get total in cents as an integer
            int total = (int) Math.round(amount * 100);

        // begin conversions/calculations
            int quarters = total / 25; // number of quarters
            int caq = total % 25; // updated total, cents after quarters

            int dimes = caq / 10; // number of dimes
            int cad = caq % 10; // updated total, cents after dimes

            int nickles = cad / 5; // number of nickles
            int can = cad % 5; // updated total, cents after nickles

            int pennies = can; // number of pennies

        // print statements and conditions
            System.out.println(" ");
            System.out.println("Your conversion results are:");

            if (quarters != 0) { // if quarters doesn't equal 0, print one of the following
                if (quarters == 1) {
                    System.out.println(quarters + " quarter"); // if quarters is = 1, print singular
                } else {
                        System.out.println(quarters + " quarters"); // otherwise, print plural
                    }
                }
            if (dimes != 0) {
                if (dimes == 1) {
                    System.out.println(dimes + " dime"); // if dimes is = 1, print singular
                } else {
                        System.out.println(dimes + " dimes"); // otherwise, print plural
                    }
                }
            if (nickles != 0) {
                if (nickles == 1) {
                    System.out.println(nickles + " nickle"); // if nickles is = 1, print singular
                } else {
                    System.out.println(nickles + " nickles"); // otherwise, print plural
                }
            }
            if (pennies != 0) {
                if (pennies == 1) {
                    System.out.println(pennies + " penny"); // if pennies is = 1, print singular
                } else {
                    System.out.println(pennies + " pennies"); // otherwise, print plural
            }

            }

        }

    }

