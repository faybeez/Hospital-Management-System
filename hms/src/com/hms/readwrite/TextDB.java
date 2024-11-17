package com.hms.readwrite;

import java.io.IOException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.time.format.*;

import com.hms.users.User.Gender;
import com.hms.users.User.BloodType;

import com.hms.items.Appointment.Status;
import com.hms.users.Administrator;
import com.hms.users.User;
import com.hms.Medicine;
import com.hms.Replenishment;
import com.hms.items.Appointment;
import com.hms.items.Prescription;



public class TextDB {
    // id = 1010000001 (10 numbers, first 3 numbers are identifiers)
    public static final int IDPARSE = 10000000;
    public static final String SEPARATOR = "|";

    // an example of reading
	public ArrayList<User> readUsers(String filename) throws IOException {
		// read String from text file
		File myFile = new File(filename);
    Scanner sc = new Scanner(myFile);
		ArrayList<User> userArray = new ArrayList<User>();// to store user data
    while (sc.hasNextLine()){
      User user = new User();
      String st = sc.nextLine();
      // get individual 'fields' of the string separated by SEPARATOR
      StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","


      int id = Integer.parseInt(star.nextToken().trim());	// getID
      String name = star.nextToken().trim();
      String dateOfBirth = star.nextToken().trim();
      String gender = star.nextToken().trim();
      String bloodType = star.nextToken().trim();
      String username = star.nextToken().trim();
      String password = star.nextToken().trim();

      //create new user --> depending on first 3 numbers of id
      //depends on id config
      switch(id / IDPARSE) {
          case 100:
              user = new Administrator(id, name, dateOfBirth, Gender.valueOf(gender), BloodType.valueOf(bloodType), username,password);
              break;
          case 101:
              user = new Administrator(id, name, dateOfBirth, Gender.valueOf(gender), BloodType.valueOf(bloodType), username,password);
              break;
      }

      // add to Professors list
      userArray.add(user);
		}
		sc.close();
    return userArray;
	}

  // an example of saving
public void saveUsers(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				User user = (User)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(String.valueOf(user.getID()).trim()); //id
				st.append(SEPARATOR);
				st.append(user.getName().trim()); //name
				st.append(SEPARATOR);
				st.append(user.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim()); //date of birth
                st.append(SEPARATOR);
				st.append(user.getGender().toString().trim()); //gender
				st.append(SEPARATOR);
                st.append(user.getBloodType().toString().trim()); //blood type
                st.append(SEPARATOR);
                st.append(user.getUsername().toString().trim()); //username
                st.append(SEPARATOR);
                st.append(user.getPassword().toString().trim()); //password
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

  /** Write fixed content to the given file.
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

     Read the contents of the given file.
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }
<<<<<<< Updated upstream
    */

}

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
	         double price = Double.parseDouble(star.nextToken().trim());
	         
	         Medicine medicine=new Medicine(medname,med_id,stock,lowstock,price);
	         medList.add(medicine);
	       
	   
	      }

	      sc.close();
	      return medList;
	   }
	   


public void saveMedicine(String filename, ArrayList<Medicine> medicineList) {
	
	List<String> alw = new ArrayList<>();

    for (int i = 0; i < medicineList.size(); i++) {
        Medicine medicine = medicineList.get(i);
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
public void readReplenishments(String filename) throws IOException {
    File myFile = new File(filename);
    Scanner sc = new Scanner(myFile);
    ArrayList<Replenishment> replenishmentList = new ArrayList<>();
    
    while (sc.hasNextLine()) {
        String line = sc.nextLine();
        StringTokenizer star = new StringTokenizer(line, SEPARATOR);

        int medID = Integer.parseInt(star.nextToken().trim());
        int replenishQuantity = Integer.parseInt(star.nextToken().trim());
        String status = star.nextToken().trim();  

        Replenishment replenishment = new Replenishment(medID, replenishQuantity, status);
        replenishmentList.add(replenishment);
    }

    
}


public void saveReplenishments(String filename, ArrayList<com.hms.Replenishment> replenishment) throws IOException {
    List<String> alw = new ArrayList<>();

    for (int i = 0; i < replenishment.size(); i++) {
        com.hms.Replenishment replenishment1 = replenishment.get(i);
        StringBuilder st = new StringBuilder();
        
        st.append(String.valueOf(replenishment1.getMedID()).trim());
        st.append(SEPARATOR);
        st.append(String.valueOf(replenishment1.getQuantity()).trim());
        st.append(SEPARATOR);
        st.append(replenishment1.getStatus().trim());
        
        alw.add(st.toString());
    }

    write(filename, alw);  
}


}








