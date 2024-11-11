package com.hms.users;

import com.hms.items.Appointment;
import com.hms.items.AppointmentManager;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;


public class Patient extends User {

    public Patient(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }
    
    public void viewAppointments(AppointmentManager m) {
        m.PrintAppointmentsFromPatientID(id);
    }

    public Appointment makeAppointmentRequest(int doctorID){
        LocalDate date = LocalDate.of(1990,1,1);
        LocalTime time = LocalTime.of(1,1);
        Scanner sc = new Scanner(System.in);

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

        Appointment a = new Appointment(this.id, doctorID, date, time);
        sc.close();
        return a;
    }
}
