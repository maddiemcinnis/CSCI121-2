import java.util.Scanner;
public class Patient {
    private String name; // user info
    private String address; // user info
    private byte age; // user info
    private int idNumber; // user info
    private int phoneNumber; // user info
    private BloodData bloodData; // user info

    public Patient() { // provide default constructors that set id & age to 0, and blood data to the default O+
        this.age = 0;
        this.idNumber = 0;
        this.bloodData = new BloodData();
    }
    public Patient(String name, String address, byte age, int idNumber, int phoneNumber, BloodData bloodData) { // create overload constructor
        this.name = name;
        this.address = address;
        this.age = age;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.bloodData = bloodData;
    }

    // provide get methods for each field
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public byte getAge() {
        return age;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public BloodData getBloodData() {
        return bloodData;
    }
}

