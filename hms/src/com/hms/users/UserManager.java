package com.hms.users;
import java.util.ArrayList;

import com.hms.readwrite.TextDB;

public class UserManager {
    private static ArrayList<User> UsersList;
    private static final int stripPrefix = 100;

    static {
        UsersList = new ArrayList<User>();
        TextDB reader = new TextDB();

        try {
            UsersList = reader.readUsers("../database/userlogindb.txt");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String getName(int id) {
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
}
