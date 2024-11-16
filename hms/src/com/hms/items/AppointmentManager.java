package com.hms.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hms.App;
import com.hms.items.Appointment.Status;
import com.hms.readwrite.TextDB;
import com.hms.users.UserManager;

public class AppointmentManager {
    private Map<Integer, Appointment> Appts = new HashMap<>();
    //doctorID -> pending apptIDs
    private Map<Integer, ArrayList<Integer>> DoctorAppt = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> PatientAppt = new HashMap<>();

    public AppointmentManager() {
        TextDB reader = new TextDB();
        Appointment a;
        int dID, pID, aID;
        ArrayList<Integer> apptIDs;

        try {
            Appts = reader.readAppointments(App.apptDB);
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

    public Map<Integer, Appointment> getApptMap() {
        return Appts;
    }

    public Map<Integer, ArrayList<Integer>> getPatientMap() {
        return PatientAppt;
    }

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

    // public ArrayList<Appointment> getPatientAppointments(int id, Status s){
    //     Iterator<Appointment> apptIterator = Appts.values().iterator();
    //     ArrayList<Appointment> apt = new ArrayList<Appointment>();
    //     Appointment temp;

    //     while(apptIterator.hasNext()) {
    //         temp = apptIterator.next();

    //         if(temp.getPatientID() == id && temp.getStatus() == s) {
    //             apt.add(temp);
    //         }
    //     }
    //     return apt;
    // }

    // public void PrintAppointmentsFromDoctorID(int id) {
    //     Iterator<Appointment> apptIterator = Appts.values().iterator();
    //     Appointment temp;

    //     while(apptIterator.hasNext()) {
    //         temp = apptIterator.next();
            
    //         if(temp.getDoctorID() == id) {
    //             temp.printAppointmentDetails();
    //         }
    //     }
    // }

    public ArrayList<Appointment> getDoctorAppts(int id, Status s) {
        Iterator<Integer> temp = DoctorAppt.get(id).iterator();
        ArrayList<Appointment> appts = new ArrayList<Appointment>();
        int a;
        while(temp.hasNext()) {
            a = temp.next();
            if(Appts.get(a).getStatus() == s) {
                appts.add(Appts.get(a));
            }
        }

        return appts;
    }

    public ArrayList<Appointment> getPatientAppts(int id, Status s) {
        Iterator<Integer> temp = PatientAppt.get(id).iterator();
        ArrayList<Appointment> appts = new ArrayList<Appointment>();
        int a;
        while(temp.hasNext()) {
            a = temp.next();
            if(Appts.get(a).getStatus() == s) {
                appts.add(Appts.get(a));
            }
        }

        return appts;
    }

    public void printAppts(ArrayList<Appointment> a, UserManager usermanager) {
        Iterator<Appointment> i = a.iterator();
        int j = 1;
        while(i.hasNext()) {
            System.out.printf("%n------------Appointment %d------------%n", j++);
            i.next().printAppointmentDetails(usermanager, false);
        }
    }

    public void saveAppts() {
        TextDB writer = new TextDB();
        
        try {
            writer.saveAppointments(App.apptDB, Appts.values());
        } catch (Exception e) {
            System.out.println("Appointment Manager " + e);
        }

        
    }
}
