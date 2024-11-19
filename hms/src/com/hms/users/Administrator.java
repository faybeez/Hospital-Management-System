package com.hms.users;

import com.hms.enums.*;

/** 
 * Administrator.java
 * 
 * User subclass for the administrator role
 */
public class Administrator extends User{
    private static final int idPrefix = 1000000000;
    
    public Administrator(String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(name, dateOfBirth, gender, bloodType, userName, password);
        int i = idPrefix + userCount;
        super.setID(i);
    }

    public Administrator(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }

}
