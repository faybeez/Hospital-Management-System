package com.hms.users;

import com.hms.items.AppointmentManager;
import com.hms.items.Prescription;
import com.hms.enums.*;
import com.hms.items.Appointment;
import com.hms.items.Inventory;

import java.util.Scanner;


import java.util.ArrayList;

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
