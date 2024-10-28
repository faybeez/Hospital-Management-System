package com.hms.users;
public class Pharmacist extends User {
    public int pharmacistNum = 0;
    public Pharmacist(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }
}
