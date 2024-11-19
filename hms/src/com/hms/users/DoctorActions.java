package com.hms.users;

import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;
import com.hms.items.Appointment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DoctorActions implements UserActions {
    Doctor d;
    ItemsService itemsService;

    @Override
    public void printActions() {
        System.out.println("What would you like to do?");
        System.out.println("1. View Patient Medical Records");
        System.out.println("2. Update Patient Medical Record");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment Requests");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8. Logout");
        return;
    }

    @Override
    public boolean executeAction(int i) throws UnsupportedOperationException {
        if(i < 1 || i > 8) {
            throw new UnsupportedOperationException("Unknown action");
        }
        switch(i) {
            case 1:
                viewPatientMedicalRecords();
                break;
            case 2:
                updatePatientMedicalRecord();
                break;
            case 3:
                viewPersonalSchedule();
                break;
            case 4:
                setAvailability();
                break;
            case 5:
                settleAppointmentRequests();
                break;
            case 6:
                viewUpcomingAppointments();
                break;
            case 7:
                recordAppointmentOutcome();
                break;
            case 8:
                return true;
        }
        return false;
    }

    //TODO not complete
    void viewPatientMedicalRecords() {

    }

    //TODO not complete
    void updatePatientMedicalRecord() {
        Scanner sc = new Scanner(System.in);

        System.out.println("What would you like to update?");
        System.out.println("1. Add new diagnoses");
        System.out.println("2. Add new pescription");
        System.out.println("3. Add new treatment plans");
        System.out.println("Key in your choice: ");

        try {
            int choice2 = Integer.valueOf(sc.nextLine()); 
        switch (choice2) {
            case 1:
                System.out.println("add new diagnoses");
                break;
            case 2:
                System.out.println("add new description");
            case 3:
                System.out.println("add new treatment plans");
        }
        } catch (Exception e) {
            System.err.println("Update Patient Medical Record Error " + e);
        }
        finally {
            sc.close();
        }
        
        
    }

    void viewPersonalSchedule() {
        try {
            d.printSchedule();
        } catch (NullPointerException e) {
            System.err.println(e);
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    void setAvailability() {
        try {
            d.UpdateUnavailable();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    void settleAppointmentRequests() {
        Scanner sc = new Scanner(System.in);
        Iterator<Appointment> i;
        try {
            System.out.println("Printing all pending appointments...");
            String ad;
            
            i = itemsService.getDoctorAppts(d.getID(), AppointmentStatus.Pending).iterator();
            Appointment a;
            
            while (i.hasNext()) {
                a = i.next();
                a.printAppointmentDetails(itemsService, true);

                System.out.println("Accept? (Y/N)");
                ad = sc.nextLine();

                switch(ad) {
                    case "Y":
                        a.setStatus(AppointmentStatus.Confirmed);
                        //TODO send notif to patient?
                        break;
                    case "N":
                        a.setStatus(AppointmentStatus.Cancelled);
                        break;
                    default:
                        System.out.println("Wrong input. Skipped.");
                        break;
                }

            }
        } catch (NullPointerException e) {
            System.err.println("No object " + e);
        } 
        catch (Exception e) {
            System.err.println(e);
        }
        finally{
            sc.close();
        }
    }

    void viewUpcomingAppointments() {
        try {
            itemsService.printAppts(itemsService.getDoctorAppts(d.getID(), AppointmentStatus.Confirmed));
        } catch (NullPointerException e) {
            System.err.println("No object " + e);
        } 
        catch (Exception e) {
            System.err.println(e);
        }
    }

    void recordAppointmentOutcome() {
        Scanner sc = new Scanner(System.in);
        try {
            ArrayList<Appointment> appt = itemsService.getDoctorAppts(d.getID(), AppointmentStatus.Completed);
            itemsService.printAppts(appt);

            System.out.println("Which appointment would you like to record the outcome of? (1-x)");
            int choice = sc.nextInt();
            sc.nextLine();

            appt.get(choice - 1).recordAppointment(itemsService);
        } catch (Exception e) {
            System.err.println("Read Appointment Outcome Error" + e);
        } finally {
            sc.close();
        }
        
    }
}
