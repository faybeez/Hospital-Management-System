package com.hms.users;

import java.io.IOException;
import java.util.Scanner;

import com.hms.items.Inventory;

public class Pharmacist_testing {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in); 
        Inventory inventory = new Inventory();
        
        try {
            inventory.medicineslist();
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View medication inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. View all Tasks");
            System.out.println("6. Log out");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // yet to call
                    break;
                case 2:
                    System.out.print("Enter the medicine ID to reduce stock: ");
                    int medId = sc.nextInt();
                    System.out.print("Enter the amount to reduce: ");
                    int amount = sc.nextInt();
                    inventory.reducestock(medId, amount);
                    break;
                case 3:
                    inventory.displayMedications();
                    break;
                case 4:
                	System.out.println("Submit Replenishment Request");
                    System.out.print("Enter the medicine ID to reduce stock: ");
                    int medId1 = sc.nextInt();
                    System.out.print("Enter the amount to replenish: ");
                    int quantity = sc.nextInt();
                    inventory.submitRequest(medId1, quantity);
                    break;
                case 5:
                    // yet to implement tasks
                    break;
                case 6:
                    System.out.println("Logging out..");
                    break;
                default:
                    System.out.println(" Please enter a valid choice");
            }
        } while (choice != 6);
        
        sc.close();
    }
}

