package com.hms.dao;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.hms.items.Scheduler;

public class ScheduleDao extends ItemDao implements Dao<Scheduler>{

    
    /** 
     * @param filename
     * @return Map<Integer, Scheduler>
     * @throws IOException
     */
    @Override
    public Map<Integer, Scheduler> read(String filename) throws IOException {
    File myFile = new File(filename);
    Scanner sc = new Scanner(myFile);
    Map<Integer, Scheduler> sMap = new HashMap<>();// to store user data

    while (sc.hasNextLine()){
      Scheduler s;
      int[][] sch = new int[7][20];
      String st = sc.nextLine();
      // get individual 'fields' of the string separated by SEPARATOR
      StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
      
      int id = Integer.parseInt(star.nextToken().trim());	// getID
      LocalDate date = LocalDate.parse(star.nextToken().trim());

      for(int i = 0; i < 7; i++) {
        String[] string = star.nextToken().trim().replaceAll("\\[", "").replaceAll("]", "").split(",");
        for(int j = 0; j < 20; j++) {
          sch[i][j] = Integer.valueOf(string[j].trim());
        }
      }
      
      //create new user --> depending on first 3 numbers of id
      //depends on id config
      s = new Scheduler();
      s.setDoctorID(id);
      s.setLastSaved(date);
      s.setSchedule(sch);
      // add to medical array map
      sMap.put(id, s);
    }

    sc.close();
    return sMap;
  }

  /** 
   * @param filename
   * @param all
   * @throws IOException
   */
  @Override
  public void save(String filename, Collection<Scheduler> all) throws IOException {
    Iterator<Scheduler> i = all.iterator();//iterator
    List<String> alw = new ArrayList<>();
        while(i.hasNext()) {
          Scheduler s = i.next();
      
          StringBuilder st = new StringBuilder() ;
          st.append(String.valueOf(s.getDoctorID()).trim()); //id
          st.append(SEPARATOR);
          st.append(s.getLastSaved().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim()); //date of birth
          st.append(SEPARATOR);
          
          for (int[] row : s.getSchedule()) {
            st.append(Arrays.toString(row))
              .append(SEPARATOR);
          }
          
          alw.add(st.toString());
			}
      try {
        write(filename,alw);
      } catch (Exception e) {
        System.out.println("save schedule write " + e);
      }
			
  }

}
