package com.hms.users;

import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;
import com.hms.items.Appointment;
import com.hms.items.MedicalRecord;

import java.util.ArrayList;
import java.util.Collections;
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
        try {
            itemsService.getMedicalRecordofPatient(p.getID()).printMedicalRecord(itemsService);
        } catch (Exception e) {
            System.err.println("View medical record error: " + e);
        }
    }

    void updatePersonalInformation() {
        Scanner sc = new Scanner(System.in);

        try {
            MedicalRecord mr = itemsService.getMedicalRecordofPatient(p.getID());

            System.out.println("Which information would you like to update?");
            System.out.println("1. Email Address");
            System.out.println("2. Phone Number");
            int choice2 = Integer.valueOf(sc.nextLine());
            switch (choice2) {
                case 1:
                    System.out.println("Enter your new email address: ");
                    String email = sc.nextLine();
                    mr.setEmailAddress(email);
                    System.out.println("Email Address has been changed!");
                    break;
                case 2:
                    System.out.println("Enter your new phone number: ");
                    String phone = sc.nextLine();
                    mr.setContactNumber(phone);
                    System.out.println("Phone number has been changed!");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println("Update personal information error " + e);
        } finally {
            sc.close();
        }
    }

    void viewAvailableAppointmentSlots() {
        Scanner sc = new Scanner(System.in);

        try {
            itemsService.printSubUsers("Doctor");
            System.out.println("Write the doctor's name you'd like to view the appointment slots of:");

            Doctor d = (Doctor)itemsService.getUserFromName(sc.nextLine());

            System.out.println("Printing Doctor " + d.getName() + "'s Schedule...");
            d.printSchedule();
        } catch (Exception e) {
            System.err.println("View Available Appointment Slots error " + e);
        } finally{
            sc.close();
        }
    }

    void scheduleAppointment() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Schedule an appointment");
            itemsService.printSubUsers("Doctor");
            System.out.println("Write the doctor's name you'd like to make an appointment with:");
            
            Doctor d = (Doctor)itemsService.getUserFromName(sc.nextLine());

            Appointment a = p.makeAppointmentRequest(d);
            if(a != null) {
                itemsService.addAppointment(a);
            }
        } catch (Exception e) {
            System.err.println("Schedule appointment error " + e);
        } finally {
            sc.close();
        }
    }

    void rescheduleAppointment() {
        Scanner sc = new Scanner(System.in);
        try {
            ArrayList<Appointment> appt = itemsService.getPatientAppts(p.getID(), AppointmentStatus.Confirmed);
            appt.addAll(itemsService.getPatientAppts(p.getID(), AppointmentStatus.Pending));

            itemsService.printAppts(appt);

            System.out.println("Which appointment would you like to reschedule? (Number 1-x)");
            int choice2 = Integer.valueOf(sc.nextLine());
            
            if(choice2 > appt.size()){
                throw new IndexOutOfBoundsException("Appointment array index out of bounds!");
            }

            Appointment a = appt.get(choice2 - 1);
            p.rescheduleAppointment(a, (Doctor)itemsService.getUserFromID(a.getDoctorID()));

        } catch (Exception e) {
            System.err.println("Schedule appointment error " + e);
        } finally {
            sc.close();
        }
    }

    void cancelAppointment() {
        Scanner sc = new Scanner(System.in);
        try {
            ArrayList<Appointment> appt = itemsService.getPatientAppts(p.getID(), AppointmentStatus.Confirmed);
            appt.addAll(itemsService.getPatientAppts(p.getID(), AppointmentStatus.Pending));

            itemsService.printAppts(appt);

            System.out.println("Which appointment would you like to cancel? (Number 1-x)");
            int choice2 = Integer.valueOf(sc.nextLine());

            if(choice2 > appt.size()){
                throw new IndexOutOfBoundsException("Appointment array index out of bounds!");
            }
            
            appt.get(choice2 - 1).setStatus(AppointmentStatus.Cancelled);
            
            System.out.println("Appointment cancelled!");
        } catch (Exception e) {
            System.err.println("Schedule appointment error " + e);
        } finally {
            sc.close();
        }
    }

    void viewScheduledAppointments() {
        try {
            ArrayList<Appointment> appt = itemsService.getPatientAppts(p.getID(), AppointmentStatus.Confirmed);
            appt.addAll(itemsService.getPatientAppts(p.getID(), AppointmentStatus.Pending));
            Collections.sort(appt);
            itemsService.printAppts(appt);
        } catch (Exception e) {
            System.err.println("view scheduled appointments error " + e);
        }
        
    }

    void viewPastAppointmentOutcomeRecords() {
        try {
            itemsService.printAppts(itemsService.getPatientAppts(p.getID(), AppointmentStatus.Completed));
        } catch (Exception e) {
            System.err.println("View past appointment outcome records error " + e);
        }
        
    }


}
