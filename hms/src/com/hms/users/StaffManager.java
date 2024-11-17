package com.hms.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.hms.readwrite.*;

public class StaffManager {

    private String filename = "hms/src/com/hms/database/userlogindb.txt";
    private ArrayList<User> userList;
    private TextDB textDB;

  
    public StaffManager() {
        userList = new ArrayList<>();
        textDB = new TextDB();

       
        try {
            userList = textDB.readUsers(filename);
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }
	
	public User staffInfo()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
		System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter date of birth (YYYY-MM-DD): ");
        String dob = sc.nextLine();
        System.out.println("Enter gender: ");
        User.Gender gender = User.Gender.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Enter blood type (e.g., OPLUS): ");
        User.BloodType bloodType = User.BloodType.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        User user1 = new User(id, name, dob, gender, bloodType, username, password);
        return user1;
		
	}
	public void addStaff(User user)
	{
		userList.add(user);
		if (user!=null)
		{
			System.out.println("User added successfully");
		}
		else
		System.out.println("Error in obtaining staff info");
	}
	public void removeStaff(int id)
	{
		for (User user: userList)
		{
			if(user.getID()==id)
			{
				userList.remove(user);
				System.out.println("Staff removed successfully");
			}
		}
		System.out.println("Staff not found and removal was unsuccessful");
	}
	
	public void updateStaff(int id)
	{
		User user1=null;
		for (User user : userList) {
	        if (user.getID() == id) {
	            user1 = user;
	            break;
	        }
	    }
		
		Scanner sc=new Scanner(System.in);
		System.out.println("What information do you want to update?");
		System.out.println("1. Name ");
        System.out.println("2. Date of birth (YYYY-MM-DD)");
        System.out.println("3. Gender ");
        System.out.println("4. Blood type");
        System.out.println("5. User name");
        System.out.println("6. Password");
        int choice=sc.nextInt();
        sc.nextLine();
        
        switch(choice)
        {
        case 1:
            System.out.println("Enter name: ");
            String name = sc.nextLine();
            user1.setName(name);
            System.out.println("Name updated");
            break;

        case 2:
            System.out.println("Enter date of birth (YYYY-MM-DD): ");
            String dob = sc.nextLine();
            user1.setDateOfBirth(dob);
            System.out.println("Date of birth updated successfully.");
            break;

        case 3:
            System.out.println("Enter gender (MALE/FEMALE): ");
            String gender = sc.nextLine().toUpperCase();
            user1.setGender(gender);
            System.out.println("Gender updated");
           
            break;

        case 4:
            System.out.println("Enter blood type (e.g OPLUS): ");
            String bloodType = sc.nextLine().toUpperCase();
            user1.setBloodType(bloodType);
            System.out.println("Blood type updated");
            break;

        case 5:
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            user1.setUsername(username);
            System.out.println("Username updated");
            break;

        case 6:
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            user1.setPassword(password);
            System.out.println("Password updated");
            break;
        	
        }
       
	}

	public void displayStaff(int filter,Scanner sc)
	
	{
		
		if (filter==1) //role
		{
			
			System.out.println("Choose the role you want to filter by:  Doctor / Pharamcist/Administrator");
			String role = sc.nextLine().toUpperCase();
			for (User user : userList) {
				if (user.getRole()==role)
				{
					System.out.println(user);			
			    }
			}
		}
		else if (filter ==2) //gender
		{
			
			System.out.println("Filter by Female/Male");
			String gender = sc.nextLine().toUpperCase();
			for (User user : userList) {
				if (user.getGender().toString()==gender)
				{
					System.out.println(user);			
			    }
			}
		}
		else //age
		{
			System.out.println("Enter age you would like to filter by: ");
			int targetage=sc.nextInt();
			sc.nextLine();
			int currentyear=2024;
			
			for (User user : userList) {
				int birthyear = user.getDateOfBirth().getYear(); 
				int age=currentyear-birthyear;
				if (age==targetage)
				{
					System.out.println(user);
				}
			}
			
		}
			
	}
}
