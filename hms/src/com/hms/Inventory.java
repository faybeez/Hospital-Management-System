package com.hms;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import com.hms.Medicine;
import com.hms.readwrite.readwritemedicine;
import java.util.Scanner;

public class Inventory {
	 private ArrayList<Medicine> medicineList;
	 private String filename = "hms/src/com/hms/database/medicinedb.txt" ;
	 
	 public Inventory()
	 {
		 medicineList = new ArrayList<>();
	 }
	
	 
	 public void medicineslist () throws IOException
	 {
		 readwritemedicine rwmed=new readwritemedicine();
		 medicineList=rwmed.medList(filename);
	 }
	 
	 public void checkstock(int medID)
	 {
		 for (Medicine medicine:medicineList)
		 {
			 if(medicine.getMed_id()==medID)
			 {
				 System.out.println( medicine.getMedname()+ " stock:  " + medicine.getStock());
				 return;
			 }
		 }
		 System.out.println("Medicine does not exist");
	 }
	 
	 public void reducestock(int medID,int amount)
		{
			for (Medicine medicine:medicineList)
			 {
				 if(medicine.getMed_id()==medID)
				 {
					 if(amount>medicine.getStock())
					 {
						 System.out.println("insufficient stocks");
						 return;
					 }
					 int stock=medicine.getStock()-amount;
					 medicine.setStock(stock);
					 System.out.println( medicine.getMedname()+ " current stock after reducing:  " + medicine.getStock());
					
					
					 return;
				 }
			 }
			 System.out.println("Unsuccessful");
		}
	 
	 
	 public void displayMedications() {
		        System.out.println("Medication Inventory:");
		        for (Medicine medicine : medicineList) {
		            System.out.println("ID: " + medicine.getMed_id() + " Name:  " + medicine.getMedname() + " Stock:  " + medicine.getStock());
		        }
		    
		}
	 public void addMedicine() {
		    Scanner sc = new Scanner(System.in);

		    System.out.println("Enter the medicine ID: ");
		    int medId = sc.nextInt();  
		    sc.nextLine(); 

		    System.out.println("Enter the medicine name: ");
		    String medName = sc.nextLine();  

		    System.out.println("Enter the initial stock quantity: ");
		    int stock = sc.nextInt();  

		    System.out.println("Enter the low stock quantity: ");
		    int lowStock = sc.nextInt();  

		    System.out.println("Enter the price of the medicine: ");
		    double price = sc.nextDouble();

		    Medicine newMed = new Medicine(medName, medId, stock, lowStock, price);

		    medicineList.add(newMed);

		    System.out.println("New medicine added successfully");

		    sc.close();
		}
	 public void removeMedicine(int medID)
	 
	 {
		 Iterator<Medicine> iterator = medicineList.iterator();
		 while (iterator.hasNext()) {
		        Medicine medicine = iterator.next();
		        if (medicine.getMed_id() == medID) {
		            iterator.remove();  
		            System.out.println("Medicine removed successfully");
		            return;
		        }
		    }

		 System.out.println("Medicine does not exist");
	 }

	 public void updateInitialStock(int medID, int stock) {
		    for (Medicine medicine : medicineList) {
		        if (medicine.getMed_id() == medID) {
		            medicine.setStock(stock); 
		            System.out.println("Stock updated successfully");
		            return;
		        }
		    }
		    System.out.println("Medicine does not exist");
		}
	 
	 public void updateLowStock(int medID, int lowstock) {
		    for (Medicine medicine : medicineList) {
		        if (medicine.getMed_id() == medID) {
		            medicine.setLowstock(lowstock); 
		            System.out.println("Low Stock level updated successfully");
		            return;
		        }
		    }
		    System.out.println("Medicine does not exist");
		}
	
	 // havent add application outcome record
	 //havent add submit replenishment request
}
