package com.hms;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import com.hms.users.User;
import com.hms.users.User.BloodType;
import com.hms.users.User.Gender;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        User test = new User(sc.nextLine(), sc.nextLine(), Gender.FEMALE, BloodType.ABMINUS, sc.nextLine(), sc.nextLine());
        test.printUserDetails();
        System.out.println("test!");

        sc.close();
    }
}
