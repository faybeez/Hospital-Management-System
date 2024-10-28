package com.hms.items;
import java.time.LocalDateTime;

public class Appointment {
    //enum
    enum Status {

    }

    private int id;
    private Status status;
    private LocalDateTime date;
    private int patientID;
    private int doctorID;
    private String typeOfService;
    private String consultNotes;
    private Prescription prescription;
}
