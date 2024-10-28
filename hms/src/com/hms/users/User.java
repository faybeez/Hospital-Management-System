package com.hms.users;
import java.io.Serializable;

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

    private String name;
    private String dateOfBirth;
    private Gender gender;
    private BloodType bloodType;
    private String userName;
    private String password;

    //constructor
    public User(String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.userName = userName;
        this.password = password;
        userCount++;
    }

    //get methods
    public String getName() {
        return this.name;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Gender getGender() {
        return this.gender;
    }

    public BloodType getBloodType() {
        return this.bloodType;
    }

    //set methods

    //print details method
    public void printUserDetails() {
        System.out.println(name + dateOfBirth + gender.toString() + bloodType.toString() + userName + password);
    }

    // consider implementing in an interface instead?
    public void viewAppointmentRecords(int id) {

    }
}
