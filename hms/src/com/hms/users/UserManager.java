package com.hms.users;

import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import java.util.Map;
import com.hms.dao.Dao;
import com.hms.dao.UserDao;
import com.hms.enums.*;

public class UserManager {
    private Map<Integer, User> UsersList = new HashMap<>();
    private static final String userDB = "hms/resources/userlogindb.txt";

    public UserManager() {
        Dao<User> reader = new UserDao();
        try {
            this.UsersList = reader.read(userDB);
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

    public User getUserFromName(String n) throws NoSuchElementException{
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

    public void addUser(User u) {
        if(UsersList.containsKey(u.getID())) {
            System.out.println("User ID " + u.getID() +" is not unique! Unable to add user. Error.");
            return;
        }
        UsersList.put(u.getID(), u);
        return;
    }

    public List<User> filterUser(String attribute, String value, String t) {
        List<User> temp;
        switch (attribute.toLowerCase()) {
            case "age":
                switch (t) {
                    case "<=":
                        temp = UsersList.values().stream().filter(user -> user.getAge() <= Integer.valueOf(value)).collect(Collectors.toList());
                        return temp;
                    case ">=":
                        temp = UsersList.values().stream().filter(user -> user.getAge() >= Integer.valueOf(value)).collect(Collectors.toList());
                        return temp;
                    case "<":
                        temp = UsersList.values().stream().filter(user -> user.getAge() < Integer.valueOf(value)).collect(Collectors.toList());
                        return temp;
                    case ">":
                        temp = UsersList.values().stream().filter(user -> user.getAge() > Integer.valueOf(value)).collect(Collectors.toList());
                        return temp;  
                }
                break;
            case "gender":
                temp = UsersList.values().stream().filter(user -> user.getGender() == Gender.getByValue(value)).collect(Collectors.toList());
                return temp;
            case "role":
            temp = UsersList.values().stream().filter(user -> user.getDesignation().toLowerCase().compareTo(value.toLowerCase()) == 0).collect(Collectors.toList());
                return temp;
            default:
                System.out.println("Attribute doesn't exist / not expected. Exiting...");
                return null;
        }
        return null;
                                                        
    }

    public void saveUsers() {
        Dao<User> writer = new UserDao();
        try {
            //Collections.sort(UsersList.values());
            writer.save(userDB, UsersList.values());           
        } catch (Exception e) {
            System.err.println("User manager saving error: " + e);
        }

    }

}
