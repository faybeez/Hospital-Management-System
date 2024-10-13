public class Person {
    public enum BloodType {
        OPLUS("O+"),
        OMINUS("O-"),
        APLUS("A+"),
        AMINUS("A-");
    };

    private String name;
    private String dateOfBirth;
    private String gender;
    private BloodType bloodType;

    // consider implementing in an interface instead?
    public void viewAppointmentRecords(int id) {

    }
}
