package com.hms.users;
public class Patient extends User {

    public Patient(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }
    
    public void viewAppointments() {

    }
    public void makeAppointmentRequest(int doctorID){

    }
}
