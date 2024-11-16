package com.hms.items;

import com.hms.items.Appointment.Status;
import com.hms.users.User.BloodType;
import com.hms.users.User.Gender;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class MedicalRecord {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String contactNumber;
    private String emailAddress;
    private BloodType bloodType;
    private ArrayList<Integer> appointmentIDs;

    public void setContactNumber(String cn) {
        contactNumber = cn;
    }

    public void setEmailAddress(String ea) {
        emailAddress = ea;
    }

    public MedicalRecord() {

    }

    public MedicalRecord(int ID, String n, String DOB, Gender g, BloodType bt) {
        id = ID;
        name = n;
        dateOfBirth = LocalDate.parse(DOB);
        gender = g;
        bloodType = bt;
    }

    public MedicalRecord(int ID, String n, String DOB, String g, String bt, String contact, String email, ArrayList<Integer> appt){
        id = ID;
        name = n;
        dateOfBirth = LocalDate.parse(DOB);
        gender = Gender.valueOf(g);
        bloodType = BloodType.valueOf(bt);
        contactNumber = contact;
        emailAddress = email;
        appointmentIDs = appt;
    }

    public void setAppointmentIDs(ArrayList<Integer> aid) {
        appointmentIDs = aid;
    }

    public void addAppointmentID(int aid) {
        appointmentIDs.add(aid);
    }

    public void printMedicalRecord(AppointmentManager aptm) {

        //print top part of medical record

        ArrayList<Appointment> apts = aptm.getPatientAppts(id, Status.Completed);
        Iterator<Appointment> i = apts.iterator();
        Appointment a;

        while(i.hasNext()) {
            a = i.next();
            System.out.println("Date of Diagnosis: " + a.getDate().toString());
            System.out.println("Diagnosis        : " + a.getDiagnosis());
            System.out.println("Treatment        :" + a.getTreatment());
            System.out.println("-------------------------------------------------");
        }
    }
}
