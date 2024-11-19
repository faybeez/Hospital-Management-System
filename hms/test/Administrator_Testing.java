package test;
import com.hms.items.*;
import java.util.Scanner;

public class Administrator_Testing {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in); 
        //StaffManager staffManager = new StaffManager();
        Inventory inventory = new Inventory();

        do {
            System.out.println("1. Manage hospital staff");
            System.out.println("2. Display a list of staff");
            System.out.println("3. View medication inventory");
            System.out.println("4. Manage medication inventory");
            System.out.println("5. Approve replenishment requests");
            System.out.println("6. View all appointments");
            System.out.println("7. See current tasks");
            System.out.println("8. Log out");
            System.out.println("");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Managing hospital staff:");
                    System.out.println("1. Add staff member");
                    System.out.println("2. Update staff member");
                    System.out.println("3. Remove staff member");
                    System.out.print("Enter your choice: ");
                    int secondChoice = sc.nextInt();

                    if (secondChoice == 1) {
                        //User user1 = staffManager.staffInfo();
                        //staffManager.addStaff(user1);
                    } else if (secondChoice == 2) {
                        System.out.print("Enter the ID: ");
                        int id = sc.nextInt();
                        //staffManager.updateStaff(id);
                    } else if (secondChoice == 3) {
                        System.out.print("Enter the ID: ");
                        int id = sc.nextInt();
                        //staffManager.removeStaff(id);
                    }
                    break;
                case 2:
                    System.out.println("Filter by 1. Role, 2. Gender, 3. Age");
                    int filter = sc.nextInt();
                    //staffManager.displayStaff(filter,sc);
                    break;
                case 3:
                    inventory.displayMedications();
                    break;
                case 4:
                    // manage medication inventory
                    System.out.println("Enter your choice: ");
                    System.out.println("1. Add new medication: ");
                    System.out.println("2. Remove an existing medicine ");
                    System.out.println("3. Update initial stocks ");
                    System.out.println("4. Update low stock level quantity ");
                    int secondChoice1 = sc.nextInt();

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

                    break;
                case 5:
                    inventory.displayRequests();
                    System.out.println("What med ID do you want to replenish?");
                    int med_ID = sc.nextInt();
                    inventory.approveRequest(med_ID);
                    break;
                case 6:
                    // appointments
              
                    break;
                    
                case 7:
                    // put method call for tasks
              
                    break;
                case 8:
                    System.out.println("Logging out..");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
        
    }
}
