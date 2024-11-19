package com.hms;

import com.hms.enums.AppointmentStatus;
import com.hms.items.AppointmentManager;
import com.hms.items.Appointment;
import com.hms.items.Inventory;
import com.hms.items.MedicalRecordManager;
import com.hms.users.User;
import com.hms.users.UserManager;
import com.hms.items.MedicalRecord;
import com.hms.items.Medicine;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Appointment> getDoctorAppts(int id, AppointmentStatus s) throws Exception {
        return apptManager.getDoctorAppts(id, s);
    }

    public void printAppts(ArrayList<Appointment> a) {
        apptManager.printAppts(a, this);
    }

    public String getName(int id) {
        return userManager.getName(id);
    }

    public ArrayList<Appointment> getPatientAppts(int id, AppointmentStatus s) {
        return apptManager.getPatientAppts(id, s);
    }

    public MedicalRecord getMedicalRecordofPatient(int id) {
        return medicalRecordManager.getMedicalRecordofPatient(id);
    }

    public void printSubUsers(String type) {
        userManager.printSubUsers(type);
    }

    public User getUserFromName(String name) {
        return userManager.getUserFromName(name);
    }

    public User getUserFromID(int id) {
        return userManager.getUserFromID(id);
    }

    public User getUserFromUsername(String username) {
        return userManager.getUserFromUsername(username);
    }

    public void addAppointment(Appointment a) {
        apptManager.addAppointment(a);
    }

    public void addUser(User u) {
        userManager.addUser(u);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>(apptManager.getApptMap().values());
    }

    public ArrayList<Appointment> getAllAppointmentsFromStatus(AppointmentStatus s) {
        return apptManager.getAllAppointmentsFromStatus(s);
    }
    
    public List<User> filterUser(String attribute, String value, String t) {
        return userManager.filterUser(attribute, value, t);
    }

    public void addMedicine(Medicine m) {
        inventory.addMedicine(m);
    }

    public boolean checkIfMedicineIDExists(int id) {
        return inventory.checkIfMedicineIDExists(id);
    }

    public void removeMedicine(int id) {
        inventory.removeMedicine(id);
    }

    public void updateInitialStock(int medID, int stock) {
        inventory.updateInitialStock(medID, stock);
    }

    public void updateLowStock(int id, int lowstock) {
        inventory.updateLowStock(id, lowstock);
    }

    public void displayRequests() {
        inventory.displayRequests();
    }

    public void approveRequest(int id) {
        inventory.approveRequest(id);
    }

    public boolean checkIfStockEnough(String name, int amount) {
        return inventory.checkIfStockEnough(name, amount);
    }

    public void viewInventory() {
        inventory.displayMedications();
    }

    public void submitRequest(int medID, int quantity){
        inventory.submitRequest(medID, quantity);
    }
}
