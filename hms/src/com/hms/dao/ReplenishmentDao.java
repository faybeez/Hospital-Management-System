package com.hms.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

import com.hms.items.Replenishment;

public class ReplenishmentDao extends ItemDao implements Dao<Replenishment> {
    @Override
    public Map<Integer, Replenishment> read(String filename) throws IOException {
    File myFile = new File(filename);
    Scanner sc = new Scanner(myFile);
    Map<Integer, Replenishment> replenishmentList = new HashMap<>();

    while (sc.hasNextLine()) {
        String line = sc.nextLine();
        StringTokenizer star = new StringTokenizer(line, SEPARATOR);

        int medID = Integer.parseInt(star.nextToken().trim());
        int replenishQuantity = Integer.parseInt(star.nextToken().trim());
        String status = star.nextToken().trim();

        Replenishment replenishment = new Replenishment(medID, replenishQuantity, status);
        replenishmentList.put(medID, replenishment);
    }

    sc.close();
    return replenishmentList;
}

@Override
public void save(String filename, Collection<Replenishment> replenishmentList) throws IOException {
    List<String> alw = new ArrayList<>();

    for (Replenishment replenishment : replenishmentList) {
        StringBuilder st = new StringBuilder();
        st.append(String.valueOf(replenishment.getMedID()).trim());
        st.append(SEPARATOR);
        st.append(String.valueOf(replenishment.getQuantity()).trim());
        st.append(SEPARATOR);
        st.append(replenishment.getStatus().trim());

        alw.add(st.toString());
    }

    write(filename, alw);
}

}
