package com.hms;

import com.hms.items.AppointmentManager;
import com.hms.items.Inventory;
import com.hms.items.MedicalRecord;
import com.hms.items.MedicalRecordManager;
import com.hms.items.SchedulerManager;
import com.hms.users.User;
import com.hms.users.UserManager;
import com.hms.users.Patient;
import com.hms.users.Pharmacist;
import com.hms.users.Administrator;
import com.hms.users.Doctor;
import com.hms.items.Appointment;
import com.hms.enums.*;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class App {    
    /** 
     * main function of the code
     * @param args
     * 
     */
    public static void main(String[] args) {

        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        MedicalRecordManager medicalrecordmanager = new MedicalRecordManager();
        SchedulerManager schedulermanager = new SchedulerManager(usermanager);
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        User u = new User();

        //START login
        while(true) { 
            System.out.println("Enter your username: ");
            username = sc.nextLine();
            try {
                u = usermanager.getUserFromUsername(username);
                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Enter your password: ");
        password = sc.nextLine();

        if(password.compareTo(u.getPassword()) != 0) {
            System.out.println("Wrong password!");
            sc.close();
            return;
        }

        //END login
        System.out.println("Welcome " + u.getDesignation() + " " + u.getName() + "!");
        
        switch (u.getDesignation()) {
            case "Patient":
                patientActions((Patient)u, usermanager, apptmanager, medicalrecordmanager , sc);
                break;
            case "Doctor":
                doctorActions((Doctor)u, usermanager, apptmanager, inventory, sc);
                break;
            case "Pharmacist":
                pharmacistActions((Pharmacist)u, usermanager, apptmanager, inventory, sc);
                break;
            case "Administrator":
                adminActions((Administrator)u, usermanager, inventory, apptmanager, sc);
                break;
            default:
                break;
                
        }
        //logout actions
        usermanager.saveUsers();
        apptmanager.saveAppts();
        medicalrecordmanager.saveMedicalRecords();
        schedulermanager.saveSchedules();
        inventory.saveInventory();
        sc.close();
    }

    public static void patientActions(Patient p, UserManager usermanager, AppointmentManager apptmanager, MedicalRecordManager medrecordmanager, Scanner sc) {

        //print current tasks?

        int choice = -1;
        int choice2;
        String email, phone;
        Doctor d;
        ArrayList<Appointment> appt;
        Appointment a;

        MedicalRecord mr = medrecordmanager.getMedicalRecordofPatient(p.getID());
        
        while(choice != 9) {
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

            System.out.println("Key in your choice: ");
            choice = sc.nextInt(); 
            sc.nextLine();

            //switch
            switch (choice) {
                case 1:
                    System.out.println("view medical record");
                    mr.printMedicalRecord(apptmanager);
                    break;
                case 2:
                    System.out.println("update personal information");

                    System.out.println("Which information would you like to update?");
                    System.out.println("1. Email Address");
                    System.out.println("2. Phone Number");
                    choice2 = Integer.valueOf(sc.nextLine());
                    switch (choice2) {
                        case 1:
                            System.out.println("Enter your new email address: ");
                            email = sc.nextLine();
                            mr.setEmailAddress(email);
                            System.out.println("Email Address has been changed!");
                            break;
                        case 2:
                            System.out.println("Enter your new phone number: ");
                            phone = sc.nextLine();
                            mr.setContactNumber(phone);
                            System.out.println("Phone number has been changed!");
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    System.out.println("view available appointment slots");

                    usermanager.printSubUsers("Doctor");
                    System.out.println("Write the doctor's name you'd like to view the appointment slots of:");

                    try {
                        d = (Doctor)usermanager.getUserFromName(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println(e);
                        break;
                    }

                    System.out.println("Printing Doctor " + d.getName() + "'s Schedule...");
                    d.printSchedule();
                    break;
                case 4: 
                    System.out.println("Schedule an appointment");
                    usermanager.printSubUsers("Doctor");
                    System.out.println("Write the doctor's name you'd like to make an appointment with:");
                    try {
                        d = (Doctor)usermanager.getUserFromName(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println(e);
                        break;
                    }

                    a = p.makeAppointmentRequest(d, sc);
                    if(a != null) {
                        apptmanager.addAppointment(a);
                    }
                    break;
                case 5:
                    System.out.println("reschedule an appointment");
                    appt = apptmanager.getPatientAppts(p.getID(), AppointmentStatus.Confirmed);
                    appt.addAll(apptmanager.getPatientAppts(p.getID(), AppointmentStatus.Pending));

                    apptmanager.printAppts(appt, usermanager);

                    System.out.println("Which appointment would you like to reschedule? (Number 1-x)");
                    choice2 = Integer.valueOf(sc.nextLine());
                    
                    if(choice2 > appt.size()){
                        System.out.println("Invalid number!");
                        break;
                    }

                    a = appt.get(choice2 - 1);
                    p.rescheduleAppointment(a, (Doctor)usermanager.getUserFromID(a.getDoctorID()), sc);

                    break;
                case 6:
                    System.out.println("cancel an appointment");
                    appt = apptmanager.getPatientAppts(p.getID(), AppointmentStatus.Confirmed);
                    appt.addAll(apptmanager.getPatientAppts(p.getID(), AppointmentStatus.Pending));

                    apptmanager.printAppts(appt, usermanager);

                    System.out.println("Which appointment would you like to cancel? (Number 1-x)");
                    choice2 = Integer.valueOf(sc.nextLine());

                    if(choice2 > appt.size()){
                        System.out.println("Invalid number!");
                        break;
                    }
                    
                    appt.get(choice2 - 1).setStatus(AppointmentStatus.Cancelled);
                    
                    System.out.println("Appointment cancelled!");
                    break;
                case 7:
                    System.out.println("view scheduled appointments");
                    appt = apptmanager.getPatientAppts(p.getID(), AppointmentStatus.Confirmed);
                    appt.addAll(apptmanager.getPatientAppts(p.getID(), AppointmentStatus.Pending));
                    Collections.sort(appt);
                    apptmanager.printAppts(appt, usermanager);
                    
                    break;
                case 8:
                    System.out.println("view past appointment outcome records");
                    apptmanager.printAppts(apptmanager.getPatientAppts(p.getID(), AppointmentStatus.Completed), usermanager);
                case 9:
                    System.out.println("logout");
                    
                default:
                    break;
            }
        }

    } 

    public static void doctorActions(Doctor d, UserManager usermanager, AppointmentManager apptmanager, Inventory inventory, Scanner sc) {

        //print current tasks?

        int choice = -1;
        int choice2;
        ArrayList<Appointment> appt;
        Iterator<Appointment> i;

        while(choice != 8) {
            System.out.println("What would you like to do?");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Record");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability or Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");

            System.out.println("Key in your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            
            //switch
            switch (choice) {
                case 1:
                    System.out.println("Viewing patient medical records");
                    
                    break;
                case 2:
                    System.out.println("What would you like to update?");
                    System.out.println("1. Add new diagnoses");
                    System.out.println("2. Add new pescription");
                    System.out.println("3. Add new treatment plans");
                    System.out.println("Key in your choice: ");
                    choice2 = Integer.valueOf(sc.nextLine()); 
                    switch (choice2) {
                        case 1:
                            System.out.println("add new diagnoses");
                            break;
                        case 2:
                            System.out.println("add new description");
                        case 3:
                            System.out.println("add new treatment plans");
                    }
                    break;

                case 3:
                    System.out.println("view personal schedule");
                    d.getSchedule().printSchedule();

                    //TODO print specific appointments
                    break;
                case 4:
                    System.out.println("set availability for appointments");
                    d.UpdateUnavailable();
                    break;
                case 5:
                    System.out.println("Accept or decline appointments");
                    System.out.println("Printing all pending appointments...");
                    String ad;
                    
                    appt = apptmanager.getDoctorAppts(d.getID(), AppointmentStatus.Pending);
                    i = appt.iterator();
                    Appointment a;
                    
                    while (i.hasNext()) {
                        a = i.next();
                        a.printAppointmentDetails(usermanager, true);

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
                    break;
                case 6:
                    System.out.println("view upcoming appointments");
                    appt = apptmanager.getDoctorAppts(d.getID(), AppointmentStatus.Confirmed);
                    apptmanager.printAppts(appt, usermanager);
                    break;
                case 7:
                    System.out.println("record appointment outcome");
                    appt = apptmanager.getDoctorAppts(d.getID(), AppointmentStatus.Completed);

                    apptmanager.printAppts(appt, usermanager);

                    System.out.println("Which appointment would you like to record the outcome of? (1-x)");
                    choice2 = sc.nextInt();
                    sc.nextLine();

                    a = appt.get(choice2 - 1);

                    a.recordAppointment(sc, inventory);

                    break;      
                case 8: 
                    System.out.println("Logging out...");
                    
                default:
                    break;
            }
        }

    } 

    public static void pharmacistActions(Pharmacist p, UserManager usermanager, AppointmentManager apptmanager, Inventory inventory, Scanner sc) {

        //TODO print current tasks?

        int choice = -1;
        while (choice != 6) {
            System.out.println("What would you like to do?");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Status of Prescription");
            System.out.println("3. Dispense medicine based on prescription");
            System.out.println("4. View Medicine Inventory");
            System.out.println("5. Submit Replenishment Request");
            System.out.println("6. Logout");

            System.out.println("Key in your choice: ");
            choice = Integer.valueOf(sc.nextLine()); 
        
            //switch
            switch (choice) {
                case 1:
                    System.out.println("Viewing all appointment outcome records...");
                    p.viewAppointmentOutcomeRecords(apptmanager, usermanager, sc);
                    
                    break;
                case 2:
                    System.out.println("Manually update status of prescription");
                    p.changePrescriptionStatus(apptmanager, usermanager, sc);
                    break;
                case 3:
                    System.out.println("Dispensing medicine...");
                    p.dispenseMedicine(usermanager, apptmanager, inventory, sc);
                case 4:
                    System.out.println("Viewing medicine inventory...");
                    inventory.displayMedications();
                    break;
                case 5: 
                    System.out.println("submit replenishment request");
                    p.submitRepenishmentRequest(inventory, sc);
                    break;
                case 6: 
                    System.out.println("Logging out...");
                    break;
                default:
                    break;
            }
        }
        

    } 

    public static void adminActions (Administrator a, UserManager usermanager, Inventory inventory, AppointmentManager apptmanager, Scanner sc)    {

        //TODO print current tasks?

        int choice = -1;
        while(choice != 6) {
            System.out.println("What would you like to do?");
            System.out.println("1. Manage Hospital Staff");
            System.out.println("2. View all users");
            System.out.println("3. All View Appointment Datails");
            System.out.println("4. View and Manage Medicine Inventory"); 
            System.out.println("5. Approve Replenish Request");
            System.out.println("6. Log out");

            System.out.println("Key in your choice: ");
            choice = Integer.valueOf(sc.nextLine()); 
            
            //switch
            switch (choice) {
                case 1:
                    a.manageHospitalStaff(usermanager, sc);
                    break;
                case 2:
                    a.filterUserByAttribute(usermanager, sc);
                    break;
                case 3:
                    a.viewAppointments(apptmanager, usermanager, sc);
                    break;
                case 4: 
                    System.out.println("view and manage medicine invertory");
                    a.manageMedicationInventory(inventory, sc);
                    break;    
                case 5: 
                    System.out.println("Approve replenishment requests");
                    a.manageReplenishments(sc, inventory);
                case 6:
                    System.out.println("Logging out...");
                default:
                    break;
            }
        }

    } 

}

