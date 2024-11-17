package com.hms.users;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.EnumSet;

public class User implements Serializable {
    // enums

    public enum BloodType {
        OPLUS {
            @Override
            public String toString() {
                return "O+";
            }
        },
        OMINUS {
            @Override
            public String toString() {
                return "O-";
            }
        },
        APLUS {
            @Override
            public String toString() {
                return "A+";
            }
        },
        AMINUS {
            @Override
            public String toString() {
                return "A-";
            }
        },
        BPLUS {
            @Override
            public String toString() {
                return "B+";
            }
        },
        BMINUS {
            @Override
            public String toString() {
                return "B-";
            }
        },
        ABPLUS {
            @Override
            public String toString() {
                return "AB+";
            }
        },
        ABMINUS {
            @Override
            public String toString() {
                return "AB-";
            }
        };

        public static BloodType getByValue(String value) {
            for(final BloodType element : EnumSet.allOf(BloodType.class)) {
                if(element.toString().equals(value)) {
                    return element;
                }
            }
            return null;
        }
    };
    
    //missing javadoc
    public enum Gender {
        FEMALE {
            @Override
            public String toString() {
                return "Female";
            }
        },
        MALE {
            @Override
            public String toString() {
                return "Male";
            }
        };

        public static Gender getByValue(String value) {
            for(final Gender element : EnumSet.allOf(Gender.class)) {
                if(element.toString().equals(value)) {
                    return element;
                }
            }
            return null;
        }
    };

    //userCount
    public int userCount = 0;
    private static final int stripPrefix = 10000000;

    protected int id;
    protected String name;
    protected LocalDate dateOfBirth;
    protected Gender gender;
    protected BloodType bloodType;
    protected String userName;
    protected String password;

    //constructor
    public User(){
        
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
    
    public void setName(String newName) {
		// TODO Auto-generated method stub
		
	}

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(String newDob) {
		// TODO Auto-generated method stub
		
	}
    }
    public void setDateOfBirth(String newDob) {
		// TODO Auto-generated method stub
		
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
    public void setUsername(String username) {
  		// TODO Auto-generated method stub
  		
  	}
   
    }
    public void setUsername(String username) {
  		// TODO Auto-generated method stub
  		
  	}
   

    public String getPassword() {
        return password;
    }
    public void setPassword(String password2) {
		// TODO Auto-generated method stub
		
	}
    }
    public void setPassword(String password2) {
		// TODO Auto-generated method stub
		
	}

    //set methods

    //print details method NOT DONE -- RECHANGE
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

    // consider implementing in an interface instead?
    public void viewAppointmentRecords(int id) {

    }
	public void setBloodType(String bloodType2) {
		// TODO Auto-generated method stub
		
	}
	public void setGender(String gender1) {
		// TODO Auto-generated method stub
		
	}
	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
