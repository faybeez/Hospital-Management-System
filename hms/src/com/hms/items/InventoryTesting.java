package com.hms.items;
import java.io.IOException;

import com.hms.Inventory;

public class InventoryTesting {
	public static void main(String[]args)
	{
		Inventory inventory=new Inventory();
		try {
			inventory.medicineslist();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inventory.checkstock(03);
		
		inventory.reducestock(03, 95);
		
		inventory.checkstock(03);
		
	}
	

}
