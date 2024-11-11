package com.hms.users;

import com.hms.users.User.BloodType;
import com.hms.users.User.Gender;
import com.hms.items.Scheduler;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Doctor extends User{
    
    private Scheduler schedule = new Scheduler();

    public Doctor(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }

    public Scheduler getSchedule() {
        return schedule;
    }

    public void UpdateUnavailable() {

        Scanner sc = new Scanner(System.in);
        LocalDate dStart, dEnd;
        LocalTime tStart, tEnd;

        System.out.print("Start Date (YYYY-MM-DD): ");

        try {
            dStart = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            sc.close();
            return;
        }

        System.out.print("Start Time (HH:MM in intervals of 30 minutes): ");

        try {
            tStart = LocalTime.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            sc.close();
            return;
        }

        System.out.print("End Date (YYYY-MM-DD): ");
        try {
            dEnd = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            sc.close();
            return;
        }

        System.out.print("End Time (HH:MM in intervals of 30 minutes): ");


        try {
            tEnd = LocalTime.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            sc.close();
            return;
        }

        while(dStart.compareTo(dEnd) != 0) {
            while(tStart.compareTo(LocalTime.of(18, 0, 0)) != 0) {
                schedule.setUnavailable(dStart, tStart);
                tStart = tStart.plusMinutes(30);
            }
            tStart = LocalTime.of(8,0);
            dStart = dStart.plusDays(1);
        }

        while(tStart.compareTo(tEnd) != 0) {
            schedule.setUnavailable(dStart, tStart);
            tStart = tStart.plusMinutes(30);
        }

        sc.close();
    }
    
    public void PrintAppointmentRequests() {
        
    }

}
