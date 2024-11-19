package com.hms.items;

import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;
import com.hms.enums.BloodType;
import com.hms.enums.Gender;
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
        gender = Gender.getByValue(g);
        bloodType = BloodType.getByValue(bt);
        contactNumber = contact;
        emailAddress = email;
        appointmentIDs = appt;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public Gender getGender() {
        return this.gender;
    }

    public BloodType getBloodType() {
        return this.bloodType;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public ArrayList<Integer> getAppointmentIDs() {
        return appointmentIDs;
    }

    public void setAppointmentIDs(ArrayList<Integer> aid) {
        appointmentIDs = aid;
    }

    public void addAppointmentID(int aid) {
        appointmentIDs.add(aid);
    }

    public void printMedicalRecord(ItemsService itemsService) {

        //print top part of medical record
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth.toString());
        System.out.println("Gender: " + gender.toString());
        System.out.println("Blood Type: " + bloodType.toString());
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Contact Number: " + contactNumber);

        ArrayList<Appointment> apts = itemsService.getPatientAppts(id, AppointmentStatus.Completed);
        Iterator<Appointment> i = apts.iterator();
        Appointment a;
        System.out.println("------------------PAST DIAGNOSES------------------");
        while(i.hasNext()) {
            a = i.next();
            System.out.println("Date of Diagnosis: " + a.getDate().toString());
            System.out.println("Diagnosis        : " + a.getDiagnosis());
            System.out.println("Treatment        : " + a.getTreatment());
            System.out.println("--------------------------------------------------");
        }
    }
}
