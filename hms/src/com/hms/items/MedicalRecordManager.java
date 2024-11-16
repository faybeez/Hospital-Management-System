package com.hms.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map;

import com.hms.readwrite.TextDB;

public class MedicalRecordManager {
    //private ArrayList<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();
    private Map<Integer, MedicalRecord> medicalRecordList = new HashMap<>();
    private static final int stripPrefix = 100;

    public MedicalRecordManager() {
        TextDB reader = new TextDB();
        try {
            this.medicalRecordList = reader.readMedicalRecord("hms\\src\\com\\hms\\database\\medicalrecorddb.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public Map<Integer, MedicalRecord> getMedRecordList() {
        return this.medicalRecordList;
    }

    public MedicalRecord getMedicalRecordofPatient(int id) {
        return medicalRecordList.get(id);
    }
}
