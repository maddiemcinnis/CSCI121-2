import java.util.Scanner;

enum BloodType { // create enum for all the blood types, including Rh factors
    A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE;

    public static BloodType fromString(String bloodType) { // allow to take string parameter and return corresponding enum constant
        switch (bloodType.toUpperCase()) { // use switch-case for enums
            case "A+":
                return A_POSITIVE;
            case "A-":
                return A_NEGATIVE;
            case "B+":
                return B_POSITIVE;
            case "B-":
                return B_NEGATIVE;
            case "AB+":
                return AB_POSITIVE;
            case "AB-":
                return AB_NEGATIVE;
            case "O+":
                return O_POSITIVE;
            case "O-":
                return O_NEGATIVE;
            default:
                throw new IllegalArgumentException("This is an invalid blood type: " + bloodType); // if doesn't match an enum constant, don't allow the invalid blood type
        }
    }
}

public class BloodData {
    private BloodType bloodType; // field for blood type
    private String rhFactor; // String field for Rh factor

    public BloodData(BloodType bloodType, String rhFactor) { // constructor w parameters
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
    }

    public BloodData() { // create default constructors to O and +
        this.bloodType = BloodType.O_POSITIVE;
        this.rhFactor = "+";
    }

    public BloodType getBloodType() { // getter method for blood type
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) { // setter method for blood type
        this.bloodType = bloodType;
    }

    public String getRhFactor() { // getter method for rh factor
        return rhFactor;
    }

    public void setRhFactor(String rhFactor) { // setter method for rh factor
        this.rhFactor = rhFactor;
    }
}
