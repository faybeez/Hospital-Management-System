package com.hms.users;


import com.hms.enums.*;

/** 
 * Pharmacist.java
 * 
 * User subclass for the pharmacist role
 */
public class Pharmacist extends User {
    
    private static final int idPrefix = 1030000000;

    public Pharmacist(String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(name, dateOfBirth, gender, bloodType, userName, password);
        int i = idPrefix + userCount;
        super.setID(i);
    }

    public Pharmacist(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }

}
