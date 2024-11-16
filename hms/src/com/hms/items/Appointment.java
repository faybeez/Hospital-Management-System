package com.hms.items;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    //enum
    public enum Status {
        Pending {
            @Override
            public String toString() {
                return "Waiting for Doctor's Approval";
            }
        },
        Confirmed {
            @Override
            public String toString() {
                return "Confirmed";
            }
        },
        Cancelled {
            @Override
            public String toString() {
                return "Cancelled";
            }
        },
        Completed {
            @Override
            public String toString() {
                return "Completed";
            }
        }
    }

    public static int AppointmentNumber = 0;
    public static final int AppointmentIdentifier = 2000000000;
    private int id;
    private Status status;
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
        id = AppointmentIdentifier + AppointmentNumber;
        patientID = pID;
        doctorID = dID;
        this.date = date;
        this.time = time;
        status = Status.Pending;
        AppointmentNumber ++;
        prescription = new Prescription();
    }

    //public Appointment(int i, Status s, LocalDate d, LocalTime t, int pid, int did, String tos, )

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

    public Status getStatus() {
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

    public void setStatus(Status s) {
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

    public void setPrescription(Prescription p) {
        prescription = p;
    }

    public void printAppointmentDetails() {
        System.out.println("APPOINTMENT DETAILS");
    }

}
