package com.hms.users;

import com.hms.items.Appointment;
import com.hms.items.AppointmentManager;
import com.hms.items.MedicalRecord;
import com.hms.items.Appointment.Status;

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

    public Appointment makeAppointmentRequest(Doctor doctor, Scanner sc){
        LocalDate date = LocalDate.of(1990,1,1); //dummy value
        LocalTime time = LocalTime.of(1,1); //dummy value

        System.out.println("Printing Doctor's Schedule...");

        doctor.getSchedule().printSchedule();

        
        
        while(true) {
            System.out.print("Date of Appointment (maximum 6 days from current date in YYYY-MM-DD):");
            try {
                date = LocalDate.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid text! Try again.");
                continue;
            }
            if(date.compareTo(LocalDate.now()) > 6 || date.compareTo(LocalDate.now()) < 0) {
                System.out.println("Invalid date! Try again.");
                continue;
            }
            break;
        }
        
        
        while(true) {
            System.out.print("Time of Appointment (8:00 - 18:00 in HH:MM - 30 minute intervals): ");
            try {
                time = LocalTime.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid text! Try again.");
                continue;
            }
            if(time.compareTo(LocalTime.of(8,0)) < 0 || time.compareTo(LocalTime.of(18,0)) > 0 || time.getMinute() / 30 != 0) {
                System.out.println("Invalid time! Try again.");
                continue;
            }
            break;
        }

        Appointment a = new Appointment(this.id, doctor.getID(), date, time);

        if(!doctor.getSchedule().checkIfFree(a)) {
            System.out.println("You selected a time that is invalid. Please try again.");
            return null;
        }
        doctor.getSchedule().addAppointmentToSchedule(a);
        System.out.println("Appointment Created!");
        return a;
    }

    public int rescheduleAppointment(Appointment a, Doctor doctor, Scanner sc) {
        LocalDate date = LocalDate.of(1990,1,1); //dummy value
        LocalTime time = LocalTime.of(1,1); //dummy value
        String c;

        System.out.println("Printing Doctor's Schedule...");

        doctor.getSchedule().printSchedule();

        
        
        while(true) {
            System.out.print("Date of Appointment (maximum 6 days from current date in YYYY-MM-DD):");
            try {
                date = LocalDate.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid text! Try again.");
                continue;
            }
            if(date.compareTo(LocalDate.now()) > 6 || date.compareTo(LocalDate.now()) < 0) {
                System.out.println("Invalid date! Try again.");
                continue;
            }
            break;
        }
        
        
        while(true) {
            System.out.print("Time of Appointment (08:00 - 18:00 in HH:MM - 30 minute intervals): ");
            try {
                time = LocalTime.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid text! Try again.");
                continue;
            }
            if(time.compareTo(LocalTime.of(8,0)) < 0 || time.compareTo(LocalTime.of(18,0)) > 0 || time.getMinute() / 30 != 0) {
                System.out.println("Invalid time! Try again.");
                continue;
            }
            break;
        }

        System.out.println("Old date and time: " + a.getDate().toString() + " - " + a.getTime().toString());
        System.out.println("New date and time " + date.toString() + " - " + time.toString());
        System.out.println("Reschedule appointment? (Y/N)");
        c = sc.nextLine().toUpperCase();

        switch (c) {
            case "Y":
                a.setDate(date);
                a.setTime(time);
                doctor.changeScheduleSlot(date, time, -1, null);
                doctor.changeScheduleSlot(date, time, -2, a);
                a.setStatus(Status.Pending);
                break;
            case "N":
                System.out.println("Appointment date and time unchanged.");
                break;
            default:
                System.out.println("Choice invalid.");
                return -2;
        }

        return 1;
    }

}
