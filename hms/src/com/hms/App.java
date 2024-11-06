package com.hms;

import java.util.Scanner;
import java.util.ArrayList;

import com.hms.readwrite.TextDB;
import com.hms.users.User;
import com.hms.users.User.BloodType;
import com.hms.users.User.Gender;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

       // User test = new User(1001, sc.nextLine(), sc.nextLine(), Gender.FEMALE, BloodType.ABMINUS, sc.nextLine(), sc.nextLine());
        //test.printUserDetails();
        //System.out.println("test!");

        TextDB textdb = new TextDB();
        
        String filename = "hms\\src\\com\\hms\\database\\userlogindb.txt";
        ArrayList<User> userArray = new ArrayList<User>();
        userArray = textdb.readUsers(filename);
        User user = userArray.get(0);

        user.printUserDetails();

        sc.close();
    }
}
