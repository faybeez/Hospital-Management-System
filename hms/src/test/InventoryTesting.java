package test;

import com.hms.items.Inventory;

public class InventoryTesting {
	public static void main(String[]args)
	{
		Inventory inventory=new Inventory();
		// try {
		// 	inventory.medicineslist();
		// } catch (IOException e) {
		// 	// Auto-generated catch block
		// 	e.printStackTrace();
		// }
		inventory.checkstock(03);
		
		inventory.reducestock(03, 95);
		
		inventory.checkstock(03);
		
	}
	

}
