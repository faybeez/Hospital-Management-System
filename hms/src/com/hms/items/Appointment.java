package com.hms.items;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;


public class Appointment implements Comparable<Appointment> {

    public static int AppointmentNumber = 0;
    public static final int AppointmentIdentifier = 2000000000;
    private int id;
    private AppointmentStatus status;
    private LocalDate date;
    private LocalTime time;
    private int patientID;
    private int doctorID;
    private String typeOfService;
    private String diagnosis;
    private String treatment;
    private String consultNotes;
    private Prescription prescription;

    public Appointment() {
        AppointmentNumber++;
    }

    public Appointment(int pID, int dID, LocalDate date, LocalTime time) {
        id = AppointmentIdentifier + ++AppointmentNumber;
        patientID = pID;
        doctorID = dID;
        this.date = date;
        this.time = time;
        status = AppointmentStatus.Pending;
        prescription = new Prescription();
    }

    @Override
    public int compareTo(Appointment o) {
        if(date.compareTo(o.getDate()) != 0) {
            return (date.compareTo(o.getDate()));
        }
        else {
            return (time.compareTo(o.getTime()));
        }
    }

    public String getTypeofService() {
        return typeOfService;
    }
    
    public String getConsultNotes() {
        return consultNotes;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public int getAppointmentID() {
        return id;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public AppointmentStatus getStatus() {
        return status;
    }
    
    public int getPatientID() {
        return patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setID(int i) {
        id = i;
    }

    public void setStatus(AppointmentStatus s) {
        status = s;
    }

    public void setDate(LocalDate d) {
        date = d;
    }

    public void setTime(LocalTime t) {
        time = t;
    }

    public void setPatientID(int Pid) {
        patientID = Pid;
    }

    public void setDoctorID(int Did) {
        doctorID = Did;
    }

    public void setTypeOfService(String ToS) {
        typeOfService = ToS;
    }

    public void setDiagnosis(String d) {
        diagnosis = d;
    }
    public void setTreatment(String t) {
        treatment = t;
    }

    public void setConsultNotes(String Cn) {
        consultNotes = Cn;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public void printAppointmentDetails(ItemsService itemsService, boolean note) {
        //System.out.println("ID: " + id);
        System.out.println("Status: " + status);
        System.out.println("Scheduled Date: " + date);
        System.out.println("Scheduled Time: " + time);
        String doctorName = itemsService.getName(doctorID);
        System.out.println("Doctor: " + doctorName);
        String patientName = itemsService.getName(patientID);
        System.out.println("Patient: " + patientName);
        if(status == AppointmentStatus.Completed) {
            System.out.println("Type of Service: " + typeOfService);
            System.out.println("Diagnosis: " + diagnosis);
            System.out.println("Treatment: " + treatment);
            if (note) {
                System.out.println("Consultation Notes: " + consultNotes);
            }
            System.out.println("Prescription: ");
            prescription.readPrescription(); 
        }
         
    }

    public void recordAppointment(ItemsService itemsService) {
        Scanner sc = new Scanner(System.in);
        String medName, notes;
        int medamt;

        status = AppointmentStatus.Completed;
        System.out.println("Type of Service: ");
        typeOfService = sc.nextLine();
        System.out.println("Diagnosis: ");
        diagnosis = sc.nextLine();
        System.out.println("Treatment: ");
        treatment = sc.nextLine();
        System.out.println("Consult Notes: ");
        consultNotes = sc.nextLine();
        System.out.println("Recording prescriptions...");
        while(true) {
            System.out.println("Medicine name (to exit, write 000): ");
            medName = sc.nextLine();
            if(!itemsService.checkIfMedicineExists(medName)) {
                System.out.println("Medicine name doesn't exist! Try again...");
                continue;
            }
            if(medName.compareTo("000") == 0) {
                break;
            }
            System.out.println("Amount of medicine prescribed (tablets): ");
            medamt = sc.nextInt();
            sc.nextLine();
            System.out.println("Notes:");
            notes = sc.nextLine();

            prescription.addMedicine(medName, medamt, notes);
            sc.close();
        }
    }
}