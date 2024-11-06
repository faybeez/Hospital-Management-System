package com.hms.items;

import java.util.ArrayList;
import java.util.Iterator;

public class AppointmentManager {
    private ArrayList<Appointment> Appts = new ArrayList<Appointment>();

    public void addAppointment(Appointment a) {
        Appts.add(a);
    }
    public void deleteAppointment(int index) {
        Appts.remove(index - 1);
    }
    public void PrintAppointmentsFromPatientID(int id) {
        Iterator<Appointment> apptIterator = Appts.iterator();
        Appointment temp;

        while(apptIterator.hasNext()) {
            temp = apptIterator.next();

            if(temp.getPatientID() == id) {
                temp.printAppointmentDetails();
            }
        }
    }

    public void PrintAppointmentsFromDoctorID(int id) {
        Iterator<Appointment> apptIterator = Appts.iterator();
        Appointment temp;

        while(apptIterator.hasNext()) {
            temp = apptIterator.next();
            
            if(temp.getDoctorID() == id) {
                temp.printAppointmentDetails();
            }
        }
    }
}
