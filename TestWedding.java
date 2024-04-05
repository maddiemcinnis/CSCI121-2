import java.util.Scanner;
import java.time.LocalDate;

public class TestWedding {
//import java.util.Scanner;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Groom's first name:");
        String groomFirstName = sc.nextLine();
        System.out.println("Groom's last name:");
        String groomLastName = sc.nextLine();

        System.out.println("Bride's first name:");
        String brideFirstName = sc.nextLine();
        System.out.println("Bride's last name:");
        String brideLastName = sc.nextLine();

        Person groom = new Person(groomFirstName, groomLastName);
        Person bride = new Person(brideFirstName, brideLastName);

        Couple couple = new Couple(groom, bride);

        System.out.println("Wedding location:");
        String location = sc.nextLine();

        System.out.println("Date:");
        LocalDate weddingDate = LocalDate.parse(sc.nextLine());

        Wedding wedding = new Wedding(weddingDate, couple, location);

        System.out.println("Details:");
        System.out.println(getWedding);
    }
}