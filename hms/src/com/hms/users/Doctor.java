package com.hms.users;

import com.hms.users.User.BloodType;
import com.hms.users.User.Gender;

public class Doctor extends User{
    public Doctor(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }
}
