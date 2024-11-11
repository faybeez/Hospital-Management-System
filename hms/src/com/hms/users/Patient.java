package com.hms.users;

import com.hms.items.Appointment;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;


public class Patient extends User {

    public Patient(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }
    
    public void viewAppointments() {
        
    }

    public Appointment makeAppointmentRequest(int doctorID){
        String TOS;
        LocalDate date = LocalDate.of(1990,1,1);
        LocalTime time = LocalTime.of(1,1);
        int choice;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Type of services:");
        System.out.println("1. Consultation");
        System.out.println("2. Follow-up");
        System.out.println("3. X-ray");

        choice = sc.nextInt();

        switch (choice) {
            case 1:
                TOS = "Consultation";
                break;
            case 2:
                TOS = "Follow-up";
                break;
            case 3:
                TOS = "X-ray";
                break;
            default:
                TOS = "";
                break;
        }

        System.out.print("Date of Appointment (maximum 6 days from current date in YYYY-MM-DD):");
        
        while(true) {
            try {
                date = LocalDate.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid text! Try again.");
            }
            if(date.compareTo(LocalDate.now()) > 6 || date.compareTo(LocalDate.now()) < 0) {
                System.out.println("Invalid date! Try again.");
            }
            break;
        }
        
        System.out.print("Time of Appointment (8:00 - 18:00 in HH:MM - 30 minute intervals): ");
        while(true) {
            try {
                time = LocalTime.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid text! Try again.");
            }
            if(time.compareTo(LocalTime.of(8,0)) < 0 || time.compareTo(LocalTime.of(18,0)) > 0 || time.getMinute() / 30 != 0) {
                System.out.println("Invalid time! Try again.");
            }
            break;
        }

        Appointment a = new Appointment(this.id, doctorID, TOS, date, time);
        sc.close();
        return a;
    }
}
