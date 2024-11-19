package com.hms.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hms.ItemsService;
import com.hms.dao.AppointmentDao;
import com.hms.dao.Dao;
import com.hms.enums.AppointmentStatus;

public class AppointmentManager {
    private Map<Integer, Appointment> Appts = new HashMap<>();
    //doctorID -> pending apptIDs
    private Map<Integer, ArrayList<Integer>> DoctorAppt = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> PatientAppt = new HashMap<>();
    private static final String apptDB = "hms/resources/appointmentdb.txt";

    public AppointmentManager() {
        Dao<Appointment> reader = new AppointmentDao();
        Appointment a;
        int dID, pID, aID;
        ArrayList<Integer> apptIDs;

        try {
            Appts = reader.read(apptDB);
        } catch (Exception e) {
            System.out.println("Appt Manager " + e);
        }
        
        Iterator<Appointment> i = Appts.values().iterator();

        while(i.hasNext()) {
            a = i.next();
            dID = a.getDoctorID();
            pID = a.getPatientID();
            aID = a.getAppointmentID();
            apptIDs = new ArrayList<Integer>();

            //checking if key has been used
            if(DoctorAppt.containsKey(dID)) {
                DoctorAppt.get(dID).add(aID);
            }
            else{
                apptIDs.add(aID);
                DoctorAppt.put(dID, apptIDs);
            }
            apptIDs = new ArrayList<Integer>();
            //same for patient list
            if(PatientAppt.containsKey(pID)) {
                PatientAppt.get(pID).add(aID);
            }
            else{
                apptIDs.add(aID);
                PatientAppt.put(pID, apptIDs);
            }
        }
    }

    
    /** 
     * @return Map<Integer, Appointment>
     */
    public Map<Integer, Appointment> getApptMap() {
        return Appts;
    }

    
    /** 
     * @return Map<Integer, ArrayList<Integer>>
     */
    public Map<Integer, ArrayList<Integer>> getPatientMap() {
        return PatientAppt;
    }

    
    /** 
     * @return Map<Integer, ArrayList<Integer>>
     */
    public Map<Integer, ArrayList<Integer>> getDoctorMap() {
        return DoctorAppt;
    }

    public Appointment getAppointmentFromID(int aID) {
        return Appts.get(aID);
    }

    public void addAppointment(Appointment a) {
        Appts.put(a.getAppointmentID(), a);
        //add to doctor map
        if(DoctorAppt.containsKey(a.getDoctorID())) {
            DoctorAppt.get(a.getDoctorID()).add(a.getAppointmentID());
        }
        else{
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(a.getAppointmentID());
            DoctorAppt.put(a.getDoctorID(),temp);
        }
        //add to patient map
        if(PatientAppt.containsKey(a.getPatientID())) {
            PatientAppt.get(a.getPatientID()).add(a.getAppointmentID());
        }
        else{
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(a.getAppointmentID());
            PatientAppt.put(a.getPatientID(),temp);
        }
    }
    

    public void deleteAppointment(int id) {
        if(Appts.remove(id) == null) {
            System.out.println("Appointment invalid! Please try again.");
        }
        else{
            int dID = Appts.get(id).getDoctorID();
            int pID = Appts.get(id).getPatientID();
            DoctorAppt.get(dID).remove(id);
            PatientAppt.get(pID).remove(id);
            System.out.println("Appointment deleted.");
        }
    }

    
    /** 
     * @param id
     */
    @Deprecated
    public void PrintAppointmentsFromPatientID(int id) {
        Iterator<Appointment> apptIterator = Appts.values().iterator();
        Appointment temp;

        while(apptIterator.hasNext()) {
            temp = apptIterator.next();

            if(temp.getPatientID() == id) {
                //temp.printAppointmentDetails();
            }
        }
    }

    @Deprecated
    public ArrayList<Appointment> getPatientAppointments(int id, AppointmentStatus s){
        Iterator<Appointment> apptIterator = Appts.values().iterator();
        ArrayList<Appointment> apt = new ArrayList<Appointment>();
        Appointment temp;

        while(apptIterator.hasNext()) {
            temp = apptIterator.next();

            if(temp.getPatientID() == id && temp.getStatus() == s) {
                apt.add(temp);
            }
        }
        return apt;
    }

    @Deprecated
    public void PrintAppointmentsFromDoctorID(int id) {
        Iterator<Appointment> apptIterator = Appts.values().iterator();
        Appointment temp;

        while(apptIterator.hasNext()) {
            temp = apptIterator.next();
            
            if(temp.getDoctorID() == id) {
                //temp.printAppointmentDetails();
            }
        }
    }

    public ArrayList<Appointment> getDoctorAppts(int id, AppointmentStatus s) throws Exception {
        if(!(DoctorAppt.containsKey(id))) {
            throw new Exception("Doctor has no appointments listed!");
        }
        Iterator<Integer> temp = DoctorAppt.get(id).iterator();
        ArrayList<Appointment> appts = new ArrayList<Appointment>();
        int a;
        if(s == null) {
            while(temp.hasNext()) {
                a = temp.next();
                appts.add(Appts.get(a));
            }
        }
        else {
            while(temp.hasNext()) {
                a = temp.next();
                if(Appts.get(a).getStatus() == s) {
                    appts.add(Appts.get(a));
                }
            }
    
        }

        return appts;
    }

    public ArrayList<Appointment> getAllAppointmentsFromStatus(AppointmentStatus s) {
        Iterator<Appointment> i = Appts.values().iterator();
        ArrayList<Appointment> temp = new ArrayList<Appointment>();
        Appointment a;
        while(i.hasNext()) {
            a = i.next();
            if(a.getStatus() == s) {
                temp.add(a);
            }
        }
        return temp;
    }

    public ArrayList<Appointment> getPatientAppts(int id, AppointmentStatus s) throws Exception {
        if(!(PatientAppt.containsKey(id))) {
            throw new Exception("Patient has no appointments listed!");
        }
        Iterator<Integer> temp = PatientAppt.get(id).iterator();
        ArrayList<Appointment> appts = new ArrayList<Appointment>();
        int a;
        if(s == null) {
            while(temp.hasNext()) {
                a = temp.next();
                appts.add(Appts.get(a));
            }
        }
        else {
            while(temp.hasNext()) {
                a = temp.next();
                if(Appts.get(a).getStatus() == s) {
                    appts.add(Appts.get(a));
                }
            }
    
        }
        
        return appts;
    }

    public void printAppts(ArrayList<Appointment> a, ItemsService itemsService) {
        Iterator<Appointment> i = a.iterator();
        int j = 1;
        while(i.hasNext()) {
            System.out.printf("%n------------Appointment %d------------%n", j++);
            i.next().printAppointmentDetails(itemsService, false);
        }
    }

    public void saveAppts() {
        Dao<Appointment> writer = new AppointmentDao();
        
        try {
            writer.save(apptDB, Appts.values());
        } catch (Exception e) {
            System.out.println("Appointment Manager " + e);
        }

        
    }
}
