package com.hms;

import com.hms.enums.AppointmentStatus;
import com.hms.items.AppointmentManager;
import com.hms.items.Appointment;
import com.hms.items.Inventory;
import com.hms.items.MedicalRecordManager;
import com.hms.users.UserManager;

import java.util.ArrayList;

public class ItemsService {
    private UserManager userManager;
    private AppointmentManager apptManager;
    private MedicalRecordManager medicalRecordManager;
    private Inventory inventory;

    public ItemsService(AppointmentManager apptManager, MedicalRecordManager medicalRecordManager, Inventory inventory, UserManager userManager) {
        this.apptManager = apptManager;
        this.medicalRecordManager = medicalRecordManager;
        this.userManager = userManager;
        this.inventory = inventory;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public boolean checkIfMedicineExists(String medName) {
        return inventory.checkIfMedicineExists(medName);
    }

    public ArrayList<Appointment> getDoctorAppts(int id, AppointmentStatus s) {
        return apptManager.getDoctorAppts(id, s);
    }

    public void printAppts(ArrayList<Appointment> a) {
        apptManager.printAppts(a, this);
    }

    public String getName(int id) {
        return userManager.getName(id);
    }

}
