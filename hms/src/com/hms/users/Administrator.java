package com.hms.users;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

import com.hms.items.AppointmentManager;
import com.hms.items.Inventory;
import com.hms.enums.*;
import com.hms.items.Appointment;

import java.util.List;

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
