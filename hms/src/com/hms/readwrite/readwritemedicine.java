package com.hms.readwrite;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import com.hms.items.Medicine;

/*
public class readwritemedicine {
   public static final String SEPARATOR = "|";

   public ArrayList<Medicine> medList(String filename) throws IOException {
      File myFile = new File(filename);
      Scanner sc = new Scanner(myFile);

      ArrayList<Medicine> medList = new ArrayList<>();

      while(sc.hasNextLine()) {
       
         String line = sc.nextLine();
         StringTokenizer star = new StringTokenizer(line, SEPARATOR);
         
         String medname = star.nextToken().trim();
         int med_id = Integer.parseInt(star.nextToken().trim());
         int stock = Integer.parseInt(star.nextToken().trim());
         int lowstock = Integer.parseInt(star.nextToken().trim());
         
         Medicine medicine=new Medicine(medname,med_id,stock,lowstock);
         medList.add(medicine);
       
   
      }

      sc.close();
      return medList;
   }

 
}
   */
