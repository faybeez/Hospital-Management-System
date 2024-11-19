package com.hms.users;

import java.util.ArrayList;


import com.hms.ItemsService;
import com.hms.enums.AppointmentStatus;
import com.hms.enums.PrescriptionStatus;
import com.hms.items.Appointment;
import com.hms.items.Prescription;
import com.hms.App;

public class PharmacistActions implements UserActions {
    Pharmacist p;
    ItemsService itemsService;

    public PharmacistActions(Pharmacist p, ItemsService itemsService) {
        this.p = p;
        this.itemsService = itemsService;
    }

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
        try {
            ArrayList<Appointment> completedAppts = itemsService.getAllAppointmentsFromStatus(AppointmentStatus.Completed);
            itemsService.printAppts(completedAppts);
        } catch (Exception e) {
            System.err.println("View appointment outcome error " + e);
        }
    }

    void updatePrescriptionStatus(){
        try {
            ArrayList<Appointment> completedAppts = itemsService.getAllAppointmentsFromStatus(AppointmentStatus.Completed);

            itemsService.printAppts(completedAppts);

            System.out.println("Which prescription would you like to change the status of?");

            int choice = App.sc.nextInt();
            App.sc.nextLine();

            if(choice < 1 || choice > completedAppts.size()) {
                throw new ArrayIndexOutOfBoundsException("Prescription Index out of bounds!");
            }

            Appointment a = completedAppts.get(choice - 1);
            System.out.println("Current status of prescription: " + a.getPrescription().getPrescriptionStatus());
            System.out.println("What would you like to switch it to?");
            System.out.println("1. Uncollected");
            System.out.println("2. Collected");
            
            choice = App.sc.nextInt();
            App.sc.nextLine();

            switch (choice) {
                case 1:
                    a.getPrescription().setPrescriptionStatus(PrescriptionStatus.Uncollected);
                    System.out.println("Status of prescription changed!");
                    break;
            
                default:
                    a.getPrescription().setPrescriptionStatus(PrescriptionStatus.Collected);
                    System.out.println("Status of prescription changed!");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Update prescription error " + e);
        } finally {
        }
    }

    void dispenseMedicineFromPrescription() {
        try {
            System.out.println("Enter name of patient:");
            String name = App.sc.nextLine();
            User u;

            u = itemsService.getUserFromName(name);

            ArrayList<Appointment> appts = itemsService.getPatientAppts(u.getID(), AppointmentStatus.Completed);
            itemsService.printAppts(appts);
            System.out.println("Which prescription would you like to dispense?");
            int choice = App.sc.nextInt();
            App.sc.nextLine();

            Appointment a = appts.get(choice - 1);
            Prescription p = a.getPrescription();

            Boolean success = true;

            for(int i = 0; i < p.getMedicineList().size(); i++) {
                success = success && itemsService.checkIfStockEnough(p.getMedicineFromList(i), p.getMedAmtFromList(i));
            }

            if (success) {
                System.out.println("All medicine have the required stocks. Dispensing now...");
                for(int i = 0; i < p.getMedicineList().size(); i++) {
                    itemsService.checkIfStockEnough(p.getMedicineFromList(i), p.getMedAmtFromList(i));
                }

                p.setPrescriptionStatus(PrescriptionStatus.Collected);
            }
            else {
                throw new ArithmeticException("Not enough stock to dispense the medicine. Submit a replenishment request and try again later.");
            }
        } catch (Exception e) {
            System.err.println("Dispense medicine error: " + e);
        } finally {
        }
    }

    void viewMedicineInventory() {
        try {
            itemsService.viewInventory();
        } catch (Exception e) {
            System.err.println("View medicine inventory error: " + e);
        }
        
    }

    void submitRepenishmentRequest() {
        try {
            System.out.println("Submit Replenishment Request");
            System.out.print("Enter the medicine ID to reduce stock: ");
            int medId1 = App.sc.nextInt();
            App.sc.nextLine();
            System.out.print("Enter the amount to replenish: ");
            int quantity = App.sc.nextInt();
            App.sc.nextLine();
            itemsService.submitRequest(medId1, quantity);
        } catch (Exception e) {
            System.err.println("Submit replenishment request error: " + e);
        } finally {
        }
    }
}
