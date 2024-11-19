package com.hms.users;
import java.time.LocalDate;

import com.hms.enums.*;

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
        System.out.println("User count: " + userCount);
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
    //get methods

    public int getID() {
        return id;
    }

    public String getName() {
        return this.name;
    }
    
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Gender getGender() {
        return this.gender;
    }

    public BloodType getBloodType() {
        return this.bloodType;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return (LocalDate.now().getYear() - dateOfBirth.getYear());
    }

    //set methods

    public void setID(int i) {
        this.id = i;
    }
    
    public void setName(String newName) {
        name = newName;
	}


    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password2) {
		password = password2;
		
	}

    public void setDateOfBirth(LocalDate dOB) {
		dateOfBirth = dOB;		
	}


    public void printUserDetails() {
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth.toString());
        System.out.println("Gender: " + gender.toString());
        System.out.println("Blood Type: " + bloodType.toString());
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
    }

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

	public void setBloodType(BloodType b) {
		bloodType = b;
		
	}
	public void setGender(Gender g) {
        gender = g;
		
	}

    @Override
    public int compareTo(User u) {
        return id - u.getID();
    }
}
