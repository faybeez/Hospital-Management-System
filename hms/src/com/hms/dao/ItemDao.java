package com.hms.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemDao {
  
  public static final int IDPARSE = 10000000;
  public static final String SEPARATOR = "|";

    
    /** 
     * handles the writing to file process
     * @param fileName file to write to
     * @param data data to write
     * @throws IOException
     */
    public static void write(String fileName, List data) throws IOException  {
      PrintWriter out = new PrintWriter(new FileWriter(fileName));

      try {
      for (int i =0; i < data.size() ; i++) {
            out.println((String)data.get(i));
      }
      }
      finally {
        out.close();
      }
  }
}
