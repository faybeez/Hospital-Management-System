package com.hms;

import java.io.IOException;
import java.util.ArrayList;

import com.hms.items.Medicine;
import com.hms.readwrite.readwritemedicine;


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
	
	 // havent add application outcome record
	 //havent add submit replenishment request
}
