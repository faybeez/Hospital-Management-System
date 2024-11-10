package com.hms;

import java.util.Scanner;
import java.nio.file.SecureDirectoryStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.hms.items.AppointmentManager;
import com.hms.readwrite.TextDB;
import com.hms.users.User;
import com.hms.users.UserManager;
import com.hms.users.User.BloodType;
import com.hms.users.User.Gender;

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
        System.out.println("1. View medical record");
        System.out.println("2. Update contact information");
        System.out.println("3. View all current Appointments");
        System.out.println("4. Schedule a new Appointment");
        System.out.println("5. Reschedule an existing Appointment");
        System.out.println("6. Reschedule an existing Appointment");
        System.out.println("7. Cancel an Appointment");
        System.out.println("8. View past Appointment details");

        //switch
        switch (choice) {
            case 1:
                
                break;
        
            default:
                break;
        }

    } 
}
