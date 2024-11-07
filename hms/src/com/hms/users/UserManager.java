package com.hms.users;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.hms.readwrite.TextDB;

public class UserManager {
    private ArrayList<User> UsersList = new ArrayList<User>();
    private static final int stripPrefix = 100;

    public UserManager() {
        TextDB reader = new TextDB();
        try {
            this.UsersList = reader.readUsers("hms\\src\\com\\hms\\database\\userlogindb.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public ArrayList<User> getUsersList() {
        return this.UsersList;
    }

    public String getName(int id) {
        int index = id % stripPrefix;
        String name = UsersList.get(index - 1).getName();

        return name;
    }

    public User getUserFromID(int id) {
        int index = id % stripPrefix;
        User u = new User();
        u = UsersList.get(index - 1);
        return u;
    }

    public User getUserFromUsername(String username) {
        User u = new User();
        Iterator<User> i = UsersList.iterator();
        while(i.hasNext()) {
            u = i.next();
            if(username.compareTo(u.getUsername()) == 0) {
                return u;
            }
        }
        throw new NoSuchElementException("Username " + username + " not found.");
    }

    public void printAllUsers() {
        User u = new User();
        Iterator<User> i = UsersList.iterator();
        while(i.hasNext()) {
            u = i.next();
            u.printUserDetails();
        }
    }
    //assuming the Userslist (and db by extension) is not sorted
    public void printSubUsers(String c) {
        User u = new User();

        Iterator<User> i = UsersList.iterator();
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
}
