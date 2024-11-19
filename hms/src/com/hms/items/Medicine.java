package com.hms.items;

public class Medicine {
	private static int totalMedicine = 0;
	private static final int idPrefix = 2010000000;
	private String medname;
	private int medId;
	private int stock;
	private int lowStock; // threshold
	private double price;  


	public Medicine(String medname, int stock, int lowStock, double price) {	
		this.medname = medname;
		this.medId = idPrefix + ++totalMedicine;
		this.stock = stock;
		this.lowStock = lowStock;
		this.price = price;
		totalMedicine++;
	}

	public Medicine(String medname, int medId, int stock, int lowStock, double price) {	
		this.medname = medname;
		this.medId = medId;
		this.stock = stock;
		this.lowStock = lowStock;
		this.price = price;
		totalMedicine++;
	}


	public String getMedname() {
		return medname;
	}

	
	public int getMed_id() {
		return medId;
	}


	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	public int getLowstock() {
		return lowStock;
	}

	public void setLowstock(int lowStock) {
		this.lowStock = lowStock;
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

