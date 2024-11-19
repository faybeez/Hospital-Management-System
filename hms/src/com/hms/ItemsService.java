package com.hms;

import com.hms.enums.AppointmentStatus;
import com.hms.items.AppointmentManager;
import com.hms.items.Appointment;
import com.hms.items.Inventory;
import com.hms.items.MedicalRecordManager;
import com.hms.users.Patient;
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

    
    /** 
     * gets the usermanager
     * @return UserManager
     */
    public UserManager getUserManager() {
        return userManager;
    }

    
    /** 
     * checks if a medicine exists based on its' name
     * @param medName
     * @return boolean
     */
    public boolean checkIfMedicineExists(String medName) {
        return inventory.checkIfMedicineExists(medName);
    }

    
    /** 
     * gets all of a doctor's appointments, can be filtered based on status
     * @param id
     * @param s
     * @return ArrayList<Appointment>
     * @throws Exception
     */
    public ArrayList<Appointment> getDoctorAppts(int id, AppointmentStatus s) throws Exception {
        return apptManager.getDoctorAppts(id, s);
    }

    
    /** 
     * print the appointments of an array
     * @param a
     */
    public void printAppts(ArrayList<Appointment> a) {
        apptManager.printAppts(a, this);
    }

    
    /** 
     * gets a name of a user from its' id
     * @param id
     * @return String
     */
    public String getName(int id) {
        return userManager.getName(id);
    }

    
    /** 
     * gets all of a patient's appointments, can be filtered based on status
     * @param id
     * @param s
     * @return ArrayList<Appointment>
     * @throws Exception
     */
    public ArrayList<Appointment> getPatientAppts(int id, AppointmentStatus s) throws Exception {
        return apptManager.getPatientAppts(id, s);
    }

    
    /** 
     * gets the medical record of a certain patient
     * @param id
     * @return MedicalRecord
     */
    public MedicalRecord getMedicalRecordofPatient(int id) {
        return medicalRecordManager.getMedicalRecordofPatient(id);
    }

    
    /** 
     * prints subusers depending on their role (Doctor, Patient,)
     * @param type
     */
    public void printSubUsers(String type) {
        userManager.printSubUsers(type);
    }

    
    /** 
     * fetches a user from its' name
     * @param name
     * @return User
     */
    public User getUserFromName(String name) {
        return userManager.getUserFromName(name);
    }

    
    /** 
     * fetches a user from its' id
     * @param id
     * @return User
     */
    public User getUserFromID(int id) {
        return userManager.getUserFromID(id);
    }

    
    /** 
     * fetches a user from its' username
     * @param username
     * @return User
     */
    public User getUserFromUsername(String username) {
        return userManager.getUserFromUsername(username);
    }

    
    /** 
     * adds an appointment to the appointment manager
     * @param a
     */
    public void addAppointment(Appointment a) {
        apptManager.addAppointment(a);
    }

    
    /** 
     * adds a user to the usermanager
     * @param u
     */
    public void addUser(User u) {
        userManager.addUser(u);
    }

    
    /** 
     * gets all appointments
     * @return ArrayList<Appointment>
     */
    public ArrayList<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>(apptManager.getApptMap().values());
    }

    
    /** 
     * gets appointment based on its' status
     * @param s
     * @return ArrayList<Appointment>
     */
    public ArrayList<Appointment> getAllAppointmentsFromStatus(AppointmentStatus s) {
        return apptManager.getAllAppointmentsFromStatus(s);
    }
    
    
    /** 
     * filters users according to different attributes
     * @param attribute
     * @param value
     * @param t
     * @return List<User>
     */
    public List<User> filterUser(String attribute, String value, String t) {
        return userManager.filterUser(attribute, value, t);
    }

    
    /** 
     * adds a medicine to inventory
     * @param m
     */
    public void addMedicine(Medicine m) {
        inventory.addMedicine(m);
    }

    
    /** 
     * checks if the medicine ID exists
     * @param id
     * @return boolean
     */
    public boolean checkIfMedicineIDExists(int id) {
        return inventory.checkIfMedicineIDExists(id);
    }

    
    /** 
     * removes a medicine from inventory
     * @param id
     */
    public void removeMedicine(int id) {
        inventory.removeMedicine(id);
    }

    
    /** 
     * updates the initial stock attribute
     * @param medID
     * @param stock
     */
    public void updateInitialStock(int medID, int stock) {
        inventory.updateInitialStock(medID, stock);
    }

    
    /** 
     * updates the lowstock attribute
     * @param id
     * @param lowstock
     */
    public void updateLowStock(int id, int lowstock) {
        inventory.updateLowStock(id, lowstock);
    }

    public void displayRequests() {
        inventory.displayRequests();
    }

    
    /** 
     * approves replenishment request
     * @param id
     */
    public void approveRequest(int id) {
        inventory.approveRequest(id);
    }

    
    /** 
     * checks if a stock of medicine is enough
     * @param name
     * @param amount
     * @return boolean
     */
    public boolean checkIfStockEnough(String name, int amount) {
        return inventory.checkIfStockEnough(name, amount);
    }

    public void viewInventory() {
        inventory.displayMedications();
    }

    
    /** 
     * submit replenishment request
     * @param medID
     * @param quantity
     */
    public void submitRequest(int medID, int quantity){
        inventory.submitRequest(medID, quantity);
    }

    
    /** 
     * creates a medical record for new patients
     * @param p
     * @throws Exception
     */
    public void createMedicalRecordForNewPatient(Patient p) throws Exception{
        MedicalRecord mr = new MedicalRecord(p.getID(), p.getName(), p.getDateOfBirth().toString(), p.getGender(),p.getBloodType());
        medicalRecordManager.addMedicalRecord(mr);
    }
}
