package com.hms.users;

import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;
import com.hms.items.Appointment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PatientActions implements UserActions {
    Patient p;
    ItemsService itemsService;

    @Override
    public void printActions() {
        System.out.println("What would you like to do?");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Apppointment Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. Logout");
        return;
    }


    @Override
    public boolean executeAction(int i) throws UnsupportedOperationException {
        if(i < 1 || i > 9) {
            throw new UnsupportedOperationException("Unknown action");
        }
        switch(i) {
            case 1:
                viewMedicalRecord();
                break;
            case 2:
                updatePersonalInformation();
                break;
            case 3:
                viewAvailableAppointmentSlots();
                break;
            case 4:
                scheduleAppointment();
                break;
            case 5:
                rescheduleAppointment();
                break;
            case 6:
                cancelAppointment();
                break;
            case 7:
                viewScheduledAppointments();
                break;
            case 8:
                viewPastAppointmentOutcomeRecords();
                break;
            case 9:
                return true;
        }
        return false;
    }

    void viewMedicalRecord() {

    }

    void updatePersonalInformation() {

    }

    void viewAvailableAppointmentSlots() {

    }

    void scheduleAppointment() {

    }

    void rescheduleAppointment() {

    }

    void cancelAppointment() {

    }

    void viewScheduledAppointments() {

    }

    void viewPastAppointmentOutcomeRecords() {
        
    }


}
