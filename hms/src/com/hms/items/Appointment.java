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
    private String consultNotes;
    private String prescriptionID;

    //string in form of YYYY-MM-DDTHH:MM:SS where SS defaults to 00
    public Appointment(int pID, int dID, String typeofservice, LocalDate date, LocalTime time) {
        id = AppointmentIdentifier + AppointmentNumber;
        patientID = pID;
        doctorID = dID;
        typeOfService = typeofservice;
        this.date = date;
        this.time = time;
        status = Status.Confirmed;
        AppointmentNumber ++;
    }

    public int getAppointmentID() {
        return id;
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

    public void setStatus(Status s) {
        status = s;
    }

    public void setDate(LocalDate d) {
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
