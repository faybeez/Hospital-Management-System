package com.hms.users;

import com.hms.ItemsService;

public class AdministratorActions implements UserActions {
    Pharmacist p;
    ItemsService itemsService;
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

    }

    void viewAllUsers() {

    }

    void viewAppointmentDetails() {

    }
    
    void manageMedicineInventory() {

    }

    void manageReplenishmentRequest() {
        
    }
}
