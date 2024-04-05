import java.util.Scanner;

public class TestPatient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // patient 1 (default values)
        Patient patient1 = new Patient();
        System.out.println("\nPatient 1 (DEFAULT) Info:");
        System.out.println("Name: " + patient1.getName()); // null
        System.out.println("Address: " + patient1.getAddress()); // null
        System.out.println("Age: " + patient1.getAge()); // default 0
        System.out.println("ID Number: " + patient1.getIdNumber()); // default 0
        System.out.println("Phone Number: " + null); // null
        System.out.println("Blood Type: " + patient1.getBloodData().getBloodType()); // default O_POSITIVE
        System.out.println("Rh Factor: " + patient1.getBloodData().getRhFactor()); // default +

        // patient 2  (user input values)
        System.out.println("\nPatient 2, enter your info:"); // prompt user for info
        System.out.println("Name: ");
        String name2 = sc.nextLine();
        System.out.println("Address: ");
        String address2 = sc.nextLine();
        System.out.println("Age: ");
        byte age2 = Byte.parseByte(sc.nextLine());
        System.out.println("ID number: ");
        int idNumber2 = Integer.parseInt(sc.nextLine());
        System.out.println("Phone number: ");
        int phoneNumber2 = Integer.parseInt(sc.nextLine());
        System.out.println("Blood type: ");
        String bloodType2 = sc.nextLine();
        System.out.println("Rh factor: ");
        String rhFactor2 = sc.nextLine();

        Patient patient2 = new Patient(name2, address2, age2, idNumber2, phoneNumber2, new BloodData(BloodType.fromString(bloodType2), rhFactor2)); // parameters
        System.out.println("\nPatient 2 UPDATED INFO:"); // print updated info as entered
        System.out.println("Name: " + patient2.getName());
        System.out.println("Address: " + patient2.getAddress());
        System.out.println("Age: " + patient2.getAge());
        System.out.println("ID Number: " + patient2.getIdNumber());
        System.out.println("Phone Number: " + patient2.getPhoneNumber());
        System.out.println("Blood Type: " + patient2.getBloodData().getBloodType());
        System.out.println("Rh Factor: " + patient2.getBloodData().getRhFactor());

        // patient 3  (user input values but default BloodData)
        System.out.println("\nPatient 3, enter your info:"); // prompt user for info
        System.out.println("Name: ");
        String name3 = sc.nextLine();
        System.out.println("Address: ");
        String address3 = sc.nextLine();
        System.out.println("Age: ");
        byte age3 = Byte.parseByte(sc.nextLine());
        System.out.println("ID number: ");
        int idNumber3 = Integer.parseInt(sc.nextLine());
        System.out.println("Phone number: ");
        int phoneNumber3 = Integer.parseInt(sc.nextLine());
        System.out.println("Blood type: ");
        String bloodType3 = sc.nextLine();
        System.out.println("Rh factor: ");
        String rhFactor3 = sc.nextLine();


        Patient patient3 = new Patient(name3, address3, age3, idNumber3, phoneNumber3, new BloodData());
        System.out.println("\nPatient 3's CANNOT UPDATE INFO:"); // don't allow user's updated input to change patient 3; use default info
        System.out.println("Name: " + patient1.getName()); // null
        System.out.println("Address: " + patient1.getAddress()); // null
        System.out.println("Age: " + patient1.getAge()); // 0
        System.out.println("ID Number: "  + patient1.getIdNumber()); // 0
        System.out.println("Phone Number: " + null); // null
        System.out.println("Blood Type: " + patient3.getBloodData().getBloodType()); // default O_POSITIVE
        System.out.println("Rh Factor: " + patient3.getBloodData().getRhFactor()); // default +
    }
}
