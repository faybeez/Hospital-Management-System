package com.hms;

public class Medicine {
	private String medname;
	private int med_id;
	private int stock;
	private int lowstock;
	
	public Medicine(String medname, int med_id, int stock, int lowstock)
	{	
		this.medname=medname;
		this.med_id=med_id;
		this.stock=stock;
		this.lowstock=lowstock;
	}
	
	
	
	public String getMedname() {
		return medname;
	}
	
	
	public int getMed_id() {
		return med_id;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock=stock;
	}
	public int getLowstock() {
		return lowstock;
	}
	
	
	
	

}
