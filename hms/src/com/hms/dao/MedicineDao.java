package com.hms.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.hms.items.Medicine;

public class MedicineDao extends ItemDao implements Dao<Medicine> {
    @Override
    public Map<Integer, Medicine> read(String filename) throws IOException {
    File myFile = new File(filename);
    Scanner sc = new Scanner(myFile);
    Map<Integer, Medicine> medList = new HashMap<>();

    while (sc.hasNextLine()) {
        String line = sc.nextLine();
        StringTokenizer star = new StringTokenizer(line, SEPARATOR);

        String medname = star.nextToken().trim();
        int med_id = Integer.parseInt(star.nextToken().trim());
        int stock = Integer.parseInt(star.nextToken().trim());
        int lowstock = Integer.parseInt(star.nextToken().trim());
        double price = Double.parseDouble(star.nextToken().trim());

        Medicine medicine = new Medicine(medname, med_id, stock, lowstock, price);
        medList.put(med_id, medicine);
    }

    sc.close();
    return medList;
}

@Override
public void save(String filename, Collection<Medicine> medicineList) throws IOException {
    List<String> alw = new ArrayList<>();

    for (Medicine medicine : medicineList) {
        StringBuilder st = new StringBuilder();
        st.append(medicine.getMedname().trim());
        st.append(SEPARATOR);
        st.append(String.valueOf(medicine.getMed_id()).trim());
        st.append(SEPARATOR);
        st.append(String.valueOf(medicine.getStock()).trim());
        st.append(SEPARATOR);
        st.append(String.valueOf(medicine.getLowstock()).trim());
        st.append(SEPARATOR);
        st.append(String.valueOf(medicine.getPrice()).trim());

        alw.add(st.toString());
    }

    write(filename, alw);
}

}
