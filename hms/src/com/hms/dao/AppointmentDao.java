package com.hms.dao;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.hms.items.Appointment;
import com.hms.items.Prescription;
import com.hms.enums.*;

public class AppointmentDao extends ItemDao implements Dao<Appointment> {
    
    /** 
     * gets appointment information from file for program use
     * @param filename 
     * @return Map<Integer, Appointment>
     * @throws IOException
     */
    @Override
    public Map<Integer, Appointment> read(String filename) throws IOException {
        // read String from text file
        File myFile = new File(filename);
        Scanner sc = new Scanner(myFile);
        Map<Integer, Appointment> apptMap = new HashMap<>();// to store appt data
        Prescription p = new Prescription();
        Appointment a;
        int i;
        String temp;

        while (sc.hasNextLine()) {
            p = new Prescription();
            String st = sc.nextLine();
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
                                                                       // using delimiter ","
            int id = Integer.parseInt(star.nextToken().trim()); // getID
            AppointmentStatus status = AppointmentStatus.valueOf(star.nextToken().trim());
            LocalDate date = LocalDate.parse(star.nextToken().trim());
            LocalTime time = LocalTime.parse(star.nextToken().trim());
            int pID = Integer.parseInt(star.nextToken().trim());
            int dID = Integer.parseInt(star.nextToken().trim());
            temp = star.nextToken().trim();
            String typeOfService = (temp.compareTo("`") == 0) ? null : temp;

            temp = star.nextToken().trim();
            String diagnosis = (temp.compareTo("`") == 0) ? null : temp;
            temp = star.nextToken().trim();
            String treatment = (temp.compareTo("`") == 0) ? null : temp;
            temp = star.nextToken().trim();
            String consultNotes = (temp.compareTo("`") == 0) ? null : temp;
            i = Integer.parseInt(star.nextToken().trim());
            String medName, notes;
            int medAmt;

            for (; i > 0; i--) {
                // System.out.println("230");
                medName = star.nextToken().trim();
                medAmt = Integer.parseInt(star.nextToken().trim());
                notes = star.nextToken().trim();
                // System.out.println("234");
                p.addMedicine(medName, medAmt, notes);
            }
            // System.out.println("235");
            p.setPrescriptionStatus(PrescriptionStatus.valueOf(star.nextToken().trim()));
            // System.out.println("240");
            a = new Appointment();
            a.setID(id);
            a.setConsultNotes(consultNotes);
            a.setDoctorID(dID);
            a.setPatientID(pID);
            // System.out.println("246");
            a.setDate(date);
            a.setTime(time);
            a.setPrescription(p);
            a.setDiagnosis(diagnosis);
            a.setTreatment(treatment);
            a.setStatus(status);
            a.setTypeOfService(typeOfService);
            // System.out.println("254");
            apptMap.put(id, a);
        }
        // System.out.println("252");

        sc.close();
        return apptMap;
    }

    
    /** 
     * saves appointment information to file
     * @param filename
     * @param all
     * @throws IOException
     */
    @Override
    public void save(String filename, Collection<Appointment> all) throws IOException {
        Iterator<Appointment> i = all.iterator();// iterator
        String temp;
        List<String> alw = new ArrayList<>();
        while (i.hasNext()) {
            Appointment a = i.next();
            Iterator<String> sI = a.getPrescription().savePrescription().iterator();
            StringBuilder st = new StringBuilder();
            st.append(String.valueOf(a.getAppointmentID()).trim()); // id
            st.append(SEPARATOR);
            st.append(a.getStatus().toString().trim()); // name
            st.append(SEPARATOR);
            st.append(a.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim()); // date of birth
            st.append(SEPARATOR);
            st.append(a.getTime().format(DateTimeFormatter.ofPattern("HH:mm")).trim()); // date of birth
            st.append(SEPARATOR);
            st.append(Integer.toString(a.getPatientID()).trim()); // patient ID
            st.append(SEPARATOR);
            st.append(Integer.toString(a.getDoctorID()).trim());
            st.append(SEPARATOR);
            temp = (a.getTypeofService() == null) ? "`" : a.getTypeofService();
            st.append(temp.trim());
            st.append(SEPARATOR);
            temp = (a.getDiagnosis() == null) ? "`" : a.getDiagnosis();
            st.append(temp.trim());
            st.append(SEPARATOR);
            temp = (a.getTreatment() == null) ? "`" : a.getTreatment();
            st.append(temp.trim());
            st.append(SEPARATOR);
            temp = (a.getConsultNotes() == null) ? "`" : a.getConsultNotes();
            st.append(temp.trim());
            int j = a.getPrescription().getMedicineList().size();
            st.append(SEPARATOR);
            st.append(Integer.toString(j).trim());

            for (; j > 0; j--) {
                st.append(SEPARATOR);
                st.append(sI.next());
            }

            st.append(SEPARATOR);
            st.append(a.getPrescription().getPrescriptionStatus());

            alw.add(st.toString());
        }
        write(filename, alw);
    }

}
