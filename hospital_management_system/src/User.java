import java.io.Serializable;

public class User implements Serializable {
    public enum BloodType {
        OPLUS("O+"),
        OMINUS("O-"),
        APLUS("A+"),
        AMINUS("A-");
    };
    public enum Gender {
        FEMALE,
        MALE;
    };

    private String name;
    private String dateOfBirth;
    private Gender gender;
    private BloodType bloodType;
    private User role;
    private String userName;
    private String password;

    public User(String name, String dateOfBirth, Gender gender, BloodType bloodType, User role) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Gender getGender() {
        return this.gender;
    }

    public BloodType getBloodType() {
        return this.bloodType;
    }

    // consider implementing in an interface instead?
    public void viewAppointmentRecords(int id) {

    }
}
