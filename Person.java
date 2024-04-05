public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

//public static void main(String[] args) {
//Person p1 = new Person();
//p1.setFirstName("Cube");
//p1.setLastName("Cat");
//
//System.out.println("First name: " + p1.getFirstName());
//System.out.println("Last name: " + p1.getLastName());
