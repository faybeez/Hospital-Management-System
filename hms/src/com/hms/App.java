package com.hms;

import com.hms.items.AppointmentManager;
import com.hms.items.MedicalRecord;
import com.hms.items.MedicalRecordManager;
import com.hms.items.Appointment.Status;
import com.hms.users.User;
import com.hms.users.UserManager;
import com.hms.users.Patient;
import com.hms.users.Doctor;
import com.hms.items.Appointment;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class App {
    public static final String userDB = "hms/src/com/hms/database/userlogindb.txt";
    public static final String apptDB = "hms/src/com/hms/database/appointmentdb.txt";
    public static final String schedulerDB = "hms/src/com/hms/database/schedulerdb.txt";
    public static void main(String[] args) throws Exception {

        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        MedicalRecordManager medicalrecordmanager = new MedicalRecordManager();
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
                patientActions((Patient)u, usermanager, apptmanager, medicalrecordmanager);
                break;
            case "Doctor":
                doctorActions((Doctor)u, usermanager, apptmanager);
                break;
            case "Pharmacist":
                pharmacistActions(u);
                break;
            case "Admin":
                adminActions(u);
                break;
            default:
                break;
                
        }
        //logout actions
        usermanager.saveUsers();
        apptmanager.saveAppts();

       /* User test = new User(1001, sc.nextLine(), sc.nextLine(), Gender.FEMALE, BloodType.ABMINUS, sc.nextLine(), sc.nextLine());
        //test.printUserDetails();
        //System.out.println("test!");

        TextDB textdb = new TextDB();
        
        String filename = "hms\\src\\com\\hms\\database\\userlogindb.txt";
        ArrayList<User> userArray = new ArrayList<User>();
        userArray = textdb.readUsers(filename);
        User user = userArray.get(0);

        user.printUserDetails(); */

        sc.close();
    }

    public static void patientActions(Patient p, UserManager usermanager, AppointmentManager apptmanager, MedicalRecordManager medrecordmanager) {

        //print current tasks?

        int choice = -1;
        int choice2;
        int id;
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

            Scanner sc = new Scanner(System.in);
            System.out.println("Key in your choice: ");
            choice = Integer.valueOf(sc.nextLine()); 
        

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

                    a = p.makeAppointmentRequest(d);
                    apptmanager.addAppointment(a);
                    break;
                case 5:
                    System.out.println("reschedule an appointment");
                    appt = apptmanager.getPatientAppts(p.getID(), Status.Confirmed);
                    appt.addAll(apptmanager.getPatientAppts(p.getID(), Status.Pending));

                    apptmanager.printAppts(appt, usermanager);

                    System.out.println("Which appointment would you like to reschedule? (Number 1-x)");
                    choice2 = Integer.valueOf(sc.nextLine());
                    
                    if(choice2 > appt.size()){
                        System.out.println("Invalid number!");
                        break;
                    }

                    a = appt.get(choice2 - 1);
                    p.rescheduleAppointment(a, (Doctor)usermanager.getUserFromID(a.getDoctorID()));

                    break;
                case 6:
                    System.out.println("cancel an appointment");
                    appt = apptmanager.getPatientAppts(p.getID(), Status.Confirmed);
                    appt.addAll(apptmanager.getPatientAppts(p.getID(), Status.Pending));

                    apptmanager.printAppts(appt, usermanager);

                    System.out.println("Which appointment would you like to cancel? (Number 1-x)");
                    choice2 = Integer.valueOf(sc.nextLine());

                    if(choice2 > appt.size()){
                        System.out.println("Invalid number!");
                        break;
                    }
                    
                    appt.get(choice2 - 1).setStatus(Status.Cancelled);
                    
                    System.out.println("Appointment cancelled!");
                    break;
                case 7:
                    System.out.println("view scheduled appointments");
                    appt = apptmanager.getPatientAppts(p.getID(), Status.Confirmed);
                    appt.addAll(apptmanager.getPatientAppts(p.getID(), Status.Pending));
                    Collections.sort(appt);
                    apptmanager.printAppts(appt, usermanager);
                    
                    break;
                case 8:
                    System.out.println("view past appointment outcome records");
                    apptmanager.printAppts(apptmanager.getPatientAppts(p.getID(), Status.Completed), usermanager);
                case 9:
                    System.out.println("logout");
                    
                default:
                    break;
            }
        }

    } 

    public static void doctorActions(Doctor d, UserManager usermanager, AppointmentManager apptmanager) {

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

            Scanner sc = new Scanner(System.in);
            System.out.println("Key in your choice: ");
            choice = Integer.valueOf(sc.nextLine()); 
            
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

                    //print specific appointments
                    break;
                case 4:
                    System.out.println("set availability for appointments");
                    d.UpdateUnavailable();
                    break;
                case 5:
                    System.out.println("Accept or decline appointments");
                    System.out.println("Printing all pending appointments...");
                    String ad;
                    
                    appt = apptmanager.getDoctorAppts(d.getID(), Status.Pending);
                    i = appt.iterator();
                    Appointment a;
                    
                    while (i.hasNext()) {
                        a = i.next();
                        a.printAppointmentDetails(usermanager, true);

                        System.out.println("Accept? (Y/N)");
                        ad = sc.nextLine();

                        switch(ad) {
                            case "Y":
                                a.setStatus(Status.Confirmed);
                                //send notif to patient?
                            case "N":
                                a.setStatus(Status.Cancelled);
                            default:
                                System.out.println("Wrong input. Skipped.");
                        }

                    }

                    //System.out.println("Which appointment would you like to accept / decline?");

                    break;
                case 6:
                    System.out.println("view upcoming appointments");
                    appt = apptmanager.getDoctorAppts(d.getID(), Status.Confirmed);
                    apptmanager.printAppts(appt, usermanager);
                    break;
                case 7:
                    System.out.println("record appointment outcome");
                    appt = apptmanager.getDoctorAppts(d.getID(), Status.Completed);

                    break;      
                case 8: 
                    System.out.println("Logging out...");
                    
                default:
                    break;
            }
        }

    } 

    public static void pharmacistActions(User p) {

        //print current tasks?

        int choice = -1;

        System.out.println("What would you like to do?");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Status of Prescription");
        System.out.println("3. View Medicine Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Logout");

        Scanner sc = new Scanner(System.in);
        System.out.println("Key in your choice: ");
        choice = Integer.valueOf(sc.nextLine()); 
    
        //switch
        switch (choice) {
            case 1:
                System.out.println("view appointment outcome record");
                break;
            case 2:
                System.out.println("update status of prescription");
                break;
            case 3:
                System.out.println("monitor medicine inventory");
                break;
            case 4: 
                System.out.println("submit replenishment request");
                break;
            case 5: 
                System.out.println("logout");
            default:
                break;
        }

    } 

    public static void adminActions (User p)    {

        //print current tasks?

        int choice = -1;

        System.out.println("What would you like to do?");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. Filter by Role");
        System.out.println("3. View Appointment Datails");
        System.out.println("4. View and Manage Medicine Inventory"); 
        System.out.println("5. Update Low Stock Level Alert Line");
        System.out.println("6. Approve Replenish Request");

        Scanner sc = new Scanner(System.in);
        System.out.println("Key in your choice: ");
        choice = Integer.valueOf(sc.nextLine()); 
        
        //switch
        switch (choice) {
            case 1:
                System.out.println("What would you like to do?");
                System.out.println("Add Staff Members");
                System.out.println("Updating Staff Members");
                System.out.println("Remove Staff Members");
                int choice2 = Integer.valueOf(sc.nextLine());
                switch (choice2)    {
                    case 1:
                        System.out.println("add staff member");
                        break;
                    case 2:
                        System.out.println("update staff member");
                        break;
                    case 3: 
                        System.out.println("remove staff member");
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                System.out.println("What would you like to filter by?");
                System.out.println("Role?");
                System.out.println("Gender");
                System.out.println("Age");
                choice2 = Integer.valueOf(sc.nextLine());
                switch (choice2)    {
                    case 1:
                        System.out.println("role");
                        break;
                    case 2:
                        System.out.println("gender");
                        break;
                    case 3: 
                        System.out.println("age");
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                System.out.println("voew appointment details");
                break;
            case 4: 
                System.out.println("view and manage medicine invertory");
                break;    
            case 5:
                System.out.println("update low stock level alert line");
            case 6: 
                System.out.println("approve replenish,emt request");
            default:
                break;
        }

    } 

}

