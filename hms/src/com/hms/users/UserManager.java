package com.hms.users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.platform.console.shadow.picocli.CommandLine.Help.Ansi.Text;

import java.util.Map;
import com.hms.App;

import com.hms.readwrite.TextDB;

public class UserManager {
    // private ArrayList<User> UsersList = new ArrayList<User>();
    private Map<Integer, User> UsersList = new HashMap<>();
    private static final int stripPrefix = 100;

    public UserManager() {
        TextDB reader = new TextDB();
        try {
            this.UsersList = reader.readUsers(App.userDB);
        } catch (Exception e) {
            System.out.println("user manager " + e);
        }
    }
    public Map<Integer, User> getUsersList() {
        return this.UsersList;
    }

    public String getName(int id) {
        String name = UsersList.get(id).getName();
        return name;
    }

    public User getUserFromID(int id) {
        User u = new User();
        u = UsersList.get(id);
        return u;
    }

    public User getUserFromUsername(String username) {
        User u = new User();
        Iterator<User> i = UsersList.values().iterator();
        while(i.hasNext()) {
            u = i.next();
            if(username.compareTo(u.getUsername()) == 0) {
                return u;
            }
        }
        throw new NoSuchElementException("Username " + username + " not found.");
    }

    public User getUserFromName(String n) {
        User u = new User();
        Iterator<User> i = UsersList.values().iterator();
        while(i.hasNext()) {
            u = i.next();
            if(n.toLowerCase().compareTo(u.getName().toLowerCase()) == 0) {
                return u;
            }
        }
        throw new NoSuchElementException("Username " + n + " not found.");
    }

    public void printAllUsers() {
        User u = new User();
        Iterator<User> i = UsersList.values().iterator();
        while(i.hasNext()) {
            u = i.next();
            u.printUserDetails();
        }
    }
    //assuming the Userslist (and db by extension) is not sorted
    public void printSubUsers(String c) {
        User u = new User();

        Iterator<User> i = UsersList.values().iterator();
        int count = 1;

        switch (c) {
            case "Administrator":
                while(i.hasNext()) {
                    u = i.next();
                    if(u instanceof Administrator) {
                        System.out.printf("%-5s%s %s%n", Integer.toString(count++) + ".", "Administrator", u.getName());
                    }
                }
                break;
            case "Doctor":
                while(i.hasNext()) {
                    u = i.next();
                    if(u instanceof Doctor) {
                        System.out.printf("%-5s%s %s%n", Integer.toString(count++) + ".", "Doctor", u.getName());
                    }
                }
                break;
            case "Patient":
                while(i.hasNext()) {
                    u = i.next();
                    if(u instanceof Patient) {
                        System.out.printf("%-5s%s %s%n", Integer.toString(count++) + ".", "Patient", u.getName());
                    }
                }
                break;
            case "Pharmacist":
                while(i.hasNext()) {
                    u = i.next();
                    if(u instanceof Pharmacist) {
                        System.out.printf("%-5s%s %s%n", Integer.toString(count++) + ".", "Pharmacist", u.getName());
                    }
                }
                break;
            default:
                break;
        }  
    }

    public void removeUser(int id) {
        if(UsersList.containsKey(id)) {
            UsersList.remove(id);
            System.out.println("User removed successfully!");
        }
        else{
            System.out.println("User not found and removal was unsuccessful!");
        }
    }

    public void saveUsers() {
        TextDB writer = new TextDB();
        try {
            writer.saveUsers(App.userDB, UsersList.values());           
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
