import java.util.Scanner;
public class Values {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // create scanner
        double[] values = new double[20]; // create a double with 20 values

       for (int i = 0; i < 20; i++) { // iterate 20 times, updating the message each time
           System.out.println("Enter value #" + (i+1) + ":");
           String input = sc.nextLine(); // go to next line
           double value = Double.parseDouble(input);
           values[i] = value; // assign i element to value
       }

        System.out.println("Here's a list of the 20 values inputted: ");
        for (int i = 0; i < 20; i++) {
            System.out.println(values[i]);
        }
       }
    }