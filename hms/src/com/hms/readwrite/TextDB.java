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
import java.util.Collection;
import java.util.StringTokenizer;

import javax.security.auth.login.AppConfigurationEntry;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import com.hms.users.User.Gender;
import com.hms.users.User.BloodType;
import com.hms.items.Appointment.Status;
import com.hms.items.Prescription.PrescriptionStatus;
import com.hms.items.MedicalRecord;
import com.hms.users.Administrator;
import com.hms.users.Pharmacist;
import com.hms.users.Doctor;
import com.hms.users.Patient;
import com.hms.users.User;
import com.hms.items.Appointment;
import com.hms.items.Prescription;


public class TextDB {
    // id = 1010000001 (10 numbers, first 3 numbers are identifiers)
    public static final int IDPARSE = 10000000;
    public static final String SEPARATOR = "|";

    // an example of reading
	public Map<Integer, User> readUsers(String filename) throws IOException {
		// read String from text file
		File myFile = new File(filename);
    Scanner sc = new Scanner(myFile);

		Map<Integer, User> userMap = new HashMap<>();// to store user data
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
              user = new Doctor(id, name, dateOfBirth, Gender.valueOf(gender), BloodType.valueOf(bloodType), username,password);
              break;
          case 102:
              user = new Patient(id, name, dateOfBirth, Gender.valueOf(gender), BloodType.valueOf(bloodType), username,password);
              break;
          case 103:
              user = new Pharmacist(id, name, dateOfBirth, Gender.valueOf(gender), BloodType.valueOf(bloodType), username,password);
              break;
      }

      // add to user list
      userMap.put(id, user);
		}
		sc.close();
    return userMap;
	}

  // an example of saving
public void saveUsers(String filename, Collection<User> all) throws IOException {
		Iterator<User> i = all.iterator();//iterator
    List<String> alw = new ArrayList<>();
        while(i.hasNext()) {
          User user = i.next();
          StringBuilder st = new StringBuilder() ;
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
          alw.add(st.toString());
			}
			write(filename,alw);
	}

public Map<Integer, MedicalRecord> readMedicalRecord(String filename) throws IOException {
  // read String from text file
  File myFile = new File(filename);
  Scanner sc = new Scanner(myFile);
  Map<Integer, MedicalRecord> mdMap = new HashMap<>();// to store user data

  while (sc.hasNextLine()){
    MedicalRecord md;
    int i;
    ArrayList<Integer> appt = new ArrayList<Integer>();
    String st = sc.nextLine();
    // get individual 'fields' of the string separated by SEPARATOR
    StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

    int id = Integer.parseInt(star.nextToken().trim());	// getID
    String name = star.nextToken().trim();
    String dateOfBirth = star.nextToken().trim();
    String gender = star.nextToken().trim();
    String bloodType = star.nextToken().trim();
    String email = star.nextToken().trim();
    String phoneNum = star.nextToken().trim();
    i = Integer.parseInt(star.nextToken().trim());

    for(; i > 0; i--) {
      appt.add(Integer.parseInt(star.nextToken().trim()));
    }

    //create new user --> depending on first 3 numbers of id
    //depends on id config
    md = new MedicalRecord(id, name, dateOfBirth, gender, bloodType, email, phoneNum, appt);

    // add to medical array map
    mdMap.put(id, md);
  }

  sc.close();
  return mdMap;
}

public Map<Integer, Appointment> readAppointments(String filename) throws IOException {
    // read String from text file
    File myFile = new File(filename);
    Scanner sc = new Scanner(myFile);
    Map<Integer, Appointment> apptMap = new HashMap<>();// to store appt data
    Prescription p = new Prescription();
    Appointment a;
    int i;
    String temp;

    while (sc.hasNextLine()){
      p = new Prescription();
      String st = sc.nextLine();
      // get individual 'fields' of the string separated by SEPARATOR
      StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
      int id = Integer.parseInt(star.nextToken().trim());	// getID
      Status status = Status.valueOf(star.nextToken().trim());
      LocalDate date = LocalDate.parse(star.nextToken().trim());
      LocalTime time = LocalTime.parse(star.nextToken().trim());
      int pID = Integer.parseInt(star.nextToken().trim());
      int dID = Integer.parseInt(star.nextToken().trim());
      
      temp = star.nextToken().trim();
      String typeOfService = (temp.compareTo("`") == 0) ? null : temp;
      
      temp = star.nextToken().trim();
      String diagnosis = (temp.compareTo("`") == 0) ? null : temp;
      temp = star.nextToken().trim();
      String treatment = (temp.compareTo("`") == 0) ? null : temp;
      temp = star.nextToken().trim();
      String consultNotes = (temp.compareTo("`") == 0) ? null : temp;
      i = Integer.parseInt(star.nextToken().trim());
      String medName, notes;
      int medAmt;
      
      
      for(; i > 0; i--) {
        medName = star.nextToken().trim();
        medAmt = Integer.parseInt(star.nextToken().trim());
        notes = star.nextToken().trim();
        p.addMedicine(medName, medAmt, notes);
      }
      
      p.setPrescriptionStatus(PrescriptionStatus.valueOf(star.nextToken().trim()));

      a = new Appointment();
      a.setID(id);
      a.setConsultNotes(consultNotes);
      a.setDoctorID(dID);
      a.setPatientID(pID);
      a.setDate(date);
      a.setTime(time);
      a.setPrescription(p);
      a.setDiagnosis(diagnosis);
      a.setTreatment(treatment);
      a.setStatus(status);
      a.setTypeOfService(typeOfService);
      apptMap.put(id, a);
    }
    
    sc.close();
    return apptMap;
}
  /** Write fixed content to the given file. **/
  public void saveAppointments(String filename, Collection<Appointment> all) throws IOException {
		Iterator<Appointment> i = all.iterator();//iterator
    String temp;
    List<String> alw = new ArrayList<>();
        while(i.hasNext()) {
          Appointment a = i.next();
          StringBuilder st = new StringBuilder() ;
          st.append(String.valueOf(a.getAppointmentID()).trim()); //id
          st.append(SEPARATOR);
          st.append(a.getStatus().toString().trim()); //name
          st.append(SEPARATOR);
          st.append(a.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim()); //date of birth
          st.append(SEPARATOR);
          st.append(a.getTime().format(DateTimeFormatter.ofPattern("HH:MM")).trim()); //date of birth
          st.append(SEPARATOR);
          st.append(Integer.toString(a.getPatientID()).trim()); //patient ID
          st.append(SEPARATOR);
          st.append(Integer.toString(a.getDoctorID()).trim());
          st.append(SEPARATOR);
          temp = (a.getTypeofService() == null) ? "`" : a.getTypeofService();
          st.append(temp.trim());
          st.append(SEPARATOR);
          temp = (a.getDiagnosis() == null) ? "`" : a.getDiagnosis();
          st.append(temp.trim());
          st.append(SEPARATOR);
          temp = (a.getTreatment() == null) ? "`" : a.getTreatment();
          st.append(temp.trim());
          st.append(SEPARATOR);
          temp = (a.getConsultNotes() == null) ? "`" : a.getConsultNotes();
          st.append(temp.trim());
          int j = a.getPrescription().getMedicineList().size();
          st.append(Integer.toString(j).trim());
          
          for(; j > 0; j--) {
            st.append(SEPARATOR);
            
          }
          

          alw.add(st.toString());
			}
			write(filename,alw);
	}


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


/**
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
  **/

}

