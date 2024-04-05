import java.util.Scanner;
public class TestBloodData {
    private BloodType bloodType; // using enum for field for blood type
    private String rhFactor; // String field for rh factor

    public TestBloodData(BloodType bloodType, String rhFactor) { // constructor w parameters
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
    }

    public TestBloodData() { // default constructor values
        this.bloodType = BloodType.O_POSITIVE;
        this.rhFactor = "+";
    }

    public void displayData() {
        System.out.println("Blood type: " + bloodType);
        System.out.println("Rh factor: " + rhFactor);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your blood type: "); // prompt user for blood type
        BloodType bloodType = BloodType.fromString(sc.nextLine()); // make input compatible w enum

        System.out.println("Enter you Rh factor: "); // prompt user for rh factor
        String rhFactor = sc.nextLine();

        TestBloodData defaultData = new TestBloodData(); // show default blood type data
        System.out.println("\nDefault blood data:");
        defaultData.displayData();

            System.out.println("\nDEFAULT blood data has now been updated to USER'S blood data:"); // display the details for the object again to confirm the changes are made correctly
        TestBloodData userData = new TestBloodData(bloodType, rhFactor); // change the values in the default object to the user's input values
        userData.displayData();

    }
}



