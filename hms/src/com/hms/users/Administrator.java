package com.hms.users;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

import com.hms.items.AppointmentManager;
import com.hms.items.Inventory;
import com.hms.items.Appointment.Status;
import com.hms.items.Appointment;

import java.util.List;

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
                    updateUser(u, usermanager, sc);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String n = sc.nextLine();
                    u = usermanager.getUserFromName(n);
                    updateUser(u, usermanager, sc);
                    break;
            }
            
        } else if (secondChoice == 3) {
            System.out.print("Using ID (1) or Name (2): ");
            int choice3 = sc.nextInt();
            sc.nextLine();
            switch(choice3) {
                case 1:
                    System.out.print("Enter the ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    u  = usermanager.getUserFromID(id);
                    u.printUserDetails();
                    System.out.print("Are you sure you want to delete this user? This action is irreversible! (Y/N)");
                    if(sc.nextLine().toLowerCase().compareTo("y") == 0) {
                        deleteUser(u);
                    }
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String n = sc.nextLine();
                    u = usermanager.getUserFromName(n);
                    updateUser(u, usermanager, sc);
                    break;
            }
        }
    }

    public void deleteUser(User u) {
        u.setName("DELETED");
        u.setBloodType(null);
        u.setDateOfBirth(null);
        u.setGender(null);
        u.setUsername(null);
        u.setPassword(null);
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

    public void filterUserByAttribute(UserManager usermanager, Scanner sc) {
        System.out.println("What would you like to filter by? (age, gender, role)");
        String attribute = sc.nextLine();
        String t = "", value = "";
        
        switch(attribute) {
            case "role":
                System.out.println("Choose the role you want to filter by:  Doctor / Pharamcist / Administrator");
                value = sc.nextLine();
                
                break;
            case "age":
                System.out.println("Choose one option to filter age:");
                System.out.println("1. More than (>)");
                System.out.println("2. Less than (<)");
                System.out.println("3. More than or equals to (>=)");
                System.out.println("4. Less than or equals to (<=)");
                int temp = sc.nextInt();
                sc.nextLine();

                switch (temp) {
                    case 1:
                        t = ">";
                        break;
                    case 2:
                        t = "<";
                        break;
                    case 3:
                        t = ">=";
                        break;
                    case 4:
                        t = "<=";
                        break;
                }
                System.out.println("Write the age to filter by:");
                value = sc.nextLine();

                break;
            case "gender":
                System.out.println("Write the gender to filter by: (case sensitive - Male/Female)");
                value = sc.nextLine();
                break;
        }

        List<User> filteredusers = usermanager.filterUser(attribute, value, t);
        int i = 1;
        for(User user1 : filteredusers) {
            System.out.println(i++ + ". " + user1.getDesignation() + " " + user1.getName());
            //System.out.println("Age: " + user1.getAge());
        }
    }

    public void manageMedicationInventory(Inventory inventory, Scanner sc) {
        System.out.println("Enter your choice: ");
        System.out.println("1. Add new medication: ");
        System.out.println("2. Remove an existing medicine ");
        System.out.println("3. Update initial stocks ");
        System.out.println("4. Update low stock level quantity ");
        int secondChoice1 = sc.nextInt();
        sc.nextLine();

        if (secondChoice1 == 1) {
            inventory.addMedicine(sc);
        } else if (secondChoice1 == 2) {
            System.out.println("Enter the id of the medicine you want to remove:");
            int med_id = sc.nextInt();
            inventory.removeMedicine(med_id);
        } else if (secondChoice1 == 3) {
            System.out.println("Enter the medicine ID: ");
            int id = sc.nextInt();
            System.out.println("Update the new initial stock: ");
            int stock = sc.nextInt();
            inventory.updateInitialStock(id, stock);
        } else if (secondChoice1 == 4) {
            System.out.println("Enter the medicine ID: ");
            int id = sc.nextInt();
            System.out.println("Update the new low stock: ");
            int lowstock = sc.nextInt();
            inventory.updateLowStock(id, lowstock); 
        }
    }

    public void manageReplenishments(Scanner sc, Inventory inventory) {
        inventory.displayRequests();
        System.out.println("What med ID do you want to replenish?");
        int med_ID = sc.nextInt();
        inventory.approveRequest(med_ID);
    }

    public void viewAppointments(AppointmentManager apptmanager, UserManager usermanager, Scanner sc) {
        System.out.println("Enter your choice: ");
        System.out.println("1. View all appointments: ");
        System.out.println("2. View appointments of a doctor");
        System.out.println("3. View appointments of a patient");
        System.out.println("4. View appointments based on status (Pending, Confirmed, Completed, Cancelled)");
        int choice = sc.nextInt();
        sc.nextLine();
        String temp;
        int id;
        ArrayList<Appointment> appts;

        switch (choice) {
            case 1:
                apptmanager.printAppts(new ArrayList<Appointment>(apptmanager.getApptMap().values()), usermanager);
                break;
            case 2:
                System.out.println("Printing all doctors...");
                usermanager.printSubUsers("Doctor");
                System.out.println("Enter name of doctor: (eg. bob)");
                temp = sc.nextLine();

                id = usermanager.getUserFromName(temp).getID();

                appts = apptmanager.getDoctorAppts(id, null);
                apptmanager.printAppts(appts, usermanager);
                break;
            case 3:
                System.out.println("Printing all patients...");
                usermanager.printSubUsers("Patient");
                System.out.println("Enter name of patient: (eg. bob)");
                temp = sc.nextLine();

                id = usermanager.getUserFromName(temp).getID();

                appts = apptmanager.getPatientAppts(id, null);
                apptmanager.printAppts(appts, usermanager);
            case 4:
                System.out.println("Which status would you like to check (Pending, Confirmed, Completed, Cancelled):");
                temp = sc.nextLine();
                try {
                    apptmanager.printAppts(apptmanager.getAllAppointmentsFromStatus(Status.valueOf((temp))), usermanager);
                } catch (Exception e) {
                    System.out.println("Error! Exiting...");
                }
            default:
                break;
        }
    }
}
