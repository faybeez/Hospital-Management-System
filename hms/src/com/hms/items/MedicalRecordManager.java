package com.hms.items;

import java.util.HashMap;
import java.util.Map;

import com.hms.dao.MedicalRecordDao;
import com.hms.dao.Dao;

public class MedicalRecordManager {
    //private ArrayList<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();
    private Map<Integer, MedicalRecord> medicalRecordList = new HashMap<>();
    private static final String medrecordDB = "hms/resources/medicalrecorddb.txt";
    public MedicalRecordManager() {
        Dao<MedicalRecord> reader = new MedicalRecordDao();
        try {
            this.medicalRecordList = reader.read(medrecordDB);
        } catch (Exception e) {
            System.out.println("Med record manager " + e);
        }
    }
    public Map<Integer, MedicalRecord> getMedRecordList() {
        return this.medicalRecordList;
    }

    public void addMedicalRecord(MedicalRecord m) throws Exception {
        if(medicalRecordList.containsKey(m.getID())) {
            throw new Exception("Patient ID has already been taken!");
        }
        else {
            medicalRecordList.put(m.getID(), m);
        }
    }

    public MedicalRecord getMedicalRecordofPatient(int id) {
        return medicalRecordList.get(id);
    }

    public void saveMedicalRecords() {
        Dao<MedicalRecord> writer = new MedicalRecordDao();
        
        try {
            writer.save(medrecordDB, medicalRecordList.values());
        } catch (Exception e) {
            System.out.println("Medical Record Manager " + e);
        }
    }
}
