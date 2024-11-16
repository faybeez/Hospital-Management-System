package com.hms.users;
public class Administrator extends User{
    public int administratorNum = 0;
      
    public Administrator(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }
}
