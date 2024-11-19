package com.hms;

import com.hms.items.AppointmentManager;
import com.hms.items.Inventory;
import com.hms.items.MedicalRecordManager;
import com.hms.items.SchedulerManager;
import com.hms.users.User;
import com.hms.users.UserActions;
import com.hms.users.UserManager;
import com.hms.users.Patient;
import com.hms.users.PatientActions;
import com.hms.users.Pharmacist;
import com.hms.users.PharmacistActions;
import com.hms.users.Administrator;
import com.hms.users.AdministratorActions;
import com.hms.users.Doctor;
import com.hms.users.DoctorActions;

import java.util.Scanner;

public class App {    
    /** 
     * main function of the code
     * @param args
     * 
     */

    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        MedicalRecordManager medicalrecordmanager = new MedicalRecordManager();
        SchedulerManager schedulermanager = new SchedulerManager(usermanager);
        Inventory inventory = new Inventory();
        AppSystem appSystem = new AppSystem();

        ItemsService itemsService = new ItemsService(apptmanager, medicalrecordmanager, inventory, usermanager);
        UserActions userActions = null;

            
        int c;
        User u = null;
        Boolean cont = false;

        try {
            u = appSystem.Login(usermanager);
        } catch (Exception e) {
            System.err.println("Login failed: " + e);
            return;
        }
        
        
        switch (u.getDesignation()) {
            case "Patient":
                userActions = new PatientActions((Patient)u, itemsService);
                break;
            case "Doctor":
                userActions = new DoctorActions((Doctor)u, itemsService);
                break;
            case "Pharmacist":
                userActions = new PharmacistActions((Pharmacist)u, itemsService);
                break;
            case "Administrator":
                userActions = new AdministratorActions((Administrator)u, itemsService);
                break;
            default:
                break;
        }

        if(userActions == null) {
            System.err.println("User not associated with a role!");
            sc.close();
            return;
        }


        while(!cont) {
            userActions.printActions();
            c = sc.nextInt();
            sc.nextLine();
            cont = userActions.executeAction(c);
        }

        //logout actions
        usermanager.saveUsers();
        apptmanager.saveAppts();
        medicalrecordmanager.saveMedicalRecords();
        schedulermanager.saveSchedules();
        inventory.saveInventory();
    }
}

