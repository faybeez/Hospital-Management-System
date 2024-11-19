package com.hms.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;
import com.hms.enums.BloodType;
import com.hms.enums.Gender;
import com.hms.items.Appointment;
import com.hms.items.Medicine;
import com.hms.App;

public class AdministratorActions implements UserActions {
    Administrator a;
    ItemsService itemsService;

    public AdministratorActions(Administrator a, ItemsService itemsService) {
        this.a = a;
        this.itemsService = itemsService;
    }

    @Override
    public void printActions() {
        System.out.println("What would you like to do?");
        System.out.println("1. Manage Hospital Staff");
        System.out.println("2. View all users");
        System.out.println("3. View Appointment Details");
        System.out.println("4. View and Manage Medicine Inventory"); 
        System.out.println("5. Approve Replenish Request");
        System.out.println("6. Log out");
        return;
    }

    @Override
    public boolean executeAction(int i) throws UnsupportedOperationException {
        if(i < 1 || i > 6) {
            throw new UnsupportedOperationException("Unknown action");
        }
        switch(i) {
            case 1:
                manageHospitalStaff();
                break;
            case 2:
                viewAllUsers();
                break;
            case 3:
                viewAppointmentDetails();
                break;
            case 4:
                manageMedicineInventory();
                break;
            case 5:
                manageReplenishmentRequest();
                break;
            case 6:
                return true;
        }
        return false;
    }

    void manageHospitalStaff() {
        try {
            System.out.println("1. Add staff member");
            System.out.println("2. Update staff member");
            System.out.println("3. Remove staff member");
            System.out.print("Enter your choice: ");
            int secondChoice = App.sc.nextInt();
            App.sc.nextLine();
            User u;

            if (secondChoice == 1) {
                u = createUser();
                if (u != null) {
                    itemsService.addUser(u);
                }
            } else if (secondChoice == 2) {
                System.out.print("Using ID (1) or Name (2): ");
                int choice3 = App.sc.nextInt();
                App.sc.nextLine();
                switch(choice3) {
                    case 1:
                        System.out.print("Enter the ID: ");
                        int id = App.sc.nextInt();
                        App.sc.nextLine();
                        u  = itemsService.getUserFromID(id);
                        updateUser(u);
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        String n = App.sc.nextLine();
                        u = itemsService.getUserFromName(n);
                        updateUser(u);
                        break;
                }
                
            } else if (secondChoice == 3) {
                System.out.print("Using ID (1) or Name (2): ");
                int choice3 = App.sc.nextInt();
                App.sc.nextLine();
                switch(choice3) {
                    case 1:
                        System.out.print("Enter the ID: ");
                        int id = App.sc.nextInt();
                        App.sc.nextLine();
                        u  = itemsService.getUserFromID(id);
                        u.printUserDetails();
                        System.out.print("Are you sure you want to delete this user? This action is irreversible! (Y/N)");
                        if(App.sc.nextLine().toLowerCase().compareTo("y") == 0) {
                            deleteUser(u);
                        }
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        String n = App.sc.nextLine();
                        u = itemsService.getUserFromName(n);
                        System.out.print("Are you sure you want to delete this user? This action is irreversible! (Y/N)");
                        if(App.sc.nextLine().toLowerCase().compareTo("y") == 0) {
                            deleteUser(u);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Manage Hospital Staff Error " + e);
        } finally {

        }
    }

    void viewAllUsers() {
        try {
            System.out.println("What would you like to filter by? (age, gender, role)");
            String attribute = App.sc.nextLine();
            String t = "", value = "";
            
            switch(attribute) {
                case "role":
                    System.out.println("Choose the role you want to filter by:  Doctor / Pharamcist / Administrator");
                    value = App.sc.nextLine();
                    
                    break;
                case "age":
                    System.out.println("Choose one option to filter age:");
                    System.out.println("1. More than (>)");
                    System.out.println("2. Less than (<)");
                    System.out.println("3. More than or equals to (>=)");
                    System.out.println("4. Less than or equals to (<=)");
                    int temp = App.sc.nextInt();
                    App.sc.nextLine();

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
                    value = App.sc.nextLine();

                    break;
                case "gender":
                    System.out.println("Write the gender to filter by: (case sensitive - Male/Female)");
                    value = App.sc.nextLine();
                    break;
            }

            List<User> filteredusers = itemsService.filterUser(attribute, value, t);
            int i = 1;
            for(User user1 : filteredusers) {
                System.out.println(i++ + ". " + user1.getDesignation() + " " + user1.getName());
                //System.out.println("Age: " + user1.getAge());
            }
        } catch (Exception e) {
            System.err.println("View all users error " + e);
        } finally {

        }
    }

    void viewAppointmentDetails() {
        try {
            System.out.println("Enter your choice: ");
            System.out.println("1. View all appointments: ");
            System.out.println("2. View appointments of a doctor");
            System.out.println("3. View appointments of a patient");
            System.out.println("4. View appointments based on status (Pending, Confirmed, Completed, Cancelled)");
            int choice = App.sc.nextInt();
            App.sc.nextLine();
            String temp;
            int id;
            ArrayList<Appointment> appts;

            switch (choice) {
                case 1:
                    itemsService.printAppts(itemsService.getAllAppointments());
                    break;
                case 2:
                    System.out.println("Printing all doctors...");
                    itemsService.printSubUsers("Doctor");
                    System.out.println("Enter name of doctor: (eg. bob)");
                    temp = App.sc.nextLine();

                    id = itemsService.getUserFromName(temp).getID();

                    appts = itemsService.getDoctorAppts(id, null);
                    itemsService.printAppts(appts);
                    break;
                case 3:
                    System.out.println("Printing all patients...");
                    itemsService.printSubUsers("Patient");
                    System.out.println("Enter name of patient: (eg. bob)");
                    temp = App.sc.nextLine();

                    id = itemsService.getUserFromName(temp).getID();

                    appts = itemsService.getPatientAppts(id, null);
                    itemsService.printAppts(appts);
                case 4:
                    System.out.println("Which status would you like to check (Pending, Confirmed, Completed, Cancelled):");
                    temp = App.sc.nextLine();

                    itemsService.printAppts(itemsService.getAllAppointmentsFromStatus(AppointmentStatus.valueOf(temp)));
                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println("View Appointment details error: " + e.getMessage());
        } finally {

        }
    }
    
    void manageMedicineInventory() {
        
        try {
            System.out.println("Enter your choice: ");
            System.out.println("1. Add new medication: ");
            System.out.println("2. Remove an existing medicine ");
            System.out.println("3. Update initial stocks ");
            System.out.println("4. Update low stock level quantity ");
            System.out.println("5. View Inventory ");
            int secondChoice1 = App.sc.nextInt();
            App.sc.nextLine();

            if (secondChoice1 == 1) {
                addMedicine();
            } else if (secondChoice1 == 2) {
                System.out.println("Enter the id of the medicine you want to remove:");
                int med_id = App.sc.nextInt();
                App.sc.nextLine();
                itemsService.removeMedicine(med_id);
            } else if (secondChoice1 == 3) {
                System.out.println("Enter the medicine ID: ");
                int id = App.sc.nextInt();
                App.sc.nextLine();
                System.out.println("Update the new initial stock: ");
                int stock = App.sc.nextInt();
                App.sc.nextLine();
                itemsService.updateInitialStock(id, stock);
            } else if (secondChoice1 == 4) {
                System.out.println("Enter the medicine ID: ");
                int id = App.sc.nextInt();
                App.sc.nextLine();
                System.out.println("Update the new low stock: ");
                int lowstock = App.sc.nextInt();
                App.sc.nextLine();
                itemsService.updateLowStock(id, lowstock); 
            } else if (secondChoice1 == 5) {
                itemsService.viewInventory();
            }
        } catch (Exception e) {
            System.err.println("Manage medicine inventory error: " + e);
        } finally{
        }
    }

    void manageReplenishmentRequest() {
        
        try {
            itemsService.displayRequests();
            if(itemsService.getReplenishmentRequests().isEmpty()) {
                System.out.println("No replenishment requests. Exiting...");
                return;
            }
            System.out.println("What med ID do you want to replenish?");
            int med_ID = App.sc.nextInt();
            itemsService.approveRequest(med_ID);
        } catch (Exception e) {
            System.err.println("Manage replenishment request error: " + e);
        } finally {
            ;
        }
        
    }

    void addMedicine() {

        System.out.println("Enter the medicine ID (2010000001 onwards): ");
        int medId = App.sc.nextInt();  
        App.sc.nextLine(); 

        if(itemsService.checkIfMedicineIDExists(medId)) {
            throw new IllegalArgumentException("Medicine ID exists! ID must be unique.");
        }
        else if(medId < 2010000001) {
            throw new IllegalArgumentException("Medicine ID is invalid! ID must be 10 numbers with 201 as the prefix.");
        }

        System.out.println("Enter the medicine name: ");
        String medName = App.sc.nextLine();  

        if(itemsService.checkIfMedicineExists(medName)) {
            throw new IllegalArgumentException("Medicine already exists! Medicine name must be unique.");
        }

        System.out.println("Enter the initial stock quantity: ");
        int stock = App.sc.nextInt();  
        App.sc.nextLine();

        System.out.println("Enter the low stock quantity: ");
        int lowStock = App.sc.nextInt();  
        App.sc.nextLine();

        if(lowStock < 0) {
            throw new IllegalArgumentException("Low stock threshold must not be under 0.");
        }

        System.out.println("Enter the price of the medicine: ");
        double price = App.sc.nextDouble();
        App.sc.nextLine();

        if(price <= 0) {
            throw new IllegalArgumentException("Price must not be 0 and under.");
        }

        Medicine newMed = new Medicine(medName, medId, stock, lowStock, price);

        itemsService.addMedicine(newMed);
    }

    public void deleteUser(User u) {
        u.setName("DELETED");
        u.setBloodType(null);
        u.setDateOfBirth(null);
        u.setGender(null);
        u.setUsername(null);
        u.setPassword(null);
    }

    public User createUser() throws Exception {
        
        try {
            String[] d = {"doctor", "patient", "administrator", "pharmacist"}; 
            Boolean check = false;
            Boolean usernameCheck = true;
            System.out.println("Enter Designation (Doctor, Patient, Administrator, Pharmacist): ");
            String designation = App.sc.nextLine();
            for(String s : d) {
                if(designation.toLowerCase().equals(s)) {
                    check = true;
                    break;
                }
            }

            if(!check) {
                System.out.println("Designation " + designation + " is not allowed. Exiting...");
                return null;
            }

            System.out.println("Enter name: ");
            String name = App.sc.nextLine();
            System.out.println("Enter date of birth (YYYY-MM-DD): ");
            String dob = App.sc.nextLine();
            System.out.println("Enter gender: ");
            Gender gender = Gender.valueOf(App.sc.nextLine().toUpperCase());
            System.out.println("Enter blood type (e.g., OPLUS): ");
            BloodType bloodType = BloodType.valueOf(App.sc.nextLine().toUpperCase());
            System.out.println("Enter username: ");
            String username = App.sc.nextLine();

            try {
                itemsService.getUserFromUsername(username);
            } catch (Exception e) {
                usernameCheck = false;
            }

            if(usernameCheck) {
                System.out.println("Username must be unique! Exiting...");
                return null;
            }

            System.out.println("Enter password: ");
            String password = App.sc.nextLine();

            User u;
            switch(designation.toLowerCase()) {
                case "doctor":
                    u = new Doctor(name, dob, gender, bloodType, username, password);
                    
                    return u;
                case "patient":
                    u = new Patient(name, dob, gender, bloodType, username, password);
                    itemsService.createMedicalRecordForNewPatient((Patient)u);
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
        } catch (Exception e) {
            throw e;
        } finally {
            
        }
    }

    public void updateUser(User u) {
        
        try {
            System.out.println("What information do you want to update?");
            System.out.println("1. Name ");
            System.out.println("2. Date of birth (YYYY-MM-DD)");
            System.out.println("3. Gender ");
            System.out.println("4. Blood type");
            System.out.println("5. User name");
            System.out.println("6. Password");
            int choice=App.sc.nextInt();
            App.sc.nextLine();
            
            switch(choice)
            {
            case 1:
                System.out.println("Enter name: ");
                String name = App.sc.nextLine();
                u.setName(name);
                System.out.println("Name updated");
                break;

            case 2:
                System.out.println("Enter date of birth (YYYY-MM-DD): ");
                String dob = App.sc.nextLine();
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
                Gender gender = Gender.getByValue(App.sc.nextLine());
                u.setGender(gender);
                System.out.println("Gender updated");
                break;

            case 4:
                System.out.println("Enter blood type (O+, O-, A+, A-...): ");
                BloodType bloodType = BloodType.getByValue(App.sc.nextLine());
                u.setBloodType(bloodType);
                System.out.println("Blood type updated");
                break;

            case 5:
                System.out.println("Enter username: ");
                String username = App.sc.nextLine();
                
                try {
                    itemsService.getUserFromUsername(username);
                } catch (Exception e) {
                    u.setUsername(username);
                    System.out.println("Username updated");
                    break;
                }
                System.out.println("Username must be unique! Exiting...");
                break;
            case 6:
                System.out.println("Enter password: ");
                String password = App.sc.nextLine();
                u.setPassword(password);
                System.out.println("Password updated");
                break;
            default:
                System.out.println("Nothing changed. Exiting...");
                break;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ;
        }
    }

}
