package com.hms.items;

import java.io.IOException;



import java.util.ArrayList;
import java.util.Iterator;

import com.hms.App;
import com.hms.items.Medicine;
import com.hms.*;
import com.hms.readwrite.*;

import java.util.Scanner;


public class Inventory {
	private ArrayList<Medicine> medicineList;
	private ArrayList<Replenishment> replenishment;
	//private Map<Integer, Medicine> medicineList; use this << (integer is the medicine id)
	//private Map<Integer, Replenishment> replenishmentRequests (integer is medicine id that is requested);


	public Inventory()
	{
		TextDB reader = new TextDB();

		try {
            medicineList = reader.readMedicines(App.medicineDB);
        } catch (Exception e) {
            System.out.println("Inventory Manager " + e);
        }

		try {
            replenishment = reader.readReplenishments(App.replenishmentDB);
        } catch (Exception e) {
            System.out.println("Inventory Manager " + e);
        }
		
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

	public boolean checkIfMedicineExists(String medName) {
		for (Medicine m : medicineList) {
			if(m.getMedname().equalsIgnoreCase(medName)) {
				return true;
			}
		}
		return false;
	}

	public void checkstock(String medName)
	{
		for (Medicine medicine:medicineList)
		{
			if(medicine.getMedname().equalsIgnoreCase(medName))
			{
				System.out.println(medicine.getMedname()+ " stock:  " + medicine.getStock());
				return;
			}
		}
		System.out.println("Medicine does not exist");
	}

	public boolean checkIfStockEnough(String medName, int amount) {
		for (Medicine medicine:medicineList)
			{
				if(medicine.getMedname().equalsIgnoreCase(medName))
				{
					if(amount > medicine.getStock())
					{
						System.out.println( medName + " has insufficient stocks");
						return false;
					}
					System.out.println( medName + " has sufficient stocks");
					return true;
				}
			}
		System.out.println("Unsuccessful");
		return false;
	} 
	
	public boolean reducestock(int medID,int amount) //success return true
	{
		for (Medicine medicine:medicineList)
			{
				if(medicine.getMed_id()==medID)
				{
					if(amount>medicine.getStock())
					{
						System.out.println("insufficient stocks");
						return false;
					}
					int stock=medicine.getStock()-amount;
					medicine.setStock(stock);
					System.out.println( medicine.getMedname()+ " current stock after reducing:  " + medicine.getStock());
				
				
					return true;
				}
			}
		System.out.println("Unsuccessful");
		return false;
	}

	public boolean reduceStockFromName(String medName,int amount)
	{
		for (Medicine medicine:medicineList)
			{
				if(medicine.getMedname().equalsIgnoreCase(medName))
				{
					if(amount>medicine.getStock())
					{
						System.out.println("insufficient stocks");
						return false;
					}
					int stock=medicine.getStock()-amount;
					medicine.setStock(stock);
					System.out.println( medicine.getMedname()+ " current stock after reducing:  " + medicine.getStock());
				
				
					return true;
				}
			}
			System.out.println("Unsuccessful");
			return false;
	}
	
	
	public void displayMedications() {
			System.out.println("Medication Inventory:");
			for (Medicine medicine : medicineList) {
				System.out.println("ID: " + medicine.getMed_id() + " Name:  " + medicine.getMedname() + " Stock:  " + medicine.getStock());
			}
		
	}
	public void addMedicine(Scanner sc) {
		

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
	
	public void submitRequest(int medID, int quantity) {
		for (Medicine medicine : medicineList) {
			if (medicine.getMed_id() == medID) {
				String status = "Pending";
				Replenishment newRequest = new Replenishment(medID, quantity,status);
				replenishment.add(newRequest); 
				System.out.println("Request submitted for Medicine ID: " + medID);
				return; 
			}
		}
		System.out.println("Medicine does not exist. ");
	}

	public void displayRequests() {	
	int success=0;
	System.out.println("Pending Replenishment Requests:");
		for (Replenishment request : replenishment) {
			
			if (request.getStatus().equals("Pending")) {
				System.out.println("Medicine ID: " + request.getMedID() + 
									", Replenishment Quantity: " + request.getQuantity() );
				success=1;
				
			}
		}

		
		if (success==0)
		{
			System.out.println("No pending requests");
		}
	}

	
	public void approveRequest(int medID) {
		
		for (Replenishment request : replenishment) {

			if (request.getMedID() == medID && request.getStatus().equals("Pending")) {
				
				for (Medicine medicine : medicineList) {

					if (medicine.getMed_id() == medID) {
						
						int updatedStock = medicine.getStock() + request.getQuantity();
						medicine.setStock(updatedStock);
						request.setStatus("Approved");

						System.out.println("Updated stock for medicine ID: " + medID);
						return; 
					}
				}

				System.out.println("Medicine does not exist");
				return;
			}
		}

	
		System.out.println("Process failed");
	}
	public void saveInventory() {
	    try {
	        TextDB writer = new TextDB();
	        // save medicines
	        writer.saveMedicines(App.medicineDB, medicineList);
	        
	        // save replenishment
	        writer.saveReplenishments(App.replenishmentDB, replenishment);
	    } catch (IOException e) {
	        System.out.println("Error: " + e);
	    }
	}
	
}
