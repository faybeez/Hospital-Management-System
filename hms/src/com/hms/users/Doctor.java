package com.hms.users;

import com.hms.items.Scheduler;
import com.hms.items.Appointment;
import com.hms.enums.*;
import com.hms.App;

import java.time.LocalDate;
import java.time.LocalTime;

/** 
 * Doctor.java
 * 
 * User subclass dedicated to the role doctor
 */
public class Doctor extends User{
    
    private Scheduler schedule;
    private static final int idPrefix = 1010000000;

    public Doctor(String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(name, dateOfBirth, gender, bloodType, userName, password);
        int i = idPrefix + userCount;
        super.setID(i);
        schedule = new Scheduler();
    }

    public Doctor(int id, String name, String dateOfBirth, Gender gender, BloodType bloodType, String userName, String password) {
        super(id, name, dateOfBirth, gender, bloodType, userName, password);
    }
    
    /** 
     * gets doctor's schedule
     * @return Scheduler
     */
    public Scheduler getSchedule() {
        return schedule;
    }
    
    
    /** 
     * sets doctor's schedule
     * @param s
     */
    public void setSchedule(Scheduler s) {
        s.updateLastSaved();
        schedule = s;
    }

    
    /** 
     * changes a schedule's slot status
     * @param date
     * @param time
     * @param status (0 - set free, -1 - set unavailable, other - set appointment)
     * @param a
     * @return int
     */
    public int changeScheduleSlot(LocalDate date, LocalTime time, int status, Appointment a) {

        int r = date.compareTo(LocalDate.now());
        int c = time.compareTo(LocalTime.of(8,0));
        int temp;

        if(r > 6 || c > 19 || r < 0 || c < 0) {
            System.out.println("Invalid date or time!");
            return -2;
        }

        switch(status) {
            case 0: //set free
                temp = schedule.setFree(date, time);
                break;
            case -1: //set unavailable
                temp = schedule.setUnavailable(date, time);
                break;
            default: //set appointment
                if(schedule.checkIfFree(a)) {
                    System.out.println("Date chosen is not free!");
                    return -2;
                }
                schedule.addAppointmentToSchedule(a);
                temp = -1;
                break;
        }
        return temp;

    }

    /** 
     * prints doctor's schedule
     */
    public void printSchedule() {
        if (schedule == null) {
            throw new NullPointerException("Doctor's schedule not found");
        }
        schedule.printSchedule();
    }
    /** 
     * updates doctor's schedule
     */
    public void updateSchedule() {
        schedule.updateLastSaved();
    }

    /** 
     * updates the unavailable times of the doctor
     */
    public void UpdateUnavailable() {

        LocalDate dStart, dEnd;
        LocalTime tStart, tEnd;

        System.out.print("Start Date (YYYY-MM-DD): ");

        try {
            dStart = LocalDate.parse(App.sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.print("Start Time (HH:MM in intervals of 30 minutes): ");

        try {
            tStart = LocalTime.parse(App.sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.print("End Date (YYYY-MM-DD): ");

        try {
            dEnd = LocalDate.parse(App.sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.print("End Time (HH:MM in intervals of 30 minutes): ");

        try {
            tEnd = LocalTime.parse(App.sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
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

    }

}
