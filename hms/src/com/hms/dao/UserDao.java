package com.hms.dao;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.hms.users.Administrator;
import com.hms.users.Doctor;
import com.hms.users.Patient;
import com.hms.users.Pharmacist;
import com.hms.users.User;
import com.hms.enums.*;

public class UserDao extends ItemDao implements Dao<User>{

    
    /** 
     * @param filename file to read from
     * @return Map<Integer, User> read data
     * @throws IOException file not found
     */
    @Override
    public Map<Integer, User> read(String filename) throws IOException {

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
                user = new Administrator(id, name, dateOfBirth, Gender.getByValue(gender), BloodType.getByValue(bloodType), username,password);
                break;
            case 101:
                user = new Doctor(id, name, dateOfBirth, Gender.getByValue(gender), BloodType.getByValue(bloodType), username,password);
                break;
            case 102:
                user = new Patient(id, name, dateOfBirth, Gender.getByValue(gender), BloodType.getByValue(bloodType), username,password);
                break;
            case 103:
                user = new Pharmacist(id, name, dateOfBirth, Gender.getByValue(gender), BloodType.getByValue(bloodType), username,password);
                break;
        }

        // add to user list
        userMap.put(id, user);
            }
            sc.close();
        return userMap;
	}

    
    /** 
     * @param filename file to save to
     * @param all data
     * @throws IOException
     */
    @Override
    public void save(String filename, Collection<User> all) throws IOException {
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
          st.append(user.getGender()); //gender
          st.append(SEPARATOR);
          st.append(user.getBloodType()); //blood type
          st.append(SEPARATOR);
          st.append(user.getUsername().toString().trim()); //username
          st.append(SEPARATOR);
          st.append(user.getPassword().toString().trim()); //password
          alw.add(st.toString());
			}
			write(filename,alw);
	}

}
