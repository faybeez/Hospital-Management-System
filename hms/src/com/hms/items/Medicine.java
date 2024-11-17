package com.hms.items;

public class Medicine {
	private String medname;
	private int med_id;
	private int stock;
	private int lowstock; // threshold
	private double price;  


	public Medicine(String medname, int med_id, int stock, int lowstock, double price) {	
		this.medname = medname;
		this.med_id = med_id;
		this.stock = stock;
		this.lowstock = lowstock;
		this.price = price;
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
		this.stock = stock;
	}

	
	public int getLowstock() {
		return lowstock;
	}

	public void setLowstock(int lowstock) {
		this.lowstock = lowstock;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void decreaseStock(int dec) {
		this.stock = stock - dec;
	}
}

