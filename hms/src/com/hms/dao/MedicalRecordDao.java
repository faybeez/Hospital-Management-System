package com.hms.dao;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.hms.items.MedicalRecord;

public class MedicalRecordDao extends ItemDao implements Dao<MedicalRecord> {

    
    /** 
     * @param filename file to read from
     * @return Map<Integer, MedicalRecord> read data
     * @throws IOException
     */
    @Override
    public Map<Integer, MedicalRecord> read(String filename) throws IOException {
        // read String from text file
        File myFile = new File(filename);
        Scanner sc = new Scanner(myFile);
        Map<Integer, MedicalRecord> mdMap = new HashMap<>();// to store user data

        while (sc.hasNextLine()) {
            MedicalRecord md;
            int i;
            ArrayList<Integer> appt = new ArrayList<Integer>();
            ArrayList<String> diagnoses = new ArrayList<String>();
            ArrayList<String> treatments = new ArrayList<String>();
            String st = sc.nextLine();
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
                                                                       // using delimiter ","

            int id = Integer.parseInt(star.nextToken().trim()); // getID
            String name = star.nextToken().trim();
            String dateOfBirth = star.nextToken().trim();
            String gender = star.nextToken().trim();
            String bloodType = star.nextToken().trim();
            String email = star.nextToken().trim();
            String phoneNum = star.nextToken().trim();
            i = Integer.parseInt(star.nextToken().trim());

            for (; i > 0; i--) {
                diagnoses.add(star.nextToken().trim());
                treatments.add(star.nextToken().trim());
            }

            i = Integer.parseInt(star.nextToken().trim());

            for (; i > 0; i--) {
                appt.add(Integer.parseInt(star.nextToken().trim()));
            }

            // create new user --> depending on first 3 numbers of id
            // depends on id config
            md = new MedicalRecord(id, name, dateOfBirth, gender, bloodType, phoneNum, email, appt, diagnoses, treatments);

            // add to medical array map
            mdMap.put(id, md);
        }

        sc.close();
        return mdMap;
    }

    
    /** 
     * @param filename file to save to
     * @param all data to save
     * @throws IOException
     */
    @Override
    public void save(String filename, Collection<MedicalRecord> all) throws IOException {
        Iterator<MedicalRecord> i = all.iterator();// iterator
        List<String> alw = new ArrayList<>();
        ArrayList<Integer> aptID = new ArrayList<Integer>();
        ArrayList<String> diagnoses = new ArrayList<String>();
        ArrayList<String> treatments = new ArrayList<String>();
        while (i.hasNext()) {
            MedicalRecord mr = i.next();

            StringBuilder st = new StringBuilder();
            st.append(String.valueOf(mr.getID()).trim()); // id
            st.append(SEPARATOR);
            st.append(mr.getName().trim()); // name
            st.append(SEPARATOR);
            st.append(mr.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim()); // date of birth
            st.append(SEPARATOR);
            st.append(mr.getGender().toString().trim()); // gender
            st.append(SEPARATOR);
            st.append(mr.getBloodType().toString().trim()); // blood type
            st.append(SEPARATOR);
            st.append(mr.getEmailAddress().toString().trim()); // username
            st.append(SEPARATOR);
            st.append(mr.getContactNumber().toString().trim()); // password
            st.append(SEPARATOR);
            diagnoses = mr.getDiagnoses();
            treatments = mr.getTreatmentPlans();
            st.append(Integer.toString(diagnoses.size()).trim());
            for (int j = diagnoses.size(); j > 0; j--) {
                st.append(SEPARATOR);
                st.append(diagnoses.remove(j - 1));
                st.append(SEPARATOR);
                st.append(treatments.remove(j - 1));
            }
            st.append(SEPARATOR);
            aptID = mr.getAppointmentIDs();
            st.append(Integer.toString(aptID.size()).trim()); // appt id size

            for (int j = aptID.size(); j > 0; j--) {
                st.append(SEPARATOR);
                st.append(aptID.remove(j - 1));
            }

            alw.add(st.toString());
        }
        write(filename, alw);
    }

}
