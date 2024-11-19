package com.hms.users;

import com.hms.ItemsService;

public class PharmacistActions implements UserActions {
    Pharmacist p;
    ItemsService itemsService;
    @Override
    public void printActions() {
        System.out.println("What would you like to do?");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Status of Prescription");
        System.out.println("3. Dispense medicine based on prescription");
        System.out.println("4. View Medicine Inventory");
        System.out.println("5. Submit Replenishment Request");
        System.out.println("6. Logout");
        return;
    }

    @Override
    public boolean executeAction(int i) throws UnsupportedOperationException {
        if(i < 1 || i > 6) {
            throw new UnsupportedOperationException("Unknown action");
        }
        switch(i) {
            case 1:
                viewAppointmentOutcomeRecords();
                break;
            case 2:
                updatePrescriptionStatus();
                break;
            case 3:
                dispenseMedicineFromPrescription();
                break;
            case 4:
                viewMedicineInventory();
                break;
            case 5:
                submitRepenishmentRequest();
                break;
            case 6:
                return true;
        }
        return false;
    }

    void viewAppointmentOutcomeRecords(){

    }

    void updatePrescriptionStatus(){

    }

    void dispenseMedicineFromPrescription() {

    }

    void viewMedicineInventory() {

    }

    void submitRepenishmentRequest() {
        
    }
}
