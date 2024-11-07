package com.hms.items;
import java.time.LocalDateTime;

public class Appointment {
    //enum
    public enum Status {
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
    private LocalDateTime date;
    private int patientID;
    private int doctorID;
    private String typeOfService;
    private String consultNotes;
    private String prescriptionID;

    //string in form of YYYY-MM-DDTHH:MM:SS where SS defaults to 00
    public Appointment(int pID, int dID, String typeofservice, String date) {
        id = AppointmentIdentifier + AppointmentNumber;
        patientID = pID;
        doctorID = dID;
        typeOfService = typeofservice;
        this.date = LocalDateTime.parse(date);
        status = Status.Confirmed;
        AppointmentNumber ++;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getDoctorID() {
        return doctorID;
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

    public void setPrescriptionID(String p) {
        prescriptionID = p;
    }

    public void printAppointmentDetails() {
        System.out.println("APPOINTMENT DETAILS");
    }

}
