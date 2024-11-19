package com.hms.users;

import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;
import com.hms.items.Appointment;
import com.hms.App;

import java.util.ArrayList;
import java.util.Iterator;

public class DoctorActions implements UserActions {
    Doctor d;
    ItemsService itemsService;

    public DoctorActions(Doctor d, ItemsService itemsService) {
        this.d = d;
        this.itemsService = itemsService;
    }

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

    void viewPatientMedicalRecords() {
        try {
            User u = getPatient();

            System.out.println("Printing medical record of Patient " + u.getName());

            itemsService.getMedicalRecordofPatient(u.getID()).printMedicalRecord(itemsService);
        } catch (Exception e) {
            System.err.println("View patient medical records: ");
        }
    }

    void updatePatientMedicalRecord() {
        
        try {
            User u = getPatient();

            System.out.println("Key in new diagnosis:");
            String d = App.sc.nextLine();
            System.out.println("Key in new treatment plan:");
            String t = App.sc.nextLine();

            itemsService.getMedicalRecordofPatient(u.getID()).addDiagnosisTreatment(d, t);
            System.out.println("Diagnosis " + d + " and Treatment " + t + " were added!");
        } catch (Exception e) {
            System.err.println("Update Patient Medical Record error: " + e);
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
                ad = App.sc.nextLine();

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
        
        try {
            ArrayList<Appointment> appt = itemsService.getDoctorAppts(d.getID(), AppointmentStatus.Completed);
            itemsService.printAppts(appt);

            if(appt.isEmpty()) {
                System.out.println("No appointments to record! Exiting...");
                return;
            }

            System.out.println("Which appointment would you like to record the outcome of? (1-x)");
            int choice = App.sc.nextInt();
            App.sc.nextLine();

            appt.get(choice - 1).recordAppointment(itemsService);
        } catch (Exception e) {
            System.err.println("Read Appointment Outcome Error" + e);
        } finally {
            
        }
        
    }

    User getPatient() {
        itemsService.printSubUsers("Patient");

        System.out.println("Name of patient to view:");

        String name = App.sc.nextLine();

        User u = itemsService.getUserFromName(name);

        if(u == null || !(u instanceof Patient)) {
            throw new NullPointerException("Patient doesn't exist!");
        }
        return u;
    }
}
