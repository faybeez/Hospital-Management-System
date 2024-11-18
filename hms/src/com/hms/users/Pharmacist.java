package com.hms.users;

import com.hms.items.AppointmentManager;
import com.hms.items.Prescription;
import com.hms.items.Appointment.Status;
import com.hms.items.Prescription.PrescriptionStatus;
import com.hms.items.Appointment;
import com.hms.items.Inventory;

import java.util.Scanner;


import java.util.ArrayList;

public class Pharmacist extends User {
    
    private static final int idPrefix = 1030000000;

    public Pharmacist(String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(name, dateOfBirth, gender, bloodType, userName, password);
        int i = idPrefix + userCount;
        super.setID(i);
    }

    public Pharmacist(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }

    public void viewAppointmentOutcomeRecords(AppointmentManager apptmanager, UserManager usermanager, Scanner sc) {
        //TODO different people?
        ArrayList<Appointment> completedAppts = apptmanager.getAllAppointmentsFromStatus(Status.Completed);

        apptmanager.printAppts(completedAppts, usermanager);
    }

    public void changePrescriptionStatus(AppointmentManager apptmanager, UserManager usermanager, Scanner sc) {
        ArrayList<Appointment> completedAppts = apptmanager.getAllAppointmentsFromStatus(Status.Completed);

        apptmanager.printAppts(completedAppts, usermanager);

        System.out.println("Which prescription would you like to change the status of?");
        //TODO error checking?
        int choice = sc.nextInt();
        sc.nextLine();

        Appointment a = completedAppts.get(choice - 1);
        System.out.println("Current status of prescription: " + a.getPrescription().getPrescriptionStatus());
        System.out.println("What would you like to switch it to?");
        System.out.println("1. Uncollected");
        System.out.println("2. Collected");
        
        choice = sc.nextInt();
        sc.nextLine();

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
        return;
    }

    public void dispenseMedicine(UserManager usermanager, AppointmentManager apptmanager, Inventory inventory, Scanner sc) {
        System.out.println("Enter name of patient:");
        String name = sc.nextLine();
        User u;

        try {
            u = usermanager.getUserFromName(name);
        } catch (Exception e) {
            System.out.println("Name of patient doesn't exist! Exiting...");
            return;
        }

        ArrayList<Appointment> appts = apptmanager.getPatientAppts(u.getID(), Status.Completed);
        apptmanager.printAppts(appts, usermanager);
        System.out.println("Which prescription would you like to dispense?");
        int choice = sc.nextInt();
        sc.nextLine();

        Appointment a = appts.get(choice - 1);
        Prescription p = a.getPrescription();

        Boolean success = true;

        for(int i = 0; i < p.getMedicineList().size(); i++) {
            success = success && inventory.checkIfStockEnough(p.getMedicineFromList(i), p.getMedAmtFromList(i));
        }

        if (success) {
            System.out.println("All medicine have the required stocks. Dispensing now...");
            for(int i = 0; i < p.getMedicineList().size(); i++) {
                inventory.checkIfStockEnough(p.getMedicineFromList(i), p.getMedAmtFromList(i));
            }

            p.setPrescriptionStatus(PrescriptionStatus.Collected);
            return;
        }
        else {
            System.out.println("Not enough stock to dispense the medicine. Submit a replenishment request and try again later.");
            return;
        }

        
    }
    
    public void submitRepenishmentRequest(Inventory inventory, Scanner sc) {
        System.out.println("Submit Replenishment Request");
        System.out.print("Enter the medicine ID to reduce stock: ");
        int medId1 = sc.nextInt();
        System.out.print("Enter the amount to replenish: ");
        int quantity = sc.nextInt();
        inventory.submitRequest(medId1, quantity);
    }
}
