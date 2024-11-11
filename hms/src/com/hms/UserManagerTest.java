package com.hms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import com.hms.readwrite.TextDB;
import com.hms.users.User;
import com.hms.users.UserManager;

public class UserManagerTest {
    @Test
    public void readTest() {
        ArrayList<User> UsersList = new ArrayList<User>();
        TextDB reader = new TextDB();
        try {
            UsersList = reader.readUsers("hms\\\\src\\\\com\\\\hms\\\\database\\\\userlogindb.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        UserManager um = new UserManager();
        
        ArrayList<User> UM = new ArrayList<User>();
        UM = um.getUsersList();

        assertEquals(UsersList, UM);
    }

    //testing getUserFromUsername
    public static void main(String[] args) {
        UserManager um = new UserManager();
        User u = um.getUserFromUsername("jonas1");

        u.printUserDetails();


    }

    public static void test3(String[] args) {
        UserManager um = new UserManager();

        um.printSubUsers("Pharmacist");

    }

    //testing get File
    public static void test1(String[] args) {
        TextDB textdb = new TextDB();
        String filename = "hms\\src\\com\\hms\\database\\userlogindb.txt";
        ArrayList<User> userArray = new ArrayList<User>();
        try {
            userArray = textdb.readUsers(filename);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        // Iterator i = userArray.iterator();
        // User user = new User();

        // while(i.hasNext()) {
        //     user = (User)i.next();
        //     user.printUserDetails();
        // } 

        UserManager um = new UserManager();
        
        ArrayList<User> UM = new ArrayList<User>();
        UM = um.getUsersList();

        System.out.println(UM.get(1).getName());

    }
}
