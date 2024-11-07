package com.hms.users;
import java.io.Serializable;
import java.time.LocalDate;

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
    };

    //userCount
    public int userCount = 0;
    private static final int stripPrefix = 10000000;

    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private BloodType bloodType;
    private String userName;
    private String password;

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

    //set methods

    //print details method NOT DONE -- RECHANGE
    public void printUserDetails() {
        System.out.println(name + dateOfBirth + gender.toString() + bloodType.toString() + userName + password);
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
}
