package com.hms.users;

import java.time.LocalDate;
import java.util.Scanner;

public class Administrator extends User{
    private static final int idPrefix = 1000000000;
    
    public Administrator(String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(name, dateOfBirth, gender, bloodType, userName, password);
        int i = idPrefix + userCount;
        super.setID(i);
    }

    public Administrator(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }

    public void manageHospitalStaff(UserManager usermanager, Scanner sc) {
        System.out.println("1. Add staff member");
        System.out.println("2. Update staff member");
        System.out.println("3. Remove staff member");
        System.out.println("4. View all staff members ");
        System.out.print("Enter your choice: ");
        int secondChoice = sc.nextInt();
        sc.nextLine();
        User u;

        if (secondChoice == 1) {
            u = createUser(sc);
            usermanager.addUser(u);
        } else if (secondChoice == 2) {
            System.out.print("Using ID (1) or Name (2): ");
            int choice3 = sc.nextInt();
            sc.nextLine();
            switch(choice3) {
                case 1:
                    System.out.print("Enter the ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    u  = usermanager.getUserFromID(id);
                    updateUser(u, sc);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String n = sc.nextLine();
                    staffManager.updateStaff(id);
            }
            
        } else if (secondChoice == 3) {
            System.out.print("Enter the ID: ");
            int id = sc.nextInt();
            staffManager.removeStaff(id);
        }
    }

    public User createUser(Scanner sc) {
        String[] d = {"doctor", "patient", "administrator", "pharmacist"}; 
        Boolean check = false;
        System.out.println("Enter Designation (Doctor, Patient, Administrator, Pharmacist): ");
        String designation = sc.nextLine();
        for(String s : d) {
            if(designation.toLowerCase() == s) {
                check = true;
                break;
            }
        }

        if(!check) {
            System.out.println("Designation " + designation + " is not allowed. Exiting...");
            return null;
        }

		System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter date of birth (YYYY-MM-DD): ");
        String dob = sc.nextLine();
        System.out.println("Enter gender: ");
        Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Enter blood type (e.g., OPLUS): ");
        BloodType bloodType = BloodType.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        User u;
        switch(designation.toLowerCase()) {
            case "doctor":
                u = new Doctor(name, dob, gender, bloodType, username, password);
                return u;
            case "patient":
                u = new Patient(name, dob, gender, bloodType, username, password);
                return u; 
            case "administrator":
                u = new Administrator(name, dob, gender, bloodType, username, password);
                return u;
            case "pharmacist":
                u = new Administrator(name, dob, gender, bloodType, username, password);
                return u;  
            default:
                return null;
        }
    }

    public void updateUser(User u, UserManager usermanager, Scanner sc) {
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
            u.setName(name);
            System.out.println("Name updated");
            break;

        case 2:
            System.out.println("Enter date of birth (YYYY-MM-DD): ");
            String dob = sc.nextLine();
            LocalDate d;
            try {
                d = LocalDate.parse(dob);
            } catch (Exception e) {
                System.out.println("Date of birth entered is invalid! Exiting...");
                break;
            }
            u.setDateOfBirth(d);
            System.out.println("Date of birth updated successfully.");
            break;

        case 3:
            System.out.println("Enter gender (MALE/FEMALE): ");
            Gender gender = Gender.getByValue(sc.nextLine());
            u.setGender(gender);
            System.out.println("Gender updated");
            break;

        case 4:
            System.out.println("Enter blood type (O+, O-, A+, A-...): ");
            BloodType bloodType = BloodType.getByValue(sc.nextLine());
            u.setBloodType(bloodType);
            System.out.println("Blood type updated");
            break;

        case 5:
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            
            try {
                usermanager.getUserFromUsername(username);
            } catch (Exception e) {
                u.setUsername(username);
                System.out.println("Username updated");
                break;
            }
            System.out.println("Username must be unique! Exiting...");
            break;
        case 6:
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            u.setPassword(password);
            System.out.println("Password updated");
            break;
        default:
            System.out.println("Nothing changed. Exiting...");
            break;
        }
    }
}
