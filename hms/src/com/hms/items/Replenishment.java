package com.hms.items;

public class Replenishment {
    private int medID;
    private int replenishQuantity;
    private String status;

    public Replenishment(int medID, int quantity,String status) {
        this.medID = medID;
        this.replenishQuantity = quantity;
        this.status = "Pending";
    }

  
	public int getMedID() {
        return medID;
    }

    public int getQuantity() {
        return replenishQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


	
}
