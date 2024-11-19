package com.hms.items;

import java.io.IOException;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;

import com.hms.dao.*;

public class Inventory {

	private Map<Integer, Medicine> medicineList;
	private Map<Integer, Replenishment> replenishmentRequests;
	private static final String medicineDB = "hms/resources/medicinedb.txt";
	private static final String replenishmentDB = "hms/resources/replenishmentdb.txt";


	public Inventory()
	{
		Dao<Medicine> medReader = new MedicineDao();
		Dao<Replenishment> repReader = new ReplenishmentDao();

		try {
            medicineList = medReader.read(medicineDB);
        } catch (Exception e) {
            System.out.println("Inventory Manager " + e);
        }

		try {
            replenishmentRequests = repReader.read(replenishmentDB);
        } catch (Exception e) {
            System.out.println("Inventory Manager " + e);
        }
		
	}
	
	public void checkstock(int medID)
	{
		for (Medicine medicine:medicineList.values())
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
		for (Medicine m : medicineList.values()) {
			if(m.getMedname().equalsIgnoreCase(medName)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkIfMedicineIDExists(int id) {
		try {
			medicineList.get(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void checkstock(String medName)
	{
		for (Medicine medicine:medicineList.values())
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
		for (Medicine medicine:medicineList.values())
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
		for (Medicine medicine:medicineList.values())
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
		for (Medicine medicine:medicineList.values())
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
			for (Medicine medicine : medicineList.values()) {
				System.out.println("ID: " + medicine.getMed_id() + " Name:  " + medicine.getMedname() + " Stock:  " + medicine.getStock());
			}
		
	}


	public void addMedicine(Medicine newMed) {
		medicineList.put(newMed.getMed_id(), newMed);

		System.out.println("New medicine added successfully");
	}
	public void removeMedicine(int medID)
	{
		medicineList.remove(medID);
	}

	public void updateInitialStock(int medID, int stock) {
		for (Medicine medicine : medicineList.values()) {
			if (medicine.getMed_id() == medID) {
				medicine.setStock(stock); 
				System.out.println("Stock updated successfully");
				return;
			}
		}
		
		throw new NullPointerException("Medicine not found.");
	}
	
	public void updateLowStock(int medID, int lowstock) {
		for (Medicine medicine : medicineList.values()) {
			if (medicine.getMed_id() == medID) {
				medicine.setLowstock(lowstock); 
				System.out.println("Low Stock level updated successfully");
				return;
			}
		}
		System.out.println("Medicine does not exist");
	}
	
	public void submitRequest(int medID, int quantity) {
		for (Medicine medicine : medicineList.values()) {
			if (medicine.getMed_id() == medID) {
				String status = "Pending";
				Replenishment newRequest = new Replenishment(medID, quantity,status);
				replenishmentRequests.put(medID, newRequest); 
				System.out.println("Request submitted for Medicine ID: " + medID);
				return; 
			}
		}
		System.out.println("Medicine does not exist. ");
	}

	public void displayRequests() {	
	int success=0;
	System.out.println("Pending Replenishment Requests:");
		for (Replenishment request : replenishmentRequests.values()) {
			
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
		
		for (Replenishment request : replenishmentRequests.values()) {

			if (request.getMedID() == medID && request.getStatus().equals("Pending")) {
				
				for (Medicine medicine : medicineList.values()) {

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
	        Dao<Medicine> medwriter = new MedicineDao();
			Dao<Replenishment> replenishmentwriter = new ReplenishmentDao();
	        // save medicines
	        medwriter.save(medicineDB, medicineList.values());
	        
	        // save replenishment
	        replenishmentwriter.save(replenishmentDB, replenishmentRequests.values());
	    } catch (IOException e) {
	        System.out.println("Inventory saving error: " + e);
	    }
	}
	
}
