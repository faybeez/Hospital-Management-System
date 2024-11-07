package com.hms.items;
import java.util.ArrayList;
import java.util.Iterator;
public class Prescription {
    public static int prescriptionNumber = 0;
    private static final int prescriptionIdentifier = 2000000000;
    private int prescriptionID;
    private ArrayList<String> medicineList = new ArrayList<String>();
    private ArrayList<Integer> prescriptionAmount = new ArrayList<Integer>();
    private ArrayList<String> prescriptionNotes = new ArrayList<String>();

    public Prescription() {
        prescriptionID = prescriptionNumber + prescriptionIdentifier;
        prescriptionNumber ++;
    }

    public void addMedicine(String medicine, int amount, String notes) {
        medicineList.add(medicine);
        prescriptionAmount.add(amount);
        prescriptionNotes.add(notes);
    }
    public void deleteMedicine(int index) {
        System.out.printf("Removing %s from the prescription...", medicineList.get(index - 1));
        medicineList.remove(index - 1);
        prescriptionAmount.remove(index - 1);
        prescriptionNotes.remove(index - 1);
    }

    public void readPrescription() {
        Iterator<String> medIterator = medicineList.iterator();
        Iterator<Integer> pAmtIterator = prescriptionAmount.iterator();
        Iterator<String> pNotesIterator = prescriptionNotes.iterator();

        //String out = new String();
        
        while(medIterator.hasNext()) {
            System.out.printf("%nMedicine: %s%nAmount Prescripted: %d%nNotes: %s%n", medIterator.next(), pAmtIterator.next(), pNotesIterator.next());
        }
    }
}
