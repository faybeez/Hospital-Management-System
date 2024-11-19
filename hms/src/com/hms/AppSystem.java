package com.hms;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.lang.Exception;

import com.hms.users.UserManager;
import com.hms.users.User;

public class AppSystem {
    private static final String defaultPassword = "defaultpass";

    public AppSystem() {

    }

    public User Login(UserManager userManager) throws Exception {

        String username;
        String password;
        User u;

        while(true) { 
            System.out.println("Enter your username: ");
            username = App.sc.nextLine();
            try {
                u = userManager.getUserFromUsername(username);
                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Enter your password: ");
        password = App.sc.nextLine();

        if(password.compareTo(u.getPassword()) != 0) {
            throw new Exception("Wrong password, Exiting...");
        }
        
        System.out.println("Welcome " + u.getDesignation() + " " + u.getName() + "!");
        
        if(password.compareTo(defaultPassword) == 0) {
            //TODO change pass
        }
        return u;
    }

}
