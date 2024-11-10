package com.hms;

import com.hms.items.AppointmentManager;
import com.hms.users.User;
import com.hms.users.UserManager;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
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
                patientActions(u);
                break;
            case "Doctor":
                doctorActions(u);
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

    public static void patientActions(User p) {

        //print current tasks?

        int choice = -1;

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
        choice = sc.nextInt(); 
    

        //switch
        switch (choice) {
            case 1:
                System.out.println("view medical record");
                break;
            case 2:
                System.out.println("update personal information");
                break;
            case 3:
                System.out.println("view available appointment slots");
                break;
            case 4: 
                System.out.println("schedule an appointment");
                break;
            case 5:
                System.out.println("reschedule an appointment");
                break;
            case 6:
                System.out.println("cancel an appointment");
                break;
            case 7:
                System.out.println("view scheduled appointments");
                break;        
            case 8:
                System.out.println("view past appoontment outcome records");
            case 9:
                System.out.println("logout");
            default:
                break;
        }

    } 

    public static void doctorActions(User p) {

        //print current tasks?

        int choice = -1;

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
        choice = sc.nextInt(); 
        
        //switch
        switch (choice) {
            case 1:
                System.out.println("view patient medical record");
                break;
            case 2:
                System.out.println("What would you like to update?");
                System.out.println("1. Add new diagnoses");
                System.out.println("2. Add new pescription");
                System.out.println("3. Add new treatment plans");
                System.out.println("Key in your choice: ");
                int choice2 = sc.nextInt(); 
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
                break;
            case 4:
                System.out.println("set availability for appointments");
            case 5:
                System.out.println("accept or decline appointment");
                break;
            case 6:
                System.out.println("view upcoming appointments");
                break;
            case 7:
                System.out.println("record appointment outcome");
                break;      
            case 8: 
                System.out.println("logout");
            default:
                break;
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
        choice = sc.nextInt(); 
    
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
        choice = sc.nextInt(); 
        
        //switch
        switch (choice) {
            case 1:
                System.out.println("What would you like to do?");
                System.out.println("Add Staff Members");
                System.out.println("Updating Staff Members");
                System.out.println("Remove Staff Members");
                int choice2 = sc.nextInt();
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
                choice2 = sc.nextInt();
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

