package com.hms;

import java.util.Scanner;
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
}
