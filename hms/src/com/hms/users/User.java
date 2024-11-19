package com.hms.users;
import java.time.LocalDate;

import com.hms.enums.*;

/** 
 * User.java
 * 
 * User parent class, stores information of all users
 */
public class User implements Comparable<User>  {
    //userCount
    protected static int userCount = 0;
    private static final int stripPrefix = 10000000;

    protected int id;
    protected String name;
    protected LocalDate dateOfBirth;
    protected Gender gender;
    protected BloodType bloodType;
    protected String userName;
    protected String password;

    public User() {}

    public User(String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.bloodType = bloodType;
        this.userName = userName;
        this.password = password;
        userCount++;
    }

    //for LocalDate, string in format of YEAR-MONTH-DAY eg "2024-01-12";
    public User(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.gender = gender;
        this.bloodType = bloodType;
        this.userName = userName;
        this.password = password;
        userCount++;
    }
    
    /** 
     * gets user's ID
     * @return int
     */

    public int getID() {
        return id;
    }

    
    /** 
     * gets user's name
     * @return String
     */
    public String getName() {
        return this.name;
    }
    
    
    /** 
     * gets user's date of birth
     * @return LocalDate
     */
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    
    /** 
     * gets user's gender
     * @return Gender
     */
    public Gender getGender() {
        return this.gender;
    }

    
    /** 
     * gets user's bloodtype
     * @return BloodType
     */
    public BloodType getBloodType() {
        return this.bloodType;
    }

    
    /** 
     * gets user's username
     * @return String
     */
    public String getUsername() {
        return userName;
    }

    
    /** 
     * gets user's password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    
    /** 
     * gets user's age
     * @return int
     */
    public int getAge() {
        return (LocalDate.now().getYear() - dateOfBirth.getYear());
    }

    
    /** 
     * sets user's ID
     * @param i
     */
    public void setID(int i) {
        this.id = i;
    }
    
    
    /** 
     * sets user's name
     * @param newName
     */
    public void setName(String newName) {
        name = newName;
	}


    
    /** 
     * sets user's username
     * @param username
     */
    public void setUsername(String username) {
        this.userName = username;
    }

    
    /** 
     * sets User's password
     * @param password2
     */
    public void setPassword(String password2) {
		password = password2;
		
	}

    
    /** 
     * sets User date of birth
     * @param dOB
     */
    public void setDateOfBirth(LocalDate dOB) {
		dateOfBirth = dOB;		
	}

    /** 
     * prints the user's details
     */
    public void printUserDetails() {
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth.toString());
        System.out.println("Gender: " + gender.toString());
        System.out.println("Blood Type: " + bloodType.toString());
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
    }

    
    /** 
     * gets the role of the user (Admin, Doctor, Patient, Pharm)
     * @return String
     */
    public String getDesignation() {
        int i = id / stripPrefix;

        switch(i) {
            case 100:
                return "Administrator";
            case 101:
                return "Doctor";
            case 102:
                return "Patient";
            case 103:
                return "Pharmacist";
            default:
                return "Error";
        }

    }

	
    /** 
     * sets User bloodtype
     * @param b
     */
    public void setBloodType(BloodType b) {
		bloodType = b;
		
	}
    
	
    /** 
     * sets User gender
     * @param g
     */
    public void setGender(Gender g) {
        gender = g;
		
	}

    
    /** 
     * Compares one user to another using IDs
     * @param u
     * @return int
     */
    @Override
    public int compareTo(User u) {
        return id - u.getID();
    }
}
