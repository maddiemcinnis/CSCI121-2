import java.util.Scanner;
import java.lang.Math;
public class BadSubscriptCaught {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter a value for us to calculate the square root.");
            String input = sc.nextLine();

        try {
            double number = Double.parseDouble(input);
            double squareRoot = Math.sqrt(number);
            System.out.println("The √" + number + " = " + squareRoot);
            break;
        } catch (NumberFormatException e) {
                System.out.println("Please only input a number.");
            }
            }
        }
    }

