package com.hms.items;
import java.time.LocalDateTime;

public class Appointment {
    //enum
    public enum Status {

    }

    private int id;
    private Status status;
    private LocalDateTime date;
    private int patientID;
    private int doctorID;
    private String typeOfService;
    private String consultNotes;
    private Prescription prescription;

    public Appointment() {

    }

    public void setStatus(Status s) {
        status = s;
    }

    public void setDate(LocalDateTime d) {
        date = d;
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

    public void setConsultNotes(String Cn) {
        consultNotes = Cn;
    }

    public void setPrescription(Prescription p) {
        prescription = p;
    }
}
